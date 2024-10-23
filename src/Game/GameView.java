package Game;
import Entities.BasicChick;
import Entities.Entity;
import Objects.GameObject;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class GameView extends JComponent implements CObserver {

    private GameObservable observable;
    private JFrame frame;
    private int imageFrame;
    private long lastCheck;
    

    public GameView(GameObservable observable){
        this.observable = observable;
        observable.registerObserver(this);
        frame = new JFrame();
        frame.setContentPane(this);
        frame.setSize(1280, 720);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

   


    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        g.drawString(String.valueOf(observable.getChickSpawn()), 0, 200);
        
        imageFrame++;
        if(System.currentTimeMillis() - lastCheck >= 1000){
            lastCheck = System.currentTimeMillis();
            System.out.println("FPS: " + imageFrame );
            imageFrame = 0;
        }


        if(!observable.checkWin()){
            observable.updateGame();

        /*observable.spawn();
        
        observable.updateChicks(); //update chicks physics
        observable.checkCollision(); //collision*/
        
        //drawing objects
        drawObjects(g, observable.getObjects());
        drawChicks(g, observable.getChicks());
       
        drawHitboxes(g); //debug
        repaint(); //loop 
        }else{
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 1280, 720);
        }
        
        
        
       
    }

    

    //pour dessiner un objet du jeu
    private void drawObject(Graphics g,GameObject obj){
        switch(obj.getType()){
            case "Platform" :
            g.setColor(Color.RED);
            g.drawRect(obj.getPosX(), obj.getPosY(), obj.getWidth(), obj.getHeight());
            g.fillRect(obj.getPosX(), obj.getPosY(), obj.getWidth(), obj.getHeight());
            break;
            case "Spawner":
                int[] ptsSpawnX  = new int[]{obj.getPosX(),obj.getPosX()+100,obj.getPosX()+50};
                int [] ptsSpawnY =  new int[]{obj.getPosY(),obj.getPosY(),obj.getPosY()+50};
                g.setColor(Color.white);
                g.drawPolygon(ptsSpawnX, ptsSpawnY, 3);
                g.fillPolygon(ptsSpawnX, ptsSpawnY, 3);
                break;
            case "ChickExit":
                int[] ptsExitX  = new int[]{obj.getPosX(),obj.getPosX()+100,obj.getPosX()+50};
                int [] ptsExitY =  new int[]{obj.getPosY(),obj.getPosY(),obj.getPosY()+50};
                g.setColor(Color.green);
                g.drawPolygon(ptsExitX, ptsExitY, 3);
                g.fillPolygon(ptsExitX, ptsExitY, 3);
            default:
        }
       
        
       
    }


    //dessiner tout les objets du jeu
    private void drawObjects(Graphics g,List<GameObject> objects){
        for(GameObject obj : objects){
            drawObject(g, obj);
        }
    }


    //dessiner tout les poussins du jeu
    private void drawChicks(Graphics g, List<BasicChick> entities){
        if(!entities.isEmpty())
        for (BasicChick entity : entities) {
            drawEntity(g, entity);
            //System.out.println("Chick drawn");
        }
    }

    private void drawEntity(Graphics g,Entity ent){
        switch(ent.getType()){
            case "BasicChick":
            g.setColor(Color.YELLOW);
            g.fillRect(ent.getPosX(), ent.getPosY(), 50, 100);
            
        }
    }



    //pour les boites pour les collisions c'est pour debuguer
    private void drawHitboxes(Graphics g){
        chicksHitbox(g);
        objHitbox(g);
    }

    private void chicksHitbox(Graphics g){
        for (Entity ck : observable.getChicks()) {
            g.setColor(Color.pink);
        
            g.drawRect((int)ck.getHitbox().getX(), (int)ck.getHitbox().getY(), (int)ck.getHitbox().getWidth(), (int)ck.getHitbox().getHeight());
        }
    }

    private void objHitbox(Graphics g){
        for (GameObject obj : observable.getObjects()) {
            g.setColor(Color.pink);
        
            g.drawRect((int)obj.getHitbox().getX(), (int)obj.getHitbox().getY(), (int)obj.getHitbox().getWidth(), (int)obj.getHitbox().getHeight());
        }
    }

    @Override
    public void update(){
        repaint();
    }
}
