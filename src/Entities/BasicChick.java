package Entities;

import java.awt.Rectangle;





//c'est le poussin au chomage
public class BasicChick extends Entity {

    

    //constructeur
    public BasicChick(int posX,int posY){
        super(posX, posY,"BasicChick");
        hitbox = new Rectangle(posX,posY,50, 100);

        
    }


   

    
    

    //pour bouger le poussin, il est appelé par l'observable pour mettre a jour le jeu
    public void moveChick(){
        if(!is_on_floor){ // si il touche pas le sol , on le fait tomber
            posY += gravity;
            hitbox.setLocation(posX, posY + gravity);
        }else{
            posX += 1;
            hitbox.setLocation(posX + 1, posY);
        }
        
    }

    public void is_on_floor(){
        //System.out.println("Collision worked");
        is_on_floor = true;
    }
    

    @Override
    public void update(){
        repaint();
    }
}