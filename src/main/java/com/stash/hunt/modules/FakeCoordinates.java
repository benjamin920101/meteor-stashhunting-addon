package com.stash.hunt.modules;

import com.stash.hunt.Addon;
import com.stash.hunt.FakeCoordinatesManager;
import com.stash.hunt.MagicMix;
import com.stash.hunt.ObfuscationMode;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.settings.*;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.entity.player.PlayerEntity;

public class FakeCoordinates extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<ObfuscationMode> mode = sgGeneral.add(new EnumSetting.Builder<ObfuscationMode>()
        .name("mode")
        .description("Obfuscation mode for fake coordinates")
        .defaultValue(ObfuscationMode.FIXED)
        .build()
    );

    private final Setting<Double> fixedX = sgGeneral.add(new DoubleSetting.Builder()
        .name("fixed-x")
        .description("Fixed X coordinate (for FIXED mode)")
        .defaultValue(0.0)
        .build()
    );

    private final Setting<Double> fixedZ = sgGeneral.add(new DoubleSetting.Builder()
        .name("fixed-z")
        .description("Fixed Z coordinate (for FIXED mode)")
        .defaultValue(0.0)
        .build()
    );

    private final Setting<Double> offsetX = sgGeneral.add(new DoubleSetting.Builder()
        .name("offset-x")
        .description("Offset X added to real X for DYNAMIC_OFFSET")
        .defaultValue(0.0)
        .build()
    );

    private final Setting<Double> offsetZ = sgGeneral.add(new DoubleSetting.Builder()
        .name("offset-z")
        .description("Offset Z added to real Z for DYNAMIC_OFFSET")
        .defaultValue(0.0)
        .build()
    );

    private final Setting<Double> jitter = sgGeneral.add(new DoubleSetting.Builder()
        .name("jitter-radius")
        .description("Jitter radius for JITTER mode")
        .defaultValue(2.0)
        .min(0.0)
        .build()
    );

    private final Setting<Boolean> activate = sgGeneral.add(new BoolSetting.Builder()
        .name("active")
        .description("Enable/disable fake coordinates")
        .defaultValue(false)
        .build()
    );

    public FakeCoordinates() {
        super(Addon.CATEGORY, "fake-coordinates", "Show fake coordinates on supported HUDs/minimap.");
    }

    @Override
    public void onActivate() {
        MagicMix.setActive(activate.get());
    }

    @Override
    public void onDeactivate() {
        MagicMix.setActive(false);
    }

    @EventHandler
    private void onTick(TickEvent.Post event) {
        MagicMix.setActive(activate.get());
        FakeCoordinatesManager.currentMode = mode.get();
        FakeCoordinatesManager.fixedX = fixedX.get();
        FakeCoordinatesManager.fixedZ = fixedZ.get();
        FakeCoordinatesManager.offsetX = offsetX.get();
        FakeCoordinatesManager.offsetZ = offsetZ.get();
        FakeCoordinatesManager.jitterRadius = jitter.get();

        PlayerEntity player = mc.player;
        FakeCoordinatesManager.updateFakeCoords(player);
    }
}
