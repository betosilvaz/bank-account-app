package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Autenticator {
    public static void register(String email, String name, String password, double balance, double special_check) {
        try {
            // open the file or create it if doesnt exists
            File file = new File("users.txt");
            if(!file.exists())
                file.createNewFile();

            // register new user in the file
            FileWriter fw = new FileWriter("users.txt", true);
            fw.write(String.format("%s|%s|%s|%f|%f\n", email, name, password, balance, special_check));
            fw.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean login(String email, String password) {
        boolean authenticated = false;
        try {
            // open the file or create it if doesnt exists
            File file = new File("users.txt");
            if(!file.exists()) {
                file.createNewFile();
            }

            // verify if provided credentials work with any of the users registered
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] splitted_data = data.split("[|]");
                if(splitted_data[0].equals(email) && splitted_data[2].equals(password)) {
                    authenticated = true;
                    break;
                }
            }
            sc.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        return authenticated;
    }
}
