package sample;

import MySQL.Goods;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AddGoods  {
    Stage primaryStage;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void start() throws Exception {
        Stage primaryStage=new Stage();
        this.primaryStage=primaryStage;
        VBox vBox=new VBox(20);
        vBox.setPadding(new Insets(20,50,50,50));
        vBox.getChildren().add(setFrame());
        vBox.getChildren().add(add());
        StackPane stackPane=new StackPane();
        stackPane.getChildren().addAll(vBox);
        primaryStage.setTitle("上传商品");
        Scene scene=new Scene(stackPane,430,470);
        primaryStage.setScene(scene);
        //primaryStage.setResizable(false);
        primaryStage.setOpacity(1);//不透明度
        primaryStage.initStyle(StageStyle.UTILITY);
        /*StageStyle.UNDECORATED   窗口在前，不能拖到，都没有
         *StageStyle.TRANSPARENT 不能拖到，都没有
         *StageStyle.UTILITY  只有关闭
         * StageStyle.UNIFIED 全部不显示
         *StageStyle.DECORATED 默认
         */
        primaryStage.setOnCloseRequest(e->{
            HomePage homePage=new HomePage();
            try {
                homePage.start();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        primaryStage.show();

    }

    /**
     *
     * @return 图形界面
     */
    private VBox add() {
        VBox vBox=new VBox(20);

        Label l1=new Label("商品类型");
        TextField t1=new TextField();
        HBox h1=new HBox(40);
        h1.getChildren().addAll(l1,t1);

        Label l2=new Label("商品名称");
        TextField t2=new TextField();
        HBox h2=new HBox(40);
        h2.getChildren().addAll(l2,t2);

        Label l3=new Label("商品价格");
        TextField t3=new TextField();
        HBox h3=new HBox(40);
        h3.getChildren().addAll(l3,t3);

        Label l4=new Label("图片地址");
        TextField t4=new TextField();
        HBox h4=new HBox(40);
        h4.getChildren().addAll(l4,t4);

        Label l5=new Label("商品详情");
        TextField t5=new TextField();
        HBox h5=new HBox(40);
        h5.getChildren().addAll(l5,t5);

        Label l6=new Label("商品店铺");
        TextField t6=new TextField();
        HBox h6=new HBox(40);
        h6.getChildren().addAll(l6,t6);

        Button button=new Button("上传");
        ButtonKey.setButton(button);
        button.setOnAction(e->ok(t1.getText(),t2.getText(),t3.getText(),t4.getText(),t5.getText(),t6.getText()));
        vBox.getChildren().addAll(h1,h2,h3,h4,h5,h6,button);

        return vBox;

    }

    /**
     * 标题
     * @return 标题
     */
    private HBox setFrame() {
        HBox hBox=new HBox(20);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.setStyle("-fx-background-color:#808080");
        Text end=new Text("X");
        /*字体*/
        Font font=Font.font("华文楷体", FontWeight.BOLD, FontPosture.ITALIC,30);


        hBox.getChildren().add(end);
        hBox.setAlignment(Pos.CENTER);
        hBox.setFillHeight(false);
        return hBox;
    }

    /**
     * 上传
     */
    private void ok(String kind,String name,String price,String image,String detail,String store){
        Goods goods=new Goods();
        goods.adds(kind,name,price,image,detail,store);
    }


}
