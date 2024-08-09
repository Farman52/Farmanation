package me.farmans.farmanation;

import me.farmans.farmanation.command.FarmanationCommand;
import me.farmans.farmanation.command.TestCommand;
import me.farmans.farmanation.config.FarmanationConfig;
import cc.polyfrost.oneconfig.events.event.InitializationEvent;
import me.farmans.farmanation.event.ServerTickEvent;
import me.farmans.farmanation.event.ReceivedChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import cc.polyfrost.oneconfig.utils.commands.CommandManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/**
 * The entrypoint of the Example Mod that initializes it.
 *
 * @see Mod
 * @see InitializationEvent
 */
@Mod(modid = FarmanationMod.MODID, name = FarmanationMod.NAME, version = FarmanationMod.VERSION)
public class FarmanationMod {

    // Sets the variables from `gradle.properties`. See the `blossom` config in `build.gradle.kts`.
    public static final String MODID = "@ID@";
    public static final String NAME = "@NAME@";
    public static final String VERSION = "@VER@";
    @Mod.Instance(MODID)
    public static FarmanationMod INSTANCE; // Adds the instance of the mod, so we can access other variables.
    public static FarmanationConfig config;

    // Register the config and commands.
    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        config = new FarmanationConfig();
        CommandManager.INSTANCE.registerCommand(new FarmanationCommand());
        CommandManager.INSTANCE.registerCommand(new TestCommand());

        MinecraftForge.EVENT_BUS.register(new ReceivedChatEvent());
        MinecraftForge.EVENT_BUS.register(new ServerTickEvent());
    }
}
