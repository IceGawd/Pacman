package pacman.game;

import Utilities.Settings;
import actors.Pacman;
import actors.Tile;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameClass extends ApplicationAdapter {
	public static World mainWorld;
	public static Pacman character;
	public static ShapeRenderer debugrender;
	public static boolean debug = false;

	@Override
	public void create () {
		TextureLoader.loadTexture();
		character = new Pacman(Settings.spawntilex * Settings.pacmansize, Settings.spawntiley * Settings.pacmansize);
		mainWorld = new World();
		debugrender = new ShapeRenderer();
		Gdx.graphics.setTitle("Level Up's Pacman!");
	}

	@Override
	public void render () {
		if (debug){
			debugrender.setProjectionMatrix(mainWorld.getViewport().getCamera().combined);
			debugrender.begin(ShapeRenderer.ShapeType.Line);
			debugrender.setColor(1, 1, 0, 1);
		}

		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		mainWorld.act();
		mainWorld.draw();

		if (debug) {
			debugrender.rect(character.getX(), character.getY(), character.getWidth(), character.getHeight());
			for (Tile[] line : World.tiles){
				for (Tile tile : line){
					if (tile != null){
						if (tile.u){
							debugrender.rect(tile.getX(), tile.getY() + (tile.getHeight() * (1 - Settings.percentthick)), tile.getWidth(), tile.getHeight() * Settings.percentthick);
						}
						if (tile.d){
							debugrender.rect(tile.getX(), tile.getY(), tile.getWidth(), tile.getHeight() * Settings.percentthick);
						}
						if (tile.l){
							debugrender.rect(tile.getX(), tile.getY(), tile.getWidth() * Settings.percentthick, tile.getHeight());
						}
						if (tile.r){
							debugrender.rect(tile.getX() + (tile.getWidth() * (1 - Settings.percentthick)), tile.getY(), tile.getWidth() * Settings.percentthick, tile.getHeight());
						}
					}
				}
			}
			debugrender.end();
		}

	}
	
	@Override
	public void dispose () {
	}
}
