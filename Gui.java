import Entities.*;
import Game.GameObservable;
import Game.GameView;

public class Gui {

    public Gui() {
        GameObservable game = GameObservable.getInstance();
        game.addGameObject(new Platform(0, 620, 50, 500));
        game.addGameObject(new Lava(0, 670, 50, 1280));
        //game.addGameObject(new Platform(100, 520, 50, 600));
        //game.addGameObject(new Platform(0, 520, 100, 50));
        GameView view = new GameView();
        game.addObersever(view);
    }
}
