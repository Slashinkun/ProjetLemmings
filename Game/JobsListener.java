package Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class JobsListener extends KeyAdapter {
    
    private GameObservable gObservable;

    public JobsListener(GameObservable observable){
        this.gObservable = observable;
    }


    //quand le joueur appuie sur une touche, on change l'index de la roue
    //ici c'est haut et bas
    @Override
    public void keyPressed(KeyEvent e){
        super.keyPressed(e);
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                gObservable.changeWheel(1);
                break;
            case KeyEvent.VK_DOWN:
                gObservable.changeWheel(-1);
        }
    }

}
