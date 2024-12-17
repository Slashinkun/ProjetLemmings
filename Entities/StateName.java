package Entities;

import java.awt.Color;

//le nom des jobs des poussins (à modifier)
public enum StateName {
    NORMAL(Color.yellow,"Normal"),RED(Color.red,"Red"),BLACK(Color.BLACK,"Black");

    private Color color;
    private String str;

    StateName(Color color,String name){
        this.color = color;
        this.str = name;
    }

    
    //pour l'affichage
    public Color getStateColor(){
        return this.color;
    }


    //pour l'interface
    @Override
    public String toString(){
        return this.str;
    }

    

}
