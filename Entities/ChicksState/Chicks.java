package Entities.ChicksState;

import java.util.Random;

import Entities.Direction;
import Entities.EntityType;
import Entities.GameObject;
import Entities.Obstacle.Obstacles;

//Chacun des roles des poussins hériteront de cette classe
public class Chicks extends GameObject {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 100;
    // Direction du poussin, peut etre GAUCHE, DROITE, MONTE et TOMBE
    private Direction direction;
    // Direction de base du poussin, peut etre seulement DROITE et GAUCHE
    private Direction baseDirection;
    // int qui stocke de combien il est entrain de tombé, si le poussin tombe de
    // plus 250(5 cases), on le tue, sinon on remet à 0
    private int chute;
    private Obstacles lastObstacles;

    private ChicksState state = new NormalState(this);
    private ChickStateName stateName = ChickStateName.NORMAL;

    public Chicks(int posX, int posY) {
        super(posX, posY, HEIGHT, WIDTH, EntityType.CHICK);
        this.direction = Direction.DESCEND;

        // On choisie baseDirection au hasard entre GAUCHE et DROITE au début
        Random rand = new Random();
        int randomNum = rand.nextInt(2) + 1;
        if (randomNum == 2)
            this.baseDirection = Direction.GAUCHE;
        else
            this.baseDirection = Direction.DROITE;

        this.chute = 0;
    }

    // Fonction pour bouger le poussin selon sa direction
    public void move() {
        state.move();
    }

    // Fonction pour vérifier les collisions d'un poussin avec une plateforme
    public void checkCollisionWithObstalces(Obstacles obstacles) {
        // On vérifie si le poussin a touché la plateforme si ca direction est DESCEND
        if (this.direction == Direction.DESCEND)
            checkIsOnFloor(obstacles);

        jumpCollisions(obstacles);
        sideCollisions(obstacles);
        downCollisions(obstacles);

        if (lastObstacles != null && checkIsOnFloor(lastObstacles)) {
            this.direction = this.baseDirection;
        }

        this.lastObstacles = obstacles;
    }

    // Fonction pour sauter
    public void jumpCollisions(Obstacles obstacles) {
        // Si le poussin et la plateforme sont dans la même position et que la
        // plateforme est égale ou inférieur à 50(une case) en hauteur, on fait monter
        // le poussin
        if (this.stateName == ChickStateName.TUNNELIER && obstacles.getIsDestructible())
            return;

        if (obstacles.getHeight() <= 50 && super.posY + HEIGHT == obstacles.getPosY() + obstacles.getHeight()) {
            if ((this.direction == Direction.DROITE && super.posX + WIDTH == obstacles.getPosX())
                    || (this.direction == Direction.GAUCHE
                            && super.posX == obstacles.getPosX() + obstacles.getWidth())) {
                this.posY -= 50;
            }
        }
    }

    // Fonction pour vérifier les collisions avec les côtés
    public void sideCollisions(Obstacles obstacles) {
        if (this.stateName == ChickStateName.TUNNELIER && obstacles.getIsDestructible())
            return;

        // Pour les collisions avec des murs, on change juste la direction en appelant
        // changeDirection
        if (super.posY == obstacles.getPosY() || super.posY == obstacles.getPosY() + 50
                || super.posY == obstacles.getPosY() - 50) {
            // Vérification de la position pour X
            if ((this.direction == Direction.DROITE && super.posX + WIDTH == obstacles.getPosX())
                    || (this.direction == Direction.GAUCHE
                            && super.posX == obstacles.getPosX() + obstacles.getWidth())) {
                changeDirection();

            }
        }
    }

    // Pour faire descendre le poussin quand il a atteint un bout de la plateforme
    public void downCollisions(Obstacles obstacles) {
        if (super.posY + HEIGHT == obstacles.getPosY()) {
            if (this.direction == Direction.DROITE && super.posX - WIDTH == obstacles.getPosX()) {
                this.direction = Direction.DESCEND;
            }
            if (this.direction == Direction.GAUCHE && super.posX + WIDTH == obstacles.getPosX()) {
                this.direction = Direction.DESCEND;
            }
        }

    }

    // Fonction pour vérifier si le poussin a touché une plateforme par le haut et
    // si il est tombé de plus de 250(5 cases), on le retire de l'ArrayList dans
    // GameObservable,
    // sinon on remet chute à 0 et on change la direction à baseDirection
    public boolean checkIsOnFloor(GameObject o) {
        if ((super.posX + WIDTH > o.getPosX() && super.posX < o.getPosX() + o.getWidth())
                && super.posY + HEIGHT == o.getPosY()) {
            if (this.chute >= 250 && o.getObjectType() != EntityType.EXIT) {
                gameObservable.removeChicks(this);
            } else {
                this.chute = 0;
                this.direction = this.baseDirection;
                if (this.stateName == ChickStateName.FLOATER) {
                    changeState(ChickStateName.NORMAL);
                }
                return true;
            }
        }
        return false;
    }

    public boolean isColliding(GameObject o) {
        if (super.posY == o.getPosY() || super.posY == o.getPosY() + 50
                || super.posY == o.getPosY() - 50) {
            // Vérification de la position pour X
            if ((this.direction == Direction.DROITE && super.posX + WIDTH == o.getPosX())
                    || (this.direction == Direction.GAUCHE
                            && super.posX == o.getPosX() + o.getWidth())) {
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

    // pour changer l'etat(metier) du poussin
    public void changeState(ChickStateName stateName) {
        if (this.stateName != stateName) { // on peut pas changer l'etat si c'est deja l'etat actuel
            switch (stateName) {
                case FOREUR:
                    this.state = new ForeurState(this);
                    this.stateName = ChickStateName.FOREUR;
                    break;
                case TUNNELIER:
                    this.state = new TunnelierState(this);
                    this.stateName = ChickStateName.TUNNELIER;
                    break;
                case BLOCKER:
                    this.state = new BlockerState(this);
                    this.stateName = ChickStateName.BLOCKER;
                    break;
                case FLOATER:
                    this.state = new FloaterState(this);
                    this.stateName = ChickStateName.FLOATER;
                    break;
                case NORMAL:
                    this.state = new NormalState(this);
                    this.stateName = ChickStateName.NORMAL;
                    break;
            }
        }
    }

    // Getters
    public Direction getDirection() {
        return this.direction;
    }

    public void addChute(int x) {
        this.chute += x;
    }

    public int getDirectionX() {
        return this.direction.getX();
    }

    public int getDirectionY() {
        return this.direction.getY();
    }

    public ChickStateName getState() {
        return this.stateName;
    }

    // Setters
    public void setDirection(Direction dir) {
        this.direction = dir;
    }
}
