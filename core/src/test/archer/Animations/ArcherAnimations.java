package test.archer.Animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

//加载Archer的图片和动画类
public class ArcherAnimations {


    private Animation archerMoveUpAnimation;
    private Animation archerMoveLeftAnimation;
    private Animation archerMoveDownAnimation;
    private Animation archerMoveRightAnimation;

    private Animation archerMoveRightUpAnimation;
    private Animation archerMoveRightDownAnimation;
    private Animation archerMoveLeftUpAnimation;
    private Animation archerMoveLeftDownAnimation;


    private TextureAtlas atlas;

    private static ArcherAnimations instance;



    public static ArcherAnimations getInstance(){
        if (instance==null){
            instance=new ArcherAnimations();
        }
        return instance;
    }


    private  ArcherAnimations() {
        atlas = new TextureAtlas(Gdx.files.internal("atlas/output/game.atlas"));
        archerMoveUpAnimation = this.createArcherAnimations(ArcherMoveEnum.MOVEUP.getName());
        archerMoveDownAnimation = this.createArcherAnimations(ArcherMoveEnum.MOVEDOWN.getName());
        archerMoveLeftAnimation = this.createArcherAnimations(ArcherMoveEnum.MOVELEFT.getName());
        archerMoveRightAnimation = this.createArcherAnimations(ArcherMoveEnum.MOVERIGHT.getName());
        archerMoveLeftUpAnimation = this.createArcherAnimations(ArcherMoveEnum.MOVELEFTUP.getName());
        archerMoveLeftDownAnimation = this.createArcherAnimations(ArcherMoveEnum.MOVELEFTDOWN.getName());
        archerMoveRightUpAnimation = this.createArcherAnimations(ArcherMoveEnum.MOVERIGHTUP.getName());
        archerMoveRightDownAnimation = this.createArcherAnimations(ArcherMoveEnum.MOVERIGHTDOWN.getName());
    }

    public Animation getArcherMoveUpAnimation() {
        return archerMoveUpAnimation;
    }

    public void setArcherMoveUpAnimation(Animation archerMoveUpAnimation) {
        this.archerMoveUpAnimation = archerMoveUpAnimation;
    }

    public Animation getArcherMoveLeftAnimation() {
        return archerMoveLeftAnimation;
    }

    public void setArcherMoveLeftAnimation(Animation archerMoveLeftAnimation) {
        this.archerMoveLeftAnimation = archerMoveLeftAnimation;
    }

    public Animation getArcherMoveDownAnimation() {
        return archerMoveDownAnimation;
    }

    public void setArcherMoveDownAnimation(Animation archerMoveDownAnimation) {
        this.archerMoveDownAnimation = archerMoveDownAnimation;
    }

    public Animation getArcherMoveRightAnimation() {
        return archerMoveRightAnimation;
    }

    public void setArcherMoveRightAnimation(Animation archerMoveRightAnimation) {
        this.archerMoveRightAnimation = archerMoveRightAnimation;
    }

    public Animation getArcherMoveRightUpAnimation() {
        return archerMoveRightUpAnimation;
    }

    public void setArcherMoveRightUpAnimation(Animation archerMoveRightUpAnimation) {
        this.archerMoveRightUpAnimation = archerMoveRightUpAnimation;
    }

    public Animation getArcherMoveRightDownAnimation() {
        return archerMoveRightDownAnimation;
    }

    public void setArcherMoveRightDownAnimation(Animation archerMoveRightDownAnimation) {
        this.archerMoveRightDownAnimation = archerMoveRightDownAnimation;
    }

    public Animation getArcherMoveLeftUpAnimation() {
        return archerMoveLeftUpAnimation;
    }

    public void setArcherMoveLeftUpAnimation(Animation archerMoveLeftUpAnimation) {
        this.archerMoveLeftUpAnimation = archerMoveLeftUpAnimation;
    }

    public Animation getArcherMoveLeftDownAnimation() {
        return archerMoveLeftDownAnimation;
    }

    public void setArcherMoveLeftDownAnimation(Animation archerMoveLeftDownAnimation) {
        this.archerMoveLeftDownAnimation = archerMoveLeftDownAnimation;
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public void setAtlas(TextureAtlas atlas) {
        this.atlas = atlas;
    }

    public Animation createArcherAnimations(String nameStr){
        return  new com.badlogic.gdx.graphics.g2d.Animation(0.05f, atlas.findRegions(nameStr), com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP);
    }

}
