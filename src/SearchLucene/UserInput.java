package SearchLucene;

import java.util.Scanner;

/**
 * Created by huang on 11/26/17.
 */
public class UserInput {

    public String getUserInput() {
        String query = "";
        Scanner reader = new Scanner(System.in);
        System.out.println("please enter a query: ");
        query = reader.nextLine();
        return query;
    }
}
