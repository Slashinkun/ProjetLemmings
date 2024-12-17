package Entities;

import Game.GameObservable;

public class Exit extends GameObject {
    private static final int HEIGHT = 100;
    private static final int WIDTH = 50;
    // Nombre de poussin qui sont sorties
    private int chicksExit;
    // Nombre de poussin qui doivent sortir pour gagner
    private int numberOfChicksExitForWin;

    public Exit(int posX, int posY, int numberOfChicksExitForWin, GameObservable gameObservable) {
        super(posX, posY, HEIGHT, WIDTH, EntityType.EXIT, gameObservable);
        this.chicksExit = 0;
        this.numberOfChicksExitForWin = numberOfChicksExitForWin;
    }

    public boolean checkWin() {
        if (this.chicksExit >= this.numberOfChicksExitForWin)
            return true;
        return false;
    }

    // Getters
    public int getChicksExit() {
        return this.chicksExit;
    }

    public int getNumberOfChicksExitForWin() {
        return this.numberOfChicksExitForWin;
    }

    // Setters
    public void incrementChicksExit() {
        this.chicksExit++;
    }

}
