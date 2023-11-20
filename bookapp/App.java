package bookapp;

import java.io.BufferedReader;
import java.io.FileReader;

public class App {
     
    public static String loggeduser() {
        String word = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(".user"))) {
            word = reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return word;
    }
    public static void main(String[] args) {
        UserController users = new UserController();
        users.logOut();
        users.login("Sofia","password");
        String username = loggeduser();
        users.switchPlan(username);
	users.lendBook(username, 1);
        users.lendBook(username, 18);
        users.lendBook(username, 20);
        users.lendBook(username, 5);
      //  users.listBooks();
      //  users.switchPlan(username);
    }
}
