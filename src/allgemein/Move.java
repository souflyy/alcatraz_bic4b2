/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allgemein;

import java.io.Serializable;
import at.falb.games.alcatraz.api.*;

/**
 *
 * @author danielpozarek
 */
public class Move implements Serializable {
    
    // ###################### Variablen ####################
    private int row;
    private int column;
    private int rowOrColumn;
    private Player player;
    private Prisoner prisoner;
    
    // ###################### Konstruktor ######################
    public Move(){}
    
    // ###################### Konstruktor ######################
    public Move(Player player, Prisoner prisoner, int row, int column, int rowOrColumn){
        super();
        this.row = row;
        this.column = column;
        this.rowOrColumn = rowOrColumn;
        this.player = player;
        this.prisoner = prisoner;
    }

    // ###################### Setter ####################
    public void setRowOrColumn(int rowOrColumn){
        this.rowOrColumn = rowOrColumn;
    }
    public void setRow(int row){
        this.row = row;
    }
    public void setColumn(int column){
        this.column = column;
    }
    public void setPlayer(Player player){
        this.player = player;
    }
    public void setPrisoner(Prisoner prisoner){
        this.prisoner = prisoner;
    }
    
    // ###################### Getter ####################
    public int getRowOrColumn(){
        return this.rowOrColumn;
    }
    public int getRow(){
        return this.row;
    }
    public int getColumn(){
        return this.column;
    }
    public Player getPlayer(){
        return this.player;
    }
    public Prisoner getPrisoner(){
        return this.prisoner;
    }
}

