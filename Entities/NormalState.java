package Entities;



public class NormalState extends ChicksState {
    
    public NormalState(Chicks chick){
        super(chick);
        this.stateName = StateName.NORMAL;
    }


    @Override
    public void move(){
        chick.posX += chick.getDirection().getX();
        chick.posY += chick.getDirection().getY();
        
        if (chick.getDirection() == Direction.DESCEND) {
            chick.addChute(1);
        }
    }
}
