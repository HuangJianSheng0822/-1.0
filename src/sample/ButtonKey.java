package sample;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class ButtonKey {
    public static void setButton(Button button){
        button.setStyle("-fx-background-color: linear-gradient(to bottom,#ABDCFF,#0396FF)  ");
        button.setOnMouseEntered(e->{
            button.setTextFill(Color.WHEAT);
            button.setStyle("-fx-background-color: linear-gradient(to bottom,#FEB692,#EA5455)  ");
        });
        button.setOnMouseExited(e->{
            button.setTextFill(Color.BLACK);
            button.setStyle("-fx-background-color: linear-gradient(to bottom,#ABDCFF,#0396FF)  ");
        });

    }
}
