package Entities.ChicksState;

import Entities.Chicks;
import Entities.Direction;

public class NormalState extends ChicksState {

    public NormalState(Chicks chick) {
        super(chick);
    }

    @Override
    public void move() {
        this.chick.setPosX(chick.getPosX() + chick.getDirectionX());
        this.chick.setPosY(chick.getPosY() + chick.getDirectionY());

        if (chick.getDirection() == Direction.DESCEND) {
            chick.addChute(1);
        }
    }
}
