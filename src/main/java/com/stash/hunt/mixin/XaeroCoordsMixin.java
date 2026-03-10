package com.stash.hunt.mixin;

import com.stash.hunt.MagicMix;
import net.minecraft.client.font.TextRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xaeroplus.module.impl.Drawing;

@Mixin(Drawing.class)
public class XaeroCoordsMixin {

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Ljava/lang/String;FFI)I"))
    private int replaceCoordString(TextRenderer fr, String text, float x, float y, int color) {
        if (MagicMix.coordinatesIsActive()) {
            String fake = String.format("X: %d  Z: %d", (int) MagicMix.getX(), (int) MagicMix.getZ());
            return fr.draw(fake, x, y, color);
        }
        return fr.draw(text, x, y, color);
    }
}
