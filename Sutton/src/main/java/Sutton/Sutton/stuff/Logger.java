package Sutton.Sutton.stuff;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Logger {

    public static void log(String info) {

        try {

            String s = info + "\n";
            Writer output = new BufferedWriter(new FileWriter("/home/pi/Desktop/log.txt", true));
            output.append(s);
            output.close();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
