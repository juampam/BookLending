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
    // Look for CreateUser in Documentation at https://github.com/juampam/BookLending
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
    // Look for Login in Documentation at https://github.com/juampam/BookLending
    public void login(String username, String password){
        String line;
        String userlocated = "";
        try(BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            while ((line = br.readLine()) != null) {
                if (line.contains(username) && line.contains(password)) {
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
            }
        }
    }
}
