package game.climatar.news;

import game.climatar.architecture.Controller;

import java.util.List;

import game.climatar.GameState;
import game.climatar.WorldSimulator;
import game.climatar.architecture.ControllerManager;
import game.climatar.map.Nation;

public class NewsEventControl extends Controller{
    private NewsEventGenerator PressMill;
   private  NewsEvent currentEvent;
    private GameState gs;
    private WorldSimulator ws;
    private int turn;
    NewsView view;
    @Override
    protected void initialize() {
        gs = (GameState) getParentController().getModel();
        ws =(WorldSimulator) getParentController();
        PressMill= new NewsEventGenerator(gs);
        turn= 0;   }

    @Override
    protected void layoutView() {
        showView(view);
    }

    @Override
      protected void tick() {
          
           
          
    
        }
        public void pauseTrigger(){
             currentEvent= PressMill.triggerWorldEvents();
            if(currentEvent.getType()== NewsEvent.NewsType.PASSIVE){
                //Do Shit
            }else if(currentEvent.getType()== NewsEvent.NewsType.INTER){
                //Do some other shit
            }
        }

		public void getNewsEvent() {
			 if(turn%2==0){
			currentEvent=PressMill.triggerPlayerEvents();
			 }else{
				 currentEvent=PressMill.triggerWorldEvents();
			 } 
			
			
		}
		public void respondActiveYes(){
			ws.passConseq(currentEvent.getYConseq());
		}
		public void respondActiveNo(){
			ws.passConseq(currentEvent.getNConseq());
		}
		
}
