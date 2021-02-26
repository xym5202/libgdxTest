package test.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import test.TestGame;
import test.archer.ArcherActor;
import test.archer.KeyBordControlArcherActor;
import test.archer.MouseControlArcherActor;

public class MoveScreen implements Screen {

    final TestGame testGame;

    private Stage stage;
    private ArcherActor archerActor;



    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public ArcherActor getArcherActor() {
        return archerActor;
    }

    public void setArcherActor(ArcherActor archerActor) {
        this.archerActor = archerActor;
    }


    public MoveScreen(TestGame testGame) {
        this.testGame=testGame;
        stage=new Stage();
        archerActor =new KeyBordControlArcherActor(testGame);
        archerActor.setPosition(800 / 2, archerActor.getHeight() / 2);
        stage.addActor(archerActor);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
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
        if (stage!=null){
            stage.dispose();
        }

    }
}
