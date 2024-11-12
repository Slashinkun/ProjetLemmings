import Entities.*;
import Game.GameObservable;
import Game.GameView;

public class Gui {

    public Gui() {
        GameObservable game = GameObservable.getInstance();

        // Plateforme de base
        game.addGameObject(new Platform(100, 200, 50, 1110));
        // Pour vérifier la mort avec 5 cases
        // game.addGameObject(new Platform(0, 520, 50, 600));
        // Pour vérifier les collisions,
        game.addGameObject(new Platform(100, 100, 100, 50));

        // Lave
        game.addGameObject(new Lava(0, 620, 50, 1280));

        GameView view = new GameView();
        game.addObersever(view);
    }
}
