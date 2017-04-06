package game.climatar.news;

import java.util.Comparator;


public class indexComparator implements Comparator<NewsEvent> {
    @Override
    public int compare(NewsEvent n1, NewsEvent n2) {

        return  n1.getIndex().compareTo(n2.getIndex());
    }

}
