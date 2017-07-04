package com.kurotkin;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vitaly Kurotkin on 30.06.2017.
 */
public class TohnichiSTC2 {
    public static ArrayList<Fastener> req(){
        ArrayList<Fastener> fasteners = new ArrayList<Fastener>();
        ComPort comPort = new ComPort(Settings.bSizeSTC2);
        while(!comPort.getResult()){
            System.out.println(".");
        }

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.schedule(new Runnable() {
            @Override
            public void run() {
                String[] str = comPort.getFromSerial().split("\r\n");
                for (String s : str) {
                    Fastener newFast = new Fastener();
                    String[] st = s.split(",");
                    newFast.id = Integer.parseInt(st[1]);
                    newFast.torque = Double.parseDouble(st[2]);
                    newFast.tagName = "TohnichiCEM";
                    fasteners.add(newFast);
                }
            }
        }, 5, TimeUnit.SECONDS);

        while (!fasteners.isEmpty()) {}
        return fasteners;
    }
}
