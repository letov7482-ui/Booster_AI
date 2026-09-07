package com.hyperframe.visibility;

public final class VisibilityStats {

    private static long entityChecks;
    private static long entityCulled;

    private static long blockEntityChecks;
    private static long blockEntitiesCulled;

    private VisibilityStats() {
    }

    public static void resetFrame() {
        entityChecks = 0;
        entityCulled = 0;

        blockEntityChecks = 0;
        blockEntitiesCulled = 0;
    }

    public static void entityCheck() {
        entityChecks++;
    }

    public static void entityCulled() {
        entityCulled++;
    }

    public static void blockEntityCheck() {
        blockEntityChecks++;
    }

    public static void blockEntityCulled() {
        blockEntitiesCulled++;
    }

    public static long getEntityChecks() {
        return entityChecks;
    }

    public static long getEntityCulled() {
        return entityCulled;
    }

    public static long getBlockEntityChecks() {
        return blockEntityChecks;
    }

    public static long getBlockEntitiesCulled() {
        return blockEntitiesCulled;
    }
}
