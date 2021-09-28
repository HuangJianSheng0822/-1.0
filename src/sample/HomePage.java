package sample;

import MySQL.Shopping;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.SQLException;


public class HomePage  {
    Stage stage;
    public void start() throws Exception {
        Stage primaryStage=new Stage();
        stage=primaryStage;
        VBox vBox=new VBox(20);
        vBox.setStyle("-fx-background-color:#f0ffff");
        vBox.setPadding(new Insets(0,0,50,0));
        vBox.getChildren().add(setFrame());
        vBox.getChildren().add(setTop());
        vBox.getChildren().add(setCenter());
        Scene scene=new Scene(vBox,1700,750);
        primaryStage.getIcons().add(new Image("icon/1-2004121ZS0.png"));
        primaryStage.setTitle("XX商城");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setOpacity(1);//不透明度
        primaryStage.show();
    }



    /**欢迎界面*/
    private HBox setFrame() {
        HBox hBox=new HBox(1400);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.setStyle("-fx-background-color:#808080");
        Text wel=new Text("欢迎来的XX商城");
        Text end=new Text("退出登录");
        /*字体*/
        Font font=Font.font("华文楷体", FontWeight.BOLD, FontPosture.ITALIC,30);
        wel.setFont(font);
        wel.setFill(Color.WHEAT);
        hBox.getChildren().add(wel);
        hBox.getChildren().add(end);
        hBox.setAlignment(Pos.CENTER);
        hBox.setFillHeight(false);
        return hBox;
    }

    /**顶部标签*/
    private HBox setTop() {
        HBox hBox=new HBox(100);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(new ImageView(new Image("icon/狐妖小红娘1 (1).jpg")));
        String[] kind={"9.9包邮","充值中心","拍卖","超市","领券","生活.缴费","购物车","上传商品"};
        for (int i = 0; i < kind.length; i++) {
            Label l=new Label(kind[i]);
            LabelKey.setLabel(l);
            hBox.getChildren().add(l);
            if (l.getText().equals("购物车")){
                l.setOnMousePressed(e->{
                    ShoppingCart shoppingCart=new ShoppingCart();
                    try {
                        stage.close();
                        shoppingCart.start();

                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                });
            }
            if (l.getText().equals("上传商品")){
                l.setOnMousePressed(e->{
                    AddGoods addGoods=new AddGoods();
                    try {
                        stage.close();
                        addGoods.start();

                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                });
            }

        }
//        Label shop=new Label("购物车");
//        LabelKey.setLabel(shop);
        /*一个标签*/
//        shop.setOnMousePressed(e->{
//            ShoppingCart shoppingCart=new ShoppingCart();
//            try {
//                stage.close();
//                shoppingCart.start();
//
//            } catch (Exception exception) {
//                exception.printStackTrace();
//            }
//        });
        Circle circle=new Circle(20);
        hBox.getChildren().add(circle);
        return hBox;
    }

    /**中间页面*/
    private Node setCenter() {
        HBox hBox=new HBox(120);
        hBox.setPadding(new Insets(0,50,10,50));
        VBox v1=new VBox(30);//左侧标签
        Label l1=new Label("查看所有商品");
        v1.getChildren().add(l1);
        LabelKey.setLabel(l1);
        v1.setAlignment(Pos.TOP_CENTER);
        VBox v2=new VBox();//右侧图片
        String[] kind={"图书","手机","水果","男鞋","男装","食品","美妆护肤","玩具","                                                             "};
        for (int i = 0; i < kind.length; i++) {
            Label l=new Label(kind[i]);
            LabelKey.setLabel(l);
            v1.getChildren().add(l);
            l.setOnMousePressed(e->{
                stage.close();
                SelectAs selectAs=new SelectAs(l.getText());
                try {
                    selectAs.start();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            });
        }

        TextField textField=new TextField();
        textField.setOnAction(event -> {
            Shopping shopping=new Shopping();
            try {
                if ((int)shopping.getTradeNews(-1,textField.getText(),null)[0]>0){
                    //System.out.println("SS");
                }else {
                    //System.out.println("66");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        ImageView imageView=new ImageView(new Image("icon/1-1604211614000-L.png"));
        imageView.setFitWidth(50D);
        imageView.setFitHeight(50D);
        HBox hBox1=new HBox();
        hBox1.setAlignment(Pos.CENTER);
        hBox1.getChildren().addAll(textField,imageView);
        v1.getChildren().addAll(hBox1);
        /*所有商品*/
        l1.setOnMousePressed(e->{
            AllShop allShop=new AllShop();
            try {
                stage.close();
                allShop.start();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        /*图片动画*/
        ImageView i1=new ImageView(new Image("icon/16.jpg"));
        ImageView i2=new ImageView(new Image("icon/17.jpg"));
        ImageView i3=new ImageView(new Image("icon/背景图.jpg"));
        v2.getChildren().add(i1);
        EventHandler<ActionEvent> e1=e->{
            v2.getChildren().remove(i1);
            v2.getChildren().add(i2);
        };
        EventHandler<ActionEvent> e2=e->{
            v2.getChildren().remove(i2);
            v2.getChildren().add(i3);
        };
        EventHandler<ActionEvent> e3=e->{
            v2.getChildren().remove(i3);
            v2.getChildren().add(i1);
        };
        Timeline timeline=new Timeline(new KeyFrame(Duration.millis(500),e1),new KeyFrame(Duration.millis(1000),e2),new KeyFrame(Duration.millis(1500),e3));
        timeline.setCycleCount(-1);
        timeline.setRate(0.35);
        timeline.play();
        hBox.getChildren().addAll(v1,v2);
        return hBox;
    }

}
