package me.farmans.farmanation.hud;

import cc.polyfrost.oneconfig.hud.SingleTextHud;
import me.farmans.farmanation.util.Functions;

public class ABNotifiers extends SingleTextHud {
    public ABNotifiers() {
        super("Damage Powerup", true);
    }

    @Override
    public String getText(boolean example) {
        if (example) return "12s";
        return Functions.getTaskTime("arenaDamage") + "s";
    }
}
