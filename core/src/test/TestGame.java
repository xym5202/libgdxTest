package test;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import test.Screen.MoveScreen;

import java.util.Iterator;

public class TestGame extends Game {
    //相机
    private OrthographicCamera camera;
    //绘制2D图像，例如加载纹理
    private SpriteBatch batch;

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    /**
     * 初始化
     */
    @Override
    public void create() {


        camera = new OrthographicCamera();
        //确保相机始终向我们展示800*480单位宽的区域
        camera.setToOrtho(false, 800, 480);

        batch = new SpriteBatch();
        this.setScreen(new MoveScreen(this));

    }

    /**
     * 关闭时处理资源
     */
    @Override
    public  void  dispose(){
        batch.dispose();
    }


}
