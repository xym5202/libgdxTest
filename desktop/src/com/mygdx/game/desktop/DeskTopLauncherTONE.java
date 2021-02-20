package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import drop.Drop1;

public class DeskTopLauncherTONE {

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title="Drop";
        config.height=480;
        config.width=800;
        //设置窗口大小不可变
        config.resizable=false;
        new LwjglApplication(new Drop1(), config);
    }
}
