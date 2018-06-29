package com.qait.automation.SnlMaven;

/**
 *
 * @author Ramandeep
 */
public class MaxPlayersReachedExeption extends Exception{
    
    public MaxPlayersReachedExeption(Integer players){
        super("The board already has maximum allowed Player: " + players);
    }
}
