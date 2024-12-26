package Game;

import Entities.Chicks;
import Entities.GameObject;
import Entities.ChicksState.StateName;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class GameView extends JComponent implements Observer {
    private GameObservable gameObservable;
    private JFrame frame;
    private final int FRAME_PER_SEC = 20;

    public GameView() {
        this.gameObservable = GameObservable.getInstance();
        this.gameObservable.registerObserver(this);
        this.frame = new JFrame();
        this.frame.setContentPane(this);
        this.frame.setSize(1280, 720);
        this.frame.setResizable(false);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
        this.frame.addMouseListener(new ScreenListener(gameObservable));
        this.frame.addKeyListener(new JobsListener(gameObservable));
        frame.setTitle("Chicks");
    }

    @Override
    public void update() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        try {
            Thread.sleep(100 / FRAME_PER_SEC);
        } catch (InterruptedException ex) {
        }
        // On met à jour le gameObservable
        gameObservable.updateGame();

        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.GRAY);
        // Nombre de poussin spawné
        g.drawString("Chicks released : " + this.gameObservable.getChickSpawn() + "/"
                + this.gameObservable.getChicksLimitSpawn(), 25, 25);
        // Nombre de poussin qui sont sorties
        g.drawString("Chicks saved : " + this.gameObservable.getChicksExit() + "/"
                + this.gameObservable.getNumChickExitedForWin(), 25, 50);
        // Nombre de poussin qui sont en vie dehord
        g.drawString("Chicks out : " + this.gameObservable.getNumberOfChicks(), 25, 75);

        drawJobsWheel(g);

        // S'il a gagné
        if (this.gameObservable.getWin() == true) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("impact", Font.BOLD, 50));
            g.drawString("You have won", 500, 300);
            return;
        }

        drawObjects(g, this.gameObservable.getGameObjects());
        drawChicks(g, this.gameObservable.getChicks());

    }

    // dessiner tout les objets du jeu
    private void drawObjects(Graphics g, ArrayList<GameObject> objects) {
        for (GameObject obj : objects) {
            drawObject(g, obj);
        }
    }

    // pour dessiner un objet du jeu
    private void drawObject(Graphics g, GameObject obj) {
        switch (obj.getObjectType()) {
            case PLATFORM:
                Color color = new Color(160, 82, 45);
                drawRectangle(g, color, obj);
                g.setColor(color.pink);
                g.drawRect(obj.getPosX(), obj.getPosY(), obj.getWidth(), obj.getHeight());
                break;
            case SPAWNER:
                drawPolygon(g, Color.WHITE, obj);
                break;
            case EXIT:
                drawPolygon(g, Color.GREEN, obj);
                break;
            case LAVA:
                drawRectangle(g, Color.RED, obj);
                break;
            default:
                break;
        }
    }

    // Pour la plateforme, la lave et aussi les futures obstacles
    private void drawRectangle(Graphics g, Color color, GameObject obj) {
        g.setColor(color);
        g.drawRect(obj.getPosX(), obj.getPosY(), obj.getWidth(), obj.getHeight());
        g.fillRect(obj.getPosX(), obj.getPosY(), obj.getWidth(), obj.getHeight());
    }

    // Pour le spawner et l'exit, on dessine un polygon
    private void drawPolygon(Graphics g, Color color, GameObject obj) {
        int[] ptsX = new int[] { obj.getPosX(), obj.getPosX() + 50, obj.getPosX() + 25 };
        int[] ptsY = new int[] { obj.getPosY(), obj.getPosY(), obj.getPosY() + 50 };
        g.setColor(color);
        g.drawPolygon(ptsX, ptsY, 3);
        g.fillPolygon(ptsX, ptsY, 3);
    }

    // Prend le tableau des poussins et les dessinent
    private void drawChicks(Graphics g, ArrayList<Chicks> chicks) {
        if (!chicks.isEmpty())
            for (int i = 0; i < chicks.size(); i++) {
                drawChick(g, chicks.get(i));
            }
    }

    // Dessine un poussin
    private void drawChick(Graphics g, Chicks c) {
        g.setColor(getColorOnState(c.getState()));
        g.fillRect(c.getPosX(), c.getPosY(), c.getWidth(), c.getHeight());
    }

    // dessine l'interface pour choisir le metier du poussin (le truc milleu à a
    // gauche)
    private void drawJobsWheel(Graphics g) {

        for (int i = 0; i < gameObservable.getNbJobs(); i++) {
            g.setColor(Color.gray);
            g.drawRect(0, 100 * (i + 1), 100, 100);
            g.setColor(Color.white);
            g.drawString(gameObservable.getJobsList()[i].toString(), 10, 130 * (i + 1));
            g.setColor(getColorOnState(gameObservable.getJobsList()[i]));
            g.fillRect(50, 130 * (i + 1), 25, 25);
        }

        g.setColor(Color.green);

        g.drawRect(0, 100 * (gameObservable.getCurrentJobIndex() + 1), 100, 100);
    }

    private static Color getColorOnState(StateName state) {
        switch (state) {
            case RED:
                return Color.RED;
            case BLACK:
                return Color.BLACK;
            default:
                return Color.YELLOW;
        }
    }

}
