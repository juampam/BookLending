package bookapp;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    User user = new User();
    private final String PATH = "database/users.csv";
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
                    writer.write(username +","+ newEra[2]);
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
        try (BufferedReader reader = new BufferedReader(new FileReader("database/books.csv"))) {
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
    public void lendBook(String user, int targetId){
        String line = "";
        String[] uservalues = user.split(",");
        String newTable = "database/" + uservalues[0] + "_books.csv";
        boolean zerovalue  = fileExists(newTable);
        int lines = 0;
        createEmptyFile(uservalues[0]);
        try {
            lines = (int) Files.lines(Path.of(newTable)).count();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader("database/books.csv"))) {
            reader.readLine();

            List<String[]> rows = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                rows.add(values);
            }

                for (String[] row : rows) {
                    if (row.length > 0) {
                        int id = Integer.parseInt(row[0].trim());
                        if (id == targetId) {
                          //  System.out.println(row.length);
                          //  System.out.println(zerovalue);
                            System.out.println(lines);
                            if( uservalues[1].equals("false") && lines >= 3 && !row[3].equals("Magazine")) {
                                System.out.println("Switch to premium to get more amazing books!");
                            }else if(uservalues[1].equals("true") && lines == 5 && !row[3].equals("Magazine")){
                                System.out.println("Premium users only can lend 5 books");
                            }else{
                                writeToFile(row[2], newTable,true);
                                break;
                            }
                        }
                    }
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void switchPlan(String user){
        String[] uservalues = user.split(",");
        String line = "";

        if (uservalues[1].equals("false")) {
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(".user"))) {
             writer.write(uservalues[0] + "," + "true");
            } catch (Exception e) {
                e.printStackTrace();
            }
             try (BufferedReader br = new BufferedReader(new FileReader("database/users.csv"));
                FileWriter fw = new FileWriter("tmp")) {
                while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(uservalues[0])) {
                    data[2] = "true";
                }
                System.out.println(data[2] + uservalues[1]);
                fw.write(String.join(",", data) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        new File("tmp").renameTo(new File("database/users.csv"));
        }
    }

    private boolean fileExists(String fileName) {
        Path path = Paths.get(fileName);
        return Files.exists(path) && Files.isRegularFile(path);
    }

    private void writeToFile(String value, String outputFile, boolean append) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile,append))) {
            writer.write(value + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void createEmptyFile(String user) {
        String newTable = "database/" + user + "_books.csv";
        try {
            File file = new File(newTable);

            file.createNewFile();
       
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

