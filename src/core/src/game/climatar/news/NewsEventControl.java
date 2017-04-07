package game.climatar.news;

import com.badlogic.gdx.Gdx;

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
        //PressMill = new NewsEventGenerator(gs);

    }

    @Override
    protected void layoutView() {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        view.setFrame(10.0f, height/2, 3*width/4, height/2 );
        showView(view);
    }

    @Override
      protected void tick() {
          
           
          
    
        }
        /*public void pauseTrigger(){
            NewsEvent currentEvent= PressMill.triggerWorldEvents();
            if(currentEvent.getType()== NewsEvent.NewsType.PASSIVE){
                //Do Shit
            }else if(currentEvent.getType()== NewsEvent.NewsType.INTER){
                //Do some other shit
            }
        }*/

		public void getActiveEvent() {
            NewsEvent currentEvent = PressMill.triggerPlayerEvents();
			view.getUserInput(currentEvent);

			
		}
}
