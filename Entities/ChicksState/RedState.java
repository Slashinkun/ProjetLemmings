package Entities.ChicksState;

import Entities.Chicks;
import Entities.Direction;

public class RedState extends ChicksState {

    public RedState(Chicks chick) {
        super(chick);
        chick.setDirection(Direction.DESCEND);

    }

    @Override
    public void move() {
    }
}
