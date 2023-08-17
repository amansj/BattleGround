package com.battleship.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.battleship.dao.GameDao;
import com.battleship.execption.GameExeception;
import com.battleship.model.Game;

@Component
public class GameServiceImpl implements GameService{
	
	@Autowired
	private GameDao gameDao;
	
	@Override
	public String initGame(int N) {
		
		Game game=new Game(N);
		
		gameDao.initGame(game);
		
		
		return game.getGameId();
		
		
	}
	
	

	@Override
	public void addShip(String gameId, String id, int size, int xPositionPlayerA, int yPositionPlayerA,
			int xPositionPlayerB, int yPositionPlayerB) throws GameExeception {
		Optional<Game> gameOpt=gameDao.get(gameId);
		if(!gameOpt.isPresent()) {
			throw new GameExeception("Game Not Present");
		}
		
		Game game=gameOpt.get();
		if(game.isGameCompleted()) {
			throw new GameExeception("Game Already completed");
		}
		if(game.isGameStarted()) {
			throw new GameExeception("Game Already started");
		}
		
		String[][] ground=game.getGround();
		putShip(ground, yPositionPlayerA, xPositionPlayerA, id, "A", game.getN(), size);
		putShip(ground, yPositionPlayerB, xPositionPlayerB, id, "B", game.getN(), size);
		game.setPlayerAShipCount(game.getPlayerAShipCount()+1);
		game.setPlayerBShipCount(game.getPlayerBShipCount()+1);
		gameDao.update(game);
		
	}

	@Override
	public void startGame(String gameId) throws GameExeception {
		Optional<Game> gameOpt=gameDao.get(gameId);
		if(!gameOpt.isPresent()) {
			throw new GameExeception("Game Not Present");
		}
		Game game=gameOpt.get();
		if(game.isGameCompleted()) {
			throw new GameExeception("Game Already completed");
		}
		if(game.isGameStarted()) {
			throw new GameExeception("Game Already started");
		}
		game.setGameStarted(true);
		gameDao.update(game);
		
	}

	@Override
	public void playerTurn(String gameId, String player, int xPos, int yPos) throws GameExeception {
		Optional<Game> gameOpt=gameDao.get(gameId);
		if(!gameOpt.isPresent()) {
			throw new GameExeception("Game Not Present");
		}
		Game game=gameOpt.get();
		if(game.isGameCompleted()) {
			throw new GameExeception("Game Already completed");
		}
		if(player.equals(game.getLastPayerTurn())) {
			throw new GameExeception("Invalid Player");
		}
		
		boolean status =turn(game.getGround(), yPos+1, xPos+1);
		
		if(status) {
			if("A".equals(player)) {
				
				game.setPlayerBShipCount(game.getPlayerBShipCount()-1);
			}else {
				game.setPlayerAShipCount(game.getPlayerAShipCount()-1);
			}
		}
		
		System.out.println("Ships Remaining");
		System.out.println("PlayerA:"+game.getPlayerAShipCount());
		System.out.println("PlayerB:"+game.getPlayerBShipCount());
		
		if(game.getPlayerBShipCount()==0) {
			System.out.println("GameOver. PlayerA wins.");
			game.setGameCompleted(true);
		}
		if(game.getPlayerAShipCount()==0) {
			System.out.println("GameOver. PlayerB wins.");
			game.setGameCompleted(true);
		}
		gameDao.update(game);
		
		
		
	}
	
	private boolean turn(String[][] ground,int xPos,int yPos) {
		
		if(ground[xPos][yPos+1]==null) {
			System.out.println("Miss");
			return false;
		}
		
		String ship= ground[xPos][yPos];
		
		removeShip(xPos, yPos, ship, ground);
		System.out.println("Hit");
		
		return true;
		
		
	}
	
	private void removeShip(int xPos,int yPos,String ship,String[][] ground) {
		
		if(xPos<0) {
			return;
		}
		if(yPos<0) {
			return;
		}
		if(xPos>=ground.length) {
			return;
		}
		if(yPos>=ground.length) {
			return;
		}
		if(ground[xPos][yPos]==null) {
			return;
		}
		if(!ground[xPos][yPos].equals(ship)) {
			return;
		}
		ground[xPos][yPos]=null;
		
		removeShip(xPos-1, yPos, ship, ground);
		removeShip(xPos-1, yPos-1, ship, ground);
		removeShip(xPos, yPos-1, ship, ground);
		
	}
	
	
	private void putShip(String[][] ground,int xPos,int yPos,String shipId,String player,int N, int size) {
		
		if(player.equals("A") && yPos>N/2) {
			throw new GameExeception("Invalid Position");
		}
		
		if(player.equals("B") && yPos<N/2) {
			throw new GameExeception("Invalid Position");
		}
		
		int mid=size/2;
		
		String shipIdP=player+"-"+shipId;
		for(int i=xPos+1;i>xPos-mid;i--) {
			for(int j=yPos+1;j>yPos-mid;j--) {
				ground[i][j]=shipIdP;
			}
		}
		
	}



	@Override
	public void viewBattleField(String gameId) {
		Optional<Game> gameOpt=gameDao.get(gameId);
		if(!gameOpt.isPresent()) {
			throw new GameExeception("Game Not Present");
		}
		Game game=gameOpt.get();
		
		int N=game.getN();
		String[][] ground=game.getGround();
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				System.out.print(ground[i][j]+"  ");
			}
			System.out.println();
		}
		
	}
	

}
