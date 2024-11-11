package Entities;

import java.util.Random;

//Chacun des roles des poussins hériteront de cette classe
public class Chicks extends GameObject {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 100;
    private static final int GRAVITY = 1;
    private Direction direction;
    private boolean isDead = false;

    public Chicks(int posX, int posY) {
        super(posX, posY, HEIGHT, WIDTH, EntityType.BASIC_CHICK);
        this.direction = Direction.DESCEND;
    }

    public void move() {
        if (this.direction == Direction.DESCEND) {
            super.posY += GRAVITY;

        } else {
            if (this.direction == Direction.DROITE) {
                super.posX += 1;
            } else if (this.direction == Direction.GAUCHE) {
                super.posX -= 1;
            }
        }
    }

    public void chooseRandomDirection() {
        Random rand = new Random();
        int randomNum = rand.nextInt(2) + 1;
        if (randomNum == 2)
            this.direction = Direction.DROITE;
        else
            this.direction = Direction.GAUCHE;
    }

    public void changeDirection() {
        if (this.direction == Direction.DROITE)
            this.direction = Direction.GAUCHE;
        else
            this.direction = Direction.DROITE;
    }

    public void calculateHeightFall(GameObject object){
        int distance = object.getPosY()-posY;

        if(distance >= 250){
            setDeath(true);
            System.out.println("RIP");
        }
    }


    public void checkCollisionWithPlatform(Platform platform) {


            calculateHeightFall(platform);

        if (platform.getHeight() <= 50 && super.posY + HEIGHT == platform.getPosY() + platform.getHeight()) {
                
            if ((this.direction == Direction.DROITE && super.posX + WIDTH == platform.getPosX())
                    || (this.direction == Direction.GAUCHE && super.posX == platform.getPosX() + platform.getWidth())) {
                this.posY -= 50;
            }
        } else if (platform.getHeight() <= 50 && super.posY + HEIGHT == platform.getPosY()) {

            if (this.direction == Direction.GAUCHE && super.posX + WIDTH == platform.getPosX()) {
                this.direction = Direction.DESCEND;
            } else if (this.direction == Direction.DROITE && super.posX == platform.getPosX() + platform.getWidth()) {
                this.direction = Direction.DESCEND;
            }
        } else if (((platform.getPosY() >= super.posY && platform.getPosY() <= super.posY + HEIGHT)
                || (platform.getPosY() + platform.getHeight() >= super.posY
                        && platform.getPosY() + platform.getHeight() <= super.posY + HEIGHT))) {
            // Vérification de la position pour X
            if (this.direction == Direction.DROITE && super.posX + WIDTH == platform.getPosX()) {
                this.direction = Direction.GAUCHE;
            } else if (this.direction == Direction.GAUCHE && super.posX == platform.getPosX() + platform.getWidth()) {
                this.direction = Direction.DROITE;
            }
        }

        if (this.direction == Direction.DESCEND)
            checkIsOnFloor(platform);
    }

    public void checkIsOnFloor(Platform platform) {
        if (super.posX > platform.getPosX() && super.posX < platform.getPosX() + platform.getWidth()
                && super.posY + HEIGHT == platform.getPosY()) {
                    calculateHeightFall(platform);
            chooseRandomDirection();
        }
    }

    // Getters
    public Direction getDirection() {
        return this.direction;
    }

    
    public boolean getIsDead(){
        return this.isDead;
    }

    public void setDeath(boolean isDead){
        this.isDead = isDead;
    }

    // public boolean getIsOnFloor() {
    // return this.isOnFloor;
    // }

    // Setters
    // public void setIsOnFloor(boolean isOnFloor) {
    // this.isOnFloor = isOnFloor;
    // }

}

// On utilisera le Direction.MONTE quand on passera à l'étape 2 et que monter
// deviendra plus complexe
enum Direction {
    DROITE, GAUCHE, DESCEND, MONTE;
}