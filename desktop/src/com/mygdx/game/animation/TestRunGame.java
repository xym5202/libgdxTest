package com.mygdx.game.animation;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TestRunGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture walkSheetTexture;

    //行走动画
    private Animation walkAnimation;
    private TextureRegion currentFrame;

    private float stateTime;


    public void create() {
        batch = new SpriteBatch();

        walkSheetTexture = new Texture(Gdx.files.internal("run.png"));
        int frameRows = 5;
        int frameCols = 6;

        int perCellWidth = walkSheetTexture.getWidth() / frameCols;
        int perCellHeight = walkSheetTexture.getHeight() / frameRows;

        //按照单元格切分成二维数组
        TextureRegion[][] cellRegions = TextureRegion.split(walkSheetTexture, perCellWidth, perCellHeight);

        TextureRegion[] walkFrames = new TextureRegion[frameRows * frameCols];
        int index = 0;
        for (int row = 0; row < frameRows; row++) {
            for (int col = 0; col < frameCols; col++) {
                walkFrames[index++] = cellRegions[row][col];
            }
        }

        walkAnimation = new Animation(0.05f, walkFrames);

        walkAnimation.setPlayMode(Animation.PlayMode.LOOP);
    }


    @Override
    public void render() {
        // 黑色清屏
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stateTime += Gdx.graphics.getDeltaTime();

        currentFrame = (TextureRegion) walkAnimation.getKeyFrame(stateTime);

        batch.begin();
        batch.draw(currentFrame, 50, 100);
        batch.end();
    }
    @Override
    public void dispose() {
        // 释放资源
        if (walkSheetTexture != null) {
            walkSheetTexture.dispose();
        }
        if (batch != null) {
            batch.dispose();
        }
    }
}
