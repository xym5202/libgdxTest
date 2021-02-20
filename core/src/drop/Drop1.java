package drop;

import Screen.MainMenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Drop1 extends Game {

    //绘制2D图像，例如加载纹理
    public SpriteBatch batch;

    //添加字体
    public BitmapFont font;


    @Override
    public void create() {
        batch = new SpriteBatch();
        //使用libgdx的默认字体
        font =new BitmapFont();
        this.setScreen(new MainMenuScreen(this));
    }

    public void  render(){
        super.render();
    }

    public void dispose(){
        batch.dispose();
        font.dispose();
    }
}
