package Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import drop.Drop;
import drop.Drop1;

public class MainMenuScreen implements Screen {

    final Drop1 game;
    OrthographicCamera camera;

    public MainMenuScreen(Drop1 game) {
        this.game = game;

        camera =new OrthographicCamera();
        camera.setToOrtho(false,800,480);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0.2f,1);
        camera.update();
        game.batch.begin();
        game.font.draw(game.batch, "Welcome to Drop",100,150);
        game.font.draw(game.batch, "Tap anywhere to begin",100,100);
        game.batch.end();

        if (Gdx.input.isTouched()){
            game.setScreen(new GameScreen(game));
            dispose();
        }
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
