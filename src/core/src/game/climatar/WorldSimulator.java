package game.climatar;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import game.climatar.GameState.WorldProperty;
import game.climatar.architecture.Controller;
import game.climatar.architecture.SetModel;
import game.climatar.map.MapView;
import game.climatar.map.Nation;
import game.climatar.news.ConseqType;
import game.climatar.news.ConseqType.Consequence;
import game.climatar.news.NewsEventControl;
import game.climatar.systems.ghg.GHGController;
import game.climatar.systems.political.PoliticalController;
import game.climatar.systems.weather.WeatherController;

@SetModel(GameState.class)
public class WorldSimulator extends Controller {

	// sick tunes
	Music music;

    // Actively logged SubSystems
    private boolean ghgIsActive, weatherIsActive, politicalIsActive, isPaused = false, eventGen = false;
    private List<ConseqType> currentAE;
    private List<ConseqType> currentPE;


    // Sub-system State Controllers
    private GHGController ghgSystems;
    private WeatherController weatherSystems;
    private PoliticalController politicalSystems;

    // News Events
    private NewsEventControl newsController;

    private MapView mapView;
    private GreyView greyView;
    private UIView uiView;
    private PauseView pauseView;
    private OverlayMenuView overlayMenuView;

    /**
     * Start a new game, Controlling all aspects of the world, Call Simulate
     * after Creation!
     */
    public void newGame(Nation player) {
		// get those positive vibes going
		music = Gdx.audio.newMusic(Gdx.files.internal("love.mp3"));
		music.setVolume(1.0f);
		music.setLooping(true);
		music.play();
		
        // Set up what systems are being used
		setGhgIsActive(true);
		setPoliticalIsActive(true);
		setWeatherIsActive(true);

        // Set up the Game State
        ((GameState) getModel()).init(player);
        newsController.startGeneration();
        overlayMenuView.hide(false);
    }


    /**
     * Update Function to handle all requests to step the state of the world
     */
    public void Simulate() {
        // GENERATE NEWS
        // REACT TO NEWS
        if(!newsController.getNewsEvent())
            return;
        pauseGame();
        eventGen = true;


        // UPDATE Sub Systems
        // if(ghgIsActive)
        // gameState.updateWorldGHG(ghgSystems.getEmissionsPerUpdate());
        // TODO if(weatherIsActive)
        // TODO weatherSystems.Update();
        // if(politicalIsActive)
        // gameState.updateWorldPlayerPolitics(politicalSystems.getTotalRelations());


    }

    boolean overlayMenuIsShowing = false;

    public void toggleOverlayMenu() {
        if (!overlayMenuIsShowing) {
            overlayMenuView.show();
        } else {
            overlayMenuView.hide();
        }

        overlayMenuIsShowing = !overlayMenuIsShowing;
    }


    private void pauseGame() {
        isPaused = true;
		music.pause();
    }

    private void resumeGame() {
        isPaused = false;
		music.play();
    }

    // ========================================================
    // =============== Set Activated Systems ==================

    /**
     * Enable/Disable GHG Sub System
     *
     * @param ghgIsActive Enable/Disable
     */
    public void setGhgIsActive(boolean active) {
        ghgSystems.setActive(active);
    }

    /**
     * Enable/Disable Weather Sub System
     *
     * @param active Enable/Disable
     */
    public void setWeatherIsActive(boolean active) {
        weatherSystems.setActive(active);;
    }

    /**
     * Enable/Disable Political Sub System
     *
     * @param active Enable/Disable
     */
    public void setPoliticalIsActive(boolean active) {
        politicalSystems.setActive(active);;
    }



    public boolean getPoliticalIsActive(){
        return politicalSystems.isActive();
    }

    public boolean getGHGIsActive(){
        return ghgSystems.isActive();
    }

    public boolean getWeatherIsActive(){
        return weatherSystems.isActive();
    }
    
    public void togglePoliticalSystem() {
		boolean active = getPoliticalIsActive();
		setPoliticalIsActive(!active);
	}
    
    public void toggleGHGSystem() {
		boolean active = getGHGIsActive();
		setGhgIsActive(!active);
	}

    public void toggleWeatherSystem() {
		boolean active = getWeatherIsActive();
		setWeatherIsActive(!active);
	}

    // ========================================================
    // =================---- Overrides ----====================
    @Override
    protected void layoutView() {
        float PAD = 0;
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        mapView.setFrame(PAD, PAD, width - PAD * 2, height - PAD * 2);
        uiView.setFrame(0, height - width, width / 4, width);
        pauseView.setFrame(width - width / 4 , height - width / 4, width / 4, width / 4);
        greyView.setFrame(0,0,width*3, height/3.25f);
        overlayMenuView.setFrame(0, 2*height/5, width, height/5);
        
        showView(mapView, pauseView, uiView);
    }

