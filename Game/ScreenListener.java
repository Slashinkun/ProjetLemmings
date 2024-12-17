package Game;

import Entities.Chicks;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ScreenListener implements MouseListener {
    private GameObservable observable;

    public ScreenListener(GameObservable observable){
        this.observable = observable;
    }


    //quand le joueur clique sur un poussin
    @Override
    public void mouseClicked(MouseEvent e) {
        Chicks ck = observable.getChickAt(e.getX(), e.getY());
        if(ck != null){
            ck.changeState(observable.getCurrentJob());
        }
        
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       System.out.println("Mouse entered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Mouse exited");
        
    }
    
}
