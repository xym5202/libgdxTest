package test.archer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import test.TestGame;
import test.archer.Animations.ArcherAnimations;

public class KeyBordControlArcherActor extends ArcherActor {


    private ArcherAnimations archerAnimations;
    private Rectangle archerRectangle;

    private int speed;
    private float stateTime;
    float stateTime1;
    private TextureRegion currentFrame;

    public KeyBordControlArcherActor(TestGame testGame) {
        archerAnimations = ArcherAnimations.getInstance();
        archerRectangle = new Rectangle();
        archerRectangle.x = 400;
        archerRectangle.y = 20;
        speed = 60;
        currentFrame = (TextureRegion) archerAnimations.getArcherMoveLeftAnimation().getKeyFrame(1);
        archerRectangle.height = currentFrame.getRegionHeight();
        archerRectangle.width = currentFrame.getRegionWidth();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (currentFrame == null || !isVisible()) {
            return;
        }
        batch.draw(currentFrame, archerRectangle.x, archerRectangle.y, archerRectangle.getWidth(), archerRectangle.getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += Gdx.graphics.getDeltaTime();
        move();
    }


    public void move() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            archerRectangle.x -= speed * Gdx.graphics.getDeltaTime();
            currentFrame = (TextureRegion) archerAnimations.getArcherMoveLeftAnimation().getKeyFrame(stateTime);
        }


        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            archerRectangle.x += speed * Gdx.graphics.getDeltaTime();
            currentFrame = (TextureRegion) archerAnimations.getArcherMoveRightAnimation().getKeyFrame(stateTime);
        }


        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            archerRectangle.y -= speed * Gdx.graphics.getDeltaTime();
            currentFrame = (TextureRegion) archerAnimations.getArcherMoveDownAnimation().getKeyFrame(stateTime);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            archerRectangle.y += speed * Gdx.graphics.getDeltaTime();
            currentFrame = (TextureRegion) archerAnimations.getArcherMoveUpAnimation().getKeyFrame(stateTime);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)&&Gdx.input.isKeyPressed(Input.Keys.W)){
            currentFrame = (TextureRegion) archerAnimations.getArcherMoveLeftUpAnimation().getKeyFrame(stateTime);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)&&Gdx.input.isKeyPressed(Input.Keys.S)){
            currentFrame = (TextureRegion) archerAnimations.getArcherMoveLeftDownAnimation().getKeyFrame(stateTime);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)&&Gdx.input.isKeyPressed(Input.Keys.S)){
            currentFrame = (TextureRegion) archerAnimations.getArcherMoveRightDownAnimation().getKeyFrame(stateTime);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)&&Gdx.input.isKeyPressed(Input.Keys.W)){
            currentFrame = (TextureRegion) archerAnimations.getArcherMoveRightUpAnimation().getKeyFrame(stateTime);
        }


    }

}
