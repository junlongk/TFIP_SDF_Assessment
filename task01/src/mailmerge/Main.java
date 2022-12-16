package mailmerge;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        String csvFile = args[0];
        String templateFile = args[1];

        // Creates a list of maps to store user's information
        List<Map<String, String>> userList = Read.data(csvFile);

        // Loop throught the list to output the emails
        for (Integer i = 0; i < userList.size(); i++) {
            Read.emailOutput(userList.get(i), templateFile);
        }
    }
}