package com.battleship.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.battleship.model.Game;

@Component
public class GameDao {
	
	private Map<String,Game> gameDetails;
	
	public GameDao() {
		
		gameDetails=new HashMap<>();
		
	}
	
	public void initGame(Game game) {
		gameDetails.put(game.getGameId(), game);
	}
	
	public void update(Game game) {
		gameDetails.put(game.getGameId(), game);
	}
	
	public Optional<Game> get(String gameId) {
		
		if(gameDetails.containsKey(gameId)) {
			return Optional.of(gameDetails.get(gameId));
		}
		return Optional.empty();
		
	}
	
	

}
