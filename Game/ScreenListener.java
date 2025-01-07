package Game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Entities.ChicksState.Chicks;

public class ScreenListener extends MouseAdapter {
    private GameObservable observable;

    public ScreenListener(GameObservable observable) {
        this.observable = observable;
    }

    // quand le joueur clique sur un poussin
    @Override
    public void mouseClicked(MouseEvent e) {
        Chicks chick = this.observable.getChickAt(e.getX(), e.getY());
        if (chick != null) {
            chick.changeState(observable.getCurrentJob());
        }
    }
}
