package com.mygdx.game.test;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncherTest {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title="MainTest";
		config.width=800;
		config.height=480;
		new LwjglApplication(new MainGame(), config);
	}
}
