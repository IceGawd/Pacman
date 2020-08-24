package actors;

import Utilities.AnimatedSprite;
import Utilities.Animation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static pacman.game.GameClass.mainWorld;

public class SuperActor extends Actor {
    public AnimatedSprite sprite;

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (mustBeDrawn()) {
            sprite.animate(Gdx.graphics.getDeltaTime());
            sprite.setPosition(getX(), getY());
            sprite.setRotation(getRotation() - 90);
            sprite.draw(batch);
        }
    }

    public boolean mustBeDrawn() {
        return (mainWorld.getCamera().frustum.pointInFrustum(getX() - sprite.getWidth() / 2f, getY() - sprite.getHeight() / 2f, 0) | mainWorld.getCamera().frustum.pointInFrustum(getX() - sprite.getWidth() / 2f, getY() + sprite.getHeight() / 2f, 0) |
                mainWorld.getCamera().frustum.pointInFrustum(getX() + sprite.getWidth() / 2f, getY() - sprite.getHeight() / 2f, 0) | mainWorld.getCamera().frustum.pointInFrustum(getX() + sprite.getHeight() / 2f, getY() + sprite.getHeight() / 2f, 0));
    }

    public void setSprite(Animation animation) {
        sprite.switchAnimation(animation);
    }
}
