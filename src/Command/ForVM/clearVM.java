package Command.ForVM;

import aggregate.Course;
import aggregate.FVM.IVM;
import aggregate.Users;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static aggregate.Course.course0;
import static aggregate.FVM.IVM.vm0;
import static aggregate.Users.*;

public class clearVM {
    public static void run(String command, ArrayList<Course> arr_courses, ArrayList<String> arr_courseIds, ArrayList<Users> arr_User, ArrayList<String> arr_UsersId, Scanner sc) {
        String p="\\S+";
        Pattern r=Pattern.compile(p);
        Matcher m=r.matcher(command);
        String[] mm=new String[10];
        int i=0;
        String com;
        while(m.find())
            mm[i++]=m.group();
        i-=1;
        int rot=Integer.parseInt(mm[1])-1;
        if(i>=2&&i==0){
            System.out.println("arguments illegal");
            return;
        }
        if(loginFlag){
            if(selectFlag){
                if(user0.getType()==1){
                    System.out.println("clear "+course0.vmList.get(rot).theType+" success");
                    if(rot>course0.vmList.size()) {
                        System.out.println("The Vm is not exist!");
                        return;
                    }
                    course0.vmList.get(rot).coms.clear();
                    arr_courses.set(arr_courseIds.lastIndexOf(course0.getCourseId()),course0);//course0写入总表
                }else System.out.println("permission denied");
            }else System.out.println("no course selected");
        }else System.out.println("not logged in");
    }
}
