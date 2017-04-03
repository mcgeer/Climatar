package game.climatar.play;

import com.badlogic.gdx.Screen;

public class PlayScreen implements Screen {

    private float hudscale;
    private PlayScreenController controller;

    public PlayScreen(PlayScreenController playScreenController, float hudScale) {
        this.controller = playScreenController;
        this.hudscale = hudScale;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
