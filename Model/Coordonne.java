package Model;

//Cette class sera hérité par poussin, 
//exit et spawner pour éviter les répétitions de fonctions
public abstract class Coordonne {
    private int xCoordonne;
    private int yCoordonne;

    public Coordonne(int xCoordonne, int yCoordonne) {
        this.xCoordonne = xCoordonne;
        this.yCoordonne = yCoordonne;
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

    // Rajouter les setters
}
