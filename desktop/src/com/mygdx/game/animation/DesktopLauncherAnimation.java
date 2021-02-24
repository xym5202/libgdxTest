package com.mygdx.game.animation;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncherAnimation {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title="Drop";
		config.width=800;
		config.height=480;
		new LwjglApplication(new TestRunGame(), config);
	}
}
