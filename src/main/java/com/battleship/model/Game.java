package com.battleship.model;

import java.util.UUID;

public class Game {
	
	private String gameId;
	
	private int N;
	
	private String[][] ground;
	
	private boolean isGameStarted;
	
	private String lastPayerTurn;
	
	private boolean isGameCompleted;
	
	private int playerAShipCount;
	
	private int playerBShipCount;
	
	
	
	
	public Game(int N) {
		this.N=N+1;
		
		ground=new String[N+1][N+1];
		
		gameId=UUID.randomUUID().toString();
		isGameStarted=false;
		isGameCompleted=false;
	}

	public String getGameId() {
		return gameId;
	}

	public int getN() {
		return N;
	}

	public String[][] getGround() {
		return ground;
	}

	public boolean isGameStarted() {
		return isGameStarted;
	}

	public void setGameStarted(boolean isGameStarted) {
		this.isGameStarted = isGameStarted;
	}

	public String getLastPayerTurn() {
		return lastPayerTurn;
	}

	public void setLastPayerTurn(String lastPayerTurn) {
		this.lastPayerTurn = lastPayerTurn;
	}

	public boolean isGameCompleted() {
		return isGameCompleted;
	}

	public void setGameCompleted(boolean isGameCompleted) {
		this.isGameCompleted = isGameCompleted;
	}

	public int getPlayerAShipCount() {
		return playerAShipCount;
	}

	public void setPlayerAShipCount(int playerAShipCount) {
		this.playerAShipCount = playerAShipCount;
	}

	public int getPlayerBShipCount() {
		return playerBShipCount;
	}

	public void setPlayerBShipCount(int playerBShipCount) {
		this.playerBShipCount = playerBShipCount;
	}
	
	
	
	

}
