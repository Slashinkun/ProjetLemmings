package Entities;

import java.awt.Taskbar.State;
import java.util.Random;

import Entities.ChicksState.BlackState;
import Entities.ChicksState.ChicksState;
import Entities.ChicksState.NormalState;
import Entities.ChicksState.RedState;
import Entities.ChicksState.StateName;

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

    private Platform lasPlatform;

    private ChicksState state = new NormalState(this);
    private StateName stateName = StateName.NORMAL;

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

    public boolean checkCollisionWithExit(Exit exit) {
        if (exit.posY >= super.posY && exit.posY <= super.posY + HEIGHT) {
            if (super.posX >= exit.getPosX() && super.posX <= exit.getPosX() + exit.getWidth())
                return true;
        }
        return false;
    }

    // Fonction pour vérifier les collisions d'un poussin avec une plateforme
    public void checkCollisionWithPlatform(Platform platform) {
        // On vérifie si le poussin a touché la plateforme si ca direction est DESCEND
        if (this.direction == Direction.DESCEND)
            checkIsOnFloor(platform);

        jumpCollisions(platform);
        sideCollisions(platform);
        downCollisions(platform);

        if (lasPlatform != null && checkIsOnFloor(lasPlatform))
            this.direction = this.baseDirection;

        this.lasPlatform = platform;
    }

    // Fonction pour sauter
    public void jumpCollisions(Platform platform) {
        // Si le poussin et la plateforme sont dans la même position et que la
        // plateforme est égale ou inférieur à 50(une case) en hauteur, on fait monter
        // le poussin
        if (platform.getHeight() <= 50 && super.posY + HEIGHT == platform.getPosY() + platform.getHeight()) {
            if ((this.direction == Direction.DROITE && super.posX + WIDTH == platform.getPosX())
                    || (this.direction == Direction.GAUCHE && super.posX == platform.getPosX() + platform.getWidth())) {
                this.posY -= 50;
            }
        }
    }

    // Fonction pour vérifier les collisions avec les côtés
    public void sideCollisions(Platform platform) {
        // Pour les collisions avec des murs, on change juste la direction en appelant
        // changeDirection
        if (super.posY == platform.getPosY()) {
            // Vérification de la position pour X
            if ((this.direction == Direction.DROITE && super.posX + WIDTH == platform.getPosX())
                    || (this.direction == Direction.GAUCHE && super.posX == platform.getPosX() + platform.getWidth())) {
                changeDirection();
                System.out.println("Passe ici Collission");
            }
        }
    }

    public void downCollisions(Platform platform) {
        // Pour faire descendre le poussin quand il a atteint un bout de la plateforme
        if (super.posY + HEIGHT == platform.getPosY()) {
            if (this.direction == Direction.DROITE && super.posX - WIDTH == platform.getPosX() + 1) {
                this.direction = Direction.DESCEND;
            }
            if (this.direction == Direction.GAUCHE && super.posX + WIDTH == platform.getPosX() - 1) {
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

    // pour changer l'etat(metier) du poussin
    public void changeState(StateName stateName) {

        if (this.stateName != stateName) { // on peut pas changer l'etat si c'est deja l'etat actuel
            switch (stateName) {
                case RED:
                    this.state = new RedState(this);
                    this.stateName = StateName.RED;
                    break;
                case BLACK:
                    this.state = new BlackState(this);
                    this.stateName = StateName.BLACK;
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

    public StateName getState() {
        return this.stateName;
    }

    // Setters
    public void setDirection(Direction dir) {
        this.direction = dir;
    }
}
