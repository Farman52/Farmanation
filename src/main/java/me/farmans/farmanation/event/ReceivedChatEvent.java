package me.farmans.farmanation.event;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.farmans.farmanation.config.FarmanationConfig;
import me.farmans.farmanation.util.Functions;
import me.farmans.farmanation.util.GTBWordlist;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class ReceivedChatEvent {
    public String lastActionBar = "";
    public String[] wordlist = GTBWordlist.getWordList();


    @SubscribeEvent
    public void onReceivedChat(ClientChatReceivedEvent event) {
        if (Functions.checkGame().equals("guess the build")) {
            if (FarmanationConfig.gtbSolver) {//GTB SOLVER
                if (event.type == 2 /*ACTION BAR*/) {
                    String message = Functions.unformatMessage(event.message.getUnformattedText());
                    if (!lastActionBar.equals(message)) {
                        lastActionBar = message; //ULOZI LAST ACTION BAR ABY CHECKLO V PODMINCE NAD ABY SE TO NEOPAKOVALO FURT DOKOLA
                        if (message.startsWith("The theme is")) {
                            if (wordlist.length == 0) {
                                Functions.sendMessage("Wordlist hasn't loaded"); //SNAD TENTO ERROR NIKDY NESKOCI
                                return;
                            }
                            String hint = message.replace("The theme is ", "").toLowerCase();
                            System.out.println(hint);
                            List possibleWords = new ArrayList<String>(); //LOWKEY NECHAPU JAVU
                            for (int i = 0; i < wordlist.length; i++) {
                                String value = wordlist[i].trim();
                                String word = value.toLowerCase();
                                if (word.length() != hint.length()) {
                                    continue;
                                }
                                int j;
                                for (j = 0; j < hint.length(); j++) {
                                    if (hint.charAt(j) == '_') continue;
                                    if (hint.charAt(j) != word.charAt(j)) break;
                                }
                                if (j == hint.length()) possibleWords.add(value);
                            }
                            System.out.println(possibleWords);
                            Functions.sendMessage(ChatFormatting.DARK_GREEN + "Potential answers: " + ChatFormatting.GREEN + String.join(", ", possibleWords));
                        }
                    }
                }
            }
        }
        if (Functions.checkGame().equals("arena brawl")) {
            if (FarmanationConfig.healingPowerup || FarmanationConfig.damagePowerup) {
                if (event.type == 1) {
                    String message = Functions.unformatMessage(event.message.getUnformattedText());
                    if (message.toLowerCase().endsWith("powerup has spawned!")) {
                        String powerup = message.split(" ")[1].toLowerCase();
                        if (FarmanationConfig.healingPowerup && powerup.equals("healing")) {
                            Minecraft.getMinecraft().ingameGUI.displayTitle(ChatFormatting.GREEN + powerup.toUpperCase() + " POWERUP", "", 0, 50, 10);
                            Minecraft.getMinecraft().thePlayer.playSound("random.orb", 50, 0.5f);
                        } else if (FarmanationConfig.damagePowerup && powerup.equals("damage")) {
                            Minecraft.getMinecraft().ingameGUI.displayTitle(ChatFormatting.RED + powerup.toUpperCase() + " POWERUP", "", 0, 50, 10);
                            Minecraft.getMinecraft().thePlayer.playSound("random.orb", 50, 0.5f);
                        }
                    } else if (message.toLowerCase().endsWith("powerup!") && message.contains("activated the") ) {
                        String powerup = message.split(" ")[3].toLowerCase();
                        String player = message.split(" ")[0].toLowerCase();
                        if (powerup.equals("healing") && FarmanationConfig.healingPowerup) {
                            Minecraft.getMinecraft().ingameGUI.displayTitle(ChatFormatting.YELLOW + player + ChatFormatting.RESET + " took the " + ChatFormatting.GREEN + powerup, "", 0, 50, 10);
                        } else if (powerup.equals("damage") && FarmanationConfig.damagePowerup) {
                            Minecraft.getMinecraft().ingameGUI.displayTitle(ChatFormatting.YELLOW + player + ChatFormatting.RESET + " took the " + ChatFormatting.RED + powerup, "", 0, 50, 10);
                            Functions.repeatTask("arenaDamage", 12, true);

                        }
                    }
                }
            }
        }
    }
}
