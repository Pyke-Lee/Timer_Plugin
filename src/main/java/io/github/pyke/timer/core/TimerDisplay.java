package io.github.pyke.timer.core;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.time.Duration;

public class TimerDisplay {
    private final BossBar bossBar = Bukkit.createBossBar("", BarColor.YELLOW, BarStyle.SOLID);

    public void showToAll() {
        for (Player player : Bukkit.getOnlinePlayers()) { bossBar.addPlayer(player); }
        bossBar.setVisible(true);
    }

    public void hide() {
        bossBar.setVisible(false);
        bossBar.removeAll();
    }

    public void update(long remainingMillis, long initialMillis) {
        Duration dura = Duration.ofMillis(remainingMillis);
        String title = String.format("%02d시간 %02d분 %02d초", dura.toHoursPart(), dura.toMinutesPart(), dura.toSecondsPart());
        bossBar.setTitle(title);
        double progress = remainingMillis < initialMillis ? remainingMillis / (double) initialMillis : 1.0;
        bossBar.setProgress(Math.max(0.0, progress));
    }

    public void addPlayer(Player player) {
        bossBar.addPlayer(player);
    }
}
