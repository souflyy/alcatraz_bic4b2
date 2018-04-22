/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allgemein;

import java.util.UUID;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author danielpozarek
 */
public class Room implements Serializable {
    
    // ###################### Variablen ####################
    private String roomName;
    private UUID roomID;
    private int maxPlayers;
    private ArrayList<Player> actualPlayersInRoom;
    
    // ###################### Funktionen ####################
    // Neue Room Liste
    public void initActualPlayersInRoom(){
        this.actualPlayersInRoom = new ArrayList<Player>();
    }
    
    // Spieler in Room hinzufuegen + Kontrolle ob schon vorhanden
    public void addPlayer(Player player) throws Exception {
        for(Player x : actualPlayersInRoom){
            if(x.getPlayerName().equals(player.getPlayerName()) ) throw new Exception("Spielername existiert schon. Bitte neuen waehlen!"); 
        }
        actualPlayersInRoom.add(player);
    } 
    
    // Spieler aus Room entfernen
    public void removeUser(Player player) {
        actualPlayersInRoom.remove(player);
    }
    
    // ###################### Setter ####################
    public void setRoomID(UUID roomID){
        this.roomID = roomID;
    }
    public void setRoomName(String roomName){
        this.roomName = roomName;
    }
    public void setMaxPlayer(int maxPlayers){
        this.maxPlayers = maxPlayers;
    }
    
    // ###################### Getter ####################
    public UUID getRoomID(){
        return this.roomID;
    }
    public String getRoomName(){
        return this.roomName;
    }
    public int getMaxPlayer(){
        return this.maxPlayers;
    }
    
    // Get Raumliste
    public ArrayList<Player> getRoomList(){
        return this.actualPlayersInRoom;
    }
    
    // Get Players aus Raumliste
    public int getActualPlayersInRoom(){
        int i = 0;
        for(Player x : actualPlayersInRoom){
            i++;
        }        
        return i;
    }
    
    // Get Namen von Players aus Raumliste
    public String getNamesInRoom(){
        String names = null;
        
        for(Player i : this.actualPlayersInRoom){
            names.concat(i.getPlayerName()+" ");
        }
        
        return names;
    }
}
