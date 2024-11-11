package Entities;

import Game.GameObservable;

public class Spawner extends GameObject implements Runnable {
    private static final int HEIGHT = 50;
    private static final int WIDTH = 100;
    // Nombre de poussin a spawné
    private int limitSpawn;
    // Nombre de poussin spawné
    private int chicksSpawn;
    // Si le spawner a fini ou pas
    private boolean isFinished;

    public Spawner(int posX, int posY, int limitSpawn, GameObservable gameObservable) {
        super(posX, posY, HEIGHT, WIDTH, EntityType.SPAWNER, gameObservable);
        this.chicksSpawn = 0;
        this.limitSpawn = limitSpawn;
        this.isFinished = false;
    }

    @Override
    public void run() {
        if (this.chicksSpawn == 0) {
            for (int i = 0; i <= limitSpawn; i++) {
                gameObservable.addChicks(new Chicks(posX, posY + 10));
                this.chicksSpawn++;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        this.isFinished = true;
    }

    // Spawn
    public void spawnChick() {

    }

    // Getters
    public int getChicksSpawn() {
        return this.chicksSpawn;
    }

    public int getLimitSpawn() {
        return this.limitSpawn;
    }

    public boolean getFinished() {
        return this.isFinished;
    }

}
