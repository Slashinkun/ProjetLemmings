package Entities.ChicksState;

import Entities.Direction;
import Entities.Obstacle.ObstacleType;
import Entities.Obstacle.Obstacles;

public class TunnelierState extends ChicksState {
    private int blockDestroyed;

    public TunnelierState(Chicks chick) {
        super(chick);
        this.blockDestroyed = 0;
    }

    @Override
    public void move() {
        Obstacles obstacle;
        if (chick.getDirection() == Direction.DROITE)
            obstacle = gameObservable.getObstaclesForTunnelier(chick.getPosX() + chick.getWidth(), chick.getPosY());
        else
            obstacle = gameObservable.getObstaclesForTunnelier(chick.getPosX() - chick.getWidth(), chick.getPosY());

        if (obstacle != null && obstacle.getIsDestructible()) {
            if (obstacle.getObstacleType() == ObstacleType.SPAWN)
                gameObservable.removeChicks(chick);
            obstacle.action();
            blockDestroyed++;
        }
        chick.setPosY(chick.getPosY() + chick.getDirectionY());
        chick.setPosX(chick.getPosX() + chick.getDirectionX());
        if (chick.getDirection() == Direction.DESCEND) {
            chick.addChute(1);
        }

        if (blockDestroyed >= 5)
            chick.changeState(ChickStateName.NORMAL);
    }

}
