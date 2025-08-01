package io.github.pyke.timer.command;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.IntegerArgument;
import io.github.pyke.timer.Timer;
import io.github.pyke.timer.TimerManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.time.Duration;
import java.util.Objects;

public class TimerSubtractCommand extends CommandAPICommand {
    public TimerSubtractCommand() {
        super("subtract");
        withPermission("timer.command.subtract");
        withArguments(new IntegerArgument("hour"), new IntegerArgument("minute"), new IntegerArgument("second"));
        executesPlayer((player, args) -> {
            int h = (int) Objects.requireNonNullElse(args.get("hour"), 0);
            int m = (int) Objects.requireNonNullElse(args.get("minute"), 0);
            int s = (int) Objects.requireNonNullElse(args.get("second"), 0);
            Duration duration = Duration.ofHours(h).plusMinutes(m).plusSeconds(s);

            TimerManager.getInstance().subtractTime(duration);
            player.sendMessage(Timer.SYSTEM_PREFIX.append(Component.text(String.format("타이머의 시간이 감소되었습니다. (%d시간 %d분 %d초)", h, m, s), NamedTextColor.WHITE)));
        });
    }
}
