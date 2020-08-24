package Utilities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import pacman.game.TextureLoader;

public enum Animation {
    PACMAN_LEFT(Settings.pacmanbitespeed, TextureLoader.pacmanMoveLeft, Settings.pacmansize, Settings.pacmansize),
    PACMAN_RIGHT(Settings.pacmanbitespeed, TextureLoader.pacmanMoveRight, Settings.pacmansize, Settings.pacmansize),
    PACMAN_UP(Settings.pacmanbitespeed, TextureLoader.pacmanMoveUp, Settings.pacmansize, Settings.pacmansize),
    PACMAN_DOWN(Settings.pacmanbitespeed, TextureLoader.pacmanMoveDown, Settings.pacmansize, Settings.pacmansize),
    PACMAN_DIE(Settings.pacmanbitespeed, TextureLoader.pacmanDie, Settings.pacmansize, Settings.pacmansize),
    ;

    private float frameDuration;
    private TextureRegion[] keyFrames;
    private float width;
    private float height;

    Animation(float frameDuration, TextureRegion[] keyFrames, float width, float height) {
        this.frameDuration = frameDuration;
        this.keyFrames = keyFrames;
        this.width = width;
        this.height = height;
    }

    public float getAnimationDuration(){
        return frameDuration*keyFrames.length;
    }

    public float getFrameDuration() {
        return frameDuration;
    }

    public TextureRegion[] getKeyFrames() {
        return keyFrames;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
