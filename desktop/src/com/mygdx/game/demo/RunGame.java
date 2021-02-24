package com.mygdx.game.demo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RunGame extends Game {

    public SpriteBatch batch;

    private RunGameScreen runGameScreen;


    @Override
    public void create() {
        batch =new SpriteBatch();

        this.setScreen(new RunGameScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        if (batch!=null){
            batch.dispose();
        }
    }
}
