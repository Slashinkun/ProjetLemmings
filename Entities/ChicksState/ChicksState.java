package Entities.ChicksState;

import Game.GameObservable;

public abstract class ChicksState {
    protected Chicks chick;
    protected GameObservable gameObservable = GameObservable.getInstance();

    protected ChicksState(Chicks chick) {
        this.chick = chick;
    }

    public abstract void move();

}
