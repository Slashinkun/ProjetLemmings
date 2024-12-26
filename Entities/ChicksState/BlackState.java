package Entities.ChicksState;

import Entities.Chicks;
import Entities.Direction;

public class BlackState extends ChicksState {

    public BlackState(Chicks chick) {
        super(chick);
        chick.changeDirection();
    }

    public void move() {
        this.chick.setPosX(chick.getPosX() + chick.getDirectionX());
        this.chick.setPosY(chick.getPosY() + chick.getDirectionY());

        if (chick.getDirection() == Direction.DESCEND) {
            chick.addChute(1);
        }
    }
}
