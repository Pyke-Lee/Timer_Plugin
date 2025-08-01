package io.github.pyke.timer;

import io.github.pyke.timer.command.TimerCommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.plugin.java.JavaPlugin;

public class Timer extends JavaPlugin {
    public static final Component SYSTEM_PREFIX = Component.text("[SYSTEM] ").color(TextColor.color(0xffaa00));
    public static final Logger LOGGER = LogManager.getLogger("Timer");

    @Override
    public void onEnable() {
        TimerManager.Initialize(this);
        TimerCommand.register();

        LOGGER.info("Timer 플러그인이 활성화되었습니다!");
    }

    @Override
    public void onDisable() {
        LOGGER.info("Timer 플러그인이 비활성화되었습니다!");
    }
}
