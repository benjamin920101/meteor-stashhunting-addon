package com.stash.hunt.mixin;

import com.stash.hunt.MagicMix;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.text.OrderedText;
import org.joml.Matrix4f;
import net.minecraft.client.render.VertexConsumerProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xaeroplus.module.impl.Drawing;

@Mixin(Drawing.class)
public class XaeroCoordsMixin {

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Ljava/lang/String;FFIZLorg/joml/Matrix4f;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/client/font/TextRenderer$TextLayerType;II)I"))
    private int replaceCoordString(TextRenderer fr, String text, float x, float y, int color, boolean shadow, Matrix4f matrix, VertexConsumerProvider provider, TextRenderer.TextLayerType type, int light, int overlay) {
        if (MagicMix.coordinatesIsActive()) {
            String fake = String.format("X: %d  Z: %d", (int) MagicMix.getX(), (int) MagicMix.getZ());
            return fr.draw(fake, x, y, color, shadow, matrix, provider, type, light, overlay);
        }
        return fr.draw(text, x, y, color, shadow, matrix, provider, type, light, overlay);
    }

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/text/OrderedText;FFIZLorg/joml/Matrix4f;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/client/font/TextRenderer$TextLayerType;II)I"))
    private int replaceCoordStringOrdered(TextRenderer fr, OrderedText text, float x, float y, int color, boolean shadow, Matrix4f matrix, VertexConsumerProvider provider, TextRenderer.TextLayerType type, int light, int overlay) {
        if (MagicMix.coordinatesIsActive()) {
            String fake = String.format("X: %d  Z: %d", (int) MagicMix.getX(), (int) MagicMix.getZ());
            return fr.draw(fake, x, y, color, shadow, matrix, provider, type, light, overlay);
        }
        return fr.draw(text, x, y, color, shadow, matrix, provider, type, light, overlay);
    }
}
