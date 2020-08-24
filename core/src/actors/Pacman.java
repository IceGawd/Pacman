package actors;

import Utilities.AnimatedSprite;
import Utilities.Animation;
import Utilities.Settings;
import pacman.game.World;

public class Pacman extends SuperActor {
    public int walkies;
    public int uppies;

    public float prevx;
    public float prevy;

    public Pacman(float x, float y) {
        setRotation(90);
        setX(x);
        setY(y);
        sprite = new AnimatedSprite(Animation.PACMAN_RIGHT, x, y, 90);
    }

    @Override
    public float getWidth(){
        return Settings.pacmansize;
    }
    @Override
    public float getHeight(){
        return Settings.pacmansize;
    }

    @Override
    public void act(float delta) {
//        System.out.println("x: " + getX());
//        System.out.println("y: " + getY());

        setX(getX() + walkies * Settings.pacmanspeed);
        setY(getY() + uppies * Settings.pacmanspeed);

        if (walkies == 1){
            setSprite(Animation.PACMAN_RIGHT);
        }
        if (walkies == -1){
            setSprite(Animation.PACMAN_LEFT);
        }
        if (uppies == 1){
            setSprite(Animation.PACMAN_UP);
        }
        if (uppies == -1){
            setSprite(Animation.PACMAN_DOWN);
        }

        boolean collided = false;

        for (Tile[] line : World.tiles){
            for (Tile tile : line){
                if (tile != null && tile.collide(this)){
                    collided = true;
                }
            }
        }

        if (collided){
            setX(prevx);
            setY(prevy);
        }

        prevx = getX();
        prevy = getY();

        super.act(delta);
    }
}