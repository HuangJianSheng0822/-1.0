package MySQL;

public class EveryGood {

        private int id;
        private String kind;
        private String name;
        private String price;
        private String image;
        private String detail;
        private String store;

    public EveryGood() {
    }

    public EveryGood(int id, String kind, String name, String price, String image, String detail, String store) {
            this.id = id;
            this.kind = kind;
            this.name = name;
            this.price = price;
            this.image = image;
            this.detail = detail;
            this.store = store;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getStore() {
            return store;
        }

        public void setStore(String store) {
            this.store = store;
        }

    @Override
    public String toString() {
        return "EveryGood{" +
                "id=" + id +
                ", kind='" + kind + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", image='" + image + '\'' +
                ", detail='" + detail + '\'' +
                ", store='" + store + '\'' +
                '}';
    }
}
