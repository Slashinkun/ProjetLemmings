package Entities;

import java.util.Random;

//Chacun des roles des poussins hériteront de cette classe
public class Chicks extends GameObject {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 100;
    private static final int GRAVITY = 2;
    private Direction direction;
    private boolean isOnFloor;

    public Chicks(int posX, int posY) {
        super(posX, posY, HEIGHT, WIDTH, EntityType.BASIC_CHICKS);
        this.isOnFloor = false;
        Random rand = new Random();
        int randomNum = rand.nextInt(2) + 1;
        if (randomNum == 2)
            this.direction = Direction.DROITE;
        else
            this.direction = Direction.GAUCHE;
    }

    public void move() {
        if (!this.isOnFloor) { // si il touche pas le sol , on le fait tomber
            super.posY += 1;
        } else {
            if (direction == Direction.DROITE) {
                super.posX += 1;
            } else {
                super.posX -= 1;
            }
        }
    }

    public void changeDirection() {
        if (this.direction == Direction.DROITE)
            this.direction = Direction.GAUCHE;
        else
            this.direction = Direction.DROITE;
    }

    // Getters
    public Direction getDirection() {
        return this.direction;
    }

    public boolean getIsOnFloor() {
        return this.isOnFloor;
    }

    // Setters
    public void setIsOnFloor(boolean isOnFloor) {
        this.isOnFloor = isOnFloor;
    }

}

enum Direction {
    DROITE, GAUCHE;
}