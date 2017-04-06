package game.climatar.news;

import game.climatar.GameState;
import game.climatar.architecture.Controller;

public class NewsEventControl extends Controller{
    private NewsEventGenerator PressMill;
    private GameState gs;

    NewsView view;
    @Override
    protected void initialize() {
        
       super.initialize();
       
    }

    @Override
    protected void layoutView() {
        showView(view);
    }
    
    public void generateNews(){
    	 PressMill= new NewsEventGenerator(gs);
    }

    @Override
    protected void tick() {
       // ControllerManager.delay();
    }
}
