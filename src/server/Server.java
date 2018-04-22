/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import spread.*;

/**
 *
 * @author martingrabner
 */
public class Server extends UnicastRemoteObject implements AdvancedMessageListener, ServerInterface {
    
    private static SpreadConnection connection;
    private static String serverId;
    private static SpreadGroup group;
    
    
    
    public Server() throws RemoteException {
    
        connection = initSpreadConnection("127.0.0.1","Server6");
        group = createSpreadGroup(connection, "Group1");
        connection.add(this);
        try {
        SpreadMessage message = new SpreadMessage();
        message.setObject("Hello World!");
        message.setSelfDiscard(false);
        message.addGroup(group);
        connection.multicast(message);
        }
        catch (SpreadException ex) {
            System.err.println("Spread Exception: " +ex.getMessage() + Arrays.toString(ex.getStackTrace()));
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
          System.out.println("Starting up AlcatrazService...");
          //Calculator c = new CalculatorImpl();
          LocateRegistry.createRegistry(1101);
          Naming.rebind("rmi://localhost:1101/AlcatrazService", this);
          System.out.println("Alcatraz server up and running.");
        } catch (Exception e) {
          System.out.println("Trouble!");
          e.printStackTrace();
        }
    }
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            Server s = new Server();
        } catch (RemoteException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
    private static SpreadConnection initSpreadConnection(String ipAdress, String clientName)
    {
        SpreadConnection newConnection = new SpreadConnection();
        
        try {
            newConnection.connect(InetAddress.getByName(ipAdress), 4804, clientName, false, true);
            System.out.println("connected to Spread Deamon: "+ipAdress);

            return newConnection;
        }
        catch (SpreadException ex) {
            System.err.println("Spread Exception: " +ex.getMessage() + Arrays.toString(ex.getStackTrace()));
        }
        catch (UnknownHostException ex){
            System.err.println("Unknown Host Exception: " + ex.getMessage() + Arrays.toString(ex.getStackTrace()));
        }
        return null;
    }
   
    
    private static SpreadGroup createSpreadGroup(SpreadConnection connection, String groupName)
    {
        SpreadGroup group = new SpreadGroup();
        try {
            group.join(connection, groupName);
            System.out.println("Joined spread group: "+groupName);
        } 
        catch (SpreadException ex) {
            System.err.println("Spread Exception: " +ex.getMessage() + Arrays.toString(ex.getStackTrace()));
        }
        return group;
    }

    @Override
    public void regularMessageReceived(SpreadMessage sm) {
        try {
            System.out.println("Regular message received: "+sm.getObject());
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SpreadException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void membershipMessageReceived(SpreadMessage sm) {
        System.out.println("Membership message received");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String test() throws RemoteException {
        return "Hello World!";
    }
    
}

