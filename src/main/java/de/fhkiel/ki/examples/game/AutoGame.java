package de.fhkiel.ki.examples.game;

import de.fhkiel.ki.cathedral.Building;
import de.fhkiel.ki.cathedral.Direction;
import de.fhkiel.ki.cathedral.Game;
import de.fhkiel.ki.cathedral.Placement;
import java.util.Random;

public class AutoGame {

  public static void main(String[] args) {
    Game testGame = new Game();

    System.out.println(testGame.lastTurn());

    long startTime = System.currentTimeMillis();
    long counter = 0, fails = 0;
    while(System.currentTimeMillis() - startTime < 5000){
      ++counter;
      if(testGame.takeTurn(takeTurn(testGame))){
        System.out.println(testGame.lastTurn());
      } else {
        ++fails;
      }
    }

    System.out.println("Score: " + testGame.score() + "\n");
    System.out.println(counter + " tries with " + fails + " unusable turns");
    System.out.println("Leftover buildings " +testGame.getPlacableBuildings());
  }

  private static Placement takeTurn(Game game) {
    Random rand = new Random();
    return new Placement(
        rand.nextInt(10),
        rand.nextInt(10),
        Direction.values()[rand.nextInt(Direction.values().length)],
        game.getPlacableBuildings().get(rand.nextInt(game.getPlacableBuildings().size())));
  }
}
