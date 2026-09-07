package com.hyperframe.core;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;

public class HyperBoost {

    private final PerformanceMonitor monitor;

    private boolean enabled;
    private boolean applied;

    public HyperBoost(PerformanceMonitor monitor) {
        this.monitor = monitor;
    }

    public void tick(MinecraftClient client) {
        if (!enabled || applied || client.player == null) {
            return;
        }

        apply(client);
        applied = true;
    }

    public void enable(MinecraftClient client) {
        enabled = true;
        applied = false;

        if (client != null) {
            apply(client);
            applied = true;
        }
    }

    public void disable() {
        enabled = false;
        applied = false;
    }

    private void apply(MinecraftClient client) {
        GameOptions options = client.options;

        /*
         * Базовый безопасный слой HyperBoost.
         *
         * Более глубокие оптимизации позже будут
         * выполняться через отдельные engines/mixins.
         */

        if (options.getViewDistance().getValue() > 12) {
            options.getViewDistance().setValue(12);
        }

        if (options.getSimulationDistance().getValue() > 8) {
            options.getSimulationDistance().setValue(8);
        }

        options.getEntityShadows().setValue(false);

        System.out.println(
                "[HyperFrame] HyperBoost applied. FPS: "
                        + monitor.getFps()
        );
    }

    public boolean isEnabled() {
        return enabled;
    }
}
