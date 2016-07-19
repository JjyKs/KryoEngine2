package com.mygdx.game.Client;

import com.mygdx.game.Shared.Network.NetworkPlayer;

public class Player {

    public float x, y;
    
    public NetworkPlayer networkVersion(){
        NetworkPlayer nwPlayer = new NetworkPlayer();
        
        nwPlayer.x = x;
        nwPlayer.y = y;
        
        return nwPlayer;
    }
}
