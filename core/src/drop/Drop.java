package drop;

import Screen.MainMenuScreen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class Drop extends ApplicationAdapter {
    private Texture dropImage;
    private Texture bucketImage;

    private Sound dropSound;
    private Music rainMusic;
    //相机
    private OrthographicCamera camera;
    //绘制2D图像，例如加载纹理
    private SpriteBatch batch;

    //用于存储桶位置和大小
    private Rectangle bucket;
    //存储雨滴的位置，用数组 Array取代ArrayList等java集合，并整合了垃圾回收器
    private Array<Rectangle> raindrops;
    //追踪上次余地生成时间
    private long lastDropTime;



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

        //初始化雨滴
        raindrops=new Array<Rectangle>();
        spawnRaindrop();
    }

    /**
     * 渲染
     */
    @Override
    public void render() {
        super.render();
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
        batch.draw(bucketImage, bucket.x, bucket.y);
        for (Rectangle rectangle :raindrops){
            batch.draw(dropImage,rectangle.x,rectangle.y);
        }
        batch.end();

        //添加用户控制水桶
        //首先通过调用询问鼠标模块当前是否有操作
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            //获取输入的横纵坐标
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            //将输入坐标转换为相机的坐标系
            camera.unproject(touchPos);
            //调整水桶的位置更改为输入坐标的中心
            bucket.x = touchPos.x - 64 / 2;
        }
        //添加键盘控制模块
        //getDeltaTime以秒为单位饭回最后一个帧到当前帧经过的时间（键盘按下的时间）
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))bucket.x -=200*Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))bucket.x +=200*Gdx.graphics.getDeltaTime();


        //确保桶的位置不超出屏幕限制
        if (bucket.x < 0) bucket.x = 0;
        if (bucket.x > 800 - 64) bucket.x = 800 - 64;

        //添加雨滴,每个n秒生曾雨滴
        if (TimeUtils.nanoTime()-lastDropTime>1000000000)spawnRaindrop();
        //雨滴行动，200像素每秒恒定速度移动，移除屏幕边缘删除
        for (Iterator<Rectangle> iterator=raindrops.iterator(); iterator.hasNext();){
            Rectangle raindrop=iterator.next();
            raindrop.y -=200*Gdx.graphics.getDeltaTime();
            if (raindrop.y +64<0)iterator.remove();
            //雨滴落到桶里就消失
            //overlaps 方法判断两个矩阵是否重叠
            if (raindrop.overlaps(bucket)){
            //    dropSound.play();
                iterator.remove();
            }
        }
    }

    /**
     * 在屏幕顶部生成雨滴
     */
    private void spawnRaindrop(){
        Rectangle raindrop=new Rectangle();
        raindrop.x = MathUtils.random(0,800-64);
        raindrop.y=480;
        raindrop.width=64;
        raindrop.height=64;
        raindrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }

    /**
     * 关闭时处理资源
     */
    @Override
    public  void  dispose(){
        dropImage.dispose();
        bucketImage.dispose();
       // dropSound.dispose();
        rainMusic.dispose();
        batch.dispose();
    }


}
