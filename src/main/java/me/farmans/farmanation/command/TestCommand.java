package me.farmans.farmanation.command;

import cc.polyfrost.oneconfig.libs.universal.UChat;
import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;
import cc.polyfrost.oneconfig.utils.commands.annotations.SubCommand;
import me.farmans.farmanation.event.ServerTickEvent;
import me.farmans.farmanation.util.Functions;

@Command(value = "test", description = "For testing purposes.")
public class TestCommand {
    @Main
    private void main() {
        Functions.sendMessage("test");
    }

    @SubCommand()
    private void seconds(int sec) {
        Functions.repeatTask("arenaDamage", sec, true);

    }
    @SubCommand()
    private void check() {
        UChat.chat(ServerTickEvent.scheduler.get("arena").get(1));
        System.out.println(ServerTickEvent.scheduler.get("arena").get(1));
    }
}
