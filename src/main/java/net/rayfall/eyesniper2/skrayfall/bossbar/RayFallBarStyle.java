package net.rayfall.eyesniper2.skrayfall.bossbar;

import net.kyori.adventure.bossbar.BossBar;

public enum RayFallBarStyle {
    SEGMENTED_10(BossBar.Overlay.NOTCHED_10), SEGMENTED_12(BossBar.Overlay.NOTCHED_12), SEGMENTED_20(
            BossBar.Overlay.NOTCHED_20), SEGMENTED_6(BossBar.Overlay.NOTCHED_6), SOLID(BossBar.Overlay.PROGRESS);

    private BossBar.Overlay key;

    RayFallBarStyle(BossBar.Overlay style) {
        this.key = style;
    }

    public BossBar.Overlay getKey() {
        return this.key;
    }

}
