package io.github.pyke.timer.command;

import dev.jorel.commandapi.CommandAPICommand;
import io.github.pyke.timer.Timer;
import io.github.pyke.timer.TimerManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class TimerStartCommand extends CommandAPICommand {
    public TimerStartCommand() {
        super("start");
        withPermission("timer.command.start");
        executesPlayer((player, args) -> {
            TimerManager.getInstance().start();
            player.sendMessage(Timer.SYSTEM_PREFIX.append(Component.text("타이머가 시작되었습니다.", NamedTextColor.WHITE)));
        });
    }
}
