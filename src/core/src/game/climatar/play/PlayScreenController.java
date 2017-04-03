package game.climatar.play;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.kotcrab.vis.ui.VisUI;

import game.climatar.menu.MenuScreenController;


public class PlayScreenController extends Game {

    private float hudScale;
    private PlayScreen PlayScreenView;
    private MenuScreenController menuScreenController;


    public PlayScreenController(float hudScale) {
        PlayScreenView = new PlayScreen(this, hudScale);
        this.hudScale = hudScale;
    }
    public Screen getView() {
        return PlayScreenView;
    }


    public void ReturntoMenu() {
        VisUI.load(); // load the UI skin
        setScreen(menuScreenController.getView());
    }


    @Override
    public void create() {

    }
}
