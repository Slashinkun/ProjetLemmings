package Game;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    private int posX,posY = 100;
    private int xDir = 1,yDir =1;
    
    public GamePanel(){
        
    }

    public void setPos(int posX,int posY){
        this.posX = posX;
        this.posY = posY;
    }

    public void changePosX(int value){
        this.posX += value;
        repaint();
    }

    public void changePosY(int value){
        this.posY += value;
        repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //g.setColor(Color.BLUE);
        updateRectangle();
        g.fillRect(posX, posY, 200, 50);
        repaint();
        
    }

    private void updateRectangle(){
        posX+= xDir;
        if(posX > 400 || posX < 0){
            xDir*=-1;
        }

        posY += yDir;
        if(posY > 400 || posY < 0){
            yDir*= -1;
        }

        
    }
}
