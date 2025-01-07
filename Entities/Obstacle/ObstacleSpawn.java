package Entities.Obstacle;

public class ObstacleSpawn extends Obstacles {

    public ObstacleSpawn(int posX, int posY, int height, int width) {
        super(posX, posY, height, width, true, ObstacleType.SPAWN);
    }

    @Override
    public void action() {
        gameObservable.removeGameObject(this);
        Obstacles ob1 = new Obstacles(getPosX(), getPosY() - 50, 50, 50, true);
        Obstacles ob2 = new Obstacles(getPosX(), getPosY() + 50, 50, 50, true);
        Obstacles ob3 = new Obstacles(getPosX() - 50, getPosY(), 50, 50, true);
        Obstacles ob4 = new Obstacles(getPosX() + 50, getPosY(), 50, 50, true);
        gameObservable.addGameObject(ob1);
        gameObservable.addGameObject(ob2);
        gameObservable.addGameObject(ob3);
        gameObservable.addGameObject(ob4);
    }
}
