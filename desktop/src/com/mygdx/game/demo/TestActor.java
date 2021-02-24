package com.mygdx.game.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TestActor extends Actor {

    private Animation walkAnimation;

    private Rectangle actorRectangle;

    private TextureRegion[] walkX;
    private Texture walkSheetTexture;
    private TextureRegion currentFrame;
    private TextureRegion stopFrame;

    private boolean stop;
    private boolean leftMove;
    private boolean rightMove;

    private float stateTime;

    private boolean faceToX = true;

    public Texture getWalkSheetTexture() {
        return walkSheetTexture;
    }

    public Animation getWalkAnimation() {
        return walkAnimation;
    }

    public boolean isLeftMove() {
        return leftMove;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public void setLeftMove(boolean leftMove) {
        if (rightMove && leftMove) {
            rightMove = false;
        }
        this.leftMove = leftMove;
    }

    public boolean isFaceToX() {
        return faceToX;
    }

    public void setFaceToX(boolean faceToX) {
        this.faceToX = faceToX;
    }

    public boolean isRightMove() {
        return rightMove;
    }


    public void setRightMove(boolean rightMove) {
        if (leftMove && rightMove) {
            leftMove = false;
        }
        this.rightMove = rightMove;
    }

    public TestActor() {

        walkSheetTexture = new Texture(Gdx.files.internal("run.png"));
        int frameRows = 5;
        int frameClo = 6;

        int perCellHeight = walkSheetTexture.getHeight() / frameRows;
        int perCellWidth = walkSheetTexture.getWidth() / frameClo;

        TextureRegion[][] cellRegions = TextureRegion.split(walkSheetTexture, perCellWidth, perCellHeight);

        walkX = new TextureRegion[frameRows * frameClo];

        int index = 0;

        if (index == 0) {
            for (int i = 0; i < frameRows; i++) {
                for (int j = 0; j < frameClo; j++) {
                    TextureRegion cellregion = cellRegions[i][j];
                    walkX[index++] = cellregion;
                }
            }
        }

        walkAnimation = new Animation(0.05f, walkX);
        walkAnimation.setPlayMode(Animation.PlayMode.LOOP);

        currentFrame = cellRegions[0][0];
        stopFrame = cellRegions[0][0];
        setSize(currentFrame.getRegionWidth(), currentFrame.getRegionHeight());

        actorRectangle = new Rectangle();
        actorRectangle.y = 20;
    }


    public TextureRegion getRegion() {
        return currentFrame;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);

        if (currentFrame == null || !isVisible()) {
            return;
        }

        if (stop && faceToX) {
            batch.draw(stopFrame, faceToX ? actorRectangle.x + getWidth() : actorRectangle.x, actorRectangle.y, faceToX ? -getWidth() : getWidth(), getHeight());
        } else if (stop && !faceToX) {
            batch.draw(stopFrame, faceToX ? actorRectangle.x + getWidth() : actorRectangle.x, actorRectangle.y, faceToX ? -getWidth() : getWidth(), getHeight());
        }else {
            batch.draw(currentFrame, faceToX ? actorRectangle.x + getWidth() : actorRectangle.x, actorRectangle.y, faceToX ? -getWidth() : getWidth(), getHeight());
        }

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        move();
    }

    public void move() {
        if (leftMove) {
            actorRectangle.x -= 20 * Gdx.graphics.getDeltaTime();
            stateTime += Gdx.graphics.getDeltaTime();
            currentFrame = (TextureRegion) walkAnimation.getKeyFrame(stateTime);
        }
        if (rightMove) {
            actorRectangle.x += 20 * Gdx.graphics.getDeltaTime();
            stateTime += Gdx.graphics.getDeltaTime();
            currentFrame = (TextureRegion) walkAnimation.getKeyFrame(stateTime);
        }
    }


}
