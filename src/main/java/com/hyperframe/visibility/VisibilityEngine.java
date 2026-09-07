package com.hyperframe.visibility;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Frustum;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;

public final class VisibilityEngine {

    private VisibilityEngine() {
    }

    public static boolean shouldRenderEntity(
            Entity entity,
            Frustum frustum,
            double x,
            double y,
            double z
    ) {
        if (!VisibilityConfig.ENABLED) {
            return true;
        }

        if (!VisibilityConfig.ENTITY_CULLING) {
            return true;
        }

        MinecraftClient client = MinecraftClient.getInstance();

        /*
         * Никогда не скрываем игрока.
         */
        if (entity == client.player) {
            return true;
        }

        VisibilityStats.entityCheck();

        Box box = entity.getBoundingBox();

        /*
         * Перемещаем bounding box относительно
         * координат, переданных vanilla renderer.
         */
        Box renderBox = box.offset(
                x - entity.getX(),
                y - entity.getY(),
                z - entity.getZ()
        );

        /*
         * Основной Frustum Culling.
         *
         * Если bounding box полностью вне
         * камеры — entity вообще не должна
         * проходить дальше в rendering pipeline.
         */
        if (!frustum.isVisible(renderBox)) {
            VisibilityStats.entityCulled();
            return false;
        }

        return true;
    }

    public static void clearCache() {
        // Cache подключим следующим этапом.
    }
}
