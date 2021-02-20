package drop;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class Drop extends ApplicationAdapter {
    private Texture dropImage;
    private Texture bucketImage;

    private Sound dropSound;
    private Music rainMusic;
    //相机
    private OrthographicCamera camera;
    //绘制2D图像，例如加载纹理
    private SpriteBatch batch;

    //用于存储位置和大小
    private Rectangle bucket;

    /**
     * 初始化
     */
    @Override
    public void create() {
        //加载雨滴和水桶的图片
        dropImage = new Texture(Gdx.files.internal("drop.png"));
        bucketImage = new Texture(Gdx.files.internal("bucket.png"));
        //加载声音，没有资源暂时不加载
//        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
//        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

        //开始播放背景音乐
//        rainMusic.setLooping(true);
//        rainMusic.play();

        camera = new OrthographicCamera();
        //确保相机始终向我们展示800*480单位宽的区域
        camera.setToOrtho(false, 800, 480);

        batch = new SpriteBatch();

        //绘图原点位于屏幕左下角
        bucket = new Rectangle();
        //bucket的初始横坐标位于中间位置
        bucket.x = 800 / 2 - 64 / 2;
        //y轴指向上方进行渲染
        bucket.y = 20;
        bucket.width = 64;
        bucket.height = 64;
    }

    /**
     * 渲染
     */
    @Override
    public void render() {
        //深蓝色clear屏幕
        //参数 红 绿 蓝 alpha
        ScreenUtils.clear(0, 0, 0.2f, 1);
        //刷新相机位置
        camera.update();

        //渲染存储桶
        //告诉spritebatch使用相机指定的坐标系 camera.combined 为相机的投影矩阵
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        //绘制水桶 begin和end间可以一次性绘制所有图像
        batch.draw(bucketImage,bucket.x,bucket.y);
        batch.end();

        //添加用户控制水桶
        //首先通过调用询问输入模块当前是否有操作
        if (Gdx.input.isTouched()){
            Vector3 touchPos=new Vector3();
            //获取输入的横纵坐标
            touchPos.set(Gdx.input.getX(),Gdx.input.getY(),0);
            //将输入坐标转换为相机的坐标系
            camera.unproject(touchPos);
            //调整水桶的位置更改为输入坐标的中心
            bucket.x=touchPos.x-64/2;
        }
        //确保桶的位置不超出屏幕限制
        if (bucket.x<0)bucket.x=0;
        if (bucket.x>800-64)bucket.x=800-64;
    }


}
