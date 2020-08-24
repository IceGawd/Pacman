package Utilities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimatedSprite extends Sprite {
    private TextureRegion[] arrayFrame;
    private float frameDuration;
    private float animationDuration;
    private int frameIndex;
    private float timeElapsed;


    private AnimatedSprite(float frameDuration, TextureRegion[] keyFrames, float x, float y, float rotation, float width, float height) {
        this.frameDuration = frameDuration;
        this.animationDuration = frameDuration * keyFrames.length;
        this.arrayFrame = keyFrames;
        this.timeElapsed = 0;
        this.frameIndex = 0;
        this.setRegion(keyFrames[frameIndex]);
        this.setPosition(x, y);
        this.setSize(width, height);
        this.setRotation(rotation);
    }

    public AnimatedSprite(Animation animation, float x, float y, float rotation) {
        this(animation.getFrameDuration(), animation.getKeyFrames(), x, y, rotation, animation.getWidth(), animation.getHeight());
    }

    public AnimatedSprite(Texture texture, float x, float y, float rotation, float width, float height) {
        this.setTexture(texture);
        this.setRegion(0, 0, texture.getWidth(), texture.getHeight());
        this.setPosition(x, y);
        this.setSize(width, height);
        this.setRotation(rotation);
    }

    public AnimatedSprite(Texture texture, float x, float y, float rotation) {
        this(texture, x, y, rotation, texture.getWidth(), texture.getHeight());
    }

    private void switchAnimation(float frameDuration, TextureRegion[] keyFrames, float rotation, float width, float height) {
        this.frameDuration = frameDuration;
        this.animationDuration = frameDuration * keyFrames.length;
        this.arrayFrame = keyFrames;
        this.frameIndex = 0;
        this.setRegion(keyFrames[frameIndex]);
        this.setSize(width, height);
        this.setRotation(rotation);
    }

    public void switchAnimation(Animation animation) {
        this.switchAnimation(animation.getFrameDuration(), animation.getKeyFrames(), this.getRotation(), animation.getWidth(), animation.getHeight());
    }

    public void switchAnimation(Texture texture, float rotation, float width, float height) {
        frameDuration = 0;
        animationDuration = 0;
        this.setSize(width, height);
        this.setRotation(rotation);
    }

    @Override
    public void setPosition(float x, float y) {
        this.setX(x);
        this.setY(y);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
    }

    public void animate(float delta) {
        if (animationDuration != 0) {
            timeElapsed += delta;

            while (timeElapsed >= animationDuration) {
                timeElapsed -= animationDuration;
            }

//            System.out.println("te: " + timeElapsed);
//            System.out.println("fd: " + frameDuration);

            if ((int) (timeElapsed / frameDuration) != frameIndex) {
                frameIndex = (int) (timeElapsed / frameDuration);
                this.setRegion(arrayFrame[frameIndex]);
            }
        }
    }


}