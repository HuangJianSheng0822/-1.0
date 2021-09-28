package sample;

import MySQL.Account;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.sql.SQLException;

public class Register extends Application  {
    Account account=new Account();
    private static String accnum;
    private static String possnum;
    public Stage stage;
    public String getAccount() {
        return accnum;
    }

    public void setAccnum(String accnum) {
        this.accnum = accnum;
    }

    public String getPossnum() {
        return possnum;
    }

    public void setPossnum(String possnum) {
        this.possnum = possnum;
    }

    public void start(Stage primaryStage) throws Exception {
        this.stage=primaryStage;
        GridPane g1=new GridPane();
        g1.setPadding(new Insets(50,50,50,50));
        g1.setVgap(20);
        g1.setHgap(20);
        g1.setAlignment(Pos.TOP_CENTER);
        /*账号密码*/
        g1.addRow(0,setAccAndPass()[0]);
        g1.addRow(1,setAccAndPass()[1]);
        /*登录注册*/
        g1.add(setReAndLo(),0,3);
        g1.setStyle("-fx-background-color:linear-gradient(to bottom,#EE9AE5,#5961F9)");
        FadeTransition fadeTransition=new FadeTransition(Duration.millis(5000),g1);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0.7);
        fadeTransition.setAutoReverse(true);
        fadeTransition.setCycleCount(-1);
        fadeTransition.play();
        Scene scene=new Scene(g1,430,712);
        primaryStage.getIcons().add(new Image("icon/1-1912302043260-L.png"));
        primaryStage.setTitle("登录");
        primaryStage.setScene(scene);
        primaryStage.setOpacity(1);//不透明度
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    /**账号密码*/
    public HBox[] setAccAndPass(){
        HBox[] hBoxes=new HBox[2];
        Text account=new Text("账号");
        Text password=new Text("密码");
        TextField acc=new TextField();
        acc.setPromptText("输入完成请单击Enter");
        PasswordField pss=new PasswordField();
        pss.setPromptText("输入完成请单击Enter");
        HBox a=new HBox(20);
        a.getChildren().addAll(account,acc);
        hBoxes[0]=a;
        HBox p=new HBox(20);
        p.getChildren().addAll(password,pss);
        hBoxes[1]=p;
        acc.setOnAction(e-> setAccnum(acc.getText()));
        pss.setOnAction(e-> setPossnum(pss.getText()));
        return hBoxes;
    }

    /**登录注册*/
    public HBox setReAndLo(){
        Button re=new Button("注册");
        Button lo=new Button("登录");
        ButtonKey.setButton(re);
        ButtonKey.setButton(lo);
        HBox x=new HBox(150);
        x.getChildren().addAll(re,lo);
        x.setAlignment(Pos.BASELINE_CENTER);

        /*注册*/
        re.setOnAction(e->{
            Enroll enroll=new Enroll();
            try {
                enroll.start();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        /*登录*/
        lo.setOnAction(e->{
            int flag=IsCorrect(getAccount(),getPossnum());
            if (flag==1) {//成功
                stage.close();
                HomePage homePage=new HomePage();
                try {
                    homePage.start();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }else if (flag==0){
                error("密码错误");
            }else {
                error("账号不存在");
            }

        });
        return x;
    }

    /**账号状态*/
    private int IsCorrect(String s, String s1) {
        int flag=-1;
        try {
            flag= account.testAccount(s,s1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return flag;
    }

    public void error(String s){
        Stage stage=new Stage();
        StackPane stackPane=new StackPane();
        Label label=new Label(s);
        Font font=Font.font("华文楷体", FontWeight.BOLD, FontPosture.ITALIC,20);
        label.setFont(font);
        label.setTextFill(Color.RED);
        stackPane.getChildren().add(label);
        Scene scene=new Scene(stackPane,100,40);
        stage.initStyle(StageStyle.UTILITY);
        //stage.setTitle(s);
        stage.setScene(scene);
        stage.setOpacity(1);//不透明度
        stage.show();
    }

}
