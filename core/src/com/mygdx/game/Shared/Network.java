package com.mygdx.game.Shared;

import com.esotericsoftware.kryo.Kryo;
import com.mygdx.Network.KryoNetBase.esotericsoftware.kryonet.EndPoint;

// This class is a convenient place to keep things common to both the client and server.
public class Network {

    static public final int port = 54555;

    // This registers objects that are going to be sent over the network.
    static public void register(EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();
        kryo.register(Login.class);
        kryo.register(NetworkPlayer.class);
        kryo.register(NearbyPlayers.class);
    }

    static public class Login {

    }

    static public class NetworkPlayer {

        public float x, y;
    }
    
    static public class NearbyPlayers{
        public NetworkPlayer[] players; 
    }

}
