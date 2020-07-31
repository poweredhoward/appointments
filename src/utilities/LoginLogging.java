package utilities;

import Model.Consultant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginLogging {

    public static void addEntry(Consultant consultant){
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream(
                    new File("log.txt"), true));
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

            Date now = new Date();
            String date = dateFormat.format(now);
            writer.append(String.format(
                    "Username: %s ...... Timestamp: %s \n",
                    consultant.getUsername(), date
            ));

            writer.close();
        } catch (FileNotFoundException ex) {

        }
    }
}
