package com.battleship.service;

import com.battleship.execption.GameExeception;

public interface GameService {
	
	public String initGame(int N);
	
	public void viewBattleField(String gameId);
	
	public void addShip(String gameId,String id,int size,int xPositionPlayerA,int yPositionPlayerA,int xPositionPlayerB,int yPositionPlayerB) throws GameExeception;
	
	public void startGame(String gameId) throws GameExeception ;
	
	public void playerTurn(String gameId,String player,int xPos,int yPos) throws GameExeception;

}
