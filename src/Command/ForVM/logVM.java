package Command.ForVM;

import aggregate.Course;
import aggregate.Users;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static aggregate.Course.course0;
import static aggregate.Users.*;

public class logVM {
    public static void run(String command, ArrayList<Course> arr_courses, ArrayList<String> arr_courseIds, ArrayList<Users> arr_User, ArrayList<String> arr_UsersId, Scanner sc) {
        String p="\\S+";
        Pattern r=Pattern.compile(p);
        Matcher m=r.matcher(command);
        String[] mm=new String[10];
        int i=0;
        while(m.find())
            mm[i++]=m.group();
        i-=1;
        if(i>=2&&i==0){
            System.out.println("arguments illegal");
            return;
        }
        if(loginFlag){
            if(selectFlag){
                if(user0.getType()==0 && orFlag==false){
                    int num=course0.vmIdList.lastIndexOf(user0.getId());
                    if(num==-1 || course0.vmList.get(num).coms.size()==0)
                        System.out.println("no log");
                    else{
                        course0.vmList.get(num).coms.forEach(e->{
                            System.out.println(e);
                        });
                    }
                }else System.out.println("permission denied");
            }else System.out.println("no course selected");
        }else System.out.println("not logged in");
    }
}
