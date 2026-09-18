package model;

public class BoutiqueManager extends User {

    public BoutiqueManager() {
        super();
    }

    public void manageUsers() {
        System.out.println("Manager can create and manage Sales Assistant accounts.");
    }

    public void updatePrices() {
        System.out.println("Manager can update product prices.");
    }

    public void manageDiscounts() {
        System.out.println("Manager can manage seasonal discounts and promotions.");
    }

    public void viewSalesSummary() {
        System.out.println("Manager can view daily and monthly sales summaries.");
    }

    @Override
    public void login() {
        System.out.println("Boutique Manager logged in.");
    }
}