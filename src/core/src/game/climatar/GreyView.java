package game.climatar;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;

import game.climatar.architecture.AllowController;
import game.climatar.architecture.Model;
import game.climatar.architecture.View;

/**
 * Created by Leo_Yuan on 17/4/7.
 */
@AllowController(WorldSimulator.class)
public class GreyView extends View {

    private VisTable table;
    @Override
    public void build(Group group) {

        table = new VisTable();
        Pixmap p = new Pixmap(50, 50, Pixmap.Format.RGBA8888);
        p.setColor(1/255,1/255,1/255,0.3f);
        p.fill();

        table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(p))));

        Value widthVal1 = new Value() {
            @Override
            public float get(Actor context) {
                return getFrame().width ;
            }
        };

        Value heightVal1 = new Value() {
            @Override
            public float get(Actor context) {
                return getFrame().height ;
            }
        };



        table.add("                                                     ").maxWidth(widthVal1).height(heightVal1).align(Align.left);
        group.addActor(table);
    }

    @Override
    public void layout(float x, float y, float width, float height) {
        table.pack();
        table.invalidate();
        table.validate();
        table.layout();

    }

    @Override
    public void update(Model model) {

    }

    @Override
    public void dispose() {

    }
}
