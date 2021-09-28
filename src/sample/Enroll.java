package sample;

import MySQL.Account;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.SQLException;

public class Enroll {
    Account account=new Account();
    Register register=new Register();
    private String accnum;
    private Stage stage;
    public String getRepossnum() {
        return repossnum;
    }

    public void setRepossnum(String repossnum) {
        this.repossnum = repossnum;
    }

    private String possnum;
    private String repossnum;

    public String getAccnum() {
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

    public void start() throws Exception {
        GridPane g1 = new GridPane();
        g1.setPadding(new Insets(50,50,50,50));
        g1.setVgap(20);
        g1.setStyle("-fx-background-color: linear-gradient(to bottom,#3C8CE7,#00EAFF)  ");
        g1.setHgap(20);
        g1.setAlignment(Pos.TOP_CENTER);
        /*账号密码*/
        g1.addRow(0,setAccAndPass()[0]);
        g1.addRow(1,setAccAndPass()[1]);
        g1.addRow(2,setAccAndPass()[2]);
        /*登录注册*/
        g1.add(setReAndLo(),0,3);
        Stage s1 = new Stage();
        this.stage=s1;
        s1.initStyle(StageStyle.UTILITY);
        Scene scene = new Scene(g1, 400, 400);
        s1.getIcons().add(new Image("icon/1-1F1191310490-L.png"));
        s1.setTitle("注册账号");
        s1.setScene(scene);
        s1.setResizable(false);
        s1.show();
    }

    /**注册按钮*/
    public HBox setReAndLo() {
        Register register=new Register();
        Button re = new Button("注册");
        ButtonKey.setButton(re);
        HBox x = new HBox(150);
        x.getChildren().addAll(re);
        x.setAlignment(Pos.CENTER);
        /*注册*/
        re.setOnAction(e->{
            if (isSame()){//相同
                //判断密码是否已经存在
                if (isHave(getAccnum(),"?")) {//已经存在
                    register.error("该账号已经存在");
                }else {//不存在
                    try {
                        account.indexAccount(getAccnum(),getPossnum());
                        stage.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }else {
                register.error("两次输入密码不一致");
            }
        });
        return x;
    }

    /**账号密码*/
    public HBox[] setAccAndPass(){
        HBox[] hBoxes=new HBox[3];
        Text account=new Text("              账号");
        Text password=new Text("             密码");
        Text repassword=new Text("再次输入密码");
        TextField acc=new TextField();
        acc.setPromptText("输入完成请单击Enter");
        PasswordField pss=new PasswordField();
        pss.setPromptText("输入完成请单击Enter");
        PasswordField repss=new PasswordField();
        repss.setPromptText("输入完成请单击Enter");
        HBox a=new HBox(20);
        a.getChildren().addAll(account,acc);
        hBoxes[0]=a;
        HBox p=new HBox(20);
        p.getChildren().addAll(password,pss);
        hBoxes[1]=p;
        HBox x=new HBox(20);
        x.getChildren().addAll(repassword,repss);
        hBoxes[2]=x;
        acc.setOnAction(e-> setAccnum(acc.getText()));
        pss.setOnAction(e-> setPossnum(pss.getText()));
        repss.setOnAction(e-> setRepossnum(repss.getText()));
        return hBoxes;
    }

    /**
     *
     * @return 两次密码是否相同
     */
    public boolean isSame(){
        return getPossnum().equals(getRepossnum());
    }

    /***
     *
     * @param s 账号
     * @param s1 null
     * @return 判断账号是否存在
     */
    public boolean isHave(String s, String s1){
        boolean flag=false;
        try {
            flag= account.testAccount(s,s1)!=-1;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return flag;
    }
}

