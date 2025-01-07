package Entities.Obstacle;

import Entities.EntityType;
import Entities.GameObject;

public class Obstacles extends GameObject {
    private boolean isDestructible;
    private ObstacleType type;

    public Obstacles(int posX, int posY, int height, int width, boolean isDestructible) {
        super(posX, posY, height, width, EntityType.OBSTACLE);
        this.isDestructible = isDestructible;
        if (isDestructible)
            this.type = ObstacleType.NORMAL;
        else
            this.type = ObstacleType.INDESTRUCTIBLE;
    }

    public Obstacles(int posX, int posY, int height, int width, boolean isDestructible, ObstacleType type) {
        super(posX, posY, height, width, EntityType.OBSTACLE);
        this.isDestructible = isDestructible;
        this.type = type;
    }

    public void action() {
        if (isDestructible) {
            gameObservable.removeGameObject(this);
        }
    }

    public ObstacleType getObstacleType() {
        return this.type;
    }

    public boolean getIsDestructible() {
        return this.isDestructible;
    }
}
