import Entities.Platform;
import Game.GameObservable;
import Game.GameView;

public class Gui {

    public Gui() {
        GameObservable game = GameObservable.getInstance();
        game.addGameObject(new Platform(0, 620, 50, 1280));
        game.addGameObject(new Platform(0, 520, 100, 50));
        GameView view = new GameView();

    }
}
