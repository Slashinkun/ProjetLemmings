package Entities;

import Game.GameObservable;

//Tout les poussins, les spawner, les plateformes et les exits hériteront de cette classe
public abstract class GameObject {
    protected int posX, posY, height, width;
    protected EntityType entityType;
    protected static GameObservable gameObservable;

    public GameObject(int posX, int posY, int height, int width, EntityType entityType, GameObservable gObservable) {
        this.posX = posX;
        this.posY = posY;
        this.height = height;
        this.width = width;
        this.entityType = entityType;
        gameObservable = gObservable;
    }

    public GameObject(int posX, int posY, int height, int width, EntityType entityType) {
        this.posX = posX;
        this.posY = posY;
        this.height = height;
        this.width = width;
        this.entityType = entityType;
        gameObservable = GameObservable.getInstance();
    }

    // Getters
    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public int[] getPos() {
        return new int[] { this.posX, this.posY };
    }

    public EntityType getObjectType() {
        return this.entityType;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public GameObservable getGame() {
        return gameObservable;
    }
}
