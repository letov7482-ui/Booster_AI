package com.hyperframe.core;

import net.minecraft.client.MinecraftClient;

public class PerformanceMonitor {

    private int fps;
    private double frameTime;
    private long lastTime;
    private int frames;

    public void tick(MinecraftClient client) {
        long now = System.nanoTime();

        if (lastTime != 0) {
            frameTime = (now - lastTime) / 1_000_000.0;
        }

        lastTime = now;

        frames++;

        if (frames >= 20) {
            fps = client.getCurrentFps();
            frames = 0;
        }
    }

    public int getFps() {
        return fps;
    }

    public double getFrameTime() {
        return frameTime;
    }

    public double getMemoryUsageMB() {
        Runtime runtime = Runtime.getRuntime();

        long used = runtime.totalMemory() - runtime.freeMemory();

        return used / 1024.0 / 1024.0;
    }

    public double getMemoryMaxMB() {
        return Runtime.getRuntime().maxMemory() / 1024.0 / 1024.0;
    }

    public String getPerformanceLevel() {
        if (fps >= 200) {
            return "EXCELLENT";
        }

        if (fps >= 120) {
            return "HIGH";
        }

        if (fps >= 60) {
            return "GOOD";
        }

        if (fps >= 30) {
            return "LOW";
        }

        return "CRITICAL";
    }
}
