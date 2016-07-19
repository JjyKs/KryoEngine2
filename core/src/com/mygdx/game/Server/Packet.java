package com.mygdx.game.Server;

import com.mygdx.Network.KryoNetBase.esotericsoftware.kryonet.Connection;

/**
 *
 * @author jjyks
 */
public class Packet {
    public PlayerConnection connection;
    public Object object;
    public boolean important;
    
    public Packet(Connection c, Object o, boolean important){
        connection = (PlayerConnection) c;
        object = o;
        this.important = important;
    }
    
        public Packet(Connection c, Object o){
            this(c, o, false);
        }

}
