package Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JobsListener extends KeyAdapter {

    private GameObservable gameObservable;

    public JobsListener(GameObservable observable) {
        this.gameObservable = observable;
    }

    // quand le joueur appuie sur une touche, on change l'index de la roue
    // ici c'est haut et bas
    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                this.gameObservable.changeWheel(1);
                break;
            case KeyEvent.VK_DOWN:
                this.gameObservable.changeWheel(-1);
        }
    }

}
