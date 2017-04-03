package game.climatar.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import game.climatar.ApplicationController;

public class DesktopLauncher {
	
	public static final float HUD_SCALE = 3f;
	
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 200;
		config.height = config.width*16/9;
		new LwjglApplication(new ApplicationController(HUD_SCALE), config);
	}
}
