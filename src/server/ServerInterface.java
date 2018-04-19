/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author martin
 */
public interface ServerInterface extends Remote{
    public String test() throws RemoteException;
}
