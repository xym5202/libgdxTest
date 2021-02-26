package test.archer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import test.TestGame;

public class MouseControlArcherActor extends ArcherActor {

    public static final String MOVEUP = "archer/move_up";
    public static final String MOVELEFT = "archer/move_left";
    public static final String MOVEDOWN = "archermove_down";
    public static final String MOVERIGHT = "archer/move_right";

    private int speed;
    private OrthographicCamera camera;

    private TextureRegion textureRegion;
    private Rectangle archerRectangle;

    private TextureAtlas atlas;

    //鼠标点击位置
    Vector3 vector3 = new Vector3();

    private TextureRegion currentFrame;

    private Array<TextureAtlas.AtlasRegion> archerRegionMoveUp;
    private Array<TextureAtlas.AtlasRegion> archerRegionMoveLeft;
    private Array<TextureAtlas.AtlasRegion> archerRegionMoveDown;
    private Array<TextureAtlas.AtlasRegion> archerRegionMoveRight;


    public MouseControlArcherActor(TestGame testGame) {
        atlas = new TextureAtlas(Gdx.files.internal("atlas/output/game.atlas"));
        archerRegionMoveDown = atlas.findRegions(MOVEDOWN);
        archerRegionMoveUp = atlas.findRegions(MOVEUP);
        archerRegionMoveLeft = atlas.findRegions(MOVELEFT);
        archerRegionMoveRight = atlas.findRegions(MOVERIGHT);
        this.camera = testGame.getCamera();
        //  archerVector3.set(400, 20, 0);
        archerRectangle = new Rectangle();
        archerRectangle.x = 400;
        archerRectangle.y = 20;
        vector3.set(archerRectangle.x, archerRectangle.y, 0);
        archerRectangle.height = archerRegionMoveRight.get(1).originalHeight;
        archerRectangle.width = archerRegionMoveRight.get(1).originalWidth;
        textureRegion = new TextureRegion(archerRegionMoveRight.get(1));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(textureRegion, archerRectangle.x, archerRectangle.y);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        updateVector();
        move();
    }

    public void updateVector() {
        if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            vector3.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(vector3);
        }
    }


    public void move() {
        //判断目前的位置和鼠标点击的位置
        if (!(vector3.x == archerRectangle.x && vector3.y == archerRectangle.y)) {
            //移动位置
            float pathX = vector3.x - archerRectangle.x;
            float pathY = vector3.y - archerRectangle.y;
            float distance = (float) Math.sqrt(pathX * pathX + pathY * pathY);

            //根据相似三角形原则
            float directionX = pathX / distance;
            float directionY = pathY / distance;

            //判断移动距离是否足够
            //若x的距离不够，则直接移动到点
            if (Math.abs(pathX) < Math.abs(directionX * 200 * Gdx.graphics.getDeltaTime())) {
                archerRectangle.x = vector3.x;
            }
            //若y的距离不够，则直接移动到点
            if (Math.abs(pathY) < Math.abs(directionY * 200 * Gdx.graphics.getDeltaTime())) {
                archerRectangle.y = vector3.y;
            }

            if (vector3.x!=archerRectangle.x)
            archerRectangle.x += directionX * 200 * Gdx.graphics.getDeltaTime();
            if (vector3.y!=archerRectangle.y)
            archerRectangle.y += directionY * 200 * Gdx.graphics.getDeltaTime();
        }
    }

}
