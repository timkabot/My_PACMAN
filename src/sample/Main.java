package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import org.omg.CORBA.IMP_LIMIT;

import java.io.File;
import java.time.Duration;
import java.util.Scanner;

public class Main extends Application {
    boolean end_of_game = false;
    pacman pac = new pacman();
    bot[] bots = new bot[4] ;
    Timeline intersecting;
    Timeline game_loop;
    ScoreLabel game_over = new ScoreLabel(7*50,7*50);
    boolean [][]walls = new boolean[15][15];
    AudioClip music = new AudioClip(this.getClass().getResource("/game.mp3").toExternalForm());
    ImageView imgs[][] = new ImageView[15][15];
    Group root = new Group();
    Scene main_scene = new Scene(root, 750, 750);
    @Override
    public void start(Stage primaryStage) throws Exception{
        music.setCycleCount(-1);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(main_scene);
        Scanner in = new Scanner(new File("MAP.txt"));
        for(int i=0;i<15;i++)
        {
            for(int j=0;j<15;j++)
            {
                int val = in.nextInt();
                if(val == 1)
                {
                    walls[i][j]=true;
                    imgs[i][j]= new ImageView(new Image("BrickGrey.png"));
                }
                else {
                    imgs[i][j] = new ImageView(new Image("free_block.png"));
                }
                imgs[i][j].setTranslateX(j*50+10);
                imgs[i][j].setTranslateY(i*50+10);
                imgs[i][j].setFitHeight(50);
                imgs[i][j].setFitWidth(50);
                root.getChildren().add(imgs[i][j]);
            }
        }

        main_scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode k = event.getCode();
                double x=(pac.main.getTranslateX()/50);
                double y = (pac.main.getTranslateY()/50);

                if(k == KeyCode.Q && end_of_game==true)
                {
                    root.getChildren().removeAll(game_over);
                    init_game();
                }
                else if(end_of_game){}
                else if(k==KeyCode.UP)
                {
                    if(!walls[(int)y-1][(int)x])
                    {
                        pac.main.setImage(pac.top); pac.moveTop();
                    }

                }
                else if(k==KeyCode.DOWN)
                {
                    if(!walls[(int)y+1][(int)x])
                    {
                        pac.main.setImage(pac.bot); pac.moveBot();
                    }
                }
                else if(k==KeyCode.LEFT)
                {
                    if((int)x-1<0 && ((int)y==7 || (int)y==8))
                    {
                        pac.main.setTranslateX(14*50+10);
                    }
                    else if((int)x-1>=0 && !walls[(int)y][(int)x-1])
                    {
                        pac.main.setImage(pac.left); pac.moveLeft();
                    }

                }
                else if(k==KeyCode.RIGHT)
                {
                    if((int)x+1>14 && ((int)y==7 || (int)y==8))
                {
                    pac.main.setTranslateX(0*50+10);
                }
                else
                    if(!walls[(int)y][(int)x+1])
                    {
                        pac.main.setImage(pac.right); pac.moveRight();
                    }
                }
            }
        });

        game_loop = new Timeline(new KeyFrame(javafx.util.Duration.millis(300), new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {
                for(int i =0;i<4;i++)
                {
                    int direction = bots[i].generate_direction();
                    if(direction == 0){bots[i].moveBot(walls);}
                    if(direction == 1){bots[i].moveTop(walls);}
                    if(direction == 2){bots[i].moveLeft(walls);}
                    if(direction == 3){bots[i].moveRight(walls);}
                }
            }
        }
        ));
        game_loop.setCycleCount(-1);
        intersecting = new Timeline(new KeyFrame(javafx.util.Duration.millis(10), new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {
                if(intersect())
                {
                    end_of_game = true;
                    GAME_OVER();
                    root.getChildren().add(game_over);
                }
            }
        }
        ));
        init_game();
        music.play();

        intersecting.setCycleCount(-1);
        intersecting.play();
        primaryStage.show();
    }
    public void init_game()
    {
        pac.main.setTranslateX(7*50+10);
        pac.main.setTranslateY(13*50+10);
        end_of_game=false;
        game_loop.play();
        try{
            for(int i=0;i<4;i++)
            {root.getChildren().removeAll(bots[i].main);}
        } catch (Exception e ){}
        for(int i=0;i<4;i++)
        {
            bots[i] = new bot();
            root.getChildren().add(bots[i].main);
        }
        root.getChildren().addAll(pac.main);
    }
    public boolean intersect()
    {
        for(int i=0;i<4;i++)
        {
            if(bots[i].getX()==pac.getX() && bots[i].getY()==pac.getY()) return true;
        }
        return false;
    }
    public void GAME_OVER()
    {
        game_loop.stop();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
