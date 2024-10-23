package Model;

public class Spawner extends Coordonne {
    private int nmbrPoussinApparu;
    private int nmbrPoussinAApparaitre;

    public Spawner(int nmbrPoussinAApparaitre, int xCoordonne, int yCoordonne) {
        super(xCoordonne, yCoordonne);
        this.nmbrPoussinAApparaitre = nmbrPoussinAApparaitre;
        this.nmbrPoussinApparu = 0;
    }

    public int getNmbrPoussinApparu() {
        return this.nmbrPoussinApparu;
    }

    public int getNmbrePoussinAAparraitre() {
        return this.nmbrPoussinAApparaitre;
    }

    // Rajouter les setters
}
