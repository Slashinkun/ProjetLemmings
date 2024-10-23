import Game.GameObservable;
import Game.GameView;
import Objects.Platform;

public class Gui {
    public Gui(){
        GameObservable game = new GameObservable();
        game.addGameObjects(new Platform(0, 720-100, 1280, 50));
        //game.addGameObjects(new Spawner(10, 10, game));
        //game.addGameObjects(new ChickExit(1000, 720-200, game));
        /*game.addChicks(new BasicChick(100,100));
        game.addChicks(new BasicChick(200, 100));
        game.addChicks(new BasicChick(300, 100));*/
        GameView view1 = new GameView(game);
        
       
        /*game.addGameObjects(new Platform(10, 10, 100, 100));
        game.addGameObjects(new Platform(100, 100, 300, 100));
        game.addGameObjects(new Spawner(200, 300));*/

        /*GamePanel panel = new GamePanel();
        GameView test = new GameView(panel);
        panel.requestFocus();*/
        
    }

    
    
    
}
