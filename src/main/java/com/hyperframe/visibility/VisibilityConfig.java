package com.hyperframe.visibility;

public final class VisibilityConfig {

    private VisibilityConfig() {
    }

    public static boolean ENABLED = true;

    public static boolean ENTITY_CULLING = true;
    public static boolean BLOCK_ENTITY_CULLING = true;
    public static boolean PARTICLE_CULLING = true;

    /*
     * Если объект был виден недавно,
     * повторную дорогую проверку можно пропустить.
     */
    public static boolean USE_VISIBILITY_CACHE = true;

    /*
     * Количество тиков, в течение которых
     * результат проверки считается свежим.
     */
    public static int CACHE_TICKS = 2;
}
