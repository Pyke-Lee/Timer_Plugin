package io.github.pyke.timer.listener;

import io.github.pyke.timer.TimerManager;
import io.github.pyke.timer.core.TimerState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (TimerManager.getInstance().getState() == TimerState.RUNNING || TimerManager.getInstance().getState() == TimerState.PAUSED) {
            Player player = event.getPlayer();
            TimerManager.getInstance().addPlayer(player);
            TimerManager.getInstance().updateDisplay();
        }
    }
}
