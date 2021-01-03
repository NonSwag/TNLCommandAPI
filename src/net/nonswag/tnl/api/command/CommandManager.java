package net.nonswag.tnl.api.command;

import net.nonswag.tnl.api.command.exceptions.CommandRegisterException;
import net.nonswag.tnl.api.command.executor.CommandSender;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class CommandManager {

    @Nonnull private static final List<Command> registeredCommands = new ArrayList<>();
    @Nonnull private static Character commandPrefix = '/';

    @Nonnull
    public static Character getCommandPrefix() {
        return commandPrefix;
    }

    public static void setCommandPrefix(@Nonnull Character commandPrefix) {
        CommandManager.commandPrefix = commandPrefix;
    }

    public static void registerCommand(Command command) {
        if (command.getCommand().contains(" ")) {
            new CommandRegisterException("Illegal Command structure for '" + getCommandPrefix() + command.getCommand() + "'").printStackTrace();
        } else {
            if (getRegisteredCommands().contains(command)) {
                new CommandRegisterException("The Command '" + getCommandPrefix() + command.getCommand().toLowerCase() + "' is already registered").printStackTrace();
            } else {
                getRegisteredCommands().add(command);
                System.out.println("Registered command '" + getCommandPrefix() + command.getCommand().toLowerCase() + "'");
            }
        }
    }

    public static void unregisterCommand(String command) {
        getRegisteredCommands().removeIf(commandListener -> commandListener.getCommand().equalsIgnoreCase(command));
    }

    @Nonnull
    public static List<Command> getRegisteredCommands() {
        return registeredCommands;
    }

    public static boolean onCommand(@Nonnull String message, @Nonnull CommandSender<?> sender) {
        if (!message.isEmpty()) {
            if (message.charAt(0) == CommandManager.getCommandPrefix()) {
                String[] split = message.replaceFirst(CommandManager.getCommandPrefix().toString(), "").split(" ");
                for (Command command : CommandManager.getRegisteredCommands()) {
                    if (command.getCommand().equalsIgnoreCase(split[0])) {
                        command.execute(sender, command.getCommand(), Arrays.asList(split).subList(1, split.length).toArray(new String[]{}));
                        return true;
                    } else {
                        if (command.getAliases() != null) {
                            for (String alias : command.getAliases()) {
                                if (alias.equalsIgnoreCase(split[0])) {
                                    command.execute(sender, alias, Arrays.asList(split).subList(1, split.length).toArray(new String[]{}));
                                    return true;
                                }
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        return false;
    }
}
