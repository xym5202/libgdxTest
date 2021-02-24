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
    private TextureRegion stopTextureRegion;

    private boolean leftMove;
    private boolean rightMove;

    private TextureRegion currentFrame;

    public Animation getWalkAnimation() {
        return walkAnimation;
    }

    public boolean isLeftMove() {
        return leftMove;
    }

    public void setLeftMove(boolean leftMove) {
        if (rightMove&&leftMove){
            rightMove=false;
        }
        this.leftMove = leftMove;
    }

    public boolean isRightMove() {
        return rightMove;
    }

    public void setRightMove(boolean rightMove) {
        if(leftMove&&rightMove){
            leftMove=false;
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
                    walkX[index++] = cellRegions[i][j];
                }
            }
        }

        walkAnimation = new Animation(0.05f, walkX);
        walkAnimation.setPlayMode(Animation.PlayMode.LOOP);
        stopTextureRegion=cellRegions[0][0];
        setSize(stopTextureRegion.getRegionWidth(), stopTextureRegion.getRegionHeight());

        actorRectangle = new Rectangle();
        actorRectangle.y = 20;
    }


    public TextureRegion getRegion() {
        return stopTextureRegion;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);

        if (stopTextureRegion == null || !isVisible()) {
            return;
        }

        batch.draw(stopTextureRegion, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }


    public void move(){
        if (leftMove){
            actorRectangle.x -=200*Gdx.graphics.getDeltaTime();
        }
        if (rightMove){
            actorRectangle.x +=200*Gdx.graphics.getDeltaTime();
        }
    }



}
