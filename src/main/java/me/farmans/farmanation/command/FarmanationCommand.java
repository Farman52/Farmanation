package me.farmans.farmanation.command;

import me.farmans.farmanation.FarmanationMod;
import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;

/**
 * An example command implementing the Command api of OneConfig.
 * Registered in ExampleMod.java with `CommandManager.INSTANCE.registerCommand(new ExampleCommand());`
 *
 * @see Command
 * @see Main
 * @see FarmanationMod
 */
@Command(value = FarmanationMod.MODID, description = "Access the " + FarmanationMod.NAME + " GUI.", aliases = {"farman"})
public class FarmanationCommand {
    @Main
    private void handle() {
        FarmanationMod.INSTANCE.config.openGui();
    }
}