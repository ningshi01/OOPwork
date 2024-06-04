package aggregate.FVM;

import aggregate.Users;

import java.io.Serializable;
import java.util.ArrayList;

public class IVM implements Serializable{
    public String theType;
    public transient String stuId;
    public ArrayList<String> coms=new ArrayList<>();
    public IVM(){};
    public static IVM vm0=new IVM();
}
/*
Windows,
 Linux,
 MacOS
*/