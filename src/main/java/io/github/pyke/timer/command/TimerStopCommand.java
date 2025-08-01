package io.github.pyke.timer.command;

import dev.jorel.commandapi.CommandAPICommand;
import io.github.pyke.timer.Timer;
import io.github.pyke.timer.TimerManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class TimerStopCommand extends CommandAPICommand {
    public TimerStopCommand() {
        super("stop");
        withPermission("timer.command.stop");
        executesPlayer((player, args) -> {
            TimerManager.getInstance().stop();
            player.sendMessage(Timer.SYSTEM_PREFIX.append(Component.text("타이머가 종료되었습니다.", NamedTextColor.WHITE)));
        });
    }
}
