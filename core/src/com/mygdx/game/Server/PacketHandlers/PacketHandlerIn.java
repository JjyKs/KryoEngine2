package com.mygdx.game.Server.PacketHandlers;

import com.mygdx.game.Server.PacketHandlers.MainHandlers.LoginHandler;
import com.mygdx.game.Shared.Network.*;
import com.mygdx.game.Server.Packet;
import static java.lang.Thread.sleep;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PacketHandlerIn implements Runnable {

    private Thread t;
    ConcurrentLinkedQueue<Packet> packetsWaiting;

    public PacketHandlerIn(ConcurrentLinkedQueue packetsWaiting) {
        this.packetsWaiting = packetsWaiting;
    }

    @Override
    public void run() {
        while (true) {
            Packet packet = packetsWaiting.poll();
            if (packet == null) {
                try {
                    sleep(1);
                } catch (InterruptedException ex) {
                }
            } else {
                if (packet.object instanceof Login) {
                    LoginHandler.handlePacket(packet);
                }
            }
        }
    }

    public void start() {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }
}
