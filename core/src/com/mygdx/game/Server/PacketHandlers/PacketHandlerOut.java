package com.mygdx.game.Server.PacketHandlers;

import com.mygdx.game.Server.Packet;
import com.mygdx.game.Server.PositionServer;
import static java.lang.Thread.sleep;

public class PacketHandlerOut implements Runnable {

    private Thread t;

    public PacketHandlerOut() {
    }

    @Override
    public void run() {
        while (true) {
            Packet packet = PositionServer.packetsWaitingOut.poll();
            if (packet == null) {
                try {
                    sleep(1);
                } catch (InterruptedException ex) {
                }
            } else {
                PositionServer.server.sendToUDP(packet.connection.getID(), packet.object);
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
