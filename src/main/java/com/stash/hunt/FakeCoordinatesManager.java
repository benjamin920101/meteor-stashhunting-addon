package com.stash.hunt;

import net.minecraft.entity.player.PlayerEntity;

import java.util.Random;

public class FakeCoordinatesManager {
    private static final Random RAND = new Random();

    public static double fixedX = 0.0;
    public static double fixedZ = 0.0;

    public static double offsetX = 0.0;
    public static double offsetZ = 0.0;

    public static double jitterRadius = 2.0;

    public static ObfuscationMode currentMode = ObfuscationMode.FIXED;

    public static void updateFakeCoords(PlayerEntity player) {
        if (player == null || !MagicMix.coordinatesIsActive()) return;

        switch (currentMode) {
            case FIXED:
                MagicMix.setCoords(fixedX, fixedZ);
                break;
            case DYNAMIC_OFFSET:
                MagicMix.setCoords(player.getX() + offsetX, player.getZ() + offsetZ);
                break;
            case JITTER:
                double jx = player.getX() + (RAND.nextDouble() * 2.0 - 1.0) * jitterRadius;
                double jz = player.getZ() + (RAND.nextDouble() * 2.0 - 1.0) * jitterRadius;
                MagicMix.setCoords(jx, jz);
                break;
            default:
                MagicMix.setCoords(player.getX(), player.getZ());
                break;
        }
    }
}
