package game.climatar.news;

import game.climatar.architecture.Controller;
import game.climatar.GameState;
import game.climatar.architecture.ControllerManager;
import game.climatar.map.Nation;

public class NewsEventControl extends Controller{
    private NewsEventGenerator PressMill;
    private GameState gs;

    NewsView view;
    @Override
    protected void initialize() {
        
       super.initialize();
        PressMill= new NewsEventGenerator(gs);
    }

    @Override
    protected void layoutView() {
        showView(view);
    }

    @Override
    protected void tick() {
       // ControllerManager.delay();
    }
}
