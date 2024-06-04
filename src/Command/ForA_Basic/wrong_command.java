package Command.ForA_Basic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class wrong_command {
    public static void run(String command) {
        String pat = "\\S+";
        Pattern rr=Pattern.compile(pat);
        Matcher mm= rr.matcher(command);
        mm.find();
        String get0=mm.group();
        System.out.println("command '"+get0+"' not found");
    }
}
