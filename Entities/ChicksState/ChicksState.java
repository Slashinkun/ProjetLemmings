package Entities.ChicksState;

import Entities.Chicks;

public abstract class ChicksState {
    protected Chicks chick;

    protected ChicksState(Chicks chick) {
        this.chick = chick;
    }

    public abstract void move();
}
