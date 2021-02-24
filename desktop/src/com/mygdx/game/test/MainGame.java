package com.mygdx.game.test;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainGame extends ApplicationAdapter {

    private static final String TAG = MainGame.class.getSimpleName();

    private Texture texture;
    private MyActor myActor;
    private Stage stage;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        texture = new Texture(Gdx.files.internal("run.png"));
        myActor = new MyActor(new TextureRegion(texture));
        myActor.setPosition(20, 40);

        stage = new Stage();
        stage.addActor(myActor);

        Gdx.input.setInputProcessor(stage);
        stage.addListener(new MyInputListener());
        myActor.addListener(new MyInputListener());
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        if (texture != null) {
            texture.dispose();
        }
        if (stage != null) {
            stage.dispose();
        }
    }

    private class MyInputListener extends InputListener {
        @Override
        public boolean keyDown(InputEvent event, int keycode) {
            switch (keycode) {
                case Input.Keys.A:{
                    Gdx.app.log(TAG,"向左走");
                    break;
                }
                case Input.Keys.D:{
                    Gdx.app.log(TAG,"向右走");
                    break;
                }
                case Input.Keys.W:{
                    Gdx.app.log(TAG,"向前走");
                    break;
                }
                case Input.Keys.S:{
                    Gdx.app.log(TAG,"向后走");
                    break;
                }
                default: {
                    Gdx.app.log(TAG, "其他按键, KeyCode: " + keycode);
                    break;
                }
            }
            return  false;
        }
    }

    private class MyClickListener extends ClickListener {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            Actor actor = event.getListenerActor();

            Gdx.app.log(TAG, "被点击: " + x + ", " + y + "; Actor: " + actor.getClass().getSimpleName());
        }
    }
}
