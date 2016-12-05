package sample;

import javafx.scene.chart.Axis;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *  Created by ALishYera on 23.04.2016.
 */
public class pacman {
    ImageView main;
    Image top,left,bot,right;
    public pacman()
    {
        main = new ImageView(new Image("right.png"));
        main.setFitHeight(50);
        main.setFitWidth(50);
        main.setTranslateX(7*50+10);
        main.setTranslateY(13*50+10);

        right = new Image("right.png");
        left = new Image("left.png");
        top =new Image("top.png");
        bot = new Image("bot.png");
    }
    public int getX()
    {
        return  (int)(main.getTranslateX()/50);
    }
    public int getY()
    {
        return  (int)(main.getTranslateY()/50);
    }
    public void moveTop()
    {
        main.setTranslateY(main.getTranslateY()-50);
    }
    public void moveRight()
    {
        main.setTranslateX(main.getTranslateX()+50);
    }
    public void moveBot()
    {
        main.setTranslateY(main.getTranslateY()+50);
    }
    public void moveLeft()
    {
        main.setTranslateX(main.getTranslateX()-50);
    }
}
