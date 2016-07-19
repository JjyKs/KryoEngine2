package com.mygdx.game.Server;

import com.mygdx.game.Server.ServerObjects.ServerPlayer;
import com.mygdx.game.Shared.Network.NearbyPlayers;
import com.mygdx.game.Shared.Network.NetworkPlayer;
import static java.lang.Thread.sleep;

/**
 *
 * @author jjyks
 */
public class ServerLoop {

    public ServerLoop() {
        while (true) {
            for (ServerPlayer p : PositionServer.playersOnArray) {
                long startTime = System.currentTimeMillis();
                p.update();

                NearbyPlayers nearbyPlayers = new NearbyPlayers();
                nearbyPlayers.players = new NetworkPlayer[PositionServer.playersOnArray.size()];
                int i = 0;
                for (ServerPlayer player : PositionServer.playersOnArray) {
                    nearbyPlayers.players[i] = player.networkVersion();
                    i++;
                }
                Packet packet = new Packet(p.connection, nearbyPlayers);
                PositionServer.packetsWaitingOut.add(packet);
                System.out.println("asd");
                try {
                    sleep(Math.max(0, 100 - System.currentTimeMillis() - startTime));
                } catch (InterruptedException ex) {
                }
            }
        }
    }
}
