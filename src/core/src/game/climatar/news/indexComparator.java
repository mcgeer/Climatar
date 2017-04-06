package game.climatar.news;

import java.util.Comparator;


public class indexComparator implements Comparator<NewsEvents> {
    @Override
    public int compare(NewsEvents n1, NewsEvents n2) {

        return  n1.getIndex().compareTo(n2.getIndex());
    }

}
