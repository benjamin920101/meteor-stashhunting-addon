package com.stash.hunt.mixin;

import com.stash.hunt.MagicMix;
import net.minecraft.client.gui.FontRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xaeroplus.module.impl.Drawing;

@Mixin(Drawing.class)
public class XaeroCoordsMixin {

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;drawString(Ljava/lang/String;III)I"), remap = false)
    private int replaceCoordString(FontRenderer fr, String text, int x, int y, int color) {
        if (MagicMix.coordinatesIsActive()) {
            String fake = String.format("X: %d  Z: %d", (int) MagicMix.getX(), (int) MagicMix.getZ());
            return fr.drawString(fake, x, y, color);
        }
        return fr.drawString(text, x, y, color);
    }
}
