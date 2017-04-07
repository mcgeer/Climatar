package game.climatar.news;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import game.climatar.architecture.Controller;
import game.climatar.GameState;
import game.climatar.WorldSimulator;
import game.climatar.map.Nation;

public class NewsEventControl extends Controller {
    private static NewsEventGenerator PressMill;
    public NewsEvent currentEvent;
    private int turn;
    NewsView view;

    private boolean readyForAction = false;

    public WorldSimulator getWS(){
        return (WorldSimulator) getParentController();
    }

    @Override
    protected void initialize() {

        turn = 0;
    }
    public void startGeneration(){
        System.out.println("Init");
        WorldSimulator ws = (WorldSimulator) getParentController();
        GameState gs = (GameState) ws.getModel();
        Nation n = (Nation) gs.get(GameState.WorldProperty.NATION.id());

        PressMill = new NewsEventGenerator(n);

        readyForAction = true;
    }
    @Override
    protected void layoutView() {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        view.setFrame(width/4 + 10, 11 * height / 32, 5 * width/8 , 4* height / 8);
        showView(view);
    }

    @Override
    protected void tick() {

    }

    public boolean getNewsEvent() {
        if(!readyForAction) {
            return false;
        }
        System.out.println("HAS IT");
        if (false) {
            currentEvent = PressMill.triggerPlayerEvents();
            System.out.println(currentEvent == null);
        } else {
            currentEvent = PressMill.triggerWorldEvents();
            System.out.println(currentEvent == null);
        }
        turn++;
        this.showView(view);
        return true;
    }

    public void respondActiveYes() {
        getWS().passConseq(currentEvent.getYConseq());
        this.showView();
    }

    public void respondActiveNo() {
        getWS().passConseq(currentEvent.getNConseq());
        this.showView();
    }

}
