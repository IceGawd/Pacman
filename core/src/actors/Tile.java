package actors;

import Utilities.Settings;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import pacman.game.GameClass;
import pacman.game.TextureLoader;
import pacman.game.World;

import javax.xml.soap.Text;
import java.util.Set;

public class Tile extends Actor {
    public Sprite sprite;
    public int value;
    public int wait = Settings.firecooldown + Settings.fireshoottime;
    public boolean shoot = false;
    public boolean u;
    public boolean d;
    public boolean r;
    public boolean l;

    public Tile(float x, float y, TextureRegion t, int v) {
        setRotation(90);
        setX(x);
        setY(y);

        sprite = new Sprite(t.getTexture());
        sprite.setRegion(t);
        value = v;

        if (v < Settings.up.length) {
            u = Settings.up[v];
            d = Settings.down[v];
            r = Settings.right[v];
            l = Settings.left[v];
        }
        else {
            u = false;
            d = false;
            r = false;
            l = false;
        }

        sprite.setSize(Settings.pacmansize, Settings.pacmansize);
    }

    public Tile(float x, float y, Texture t) {
        setRotation(90);
        setX(x);
        setY(y);
        sprite = new Sprite(t);

        sprite.setSize(Settings.pacmansize, Settings.pacmansize);

        if (t.equals(TextureLoader.rocket)){
            u = true;
            d = true;
            r = true;
            l = true;
        }
    }

    @Override
    public void act(float delta) {
        if (sprite.getTexture().equals(TextureLoader.rocket)) {
            wait++;
            if (shoot && wait >= Settings.fireshoottime) {
                shoot = false;
                wait = 0;
            }
            if (!shoot && wait >= Settings.firecooldown) {
                shoot = true;
                wait = 0;
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setPosition(getX(), getY());
        sprite.setRotation(getRotation() - 90);

        sprite.draw(batch);

        if (wait <= 2 * Settings.changetime || shoot){
            TextureRegion firetexture = null;
            if (shoot){
                if (wait <= Settings.changetime){
                    firetexture = TextureLoader.fire[0];
                }
                else if (wait <= 2 * Settings.changetime){
                    firetexture = TextureLoader.fire[1];
                }
                else{
                    firetexture = TextureLoader.fire[2 + (wait / Settings.changetime) % 2];
                }
            }
            else{
                if (wait <= Settings.changetime){
                    firetexture = TextureLoader.fire[1];
                }
                else {
                    firetexture = TextureLoader.fire[0];
                }
            }

            batch.draw(firetexture, (int) getX(), (int) getY() + Settings.pacmansize, Settings.pacmansize, 2 * Settings.pacmansize);
        }
    }

    @Override
    public float getHeight(){
        return Settings.pacmansize;
    }

    @Override
    public float getWidth(){
        return Settings.pacmansize;
    }

    public boolean collide(Pacman p){
        if (u){
            if (collide(getX(), getY() + (getHeight() * (1 - Settings.percentthick)), getWidth(), getHeight() * Settings.percentthick, p.getX(), p.getY(), p.getWidth(), p.getHeight())){
                return true;
            }
        }
        if (d){
            if (collide(getX(), getY(), getWidth(), getHeight() * Settings.percentthick, p.getX(), p.getY(), p.getWidth(), p.getHeight())){
                return true;
            }
        }
        if (l){
            if (collide(getX(), getY(), getWidth() * Settings.percentthick, getHeight(), p.getX(), p.getY(), p.getWidth(), p.getHeight())){
                return true;
            }
        }
        if (r){
            if (collide(getX() + (getWidth() * (1 - Settings.percentthick)), getY(), getWidth() * Settings.percentthick, getHeight(), p.getX(), p.getY(), p.getWidth(), p.getHeight())){
                return true;
            }
        }
        return false;
    }



    public static boolean collide(float x1, float y1, float width1, float height1, float x2, float y2, float width2, float height2) {
        if (x1 < x2 + width2 && x1 + width1 > x2 && y1 < y2 + height2 && y1 + height1 > y2) {
            return true;
        }
        return false;
    }
}
