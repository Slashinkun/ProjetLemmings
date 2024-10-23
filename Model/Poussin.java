package Model;

//Abstract vu que les roles vont l'hériter plus tard
public abstract class Poussin {
    private int xCoordonne;
    private int yCoordonne;
    private boolean isAlive;

    // La xDirection est soit D pour Droite, ou G pour Gauche, c'est un enum crée en
    // bas
    private XDirection xDirection;
    // La xDirection est soit H pour HAUT, ou B pour Bas
    /*
     * Le poussin peut avoir peut tomber ou monter et aller à droite ou à gauche en
     * même temps, c'est pour ça qu'on les sépare
     */
    private YDirection yDirection;
    // Vitesse de déplacement du poussin, même pour tout les poussins
    private static final int VITESSE = 1;

    public Poussin(int x, int y, XDirection xDir, YDirection yDir) {
        this.xCoordonne = x;
        this.yCoordonne = y;
        this.xDirection = xDir;
        this.yDirection = yDir;
        this.isAlive = true;
    }

    public int getX() {
        return this.xCoordonne;
    }

    public int getY() {
        return this.yCoordonne;
    }

    public int[] getCoordonne() {
        return new int[] { this.xCoordonne, this.yCoordonne };
    }

    public boolean getAlive() {
        return this.isAlive;
    }

    public XDirection getXdirection() {
        return this.xDirection;
    }

    public YDirection getYdirection() {
        return this.yDirection;
    }
}

enum XDirection {
    DROITE, GAUCHE
};

enum YDirection {
    MONTE, TOMBE
}
