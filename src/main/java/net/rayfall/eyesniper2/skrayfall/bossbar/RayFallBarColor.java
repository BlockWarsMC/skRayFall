package net.rayfall.eyesniper2.skrayfall.bossbar;


import net.kyori.adventure.bossbar.BossBar;

public enum RayFallBarColor {
    GREEN(BossBar.Color.GREEN), PINK(BossBar.Color.PINK), PURPLE(BossBar.Color.PURPLE), WHITE(
            BossBar.Color.WHITE), YELLOW(BossBar.Color.YELLOW), BLUE(BossBar.Color.BLUE), RED(BossBar.Color.RED);

    private BossBar.Color key;

    RayFallBarColor(BossBar.Color color) {
        this.key = color;
    }

    public BossBar.Color getKey() {
        return this.key;
    }
}
