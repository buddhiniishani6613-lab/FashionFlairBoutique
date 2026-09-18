package model;

public class SalesAssistant extends User {

    public SalesAssistant() {
        super();
    }

    public void addProduct() {
        System.out.println("Sales Assistant can add new products.");
    }

    public void searchProduct() {
        System.out.println("Sales Assistant can search products.");
    }

    public void processPurchase() {
        System.out.println("Sales Assistant can process customer purchases.");
    }

    @Override
    public void login() {
        System.out.println("Sales Assistant logged in.");
    }
}