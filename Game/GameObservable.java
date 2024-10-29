package Game;
//Observable

import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.Iterator;

import Entities.GameObject;
import Entities.Chicks;
import Entities.Exit;
import Entities.Spawner;

public class GameObservable {
    // SingleTon pattern, on veut qu'un seul observable
    private static GameObservable instance;

    private ArrayList<Observer> observers;
    private ArrayList<GameObject> gameObjects;
    private ArrayList<Chicks> chicks;
    private Spawner spawner;
    private Thread spawnerThread;
    private Exit exit;
    private boolean win;

    private GameObservable() {
        this.observers = new ArrayList<>();
        this.gameObjects = new ArrayList<>();
        this.chicks = new ArrayList<>();
        this.spawner = new Spawner(100, 100, 50, this);
        this.spawnerThread = new Thread(spawner);
        this.exit = new Exit(1000, 520, 20, this);
        gameObjects.add(this.spawner);
        gameObjects.add(this.exit);

    }

    public static GameObservable getInstance() {
        if (instance == null)
            instance = new GameObservable();
        return instance;
    }

    public void updateGame() {
        if (!this.win) {
            spawn();
            updateChicks();
            checkCollision();
            notifyObservers();
        }
    }

    public void spawn() {
        if (!spawnerThread.isAlive() && !this.spawner.getFinished())
            this.spawnerThread.start();
    }

    public void updateChicks() {
        if (!this.chicks.isEmpty())
            for (Chicks chick : this.chicks) {
                chick.move();
            }
    }

    public void checkCollision() {
        if (!this.chicks.isEmpty()) {
            for (int i = 0; i < this.chicks.size(); i++) {
                Chicks chick = this.chicks.get(i);
                for (GameObject object : this.gameObjects) {
                    switch (object.getObjectType()) {
                        case PLATFORM:
                            if (chick.getPosY() == object.getPosY()) {
                                chick.setIsOnFloor(true);
                            } else if ((chick.getPosX() - chick.getWidth() == object.getPosX()
                                    || chick.getPosX() == object.getPosX() - object.getWidth())) {
                                chick.changeDirection();
                            }
                            break;
                        case EXIT:
                            if (chick.getPosX() == object.getPosX() - object.getWidth()) {
                                this.exit.incrementChicksExit();
                                this.win = this.exit.checkWin();
                                this.chicks.remove(chick);
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void unregisterObserver(Observer observer) {
        this.observers.remove(observer);
    }

    private void notifyObservers() {
        for (Observer observer : observers)
            observer.update();
    }

    // Getter
    public int getChickSpawn() {
        return this.spawner.getChicksSpawn();
    }

    public int getChicksExit() {
        return this.exit.getChicksExit();
    }

    public ArrayList<GameObject> getGameObjects() {
        return this.gameObjects;
    }

    public ArrayList<Chicks> getChicks() {
        return this.chicks;
    }

    public boolean getWin() {
        return this.win;
    }

    // Setter
    public void addChicks(Chicks c) {
        this.chicks.add(c);
    }

    public void addGameObject(GameObject obj) {
        this.gameObjects.add(obj);
    }

}
