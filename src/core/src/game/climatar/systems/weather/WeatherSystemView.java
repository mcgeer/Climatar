package game.climatar.systems.weather;
 
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Group;
 
import com.kotcrab.vis.ui.widget.VisLabel;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import game.climatar.architecture.Model;
import game.climatar.architecture.View;
 
public class WeatherSystemView extends View {
 
    private VisLabel temperatureLabel;

    @Override
    public void build(Group group) {
	// dat label though
	BitmapFont labelFont = new BitmapFont(Gdx.files.internal("fonts/title-font.fnt"),
					      Gdx.files.internal("fonts/title-font.png"), false);
	LabelStyle labelStyle = new LabelStyle(labelFont, Color.WHITE);
	temperatureLabel = new VisLabel("", labelStyle);
	
	group.addActor(temperatureLabel);
    }

    @Override
    public void layout(float x, float y, float width, float height) {
	temperatureLabel.setSize(width, height);
	temperatureLabel.setPosition(x, y);
    }

    @Override
    public void update(Model model) {
    }

    @Override
    public void dispose() {
    }
}
