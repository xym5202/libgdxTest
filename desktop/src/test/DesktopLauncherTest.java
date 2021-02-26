package test;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class DesktopLauncherTest {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title="MainTest";
		config.width=800;
		config.height=480;
		TexturePacker.Settings settings=new TexturePacker.Settings();
		settings.maxHeight=1024;
		settings.maxWidth=1024;
		TexturePacker.process(settings,"desktop/res/atlas/input","desktop/res/atlas/output","game");

		new LwjglApplication(new TestGame(), config);
	}
}
