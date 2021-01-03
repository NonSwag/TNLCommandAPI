package net.nonswag.tnl.api.command;


import net.nonswag.tnl.api.command.executor.CommandSender;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class Command {

    @Nonnull private final String command;
    @Nullable private final String[] aliases;

    public Command(@Nonnull String command, @Nullable String... aliases) {
        this.command = command;
        this.aliases = aliases;
    }

    public abstract void execute(@Nonnull CommandSender<?> sender, @Nonnull String command, @Nullable String[] args);

    @Nonnull
    public String getCommand() {
        return command;
    }

    @Nullable
    public String[] getAliases() {
        return aliases;
    }
}