    @Override
    protected void tick() {
        //Simulate();

        // UPDATE WORLD STATE
        if (isPaused == false) {
            if (eventGen == false) {
                Simulate();
            } else {
                // TODO add conds for GS updates
                // getModel().set(WorldProperty.AVG_TEMP.id(), );
                getModel().set(WorldProperty.TOTAL_GHG.id(), ghgSystems.getEmissionsPerUpdate());
                getModel().set(WorldProperty.AVG_RELATIONS.id(), politicalSystems.getTotalRelations());
                setEvent();
            }
        } else {
            //DO NOTHING
        }

    }

    private void setEvent() {
        eventGen = false;

    }

    public boolean isPlaying() {
        return getModel().get(WorldProperty.PLAYING.id()) != null;
    }

    private Nation selectedNation = null;
    public void openNationView(Nation nation) {
    	for (Nation n : Nation.values()) {
            if (n != nation) {
                hideNationView(n);
            }
        }
        
    	if(nation == selectedNation) {
    		hideNationView(nation);
    		greyView.hide();
    		selectedNation = null;
    		return;
    	}
    	
    	selectedNation = nation;
    	
    	greyView.show();
        weatherSystems.getWeatherSystemController(nation).show();
        politicalSystems.getPoliticalSystemController(nation).show();
        ghgSystems.getGHGSystemController(nation).show();
        mapView.setVisibility(nation, true);
    }

    public void hideNationView(Nation nation) {
        weatherSystems.getWeatherSystemController(nation).hide();
        politicalSystems.getPoliticalSystemController(nation).hide();
        ghgSystems.getGHGSystemController(nation).hide();
        mapView.setVisibility(nation, false);
    }

    public void passConseq(List<ConseqType> yConseq,
						   Nation nation) {

		for (ConseqType c : yConseq) {

			double value = c.getValue();
			
			switch (c.getConsequence()) {
			case POLI: applyPolitical(nation, value);
			case WALLET: applyWallet(nation, value);
			case GHG: applyGHG(nation, value);
			case TEMP: applyTemperature(nation, value);
			case PERCIP: applyPrecipitation(nation, value);
			default: break;
			}
		}
		
        //Update
        resumeGame();
    }

	private void applyPolitical(Nation nation, double value) {
		switch (nation) {
		case FIRE:
			politicalSystems.getFireSubControl().setDeltaRelations(value);
			break;
		case WATER:
			politicalSystems.getWaterSubControl().setDeltaRelations(value);
			break;
		case EARTH:
			politicalSystems.getEarthSubControl().setDeltaRelations(value);
			break;
		case AIR:
			politicalSystems.getAirSubControl().setDeltaRelations(value);
			break;
		default:
			break;
		}
	}

	private void applyWallet(Nation nation, double value) {
		switch (nation) {
		case FIRE:
			politicalSystems.getFireSubControl().setDeltaWallet((int) value);
			break;
		case WATER:
			politicalSystems.getWaterSubControl().setDeltaWallet((int) value);
			break;
		case EARTH:
			politicalSystems.getEarthSubControl().setDeltaWallet((int) value);
			break;
		case AIR:
			politicalSystems.getAirSubControl().setDeltaWallet((int) value);
			break;
		default:
			break;
		}
	}

	private void applyGHG(Nation nation, double value) {
		switch (nation) {
		case FIRE:
			ghgSystems.setNationGHGDelta(nation, (int) value);
			break;
		case WATER:
			ghgSystems.setNationGHGDelta(nation, (int) value);
			break;
		case EARTH:
			ghgSystems.setNationGHGDelta(nation, (int) value);
			break;
		case AIR:
			ghgSystems.setNationGHGDelta(nation, (int) value);
			break;
		default:
			break;
		}
	}

	private void applyTemperature(Nation nation, double value) {
		switch (nation) {
		case FIRE:
			weatherSystems.getFireSubControl().setDeltaTemperature(value);
			break;
		case WATER:
			weatherSystems.getWaterSubControl().setDeltaTemperature(value);
			break;
		case EARTH:
			weatherSystems.getEarthSubControl().setDeltaTemperature(value);
			break;
		case AIR:
			weatherSystems.getAirSubControl().setDeltaTemperature(value);
			break;
		default:
			break;
		}
	}

	private void applyPrecipitation(Nation nation, double value) {
		switch (nation) {
		case FIRE:
			weatherSystems.getFireSubControl().setDeltaPrecipitation(value);
			break;
		case WATER:
			weatherSystems.getWaterSubControl().setDeltaPrecipitation(value);
			break;
		case EARTH:
			weatherSystems.getEarthSubControl().setDeltaPrecipitation(value);
			break;
		case AIR:
			weatherSystems.getAirSubControl().setDeltaPrecipitation(value);
			break;
		default:
			break;
		}
	}
}
