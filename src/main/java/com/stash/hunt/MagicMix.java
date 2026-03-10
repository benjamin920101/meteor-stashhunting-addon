package com.stash.hunt;

public class MagicMix {
    public static double x = 0.0;
    public static double z = 0.0;
    public static boolean coordinatesActive = false;

    public static boolean coordinatesIsActive() {
        return coordinatesActive;
    }

    public static double getX() { return x; }
    public static double getZ() { return z; }

    public static void setCoords(double nx, double nz) {
        x = nx;
        z = nz;
    }

    public static void setActive(boolean a) { coordinatesActive = a; }
}
