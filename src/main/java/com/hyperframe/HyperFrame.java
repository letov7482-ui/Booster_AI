package com.hyperframe;

import com.hyperframe.command.HyperFrameCommand;
import com.hyperframe.core.HyperBoost;
import com.hyperframe.core.PerformanceMonitor;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class HyperFrame implements ClientModInitializer {

    public static final String MOD_ID = "hyperframe";

    public static PerformanceMonitor PERFORMANCE;
    public static HyperBoost BOOST;

    @Override
    public void onInitializeClient() {
        PERFORMANCE = new PerformanceMonitor();
        BOOST = new HyperBoost(PERFORMANCE);

        HyperFrameCommand.register();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            PERFORMANCE.tick(client);
            BOOST.tick(client);
        });

        System.out.println("[HyperFrame] Performance Engine initialized.");
    }
}
