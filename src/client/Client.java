/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import allgemein.*;
import server.*;
import javafx.application.*;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.util.UUID;
import javafx.scene.layout.GridPane;

/**
 *
 * @author danielpozarek
 */
public class Client extends Application implements ClientInterface/*, MoveListener*/{
    
    
    
    // ############ Variablen #############
    final ToggleGroup togglegroup = new ToggleGroup();
    public Player newPlayer = new Player();
    static ServerInterface serverStub;
    
    
    
    // ####################
    // main
    // ####################
    public static void main(String[] args) throws NotBoundException, AlreadyBoundException, RemoteException {
        launch(args);
    }
    
    
    
    // ####################
    // start
    // versucht connect zu server und baut dann ui auf
    // ####################
    @Override
    public void start(final Stage firstStage) throws NotBoundException, AlreadyBoundException, RemoteException{
        try {
            connect();
        } catch (NotBoundException|AlreadyBoundException|RemoteException e) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
        }

        final BorderPane borderPane = new BorderPane();

        borderPane.setLeft(LeftPane());
        borderPane.setBottom(BottomPane());
        //borderPane.setCenter(createCenterPane());
        
        firstStage.setTitle("Raumübersicht");
        firstStage.setScene(new Scene(borderPane, 400, 400));
        firstStage.show();
    }
    
    
    
    // ####################
    // left Pane
    // ####################
    private Pane LeftPane() {
        final VBox vbox = new VBox(5);
        vbox.setPadding(new Insets(20, 10, 10, 10));
        
        Label llabel = new Label("Raum erstellen:");
        vbox.getChildren().addAll(llabel);
        
        TextField textField = new TextField("Raumname");
        textField.setPrefWidth(110);
        vbox.getChildren().add(textField);
        
        RadioButton zweiPlayer = new RadioButton("2 Spieler");	
        RadioButton dreiPlayer = new RadioButton("3 Spieler");	
        RadioButton vierPlayer = new RadioButton("4 Spieler");
        
        zweiPlayer.setSelected(true);
        zweiPlayer.setUserData(2);
        dreiPlayer.setUserData(3);
        vierPlayer.setUserData(4);
        
        RadioButton[] radiobutts = new RadioButton[]{zweiPlayer, dreiPlayer, vierPlayer};			
        for(RadioButton rb : radiobutts) {
            vbox.getChildren().add(rb);
            rb.setStyle("-fx-font: 12 arial; ");
            rb.setToggleGroup(togglegroup);
	}
			
	return vbox;
    }
    
    
    
    // ####################
    // bottom Pane
    // ####################
    private Pane BottomPane(){
        final HBox hbox = new HBox(10);
		
	hbox.setAlignment(Pos.CENTER);
	hbox.setPadding(new Insets(10, 0, 10, 0));
	Button exitbutton = new Button("Beenden"); 
	Button joinButton = new Button("Beitreten"); 
	//Button actualizeButton = new Button("aktualisieren"); 
	
	exitbutton.setStyle("-fx-border-color:  #000000; -fx-background-color: #00FF00;");
	exitbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                /*try {
                    //Stub derigstrieren
                } catch (RemoteException|NotBoundException exc) {
                    //Exception Handling
                }*/
                
                Platform.exit();
            }    
        });
        
        joinButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev){
                // Lobby zusammenstellen
            }
        });                
        //actualizeButton.setOnAction(/*Lobby Pane oeffnen*/);
        
        hbox.getChildren().addAll(exitbutton, joinButton/*, actualizeButton*/);
	hbox.setStyle("-fx-font: 22 arial;");
        
        return hbox;
    }
    
    
    
    // ####################
    // connect
    // 
    // ####################
    private void connect() throws RemoteException, NotBoundException, AlreadyBoundException {
        
        ClientInterface currentPlayer = new Client();

        newPlayer.setPlayerID(UUID.randomUUID());
        newPlayer.setClient((ClientInterface) UnicastRemoteObject.exportObject(currentPlayer, 0));
        try{
            Registry registry = LocateRegistry.getRegistry(); 
            serverStub = (ServerInterface) registry.lookup("Server");
            serverStub.connect(newPlayer.getPlayerID(),newPlayer.getClient());
        }
        catch (Exception e){
            Stage stage = new Stage();
            GridPane gridPane = new GridPane();
            final Label label = new Label();
            Button exitbutton = new Button("Exit"); 
            
            stage.setTitle("ERROR");
            stage.setScene(new Scene(gridPane, 150, 70));
            
            label.setText("Cannot connect to Server.");
            GridPane.setConstraints(label, 0, 0);
            GridPane.setConstraints(exitbutton, 0, 1);
            gridPane.getChildren().addAll(label,exitbutton);
            
            exitbutton.setStyle("-fx-border-color:  #545454; -fx-background-color: #F30B0B;");
            exitbutton.setOnAction((event) -> Platform.exit());
            
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);       
            
            stage.show();  
        }
    }
    
    
}
