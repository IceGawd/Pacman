package pacman.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class TextureLoader {
    public static AssetManager manager;
    public static TextureRegion[] pacmanMoveLeft;
    public static TextureRegion[] pacmanMoveRight;
    public static TextureRegion[] pacmanMoveUp;
    public static TextureRegion[] pacmanMoveDown;
    public static TextureRegion[] pacmanDie;
    public static TextureRegion[] fire;
    public static Texture rocket;

    public static TextureRegion[] world1 = new TextureRegion[48];
    public static TextureRegion[] world2 = new TextureRegion[48];
    public static TextureRegion[] world3 = new TextureRegion[48];

    public static void loadTexture() {
        loadTextureActors();
    }

    private static void loadTextureActors(){
        Texture tupac = new Texture("tupac.png");
        Texture world = new Texture("pacpelletcunk.png");
        Texture fire = new Texture("fire.png");
        TextureRegion[][] maintextures = TextureRegion.split(tupac, 32, 32);
        TextureRegion[][] worldtextures = TextureRegion.split(world, 16, 16);
        TextureRegion[][] firetextures = TextureRegion.split(fire, 37, 94);

        rocket = new Texture("rocket.png");
        TextureLoader.fire = firetextures[0];

        pacmanDie = new TextureRegion[12];
        for (int i = 2; i < maintextures[0].length; i++){
            pacmanDie[i - 2] = maintextures[0][i];
        }

        pacmanMoveRight = new TextureRegion[]{maintextures[0][0], maintextures[0][1]};
        pacmanMoveLeft = new TextureRegion[]{maintextures[1][0], maintextures[1][1]};
        pacmanMoveUp = new TextureRegion[]{maintextures[2][0], maintextures[2][1]};
        pacmanMoveDown = new TextureRegion[]{maintextures[3][0], maintextures[3][1]};

        for (int y = 0; y < worldtextures.length; y++){
//            System.out.println(y);
//            System.out.println(worldtextures[y].length);
            for (int x = 0; x < worldtextures[y].length; x++){
                int index = worldtextures[y].length * (y % 3) + x;
                if (y < 3){
                    world1[index] = worldtextures[y][x];
                }
                else if (y > 5){
                    world3[index] = worldtextures[y][x];
                }
                else {
                    world2[index] = worldtextures[y][x];
                }

            }
        }
    }
}
