package me.farmans.farmanation.event;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.*;

public class ServerTickEvent {
    public static Map<String, List> scheduler = new HashMap<>(); //FROM FUNCTIONS - TASK , (CUSTOM SECONDS , FINAL SECONDS, COUNTDOWNBOOL)
    private Map<String, Integer> runningTime = new HashMap<>(); // TASK , TICKS

    @SubscribeEvent
    public void onTick(TickEvent.ServerTickEvent event) {//CALCULATE TIME BASED ON INGAME TICKS
        if (scheduler.isEmpty() || event.phase == TickEvent.Phase.END) return;
        List toBeRemoved = new ArrayList<>();
        for (String task : scheduler.keySet()) {
            boolean countdown = (boolean) scheduler.get(task).get(2);
            int customSeconds = (int)scheduler.get(task).get(0);
            if (!runningTime.containsKey(task)) runningTime.put(task, 1);
            else {
                int ticks = runningTime.get(task);
                int finalSeconds = (int) scheduler.get(task).get(1);
                if (finalSeconds == customSeconds) {
                    toBeRemoved.add(task);
                } else {
                    if (ticks == 20) {
                        ticks = 0;
                        if (countdown) finalSeconds--;
                        else finalSeconds++;
                    }
                    runningTime.put(task, ticks + 1);
                    scheduler.put(task, Arrays.asList(customSeconds, finalSeconds, countdown));
                }
            }//cely tohle funguje s laskou a péčí snad pls
        }
        for (int i = 0; i<toBeRemoved.size(); i++) {
            scheduler.remove(toBeRemoved.get(i));
            runningTime.remove(toBeRemoved.get(i));
        }
        toBeRemoved.clear();
    }
}
