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
    
    public void readFile(){
        String line;
        try(BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                rows.add(values);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Look for CreateUser in Documentation at https://github.com/juampam/BookLending
    public void createUser(String username,String password, boolean premium){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(PATH, true))){
            writer.write(username + "," + password + "," + premium); //true append
            writer.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void login(String username, String password){
        readFile();
        String usernames = "";
        for (String[] row : rows) {
            if (row.length > 0) {
                 usernames = row[0];
            }
        }
        if (usernames.contains(username)) {
            System.out.println("User exist");
        }else{
            System.out.println("User " + username + " do not exist");
        }
    }
}
