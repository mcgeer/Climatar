package game.climatar.news;

import game.climatar.architecture.Controller;
import game.climatar.map.Nation;

public class NewsEventControl extends Controller{
    private NewsEventGenerator PressMill;
    private Nation Player;
    NewsView view;
    @Override
    protected void initialize() {

        super.initialize();
        PressMill= new NewsEventGenerator(Player);
    }

    @Override
    protected void layoutView() {
        showView(view);
    }

    @Override
    protected void tick() {

    }
}
