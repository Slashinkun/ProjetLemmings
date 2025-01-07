package Entities.ChicksState;

public class FloaterState extends ChicksState {

    public FloaterState(Chicks chick) {
        super(chick);
    }

    @Override
    public void move() {
        this.chick.setPosX(chick.getPosX() + chick.getDirectionX());
        this.chick.setPosY(chick.getPosY() + chick.getDirectionY());

    }

}
