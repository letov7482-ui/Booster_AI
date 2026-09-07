package com.hyperframe.visibility;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

public final class VisibilityInitializer {

    private VisibilityInitializer() {
    }

    public static void initialize() {

        ClientTickEvents.END_CLIENT_TICK.register(
                client -> {

                    if (client.world == null) {
                        VisibilityEngine.clearCache();
                    }
                }
        );
    }
}
