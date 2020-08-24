package Utilities;

import actors.Pacman;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import pacman.game.GameClass;
import pacman.game.World;

public class WorldInputProcessor implements InputProcessor {
    public Pacman player;
    private boolean rightButtonHold = false;
    private Vector3 originMoveCam;
    private Viewport viewport;
    private World worldStage;
    private float panduration = 1f;
    private float timeToCameraPositionTarget = 0;
    private float xcameraori;
    private float ycameraori;
    private float xcameradest;
    private float ycameradest;
    private float zoomduration = 0.4f;
    private float cameraZoomOrigin = 1f;
    private float cameraZoomTarget = 1f;
    private float timeToCameraZoomTarget = 0;


    public WorldInputProcessor(Viewport viewport, World stage, Pacman p) {
        this.worldStage = stage;
        Gdx.input.setInputProcessor(this);
        this.viewport = viewport;
        this.originMoveCam = new Vector3();
        this.player = p;
    }


    public void act() {
        this.renderPan();
    }

    @Override
    public boolean keyDown(int keycode) {
        if ((keycode == Input.Keys.D) || (keycode == Input.Keys.RIGHT)){
            player.walkies = 1;
            player.uppies = 0;
            return true;
        }
        if ((keycode == Input.Keys.A) || (keycode == Input.Keys.LEFT)|| (keycode==Input.Keys.Q)){
            player.walkies = -1;
            player.uppies = 0;
            return true;
        }
        if ((keycode == Input.Keys.W) || (keycode == Input.Keys.UP) || (keycode==Input.Keys.Z)){
            player.walkies = 0;
            player.uppies = 1;
            return true;
        }
        if (keycode == Input.Keys.S|| (keycode == Input.Keys.DOWN)){
            player.walkies = 0;
            player.uppies = -1;
        }
        if (keycode == Input.Keys.SPACE){
            player.walkies = 0;
            player.uppies = 0;
            player.sprite.switchAnimation(Animation.PACMAN_DIE);
        }

            return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }




    @Override
    public boolean keyTyped(char character) {
        return false;
    }


    private void renderPan() {
        if (this.timeToCameraPositionTarget > 0) {
            this.timeToCameraPositionTarget -= Gdx.graphics.getDeltaTime();
            float progress = timeToCameraPositionTarget < 0 ? 1 : 1f - timeToCameraPositionTarget / panduration;
            float x = Interpolation.pow3Out.apply(xcameraori, xcameradest, progress);
            float y = Interpolation.pow3Out.apply(ycameraori, ycameradest, progress);
            this.worldStage.getCamera().position.set(x, y, 0);
            this.worldStage.getCamera().update();
        }
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
