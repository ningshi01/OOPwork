package Game;

import java.io.IOException;
import java.util.Properties;

public class For_Proper {
    static Properties props=new Properties();
    static {
        try {
            props.load(For_Proper.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Object get(String key){
        if(props==null) return null;
        return props.get(key);
    }

}
