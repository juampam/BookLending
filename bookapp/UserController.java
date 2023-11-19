package bookapp;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    User user = new User();
    private final String PATH = "users.csv";
    List<String[]> rows = new ArrayList<>();

    public UserController(){

    } 
    // Look for checkers in Documentation at https://github.com/juampam/BookLending
    public boolean checkUsers(String username){
        String line;
        boolean exists = false;
        try(BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
               if (values[0].equals(username)) {
                    exists = true;
               }
                // rows.add(values);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }
    // Look for CreateUser in Documentation at https://github.com/juampam/BookLending
    public void createUser(String username,String password, boolean premium){
        boolean shit = checkUsers(username);
        if (shit == false) {
           try(BufferedWriter writer = new BufferedWriter(new FileWriter(PATH, true))){
                writer.write(username + "," + password + "," + premium  + "," + 0 ); //true append
                writer.newLine();
             } catch (Exception e) {
                e.printStackTrace();
             } 
        }else{
            System.out.println("The username " + username + " is already taken");
        }
        
    }
    // Look for Login in Documentation at https://github.com/juampam/BookLending
    public void login(String username, String password){
        String line;
        String userlocated = "";
        try(BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            while ((line = br.readLine()) != null) {
                if (line.contains(username)) {
                    userlocated = line;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (userlocated == "") {
            System.out.println("User " + username + " do not exist");
        }else{
            String[] newEra = userlocated.split(",");
            if (newEra[0].equals(username) && newEra[1].equals(password)) {
                System.out.println("Wellcome " + username);
                try(BufferedWriter writer = new BufferedWriter(new FileWriter(".user"))) {
                    writer.write(username);
                } catch (Exception e) {
                     e.printStackTrace();
                }
            }else{
                System.out.println("Incorrect password for " + username );
            }
        }
    }
    public void logOut(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(".user"))) {
             writer.write("");
         } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void listBooks() {
        String line = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("books.csv"))) {
            List<String[]> rows = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                rows.add(values);
            }

            // Find the maximum width for each column
            int[] columnWidths = new int[rows.get(0).length];
            for (String[] row : rows) {
                for (int i = 0; i < row.length; i++) {
                    columnWidths[i] = Math.max(columnWidths[i], row[i].length());
                }
            }

            // Print the values with proper alignment
            for (String[] row : rows) {
                for (int i = 0; i < row.length; i++) {
                    System.out.printf("%-" + (columnWidths[i] + 2) + "s", row[i]);
                }
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
