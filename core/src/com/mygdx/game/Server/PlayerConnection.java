/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Server;

import com.mygdx.Network.KryoNetBase.esotericsoftware.kryonet.Connection;
import com.mygdx.game.Server.ServerObjects.ServerPlayer;

/**
 *
 * @author jjyks
 */
public class PlayerConnection extends Connection {
    public ServerPlayer player;
}

