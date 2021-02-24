package com.mygdx.game.demo;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class TestMain {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title="TestMain";
		config.width=800;
		config.height=480;
		new LwjglApplication(new RunGame(), config);
	}
}
