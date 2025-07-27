package dao;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import entities.User;

public class UserDAO {
    public static User find(String email) {
        try {
            File file = new File("users.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] splitted_data = data.split("[|]");
                if (splitted_data[0].equals(email)) {
                    sc.close();
                    return new User(splitted_data[0], splitted_data[1], splitted_data[2], Double.parseDouble(splitted_data[3]), Double.parseDouble(splitted_data[4]));
                }
            }
            sc.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new User(null, null, null, 0.0d, 0.0d);
    }
}
