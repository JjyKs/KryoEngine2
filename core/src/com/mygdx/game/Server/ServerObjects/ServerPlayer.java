package com.mygdx.game.Server.ServerObjects;

import com.mygdx.game.Server.PlayerConnection;
import com.mygdx.game.Server.PositionServer;
import com.mygdx.game.Shared.Network;

/**
 *
 * @author jjyks
 */
public class ServerPlayer {

    public float x, y;
    public PlayerConnection connection;

    public ServerPlayer(float x, float y, PlayerConnection connection) {
        this.x = x;
        this.y = y;
        this.connection = connection;
    }
    
    public void update(){
        this.x = this.x + 1;
        this.y = this.y + 1;
    }
    
    public void addToCollections(){
        PositionServer.playersOnArray.add(this);
    }
    
        public Network.NetworkPlayer networkVersion(){
        Network.NetworkPlayer nwPlayer = new Network.NetworkPlayer();
        
        nwPlayer.x = x;
        nwPlayer.y = y;
        
        return nwPlayer;
    }
}
