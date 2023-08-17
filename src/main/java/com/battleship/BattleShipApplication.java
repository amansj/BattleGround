package com.battleship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.battleship.service.GameService;

@SpringBootApplication
public class BattleShipApplication implements CommandLineRunner{
	
	@Autowired
	private GameService gameService;

	public static void main(String[] args) {
		SpringApplication.run(BattleShipApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		int N=6;
		
		String gameId=gameService.initGame(N);
		
		gameService.addShip(gameId,"SH1", 2, 1, 5, 4, 4);
		
		gameService.viewBattleField(gameId);
		
		gameService.playerTurn(gameId, "A", 3, 0);
		
		gameService.playerTurn(gameId, "B", 1, 1);
		
		gameService.playerTurn(gameId, "A", 5, 3);
		
	}

}
