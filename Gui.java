import Entities.Obstacle.Lava;
import Entities.Obstacle.ObstacleSpawn;
import Entities.Obstacle.Obstacles;
import Game.GameObservable;
import Game.GameView;

public class Gui {

    public Gui() {
        GameObservable game = GameObservable.getInstance();

        // Plateforme de base

        for (int i = 0; i < 8; i++) {
            game.addGameObject(new Obstacles(150 + (i * 50), 300, 50, 50, false));
        }
        for (int i = 0; i < 9; i++) {
            game.addGameObject(new Obstacles(550 + (i * 50), 300, 50, 50, true));
        }
        game.addGameObject(new ObstacleSpawn(1000, 300, 50, 50));

        for (int i = 0; i < 10; i++) {
            game.addGameObject(new Obstacles(550 + (i * 50), 350, 50, 50, true));
        }
        for (int i = 0; i < 10; i++) {
            game.addGameObject(new Obstacles(550 + (i * 50), 400, 50, 50, true));
        }
        for (int i = 0; i < 10; i++) {
            game.addGameObject(new Obstacles(550 + (i * 50), 450, 50, 50, true));
        }
        for (int i = 0; i < 10; i++) {
            game.addGameObject(new Obstacles(550 + (i * 50), 500, 50, 50, true));
        }
        for (int i = 0; i < 10; i++) {
            game.addGameObject(new Obstacles(i * 50, 550, 50, 50, true));
        }

        for (int i = 0; i < 4; i++) {
            game.addGameObject(new ObstacleSpawn(1050 + (i * 50), 500, 50, 50));
        }

        // Enlever cette obstacle pour tester les floaters
        game.addGameObject(new Obstacles(100, 200, 100, 50, true));

        game.addGameObject(new Obstacles(300, 200, 100, 50, true));
        game.addGameObject(new Obstacles(350, 200, 100, 50, true));
        game.addGameObject(new Obstacles(400, 200, 100, 50, true));
        game.addGameObject(new Obstacles(0, 450, 100, 50, true));

        // Lave
        game.addGameObject(new Lava(0, 620, 50, 1280));

        GameView view = new GameView();
        game.registerObserver(view);
        game.seeGameObjects();
    }
}
