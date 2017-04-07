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
        
        overlayMenuView.hide();

        showView(mapView, greyView, pauseView, uiView, overlayMenuView);
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

    public void openNationView(Nation nation) {
        for (Nation n : Nation.values()) {
            if (n != nation) {
                hideNationView(n);
            }
        }
        uiView.setSelectedNation(nation);
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

    public void passConseq(List<ConseqType> yConseq) {
        //Update
        resumeGame();
    }
}
