package game.climatar.news;

import game.climatar.GameState;

import game.climatar.architecture.Controller;
import game.climatar.architecture.ControllerManager;



public class NewsEventControl extends Controller{
    private NewsEventGenerator PressMill;
    private GameState gs;

    NewsView view;
    @Override
    protected void initialize() {
        gs = (GameState) getParentController().getModel();
        PressMill= new NewsEventGenerator(gs);
    }

    @Override
    protected void layoutView() {
        showView(view);
    }

    @Override
    protected void tick() {
        NewsEvent currentEvent=PressMill.triggerPlayerEvents();
        boolean yesEvent=view.getUserInput();
       if(yesEvent){
           //Pass consequences
       }else{
           //Pass rep
       }
       ControllerManager.delay(15, new Runnable(){public void run(){pauseTrigger();}});

    }
    public void pauseTrigger(){
        NewsEvent currentEvent= PressMill.triggerWorldEvents();
        if(currentEvent.getType()== NewsEvent.NewsType.PASSIVE){
            //Do Shit
        }else if(currentEvent.getType()== NewsEvent.NewsType.INTER){
            //Do some other shit
        }
    }


}
