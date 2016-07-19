package com.mygdx.game.Server.PacketHandlers.MainHandlers;

import com.mygdx.game.Shared.Network.Login;
import com.mygdx.game.Server.Packet;
import com.mygdx.game.Server.ServerObjects.ServerPlayer;

/**
 *
 * @author jjyks
 */
public class LoginHandler {
    public static void handlePacket(Packet packet){
        Login login = (Login) packet.object;
        ServerPlayer newPlayer = new ServerPlayer(0, 0, packet.connection);
        packet.connection.player = newPlayer;
        newPlayer.addToCollections();
    }
}
