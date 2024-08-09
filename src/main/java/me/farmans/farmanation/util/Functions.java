package me.farmans.farmanation.util;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.farmans.farmanation.event.ServerTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.Arrays;

public class Functions {
    public static String checkGame() {
        Scoreboard scoreboard = Minecraft.getMinecraft().theWorld.getScoreboard();
        if (scoreboard.getObjectiveInDisplaySlot(1) instanceof ScoreObjective) {
            String name = scoreboard.getObjectiveInDisplaySlot(1).getDisplayName();
            return EnumChatFormatting.getTextWithoutFormattingCodes(name).toLowerCase();
        } return "none";
    }

    public static void sendMessage(String message, Boolean specialFormat) {
        String specialText = "";
        if (specialFormat == true) {specialText = ChatFormatting.DARK_AQUA + "Farmanation > " + ChatFormatting.RESET;}
        if (Minecraft.getMinecraft().thePlayer == null) return;
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(specialText + message));
    } public static void sendMessage(String message) {
        sendMessage(message, true);
    }

    public static String unformatMessage(String message) {
        return EnumChatFormatting.getTextWithoutFormattingCodes(message);
    }

    public static void repeatTask(String task, int seconds, boolean countdown) {
        int finalSeconds = 0;
        if (countdown) {
            finalSeconds = seconds;
            seconds = 0;
        }
        ServerTickEvent.scheduler.put(task, Arrays.asList(seconds, finalSeconds, countdown));
    } public static void repeatTask(String task, int seconds) { repeatTask(task, seconds, false); }
    public static int getTaskTime(String task) {
        if (ServerTickEvent.scheduler.containsKey(task)) return (int)ServerTickEvent.scheduler.get(task).get(1);
        return -1;
    }
}
