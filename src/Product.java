public class Product {
    private String id;
    private String name;
    private double price;
    private LineOfBusiness lineOfBusiness;

    public Product(String id, String name, double price, LineOfBusiness lineOfBusiness){
        this.id = id;
        this.name = name;
        this.price = price;
        this.lineOfBusiness = lineOfBusiness;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public LineOfBusiness getLineOfBusiness() {
        return lineOfBusiness;
    }
}
