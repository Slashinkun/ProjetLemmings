package Entities;



public class RedState extends ChicksState {
    
    public RedState(Chicks chick){
        super(chick);
        this.stateName = StateName.RED;
        chick.setDirection(Direction.DESCEND);
        
    }

    @Override
    public void move(){
        
        
        
    }
}
