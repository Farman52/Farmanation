package me.farmans.farmanation.config;

import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.Header;
import cc.polyfrost.oneconfig.config.annotations.Switch;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import cc.polyfrost.oneconfig.config.data.OptionSize;
import me.farmans.farmanation.FarmanationMod;

/**
 * The main Config entrypoint that extends the Config type and inits the config options.
 * See <a href="https://docs.polyfrost.cc/oneconfig/config/adding-options">this link</a> for more config Options
 */
public class FarmanationConfig extends Config {
    @Header(
            text = "Build Battle",
            size = OptionSize.DUAL
    )
    public static boolean buildbattle;

    @Switch(
            name = "Guess the Build Solver",
            description = "Tries to find potential answers for Guess the Build.",
            size = OptionSize.DUAL // Optional
    )
    public static boolean gtbSolver = false; // The default value for the boolean Switch.

    @Header(
            text = "Arena Brawl",
            size = OptionSize.DUAL
    )
    public static boolean arenabrawl;

    @Switch(
            name = "Healing Powerup Notification",
            description = "Notifies you when the healing powerup is ready."
    )
    public static boolean healingPowerup = true;

    @Switch(
            name = "Damage Powerup Notification",
            description = "Notifies you when the damage powerup is ready."
    )
    public static boolean damagePowerup = true;


    public FarmanationConfig() {
        super(new Mod(FarmanationMod.NAME, ModType.UTIL_QOL), FarmanationMod.MODID + ".json");
        initialize();
    }
}

