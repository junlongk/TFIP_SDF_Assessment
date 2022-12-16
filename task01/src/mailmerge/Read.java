package mailmerge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Read {
    
    public static List<Map<String, String>> data(String csvFile) throws Exception {
        List<Map<String, String>> allUsers = new LinkedList<>();

        // Initiate file reader
        Reader fr = new FileReader(csvFile);
        BufferedReader bfr = new BufferedReader(fr);

        // Process headers
        String line1 = bfr.readLine(); 
        List<String> headers = List.of(line1.split(","));

        // Generate list of users
        String line = "";
        while(null != (line = bfr.readLine())) {
            String[] terms = line.split(",");

            Map<String, String> user = new HashMap<>();
            for (Integer i = 0; i < terms.length; i++) {
                user.put(headers.get(i), terms[i]);
            }

            allUsers.add(user);
        }

        bfr.close();
        fr.close();

        return allUsers;
    }

    public static void emailOutput(Map<String, String> map, String templateFile) throws Exception {
        // Initiate file reader
        Reader fr = new FileReader(templateFile);
        BufferedReader bfr = new BufferedReader(fr);

        String line = "";
 
        while(null != (line = bfr.readLine())) {
            // Detects keywords in the template and replace them with the appropriate data
            Pattern chevrons = Pattern.compile("<<\\w+>>");
            Matcher m = chevrons.matcher(line);
            String outputLine = line;

            while (m.find()) {
                String match = m.group();
                String keyword = match.replaceAll("<<", "").replaceAll(">>", "");
                outputLine = outputLine.replace(match, map.get(keyword));
            }

            // If line contains "\n", insert line breaks accordingly
            if (outputLine.contains("\\n")) {
                String[] lineBreaks = outputLine.split("\\\\n");
                for (Integer l = 0; l < lineBreaks.length; l++) {
                    System.out.printf("%s\n", lineBreaks[l]);
                }
            } else {
                System.out.printf("%s\n", outputLine);
            }
        }
        System.out.printf("=================\n");

        bfr.close();
        fr.close();
    }
}
