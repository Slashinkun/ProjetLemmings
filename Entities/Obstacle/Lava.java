package Entities.Obstacle;

import Entities.EntityType;
import Entities.GameObject;

public class Lava extends GameObject {

    public Lava(int posX, int posY, int height, int width) {
        super(posX, posY, height, width, EntityType.LAVA);

    }

}
