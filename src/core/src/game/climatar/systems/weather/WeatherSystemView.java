package game.climatar.systems.weather;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;

import game.climatar.architecture.AllowController;
import game.climatar.architecture.Model;
import game.climatar.architecture.View;
import game.climatar.map.Nation;
import game.climatar.systems.weather.WeatherSystemModel.WeatherProperty;

@AllowController(WeatherSystemController.class)
public class WeatherSystemView extends View {


    private VisTable table;
    private VisLabel TemperatureLabel;
    private VisLabel PercipitationLabel;
    private VisLabel TempValueLabel;
    private VisLabel PercipValueLabel;

    @Override
    public void build(Group group) {
        table = new VisTable();

        TemperatureLabel = new VisLabel("Temperature: ");
        TempValueLabel = new VisLabel("20°C");
        PercipitationLabel = new VisLabel("Precipitation: ");
        PercipValueLabel = new VisLabel("50mm");

        Value widthVal = new Value() {
            @Override
            public float get(Actor context) {
                return getFrame().width / 2 - 20;
            }
        };

        Value heightVal = new Value() {
            @Override
            public float get(Actor context) {
                return getFrame().height / 8;
            }
        };

        table.add(TemperatureLabel).maxWidth(widthVal).height(heightVal).align(Align.left);
        table.add(TempValueLabel).maxWidth(widthVal).fillX().expandX().width(widthVal).height(heightVal).align(Align.right);
        table.row();
        table.add(PercipitationLabel).maxWidth(widthVal).height(heightVal).align(Align.left);
        table.add(PercipValueLabel).maxWidth(widthVal).fillX().expandX().width(widthVal).height(heightVal).align(Align.right);

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
        double tempupdate = (double) (Integer) model.get(WeatherProperty.DELTA_TEMPERATURE.id());
        double temp = (double) (Integer) model.get(WeatherProperty.TEMPERATURE.id());

        Nation nation = (Nation) model.get(WeatherProperty.NATION.id());
        TempValueLabel.setText(tempupdate + temp + "°C");

        int percipupdate = (Integer) model.get(WeatherProperty.DELTA_PRECIPITATION.id());
        int percip = (Integer) model.get(WeatherProperty.PRECIPITATION.id());
        TempValueLabel.setText(percip + percipupdate + "mm");
    }

    @Override
    public void dispose() {
    }
}
