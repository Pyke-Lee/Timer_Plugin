package io.github.pyke.timer.command;

import dev.jorel.commandapi.CommandAPICommand;
import io.github.pyke.timer.Timer;
import io.github.pyke.timer.TimerManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class TimerPauseCommand extends CommandAPICommand {
    public TimerPauseCommand() {
        super("pause");
        withPermission("timer.command.pause");
        executesPlayer((player, args) -> {
            TimerManager.getInstance().pause();
            player.sendMessage(Timer.SYSTEM_PREFIX.append(Component.text("타이머가 일시정지 되었습니다.", NamedTextColor.WHITE)));
        });
    }
}
