package Entities;

import java.util.Random;

//Chacun des roles des poussins hériteront de cette classe
public class Chicks extends GameObject {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 100;
    private static final int GRAVITY = 1;
    // Direction du poussin, peut etre GAUCHE, DROITE, MONTE et TOMBE
    private Direction direction;
    // Direction de base du poussin, peut etre seulement DROITE et GAUCHE
    private Direction baseDirection;
    // int qui stocke de combien il est entrain de tombé, si le poussin tombe de
    // plus 250(5 cases), on le tue, sinon on remet à 0
    private int chute;

    public Chicks(int posX, int posY) {
        super(posX, posY, HEIGHT, WIDTH, EntityType.BASIC_CHICK);
        this.direction = Direction.DESCEND;

        // On choisie baseDirection au hasard entre GAUCHE et DROITE au début
        Random rand = new Random();
        int randomNum = rand.nextInt(2) + 1;
        if (randomNum == 2)
            this.baseDirection = Direction.DROITE;
        else
            this.baseDirection = Direction.GAUCHE;

        this.chute = 0;
    }

    // Fonction pour bouger le poussin selon sa direction
    public void move() {
        if (this.direction == Direction.DESCEND) {
            super.posY += GRAVITY;
            chute += GRAVITY;

        } else {
            if (this.direction == Direction.DROITE) {
                super.posX += 1;
            } else if (this.direction == Direction.GAUCHE) {
                super.posX -= 1;
            }
        }
    }

    public boolean checkCollisionWithExit(Exit exit) {
        if (exit.posY >= super.posY && exit.posY <= super.posY + HEIGHT) {
            if (super.posX >= exit.getPosX() && super.posX <= exit.getPosX() + exit.getWidth())
                return true;
        }
        return false;
    }

    // Fonction pour vérifier les collisions d'un poussin avec une plateforme
    public void checkCollisionWithPlatform(Platform platform) {

        // Si le poussin et la plateforme sont dans la même position et que la
        // plateforme est égale ou inférieur à 50(une case) en hauteur, on fait monter
        // le poussin
        if (platform.getHeight() <= 50 && super.posY + HEIGHT == platform.getPosY() + platform.getHeight()) {
            if ((this.direction == Direction.DROITE && super.posX + WIDTH == platform.getPosX())
                    || (this.direction == Direction.GAUCHE && super.posX == platform.getPosX() + platform.getWidth())) {
                // Améliorer comment le poussin monte dans l'étape 2
                this.posY -= 50;
            }
            // Pöur faire descendre le poussin quand il a atteint un bout de la plateforme
        } else if (super.posY + HEIGHT == platform.getPosY()) {
            if ((this.direction == Direction.GAUCHE && super.posX + WIDTH == platform.getPosX())
                    || (this.direction == Direction.DROITE && super.posX == platform.getPosX() + platform.getWidth())) {
                this.direction = Direction.DESCEND;
            }
            // Pour les collisions avec des murs, on change juste la direction en appelant
            // changeDirection
        } else if (((platform.getPosY() >= super.posY && platform.getPosY() <= super.posY + HEIGHT)
                || (platform.getPosY() + platform.getHeight() >= super.posY
                        && platform.getPosY() + platform.getHeight() <= super.posY + HEIGHT))) {
            // Vérification de la position pour X
            if ((this.direction == Direction.DROITE && super.posX + WIDTH == platform.getPosX())
                    || (this.direction == Direction.GAUCHE && super.posX == platform.getPosX() + platform.getWidth())) {
                changeDirection();
            }
        }

        // On vérifie si le poussin a touché la plateforme si ca direction est DESCEND
        if (this.direction == Direction.DESCEND)
            checkIsOnFloor(platform);
    }

    // Fonction pour vérifier si le poussin a touché une plateforme par le haut et
    // si il est tombé de plus de 250(5 cases), on le retire de l'ArrayList dans
    // GameObservable,
    // sinon on remet chute à 0 et on change la direction à baseDirection
    public boolean checkIsOnFloor(GameObject o) {
        if ((super.posX > o.getPosX() && super.posX < o.getPosX() + o.getWidth())
                && super.posY + HEIGHT == o.getPosY()) {
            if (this.chute >= 250 && o.getClass() != Exit.class) {
                gameObservable.removeChicks(this);
            } else {
                this.chute = 0;
                this.direction = this.baseDirection;
                return true;
            }
        }
        return false;
    }

    // Pour changer la direction
    public void changeDirection() {
        if (this.direction == Direction.DROITE) {
            this.direction = Direction.GAUCHE;
            this.baseDirection = Direction.GAUCHE;
        } else {
            this.direction = Direction.DROITE;
            this.baseDirection = Direction.DROITE;
        }
    }

    // Getters
    public Direction getDirection() {
        return this.direction;
    }

}

// On utilisera le Direction.MONTE quand on passera à l'étape 2 et que monter
// deviendra plus complexe
enum Direction {
    DROITE, GAUCHE, DESCEND, MONTE;
}