package de.fhkiel.ki.examples.gui.withAi;

import de.fhkiel.ki.ai.CathedralAI;
import de.fhkiel.ki.cathedral.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestAI implements CathedralAI {
    @Override
    public String name() {
        return "Hannes";
    }

    @Override
    public void init(Game game) {

    }

    @Override
    public Placement takeTurn(Game game) {
		// Zur Sicherheit einmal das Spiel kopieren
        Game testGame = game.copy();

		// eine liste anlegen um moegliche Züge zu speichern
        List<Placement> listOfPlacement = new ArrayList<>();

		// über jedes einzelne Gebaeude gehen
        for(Building b : testGame.getPlacableBuildings()){
			// mit dem momentanen Gebaeude über jeden Punkt auf dem Spielfeld gehen
            for(int x = 0; x < 10; ++x){
                for(int y = 0; y < 10; ++y){
					// Auf jedem Punkt alle moeglichen Drehungen des Gebaeudes ausprobieren
                    for(Direction d: b.getTurnable().getPossibleDirections()){
						// den Moeglichen Zug erstellen
                        Placement possibleTurn = new Placement(x, y, d, b);
						// den moeglichen Zug schnell testen
                        if(testGame.takeTurn(possibleTurn, true)){
							// wenn der Zug moeglich war ihn in die Liste schreiben
                            listOfPlacement.add(possibleTurn);
							// das Testspiel zuruecksetzen
                            testGame.undoLastTurn();
                        }
                    }
                }
            }
        }
		// Die Anzahl der moeglichen Zuege ausgeben
        System.out.println(listOfPlacement.size());

		// Wenn es Züge gibt, zufaellig einen davon auswaehlen und zurueckgeben
        if(!listOfPlacement.isEmpty()) {
            Random rand = new Random();
            return listOfPlacement.get(rand.nextInt(listOfPlacement.size()));
        }
        return new Placement(5, 4, Direction._0, Building.Blue_Cathedral);
    }

    @Override
    public void stopAI() {

    }
}
