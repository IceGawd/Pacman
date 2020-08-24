package pacman.game;

import Utilities.Settings;
import Utilities.WorldInputProcessor;
import actors.Tile;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class World extends Stage {
    public Group actors = new Group();
    public WorldInputProcessor inputProcessor;
    public static Tile[][] tiles = new Tile[Settings.worldheight][Settings.worldlength];
    public SpriteBatch sb;
    public int[][] world = new int[Settings.worldheight][Settings.worldlength];
    public int level = 1;

    public World() {
        makeWorld();
        makeTiles();
        sb = new SpriteBatch();
        actors.addActor(GameClass.character);
        inputProcessor = new WorldInputProcessor(getViewport(), this, GameClass.character);
        super.addActor(actors);
        getViewport().getCamera().viewportHeight = getViewport().getScreenHeight();
        getViewport().getCamera().viewportWidth = getViewport().getScreenWidth();
    }

    private void makeWorld() {
        for (int y = 0; y < world.length; y++){
            for (int x = 0; x < world[y].length; x++){
                world[y][x] = -1;
            }
        }

        makeRectangle(0, 0, world[0].length - 1, world.length - 1);
        makeVerticalLine(2, 8, 2);
        makeHorizontalLine(2, 4, 2);
        makeVerticalLine(2, 4, 4);
        makeHorizontalLine(4, 6, 4);
        makeVerticalLine(2, 4, 6);
        makeHorizontalLine(6, 8, 2);
        makeVerticalLine(2, 8, 8);
        makeHorizontalLine(2, 8, 8);

        binaryWorldToTileCompatibleWorld();

        spawnRocket(5, 3, 90);

    }

    private void spawnRocket(int x, int y, int rotation){
        world[y][x] = -1;
        tiles[y][x] = new Tile(Settings.pacmansize * x, Settings.pacmansize * (world.length - y), TextureLoader.rocket);
        tiles[y][x].setRotation(rotation);
    }

    private void binaryWorldToTileCompatibleWorld(){
        for (int y = 0; y < world.length; y++){
            for (int x = 0; x < world[y].length; x++){
                if (world[y][x] == -2) {
                    calculateIt(x, y);
                }
            }
        }
    }

    private void calculateIt(int x, int y) {
        boolean left = isBlockAt(x - 1, y);
        boolean right = isBlockAt(x + 1, y);
        boolean up = isBlockAt(x, y - 1);
        boolean down = isBlockAt(x, y + 1);

        if (left && down) {
            world[y][x] = 0;
        }
        else if (right && down) {
            world[y][x] = 1;
        }
        else if (left && up) {
            world[y][x] = 4;
        }
        else if (right && up) {
            world[y][x] = 5;
        }
        else if (up && down) {
            if (world[y - 1][x] == 0 || world[y - 1][x] == 2){
                world[y][x] = 2;
            }
            if (world[y - 1][x] == 1 || world[y - 1][x] == 3){
                world[y][x] = 3;
            }
        }
        else if (right && left) {
            if (world[y][x - 1] == 1 || world[y][x - 1] == 10){
                world[y][x] = 10;
            }
            if (world[y][x - 1] == 5 || world[y][x - 1] == 12){
                world[y][x] = 12;
            }
        }
        else{
            world[y][x] = 0;
        }
    }

    private boolean isBlockAt(int x, int y){
        if (y >= 0 && y < world.length && x >= 0 && x < world[0].length){
            if (world[y][x] != -1){
                return true;
            }
        }
        return false;
    }

    private void makeRectangle(int x, int y, int width, int height){
        makeHorizontalLine(x, x + width, y);
        makeHorizontalLine(x, x + width, y + height);
        makeVerticalLine(y, y + height, x);
        makeVerticalLine(y, y + height, x + width);
    }

    private void makeHorizontalLine(int startx, int endx, int ypos) {
        for (int x = startx; x <= endx; x++){
            world[ypos][x] = -2;
        }
    }

    private void makeVerticalLine(int starty, int endy, int xpos) {
        for (int y = starty; y <= endy; y++){
            world[y][xpos] = -2;
        }
    }
    private void makeTiles() {
        TextureRegion[] tileset = new TextureRegion[0];
        if (level == 1){
            tileset = TextureLoader.world1;
        }
        if (level == 2){
            tileset = TextureLoader.world2;
        }
        if (level == 3){
            tileset = TextureLoader.world3;
        }

        Group tilthat = new Group();


        for (int y = 0; y < world.length; y++){
            for (int x = 0; x < world[y].length; x++){
                int value = world[y][x];
                if (value != -1) {
                    TextureRegion t = tileset[value];
                    Tile tile = new Tile(Settings.pacmansize * x, Settings.pacmansize * (world.length - y), t, value);
                    tiles[y][x] = tile;
                }
            }
        }

        for (int y = 0; y < tiles.length; y++){
            for (int x = 0; x < tiles[y].length; x++){
                if (tiles[y][x] != null) {
                    tilthat.addActor(tiles[y][x]);
                }
            }
        }

        actors.addActor(tilthat);
    }

    @Override
    public void act() {
        super.act();
        inputProcessor.act();
        getViewport().getCamera().position.set(GameClass.character.getX(), GameClass.character.getY(), 0);
    }

    @Override
    public void draw() {
        super.draw();
    }
}
