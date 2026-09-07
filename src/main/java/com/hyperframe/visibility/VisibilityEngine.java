package com.hyperframe.visibility;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;

import java.util.Map;
import java.util.WeakHashMap;

public final class VisibilityEngine {

    private static final Map<Entity, CacheEntry> CACHE =
            new WeakHashMap<>();

    private VisibilityEngine() {
    }

    public static boolean shouldRenderEntity(
            MinecraftClient client,
            Entity entity,
            Frustum frustum
    ) {
        if (!VisibilityConfig.ENABLED) {
            return true;
        }

        if (!VisibilityConfig.ENTITY_CULLING) {
            return true;
        }

        VisibilityStats.entityCheck();

        /*
         * Игрока никогда не скрываем.
         */
        if (entity == client.player) {
            return true;
        }

        /*
         * Некоторые сущности могут иметь нестандартный
         * render distance. Не вмешиваемся в них.
         */
        EntityRenderer<?> renderer =
                client.getEntityRenderDispatcher()
                        .getRenderer(entity);

        if (renderer == null) {
            return true;
        }

        /*
         * Сначала обычная frustum-проверка.
         */
        Box box = entity.getBoundingBox();

        if (!frustum.isVisible(box)) {
            VisibilityStats.entityCulled();

            updateCache(entity, false);

            return false;
        }

        /*
         * Если объект находится в поле зрения,
         * сохраняем результат.
         */
        updateCache(entity, true);

        return true;
    }

    private static void updateCache(
            Entity entity,
            boolean visible
    ) {
        CACHE.put(
                entity,
                new CacheEntry(
                        visible,
                        System.currentTimeMillis()
                )
        );
    }

    public static void clearCache() {
        CACHE.clear();
    }

    private record CacheEntry(
            boolean visible,
            long timestamp
    ) {
    }
}
