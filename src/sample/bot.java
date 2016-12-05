package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

/**
 * Created by ALishYera on 23.04.2016.
 */
public class bot {
    ImageView main;
    Image q;
    int r;
    public int generate_direction()
    {
        Random random = new Random();
        return (int)random.nextInt(4);
    }
    public bot()
    {
        Random random = new Random();
        r = random.nextInt(3);
        q = new Image("bot"+r+".png");
        main = new ImageView(q);
        main.setFitHeight(50);
        main.setFitWidth(50);
        main.setTranslateX(7*50+10);
        main.setTranslateY(9*50+10);
    }
    public int getX()
    {
       return  (int)(main.getTranslateX()/50);
    }
    public int getY()
    {
        return  (int)(main.getTranslateY()/50);
    }
    public void moveTop(boolean walls[][])
    {
        int x = this.getX();
        int y = this.getY();
        if(!walls[(int)y-1][(int)x])
        {
            this.main.setTranslateY(main.getTranslateY()-50);
        }
    }
    public void moveRight(boolean walls[][])
    {
        int x = this.getX();
        int y = this.getY();
        if(!walls[(int)y][(int)x+1])
        {
            this.main.setTranslateX(main.getTranslateX()+50);
        }
    }
    public void moveBot(boolean walls[][])
    {
        int x = this.getX();
        int y = this.getY();
        if(!walls[(int)y+1][(int)x])
        {
            this.main.setTranslateY(main.getTranslateY()+50);
        }
    }
    public void moveLeft(boolean walls[][])
    {
        int x = this.getX();
        int y = this.getY();
        if(!walls[(int)y][(int)x-1])
        {
            this.main.setTranslateX(main.getTranslateX()-50);
        }
    }
}
