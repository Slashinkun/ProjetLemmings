package Game;
//Observable

import Entities.Chicks;
import Entities.Exit;
import Entities.GameObject;
import Entities.Platform;
import Entities.Spawner;
import Entities.ChicksState.StateName;
import java.util.ArrayList;

//On utilise le singleton pattern ici en même temps que Observer/Observable pattern vu qu'on veut qu'un seul GameObservable
public class GameObservable {
    private static GameObservable instance;

    // Liste d'observer
    private ArrayList<Observer> observers;
    private ArrayList<GameObject> gameObjects;
    private ArrayList<Chicks> chicks;
    // Le spawner est un runnable vu qu'on veut qu'il spawn un poussin tout les 3
    // secondes sans que ça arrete le programme
    private Spawner spawner;
    private Thread spawnerThread;
    private Exit exit;
    private boolean win;

    private StateName[] jobs = { StateName.RED, StateName.BLACK };
    private int currentJobs = 0;

    private GameObservable() {
        this.observers = new ArrayList<>();
        this.gameObjects = new ArrayList<>();
        this.chicks = new ArrayList<>();
        this.spawner = new Spawner(200, 50, 50, this);
        this.spawnerThread = new Thread(spawner);
        this.exit = new Exit(1200, 470, 20, this);
        this.win = false;
        gameObjects.add(this.spawner);
        gameObjects.add(this.exit);
        updateGame();
    }

    // Fonction getInstances (SingleTon Pattern)
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
        // On démarre le SpawnerThread si il est pas lancer
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
                for (int y = 0; y < this.gameObjects.size(); y++) {
                    GameObject object = this.gameObjects.get(y);
                    switch (object.getObjectType()) {
                        case PLATFORM:
                            chick.checkCollisionWithPlatform((Platform) object);
                            break;
                        case EXIT:
                            if (chick.checkIsOnFloor(object)) {
                                this.exit.incrementChicksExit();
                                this.win = this.exit.checkWin();
                                this.chicks.remove(chick);
                            }
                            break;
                        case LAVA:
                            if (chick.checkIsOnFloor(object))
                                this.chicks.remove(chick);
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    // pour avoir le poussin où le joueur a cliqué
    public Chicks getChickAt(int posX, int posY) {
        for (Chicks chick : chicks) {
            if (posX > chick.getPosX() && posX < chick.getPosX() + chick.getWidth()
                    && posY > chick.getPosY() && posY < chick.getPosY() + chick.getHeight()) {
                return chick;
            }
        }
        return null;
    }

    // Observer pattern
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
    public int getChicksLimitSpawn() {
        return this.spawner.getLimitSpawn();
    }

    public int getNumChickExitedForWin() {
        return this.exit.getNumberOfChicksExitForWin();
    }

    public int getChickSpawn() {
        return this.spawner.getChicksSpawn();
    }

    public int getChicksExit() {
        return this.exit.getChicksExit();
    }

    public ArrayList<Chicks> getChicks() {
        return this.chicks;
    }

    public int getNumberOfChicks() {
        return this.chicks.size();
    }

    public ArrayList<GameObject> getGameObjects() {
        return this.gameObjects;
    }

    public boolean getWin() {
        return this.win;
    }

    // Setter
    public void addChicks(Chicks c) {
        this.chicks.add(c);
    }

    public void removeChicks(Chicks c) {
        this.chicks.remove(c);
    }

    public void addGameObject(GameObject obj) {
        gameObjects.add(obj);
    }

    // pour debug pour afficher tout les obstacles et leurs positions
    public void seeGameObjects() {
        for (GameObject obj : gameObjects) {
            System.out.println(obj.getObjectType() + "(" + obj.getPosX() + "," + obj.getPosY() + ')');
        }
    }

    // pour changer le type d'etat choisi par le joueur avec les fleches
    public void changeWheel(int value) {
        if (this.currentJobs == 0 && value == -1) {
            this.currentJobs = jobs.length - 1;
        } else if (this.currentJobs == jobs.length - 1 && value == 1) {
            this.currentJobs = 0;
        } else {
            this.currentJobs += value;
        }

        System.out.println(jobs[currentJobs]);

    }

    // pour avoir l'etat choisi par le joueur
    public StateName getCurrentJob() {
        return jobs[currentJobs];
    }

    // pour avoir l'index de l'etat choisi par le joueur
    public int getCurrentJobIndex() {
        return this.currentJobs;
    }

    // pour avoir le nombre de metier disponible
    public int getNbJobs() {
        return this.jobs.length;
    }

    // pour avoir la liste des etats disponible dans le niveau/jeu
    public StateName[] getJobsList() {
        return this.jobs;
    }

}
