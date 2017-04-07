package game.climatar.news;

import java.util.List;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import game.climatar.architecture.AllowController;
import game.climatar.architecture.Model;
import game.climatar.architecture.View;

/**
 * Created by vishe on 2017-04-06.
 */

@AllowController(NewsEventControl.class)
public class NewsView extends View {
    // Components
    private VisTable table;
    private VisLabel eventMessageLabel;
    private VisTextButton okayButton, noButton;

    private boolean isBuilt = false;

    //News Event
    private NewsEvent currentEvent;


    @Override
    public void dispose() {

    }

    @Override
    public void build(Group group) {
        group.debug();
        table = new VisTable();

        eventMessageLabel = new VisLabel("");
        eventMessageLabel.setWrap(true);

        okayButton = new VisTextButton("Okay");
        noButton = new VisTextButton("No");

        //Add Events
        okayButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                responceOkay();
            }
        });

        noButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                responceNo();
            }
        });

        //TODO REMOVE BELOW
        //Layout
        Value widthVal = new Value() {
            @Override
            public float get(Actor context) {
                return getFrame().width;
            }
        };

        Value heightVal = new Value() {
            @Override
            public float get(Actor context) {
                return getFrame().height/8;
            }
        };

        table.add(eventMessageLabel).maxWidth(widthVal).height(heightVal).align(Align.center);
        table.row();
        table.add(okayButton);
        table.add(noButton);

        group.addActor(table);
        //TODO UNTIL HERE
        isBuilt = true;
    }

    @Override
    public void layout(float x, float y, float width, float height) {
        table.pack();
        table.invalidate();
        table.validate();
        table.layout();
    }


    public void showNewsEvent(NewsEvent event) {

    }

    @Override
    public void update(Model model) {
        if(!isBuilt || currentEvent == null)
            return;

        VisTable newtable = new VisTable();

        Pixmap p = new Pixmap(50, 50, Pixmap.Format.RGBA8888);
        p.setColor(1/255,1/255,1/255,0.3f);
        p.fill();

        newtable.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(p))));

        VisLabel label = new VisLabel(currentEvent.getDescription());
        label.setWrap(true);
        label.setWidth(100); // or even as low as 10
        newtable.add(label).width(getFrame().getWidth());// <--- here you define the width! not ^
        newtable.pad(5,5,5,5);
        newtable.add(okayButton);
        newtable.add(noButton);


        if(currentEvent.getType().equals(NewsEvent.NewsType.ACTIVE))
            noButton.setVisible(true);
        else
            noButton.setVisible(false);

        this.table = newtable;

    }

    public void getUserInput(NewsEvent ct) {
        currentEvent = ct;
    }

    private void responceOkay(){
            ((NewsEventControl) getController()).respondActiveYes();
    }

    private void responceNo(){
        ((NewsEventControl) getController()).respondActiveNo();
    }

}
