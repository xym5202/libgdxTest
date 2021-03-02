package test.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeBitmapFontData;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import test.TestGame;
import test.archer.ArcherActor;
import test.archer.KeyBordControlArcherActor;


public class MoveScreen implements Screen {


    final TestGame testGame;

    private TiledMap tiledMap;
    private Stage stage;
    private ArcherActor archerActor;

    private AssetManager assetManager;

    private FreeTypeFontGenerator generator;//TTF字体发生器
    private FreeTypeFontParameter parameter;
    private FreeTypeBitmapFontData fontData;
    private BitmapFont font;//要实现的内容
    private String fontFileName ="data/font/4dfa.ttf";


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
        this.testGame = testGame;
        assetManager = new AssetManager();
        stage = new Stage();
        archerActor = new KeyBordControlArcherActor(testGame);
        archerActor.setPosition(800 / 2, archerActor.getHeight() / 2);
        stage.addActor(archerActor);
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        assetManager.load("simiao2.tmx", TiledMap.class);

        assetManager.setLoader(FreeTypeFontGenerator.class,new FreeTypeFontGeneratorLoader(new InternalFileHandleResolver()));
        assetManager.setLoader(BitmapFont.class,".ttf",new FreetypeFontLoader(new InternalFileHandleResolver()));

        FreetypeFontLoader.FreeTypeFontLoaderParameter size1Params=new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        size1Params.fontFileName=fontFileName;
        size1Params.fontParameters.size=30;
        assetManager.load(fontFileName, BitmapFont.class, size1Params);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (assetManager.update()) {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            tiledMap = assetManager.get("simiao2.tmx", TiledMap.class);
            testGame.setRenderer(new OrthogonalTiledMapRenderer(tiledMap, 1, testGame.getBatch()));
            testGame.getCamera().update();
            drawMap();
            stage.act();
            stage.draw();
            if (assetManager.isLoaded(fontFileName)){
                testGame.getBatch().begin();
                assetManager.get(fontFileName,BitmapFont.class).draw(testGame.getBatch(), "中国智造智慧全球aaaaabbbbbccccc123456",200,200);
                testGame.getBatch().end();
            }
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

    public void drawMap() {

        testGame.getRenderer().setView(testGame.getCamera());
        testGame.getRenderer().render();
    }

    @Override
    public void dispose() {
        if (stage != null) {
            stage.dispose();
            tiledMap.dispose();
            assetManager.dispose();
        }

    }
}
