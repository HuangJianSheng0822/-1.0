package test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * 测试窗口状态
 */
public class T2 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane=new Pane();
        pane.getChildren().add(new Button("66"));
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
        primaryStage.setOnCloseRequest(e->System.out.println(666));
    }
}
