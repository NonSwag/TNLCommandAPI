package net.nonswag.tnl.api.command.executor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.logging.Level;

public interface Executor {
    void sendMessage(@Nonnull String message);

    void sendMessage(@Nonnull String message, @Nonnull Level level);

    @Nullable
    String getName();
}
