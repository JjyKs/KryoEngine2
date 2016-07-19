package com.mygdx.game.Client;

import java.io.IOException;

import com.mygdx.Network.KryoNetBase.esotericsoftware.kryonet.Client;
import com.mygdx.Network.KryoNetBase.esotericsoftware.kryonet.Connection;
import com.mygdx.Network.KryoNetBase.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import com.mygdx.Network.KryoNetBase.esotericsoftware.kryonet.Listener.ThreadedListener;
import com.mygdx.game.Shared.Network;
import com.mygdx.game.Shared.Network.Login;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PositionClient {

    Client client;
    String name;

    public PositionClient() {
        client = new Client();
        client.start();

        // For consistency, the classes to be sent over the network are
        // registered by the same method for both the client and server.
        Network.register(client);

        // ThreadedListener runs the listener methods on a different thread.
        client.addListener(new ThreadedListener(new Listener() {
            public void connected(Connection connection) {
            }

            public void received(Connection connection, Object object) {

            }

            public void disconnected(Connection connection) {
                System.exit(0);
            }
        }));

        try {
            client.connect(5000, "localhost", Network.port);
        } catch (IOException ex) {
            Logger.getLogger(PositionClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        login();
        
        while (true) {
            try {
                sleep(66);
            } catch (InterruptedException ex) {
                Logger.getLogger(PositionClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void login(){       
        client.sendTCP(new Login());
    }

    public static void main(String[] args) {
        Log.set(Log.LEVEL_DEBUG);
        new PositionClient();
    }
}
