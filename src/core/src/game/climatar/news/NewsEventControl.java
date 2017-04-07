package game.climatar.news;

import com.badlogic.gdx.Gdx;
import game.climatar.architecture.Controller;
import game.climatar.GameState;
import game.climatar.WorldSimulator;

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
        turn= 0;
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

		public void getActiveEvent() {
            NewsEvent currentEvent = PressMill.triggerPlayerEvents();
            view.getUserInput(currentEvent);
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
