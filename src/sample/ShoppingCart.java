package sample;

import MySQL.AccGoods;
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
import java.util.ArrayList;

public class ShoppingCart  {
    Shopping shopping=new Shopping();
    Register register=new Register();
    Stage primaryStage;

    public void start() throws Exception {
        Stage primaryStage=new Stage();
        this.primaryStage=primaryStage;
        VBox vBox=new VBox(20);
        vBox.setStyle("-fx-background-color: linear-gradient(to bottom,#3C8CE7,#00EAFF)  ");
        vBox.getChildren().add(setFrame());
        vBox.getChildren().addAll(getShoppingCart());
        Scene scene=new Scene(vBox,800,1000);
        primaryStage.getIcons().add(new Image("icon/1-1F1191310490-L.png"));
        primaryStage.setOnCloseRequest(e->{
            HomePage homePage=new HomePage();
            try {
                homePage.start();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setTitle("购物车");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**顶部*/
    private HBox setFrame() {
        HBox hBox=new HBox(500);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.setStyle("-fx-background-color:#808080");
        Text wel=new Text("购物车");

        /*字体*/
        Font font=Font.font("华文楷体", FontWeight.BOLD, FontPosture.ITALIC,30);
        wel.setFont(font);
        wel.setFill(Color.WHEAT);
        hBox.getChildren().add(wel);
        hBox.setAlignment(Pos.CENTER);
        hBox.setFillHeight(false);
        return hBox;
    }

    /**构建商品页面*/
    private HBox createCommodity(String url, String name, String price, String quantity){
        HBox hBox=new HBox(60);
        hBox.setPadding(new Insets(10,0,10,0));
        hBox.setAlignment(Pos.CENTER);
        //hBox.setStyle("-fx-border-color:red");
        hBox.setStyle("-fx-background-color: linear-gradient(to bottom,#69FF97,#00E4FF)  ");
        ImageView imageView = new ImageView(new Image(url));
        imageView.setFitHeight(70D);
        imageView.setFitWidth(150D);
        imageView.setPreserveRatio(false);
        Label namel=new Label(name);
        Label pricel=new Label("单价："+price);
        Label quantityl=new Label("数量："+quantity);
        Button button=new Button("减少");
        ButtonKey.setButton(button);
        button.setOnAction(e->{
            reduce(register.getAccount(),name,false);
            String[] split = quantityl.getText().split("：");
            int u=Integer.valueOf(split[1])-1;
            if (Integer.valueOf(split[1])==1) {//删除
                shopping.delectShoppingCard(register.getAccount(),name);
            }else {
                quantityl.setText("数量：" + ((Integer.valueOf(split[1]) - 1) + ""));
            }
        });
        hBox.getChildren().addAll(imageView,namel,pricel,quantityl,button);
        return hBox;
    }

    /**较少数量*/
    private void reduce(String acc,String name,boolean b) {
        shopping.addToCart(acc,name,b);
    }

    /**得到商品*/
    public VBox getShoppingCart() throws SQLException {
        VBox vBox=new VBox();
        vBox.setPadding(new Insets(20,50,0,50));
        //vBox.setStyle("-fx-border-color:red");
        vBox.setAlignment(Pos.CENTER);
        Shopping shopping=new Shopping();
        ArrayList<AccGoods> shoppingCart = shopping.getShoppingCart(register.getAccount());
        //ArrayList<AccGoods> shoppingCart = allS.getShoppingCart("1");
        for (int i = 0; i < shoppingCart.size(); i++) {
            vBox.getChildren().add(createCommodity(shoppingCart.get(i).getCommodity_image(),
                    shoppingCart.get(i).getCommodity_name(),
                    shoppingCart.get(i).getCommodity_price(),
                    shoppingCart.get(i).getCommodity_quantity()+""
            ));
        }

        return vBox;
    }
}
