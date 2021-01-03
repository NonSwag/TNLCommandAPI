package net.nonswag.tnl.api.command.exceptions;

import javax.annotation.Nonnull;

public class CommandRegisterException extends Throwable {

    public CommandRegisterException() {
        super("An error has occurred while registering command");
    }

    public CommandRegisterException(@Nonnull String var1) {
        super(var1);
    }

}
