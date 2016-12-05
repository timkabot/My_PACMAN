package sample;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Created by ALishYera on 23.04.2016.
 */
public class ScoreLabel extends Pane {

    Text status = new Text("GAME OVER");
    public ScoreLabel(double x, double y) {
        setPrefHeight(200);
        setPrefWidth(500);
        setTranslateX(x - 310);

        setTranslateY(y + 10);
        setStyle("-fx-background-color: #RED;"
                + "-fx-background-radius:20px");
        setOpacity(0.8);
        status.setTranslateY(50);
        status.setTranslateX(100);
        getChildren().addAll(status);
        status.setFont(Font.font(Font.getDefault().getName(), FontWeight.BOLD, 75));
        status.setFill(new Color(107 / 255.0, 162 / 255.0, 252 / 255.0, 1.0));
    }

    public void setText(String message) {
        status.setText(message);

    }
}
