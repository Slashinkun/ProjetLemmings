import Entities.*;
import Game.GameObservable;
import Game.GameView;

public class Gui {

    public Gui() {
        GameObservable game = GameObservable.getInstance();

        // Plateforme de base
        for (int i = 10; i > 0; i--) {
            game.addGameObject(new Platform(100 + (i * 50), 300, 50, 50));
        }
        game.addGameObject(new Platform(550, 200, 50, 50));
        // Lave
        game.addGameObject(new Lava(0, 620, 50, 1280));

        GameView view = new GameView();
        game.registerObserver(view);
        game.seeGameObjects();
    }
}
