package net.nonswag.tnl.api.command.executor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.logging.Level;

public class CommandSender<E extends Executor> implements Executor {

    @Nonnull private final E executor;

    public CommandSender(@Nonnull E executor) {
        this.executor = executor;
    }

    @Nonnull
    public E getExecutor() {
        return executor;
    }

    @Override
    public void sendMessage(@Nonnull String message) {
        getExecutor().sendMessage(message);
    }

    @Override
    public void sendMessage(@Nonnull String message, @Nonnull Level level) {
        getExecutor().sendMessage(message, level);
    }

    @Nullable
    @Override
    public String getName() {
        return getExecutor().getName();
    }
}
