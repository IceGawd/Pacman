package Utilities;

public class Settings {
    public static int pacmanspeed = 10;
    public static float pacmansize = 100;
    public static float pacmanbitespeed = 1/8f;
    public static float percentthick = 1/2f;
    public static int worldheight = 20;
    public static int worldlength = 20;
    public static int spawntilex = 5;
    public static int spawntiley = 5;
    public static int firecooldown = 150;
    public static int fireshoottime = 100;
    public static int changetime = 5;

    //                               0      1      2      3      4      5      6      7      8      9      10     11     12     13
    public static boolean[] up =    {true , true , false, false, false, false, false, false, true , true , true , true , false, false, };
    public static boolean[] down =  {false, false, false, false, true , true , true , true , false, false, false, false, true , true , };
    public static boolean[] left =  {false, true , false, true , false, true , false, true , false, true , false, false, false, false, };
    public static boolean[] right = {true , false, true , false, true , false, true , false, true , false, false, false, false, false, };
}
