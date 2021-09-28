package sample;


import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class LabelKey {
    public static void setLabel(Label label){
        Font font=Font.font("华文楷体", FontWeight.BOLD, FontPosture.ITALIC,20);
        Font font1=Font.font("华文楷体", FontWeight.BOLD, FontPosture.ITALIC,25);
        label.setOnMouseEntered(e->{
            label.setTextFill(Color.RED);
            label.setFont(font1);
        });
        label.setOnMouseExited(e->{
            label.setTextFill(Color.BLACK);
            label.setFont(font);
        });

    }
}
