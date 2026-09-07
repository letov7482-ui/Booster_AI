package com.hyperframe.mixin;

import com.hyperframe.visibility.VisibilityStats;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {

    @Inject(
            method = "fillEntityRenderStates",
            at = @At("HEAD")
    )
    private void hyperframe$beginEntityVisibility(
            net.minecraft.client.render.Camera camera,
            Frustum frustum,
            net.minecraft.client.render.RenderTickCounter tickCounter,
            net.minecraft.client.render.state.WorldRenderState renderStates,
            CallbackInfo ci
    ) {
        VisibilityStats.resetFrame();
    }
}
