package model;

public class Product implements Manageable {
    @Override
public void add() {
    System.out.println("Product added successfully.");
}

@Override
public void update() {
    System.out.println("Product updated successfully.");
}

@Override
public void delete() {
    System.out.println("Product deleted successfully.");
}

    private int productID;
    private String productName;
    private String brand;
    private int categoryID;
    private String size;
    private String color;
    private double price;
    private int stockQuantity;

    public Product() {
    }

    public Product(int productID, String productName, String brand,
                   int categoryID, String size, String color,
                   double price, int stockQuantity) {

        this.productID = productID;
        this.productName = productName;
        this.brand = brand;
        this.categoryID = categoryID;
        this.size = size;
        this.color = color;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        }
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity >= 0) {
            this.stockQuantity = stockQuantity;
        }
    }

    public void displayProductDetails() {
        System.out.println("Product: " + productName);
        System.out.println("Brand: " + brand);
        System.out.println("Size: " + size);
        System.out.println("Color: " + color);
        System.out.println("Price: Rs. " + price);
        System.out.println("Stock: " + stockQuantity);
    }
}

