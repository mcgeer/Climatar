package game.climatar.news;

import game.climatar.architecture.Controller;

import java.util.List;

import game.climatar.GameState;
import game.climatar.architecture.ControllerManager;
import game.climatar.map.Nation;

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
          
           
          
    
        }
        public void pauseTrigger(){
            NewsEvent currentEvent= PressMill.triggerWorldEvents();
            if(currentEvent.getType()== NewsEvent.NewsType.PASSIVE){
                //Do Shit
            }else if(currentEvent.getType()== NewsEvent.NewsType.INTER){
                //Do some other shit
            }
        }

		public List<ConseqType> getActiveEvent() {
			 NewsEvent currentEvent=PressMill.triggerPlayerEvents();
			return view.getUserInput(currentEvent);
			
			
		}
}
