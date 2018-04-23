/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import allgemein.*;
import client.ClientInterface;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

/**
 *
 * @author martin
 */
public interface ServerInterface extends Remote{
    
    // Client - Server connect
    public void connect(UUID playerID, ClientInterface client) throws RemoteException, AlreadyBoundException;
    
    // RMI Test
    public String test() throws RemoteException;
    
}
