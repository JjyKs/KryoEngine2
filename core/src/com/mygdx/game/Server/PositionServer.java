package com.mygdx.game.Server;

import java.io.IOException;

import com.mygdx.Network.KryoNetBase.esotericsoftware.kryonet.Connection;
import com.mygdx.Network.KryoNetBase.esotericsoftware.kryonet.Listener;
import com.mygdx.Network.KryoNetBase.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;
import com.mygdx.game.Shared.Network;
import com.mygdx.game.Server.PacketHandlers.PacketHandlerIn;
import com.mygdx.game.Server.PacketHandlers.PacketHandlerOut;
import com.mygdx.game.Server.ServerObjects.ServerPlayer;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class PositionServer {

    public static Server server;
    public static CopyOnWriteArrayList<ServerPlayer> playersOnArray;
    public static ConcurrentLinkedQueue<Packet> packetsWaitingOut;

    public ConcurrentLinkedQueue<Packet> packetsWaitingIn;

    PacketHandlerIn[] packetHandlersIn = new PacketHandlerIn[8];
    PacketHandlerOut[] packetHandlersOut = new PacketHandlerOut[8];

    public PositionServer() throws IOException {
        //Initiate all static variables here
        playersOnArray = new CopyOnWriteArrayList();
        packetsWaitingOut = new ConcurrentLinkedQueue();
        server = new Server() {
            @Override
            protected Connection newConnection() {
                // By providing our own connection implementation, we can store per
                // connection state without a connection ID to state look up.
                return new PlayerConnection();
            }
        };

        //Initiate non-static variables here
        packetsWaitingIn = new ConcurrentLinkedQueue();

        //Initiate threads here
        for (int i = 0; i < packetHandlersIn.length; i++) {
            packetHandlersIn[i] = new PacketHandlerIn(packetsWaitingIn);
            packetHandlersIn[i].start();
        }

        for (int i = 0; i < packetHandlersOut.length; i++) {
            packetHandlersOut[i] = new PacketHandlerOut();
            packetHandlersOut[i].start();

        }

        Network.register(server);

        server.addListener(new Listener() {
            @Override
            public void received(Connection c, Object object) {
                packetsWaitingIn.add(new Packet(c, object));
            }

            @Override
            public void disconnected(Connection c) {

            }
        });
        server.bind(Network.port);
        server.start();
        new ServerLoop();
    }

    public static void main(String[] args) throws IOException {
        Log.set(Log.LEVEL_DEBUG);
        new PositionServer();
    }
}
