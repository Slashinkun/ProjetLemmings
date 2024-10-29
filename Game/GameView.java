package Game;

import javax.swing.JComponent;
import javax.swing.JFrame;

import Entities.Chicks;
import Entities.GameObject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class GameView extends JComponent implements Observer {
    private GameObservable gameObservable;
    private JFrame frame;

    public GameView() {
        this.gameObservable = GameObservable.getInstance();
        this.gameObservable.registerObserver(this);
        this.frame = new JFrame();
        this.frame.setContentPane(this);
        this.frame.setSize(1280, 720);
        this.frame.setResizable(false);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }

    @Override
    public void update() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.GRAY);
        g.drawString("Chicks Spawn : " + this.gameObservable.getChickSpawn(), 25, 25);
        g.drawString("Chicks Exited : " + this.gameObservable.getChicksExit(), 25, 50);

        this.gameObservable.updateGame();

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
                g.setColor(Color.RED);
                g.drawRect(obj.getPosX(), obj.getPosY(), obj.getWidth(), obj.getHeight());
                g.fillRect(obj.getPosX(), obj.getPosY(), obj.getWidth(), obj.getHeight());
                break;
            case SPAWNER:
                drawPolygon(g, Color.WHITE, obj);
                break;
            case EXIT:
                drawPolygon(g, Color.GREEN, obj);
                break;
            default:
        }
    }

    private void drawPolygon(Graphics g, Color color, GameObject obj) {
        int[] ptsX = new int[] { obj.getPosX(), obj.getPosX() + 100, obj.getPosX() + 50 };
        int[] ptsY = new int[] { obj.getPosY(), obj.getPosY(), obj.getPosY() + 50 };
        g.setColor(color);
        g.drawPolygon(ptsX, ptsY, 3);
        g.fillPolygon(ptsX, ptsY, 3);
    }

    // dessiner tout les poussins du jeu
    private void drawChicks(Graphics g, ArrayList<Chicks> chicks) {
        if (!chicks.isEmpty())
            for (int i = 0; i < chicks.size(); i++) {
                drawChick(g, chicks.get(i));
                // System.out.println("Chick drawn");
            }
    }

    private void drawChick(Graphics g, Chicks c) {
        switch (c.getObjectType()) {
            case BASIC_CHICKS:
                g.setColor(Color.YELLOW);
                g.fillRect(c.getPosX(), c.getPosY(), 50, 100);

        }
    }

}
