package MySQL;

public class AccGoods {

    private String commodity_image;
    private String commodity_name;
    private String commodity_price;
    private int commodity_quantity;

    public AccGoods(String commodity_image, String commodity_name, String commodity_price, int commodity_quantity) {
        this.commodity_image = commodity_image;
        this.commodity_name = commodity_name;
        this.commodity_price = commodity_price;
        this.commodity_quantity = commodity_quantity;
    }

    public String getCommodity_image() {
        return commodity_image;
    }

    public void setCommodity_image(String commodity_image) {
        this.commodity_image = commodity_image;
    }

    public String getCommodity_name() {
        return commodity_name;
    }

    public void setCommodity_name(String commodity_name) {
        this.commodity_name = commodity_name;
    }

    public String getCommodity_price() {
        return commodity_price;
    }

    public void setCommodity_price(String commodity_price) {
        this.commodity_price = commodity_price;
    }

    public int getCommodity_quantity() {
        return commodity_quantity;
    }

    public void setCommodity_quantity(int commodity_quantity) {
        this.commodity_quantity = commodity_quantity;
    }

    @Override
    public String toString() {
        return "AccGoods{" +
                "commodity_image='" + commodity_image + '\'' +
                ", commodity_name='" + commodity_name + '\'' +
                ", commodity_price='" + commodity_price + '\'' +
                ", commodity_quantity=" + commodity_quantity +
                '}';
    }
}
