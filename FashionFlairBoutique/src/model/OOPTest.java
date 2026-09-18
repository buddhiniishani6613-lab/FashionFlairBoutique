package model;

public class OOPTest {

    public static void main(String[] args) {

        User user1 = new SalesAssistant();
        User user2 = new BoutiqueManager();

        user1.login();
        user2.login();
    }
}