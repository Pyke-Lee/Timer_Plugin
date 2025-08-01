package io.github.pyke.timer;

import io.github.pyke.timer.core.TimerContext;
import io.github.pyke.timer.core.TimerDisplay;
import io.github.pyke.timer.core.TimerState;
import io.github.pyke.timer.listener.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.time.Duration;

public class TimerManager {
    private static TimerManager instance;

    private final Plugin plugin;
    private final TimerContext context = new TimerContext();
    private final TimerDisplay display = new TimerDisplay();
    private BukkitTask task;

    private TimerManager(Plugin plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), plugin);
    }

    public static void Initialize(Plugin plugin) {
        if (null == instance) { instance = new TimerManager(plugin); }
    }

    public static TimerManager getInstance() {
        if (null == instance) { throw new IllegalStateException("Timer Manager has not been initialized!"); }

        return instance;
    }

    public void setTime(Duration duration) {
        context.setTime(duration);
        if (getState() != TimerState.RUNNING) { display.showToAll(); }
        display.update(duration.toMillis(), context.getInitialMillis());
    }

    public void addTime(Duration duration) {
        context.addTime(duration);
    }

    public void subtractTime(Duration duration) {
        context.subtractTime(duration);
    }

    public void start() {
        if (context.getState() != TimerState.SET && context.getState() != TimerState.PAUSED) { return; }

        context.start();
        display.showToAll();

        if (null != task) { task.cancel(); }
        task = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            long remaining = context.getRemainingMillis();
            display.update(remaining, context.getInitialMillis());

            if (context.isFinished()) { stop(); }
        }, 0L, 20L);
    }

    public void pause() {
        context.pause();
        if (null != task) { task.cancel(); }
    }

    public void stop() {
        context.stop();
        if (null != task) { task.cancel(); }
        display.hide();
    }

    public TimerState getState() {
        return context.getState();
    }

    public void addPlayer(Player player) {
        display.addPlayer(player);
    }

    public void updateDisplay() {
        display.update(context.getRemainingMillis(), context.getInitialMillis());
    }
}
