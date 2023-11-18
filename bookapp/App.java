package bookapp;

public class App {
    public static void main(String[] args) {
        UserController users = new UserController();
       // users.createUser("test2", "test2", false);
        users.login("abeja","popo");
    }
}