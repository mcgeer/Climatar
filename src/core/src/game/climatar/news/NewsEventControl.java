package game.climatar.news;

import com.badlogic.gdx.Gdx;

import game.climatar.architecture.Controller;
import game.climatar.GameState;
import game.climatar.WorldSimulator;

public class NewsEventControl extends Controller {
    private NewsEventGenerator PressMill;
    private NewsEvent currentEvent;
    private GameState gs;
    private int turn;
    NewsView view;

    public WorldSimulator getWS(){
        return (WorldSimulator) getParentController();
    }

    @Override
    protected void initialize() {
        gs = (GameState) getParentController().getModel();
        PressMill = new NewsEventGenerator(gs);
        turn = 0;
    }

    @Override
    protected void layoutView() {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        view.setFrame(width/4 + 10, 7 * height / 16, 5 * width/8 , 4* height / 8);
        showView(view);
    }

    @Override
    protected void tick() {

    }

    public void getNewsEvent() {
        if (turn % 2 == 0) {
            currentEvent = PressMill.triggerPlayerEvents();
        } else {
            currentEvent = PressMill.triggerWorldEvents();
        }
        turn++;
        this.showView();
    }

    public void respondActiveYes() {
        getWS().passConseq(currentEvent.getYConseq());
        this.showView(null);
    }

    public void respondActiveNo() {
        getWS().passConseq(currentEvent.getNConseq());
        this.showView(null);
    }

}
