package io.github.pyke.timer.command;

import dev.jorel.commandapi.CommandAPICommand;

public class TimerCommand {
    public static void register() {
        new CommandAPICommand("timer")
            .withSubcommand(new TimerSetCommand())
            .withSubcommand(new TimerAddCommand())
            .withSubcommand(new TimerSubtractCommand())
            .withSubcommand(new TimerStartCommand())
            .withSubcommand(new TimerStopCommand())
            .withSubcommand(new TimerPauseCommand())
            .register();
    }
}
