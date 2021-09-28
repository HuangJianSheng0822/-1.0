package sample;

import MySQL.EveryGood;
import MySQL.SelectAsKind;
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
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class SelectAs  {
    private String kind;
    private Stage s;
    private VBox vb;
    private VBox list;

    public SelectAs(String kind) {
        this.kind = kind;
    }

    public VBox getVb() {
        return vb;
    }

    public void setVb(VBox vb) {
        this.vb = vb;
    }

    public VBox getList() {
        return list;
    }

    public void setList(VBox list) {
        this.list = list;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Stage getS() {
        return s;
    }

    public void setS(Stage s) {
        this.s = s;
    }

    public void start() throws Exception {
        Stage primaryStage = new Stage();
        s=primaryStage;
        VBox vBox=new VBox(20);
        setVb(vBox);
        vBox.setStyle("-fx-background-color: linear-gradient(to bottom,#69FF97,#00E4FF)  ");
        vBox.getChildren().add(setFrame());
        vBox.getChildren().add(numOfPages());
        setList(add(0));
        vBox.getChildren().add(list);
        Scene scene=new Scene(vBox,800,950);
        primaryStage.getIcons().add(new Image("icon/1-1604211533370-L.png"));
        primaryStage.setTitle("商品类型");
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**顶部*/
    private HBox setFrame() {
        HBox hBox=new HBox(500);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.setStyle("-fx-background-color:#808080");
        Text wel=new Text(getKind());
        Text end=new Text("返回");
        /*字体*/
        Font font=Font.font("华文楷体", FontWeight.BOLD, FontPosture.ITALIC,30);
        end.setOnMousePressed(e->{
            getS().close();
            HomePage homePage=new HomePage();
            try {
                homePage.start();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        wel.setFont(font);
        wel.setFill(Color.WHEAT);
        hBox.getChildren().add(wel);
        hBox.getChildren().add(end);
        hBox.setAlignment(Pos.CENTER);
        hBox.setFillHeight(false);
        return hBox;
    }

    /**商品页面*/
    private @NotNull
    HBox createCommodity(int id, String url, String name, String price, String shop){
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
            Register register=new Register();
            Shopping shopping=new Shopping();
            shopping.addToCart(register.getAccount(),name,true);
        });
        hBox.getChildren().addAll(imageView,namel,pricel,detaill,button);
        return hBox;
    }

    /**
     *
     * 通过createCommodity(int id,String url,String name,String price,String shop)构建每一个商品
     * @return 整个商品页面
     */
    private VBox add(int s){
        VBox vBox=new VBox(5);
        SelectAsKind selectAsKind=new SelectAsKind();
        ArrayList<EveryGood> arrayList=selectAsKind.getShoppingCart(getKind());
        for (int i = s; i < s+5; i++) {
            vBox.getChildren().add(createCommodity(arrayList.get(i).getId(),arrayList.get(i).getImage(),arrayList.get(i).getName(),arrayList.get(i).getPrice(),arrayList.get(i).getStore()));
        }
        return vBox;
    }

    /**页数*/
    private VBox numOfPages(){
       VBox vBox=new VBox();
       AtomicInteger p= new AtomicInteger(5);
       Button b1=new Button("上一页");
        b1.setOnAction(e->{
            if (p.get()>5) {
                getVb().getChildren().remove(getList());
                p.getAndAdd(-10);
                setList(add(p.getAndAdd(5)));
                getVb().getChildren().add(getList());
            }

        });
       Button b2=new Button("下一页");
        b2.setOnAction(e->{
            getVb().getChildren().remove(getList());
            setList(add(p.getAndAdd(5)));
            getVb().getChildren().add(getList());
        });
       HBox hBox = new HBox(50);
       hBox.getChildren().addAll(b1,b2);
       hBox.setAlignment(Pos.CENTER);
       vBox.getChildren().add(hBox);
       return vBox;
    }


}
