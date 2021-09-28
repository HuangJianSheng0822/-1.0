package sample;

import MySQL.Shopping;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

public class AllShop  {
    Register register=new Register();
    private VBox vb;
    private VBox list;
    private Stage stage;
    public void start() throws Exception {
        Stage primaryStage=new Stage();
        stage=primaryStage;
        VBox vBox=new VBox(20);
        vb=vBox;
        vBox.setStyle("-fx-background-color: linear-gradient(to bottom,#69FF97,#00E4FF)  ");
        vBox.getChildren().add(setFrame());
        Scene scene=new Scene(vBox,800,1000);
        vBox.getChildren().add(numOfPages());
        list=add(1);
        vBox.getChildren().add(list);
        primaryStage.getIcons().add(new Image("icon/1-1604211533370-L.png"));
        primaryStage.setTitle("所有商品");
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(event -> {
            HomePage homePage=new HomePage();
            try {
                homePage.start();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**顶部*/
    private HBox setFrame() {
        HBox hBox=new HBox(500);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.setStyle("-fx-background-color:#808080");
        Text wel=new Text("全部商品");
        /*字体*/
        Font font=Font.font("华文楷体", FontWeight.BOLD, FontPosture.ITALIC,30);
        wel.setFont(font);
        wel.setFill(Color.WHEAT);
        hBox.getChildren().add(wel);
        hBox.setAlignment(Pos.CENTER);
        hBox.setFillHeight(false);
        return hBox;
    }

    /**商品页面*/
    private HBox createCommodity(int id,String url,String name,String price,String shop){
        HBox hBox=new HBox(50);
        hBox.setPadding(new Insets(50,0,0,0));
        hBox.setAlignment(Pos.CENTER);
        ImageView imageView = new ImageView(new Image(url));
        imageView.setFitHeight(100D);
        imageView.setFitWidth(300D);
        imageView.setPreserveRatio(false);
        Label namel=new Label(name);
        Label pricel=new Label(price);
        Label shopl=new Label(shop);
        Label detaill=new Label("详情");
        Button button=new Button("加入购物车");
        ButtonKey.setButton(button);
        button.setOnAction(e->{
            Shopping shopping=new Shopping();
            shopping.addToCart(register.getAccount(),name,true);
        });
        hBox.getChildren().addAll(imageView,namel,pricel,detaill,button);
        return hBox;
    }

    /**页数*/
    private HBox numOfPages(){
        HBox hBox=new HBox(5);
        hBox.setAlignment(Pos.CENTER);
        Button button1=new Button("下一页");
        ButtonKey.setButton(button1);
        for (int i = 1; i < 19; i++) {
            Button button=new Button(i+"");
            ButtonKey.setButton(button);
            hBox.getChildren().add(button);
            button.setOnAction(e->{
                //System.out.println(button.getText());
                int x= Integer.parseInt(button.getText());
                vb.getChildren().remove(list);
                try {

                    list=add((x-1)*5+1);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                vb.getChildren().add(list);
            });
        }

        hBox.getChildren().add(button1);
        /*不理解，单原子*/
        AtomicInteger y= new AtomicInteger(19);
        button1.setOnAction(e->{
            vb.getChildren().remove(list);
            try {
                list=add((y.get() -1)*5+1);
                y.getAndIncrement();
                vb.getChildren().add(list);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        });

        return hBox;
    }

    //添加商品
    private VBox add(int s) throws SQLException, ClassNotFoundException {
        Shopping shopping=new Shopping();
        Object[] objects;
        shopping.getTradeNews(s,null,null);
        VBox vBox=new VBox();
        for (int i = s; i <= s+4; i++) {
            objects=shopping.getTradeNews(i,null,null);
            vBox.getChildren().add(createCommodity((int)objects[0],(String) objects[4],(String) objects[2],String.valueOf(objects[3]),(String) objects[6]));
        }
        return vBox;
    }

}
