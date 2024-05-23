package net.fabricmc.example;

public class mixinToggleHelper
{
    private static boolean isMixinEnabled = true;

    public static void setMixinEnabled(boolean enabled) {
        isMixinEnabled = enabled;
    }

    public static boolean isMixinEnabled() {
        return isMixinEnabled;
    }
}
