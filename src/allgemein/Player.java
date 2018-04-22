/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allgemein;

import client.ClientInterface;
import java.util.UUID;
import java.io.Serializable;

/**
 *
 * @author danielpozarek
 */
public class Player implements Serializable {
    
    // ###################### Variablen ####################
    private  UUID playerID;
    private String playerName;
    private ClientInterface client;
    private int playerIDinGame;
    
    // ###################### Setter ####################
    public void setPlayerID(UUID playerID){
        this.playerID = playerID;
    }
    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }
    public void setClient(ClientInterface client){
        this.client = client;
    }
    public void setPlayerIDinGame(int playerIDinGame){
        this.playerIDinGame = playerIDinGame;
    }
    
    // ###################### Getter ####################
    public UUID getPlayerID(){
        return this.playerID;
    }
    public String getPlayerName(){
        return this.playerName;
    }
    public ClientInterface getClient(){
        return this.client;
    }
    public int getPlayerIDinGame(){
        return this.playerIDinGame;
    }
 }
