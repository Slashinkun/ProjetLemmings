package Entities;

public class BlackState extends ChicksState {
    
    public BlackState(Chicks chick){
        super(chick);
        this.stateName = StateName.BLACK;
        chick.changeDirection();
    }

    public void move(){
        chick.posX += chick.getDirection().getX();
        chick.posY += chick.getDirection().getY();
        
        if (chick.getDirection() == Direction.DESCEND) {
            chick.addChute(1);
        }
        
      

        
    }

}
