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
        /* 
        UserController users = new UserController();
        users.login("Yui","password123");
        String username = loggeduser();
        users.switchPlan(username);
        users.listBooks();
	users.lendBook(username, 1);
        users.lendBook(username, 18);
        users.lendBook(username, 20);
        users.lendBook(username, 5);
	users.logOut();*/

    UserController userController = new UserController();

    if (args.length > 0) {
        String command = args[0].toLowerCase();

        switch (command) {
            case "createuser":
                if (args.length == 4) {
                    String username = args[1];
                    String password = args[2];
                    boolean premium = Boolean.parseBoolean(args[3]);
                    userController.createUser(username, password, premium);
                } else {
                    System.out.println("Usage: java Main createuser <username> <password> <premium>");
                }
                break;

            case "login":
                if (args.length == 3) {
                    String username = args[1];
                    String password = args[2];
                    userController.login(username, password);
                } else {
                    System.out.println("Usage: java Main login <username> <password>");
                }
                break;

            case "-help":
                if (args.length == 1) {
                    System.out.println("Comandos disponibles:");
                    System.out.println("  - createuser <username> <password> <premium>");
                    System.out.println("  - login <username> <password>");
                    System.out.println("  - logout");
                    System.out.println("  - listbooks");
                    System.out.println("  - lendbook <username> <bookId>");
                    System.out.println("  - switchplan <username>");
                } else {
                    System.out.println("Usage: java Main -help");
                }
                break;

            default:
                System.out.println("Comand not found, type java bookapp.App -help for help");
        }
    } else {
        System.out.println("Please provide a command. type java bookapp.App -help for help or see https://github.com/juampam/BookLending for more details");
    }
    }
}
