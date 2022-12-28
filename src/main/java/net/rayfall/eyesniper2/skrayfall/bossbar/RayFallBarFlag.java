package net.rayfall.eyesniper2.skrayfall.bossbar;

import net.kyori.adventure.bossbar.BossBar;

public enum RayFallBarFlag {

    CREATE_FOG(BossBar.Flag.CREATE_WORLD_FOG), DARKEN_SKY(BossBar.Flag.DARKEN_SCREEN), PLAY_BOSS_MUSIC(
            BossBar.Flag.PLAY_BOSS_MUSIC);

    private BossBar.Flag key;

    RayFallBarFlag(BossBar.Flag flag) {
        this.key = flag;
    }

    public BossBar.Flag getKey() {
        return this.key;
    }

}
