package com.mygdx.game.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class RunGameScreen implements Screen {

    final RunGame runGame;

    private TestActor testActor;
    private Stage stage;
    //相机
    OrthographicCamera camera;


    public RunGameScreen(RunGame runGame) {
        this.runGame = runGame;
        testActor = new TestActor();
        testActor.setPosition(800 / 2, testActor.getHeight() / 2);
        camera=new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        stage = new Stage();
        stage.addActor(testActor);
        Gdx.input.setInputProcessor(stage);
        stage.addListener(new TestInputListener());
        testActor.addListener(new TestInputListener());
    }


    private class TestInputListener extends InputListener {

        @Override
        public boolean keyDown(InputEvent event, int keycode) {
            switch (keycode) {
                case Input.Keys.A:
                    testActor.setStop(false);
                    testActor.setLeftMove(true);
                    testActor.setFaceToX(true);
                    break;
                case Input.Keys.D:
                    testActor.setStop(false);
                    testActor.setRightMove(true);
                    testActor.setFaceToX(false);
                    break;
            }
            return true;
        }

        @Override
        public boolean keyUp(InputEvent event, int keycode) {
            switch (keycode) {
                case Input.Keys.A:
                    testActor.setLeftMove(false);
                    testActor.setStop(true);
                    break;
                case Input.Keys.D:
                    testActor.setRightMove(false);
                    testActor.setStop(true);
                    break;
            }
            return true;
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // 黑色清屏
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();

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
        if (stage != null) {
            stage.dispose();
        }
        if (testActor.getWalkSheetTexture()!=null){
            testActor.getWalkSheetTexture().dispose();
        }
    }
}

