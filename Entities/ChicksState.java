package Entities;

public abstract class ChicksState {
    protected Chicks chick;
    protected StateName stateName;

    protected ChicksState(Chicks chick){
        this.chick = chick;
    }

    public abstract void move();

    public StateName getStateName(){
        return this.stateName;
    }
}
