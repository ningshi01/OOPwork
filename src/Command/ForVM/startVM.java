package Command.ForVM;

import aggregate.Course;
import aggregate.FVM.IVM;
import aggregate.FVM.VM_Factory;
import aggregate.Users;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static aggregate.Course.course0;
import static aggregate.FVM.IVM.vm0;
import static aggregate.Users.*;

public class startVM {
    public static void run(String command, ArrayList<Course> arr_courses, ArrayList<String> arr_courseIds, ArrayList<Users> arr_User, ArrayList<String> arr_UsersId, Scanner sc) {
        String p="\\S+";
        Pattern r=Pattern.compile(p);
        Matcher m=r.matcher(command);
        String[] mm=new String[10];
        int i=0;
        IVM vm1;
        String com="";
        while(m.find())
            mm[i++]=m.group();
        i-=1;
        if(i>=1){
            System.out.println("arguments illegal");
            return;
        }
        if(loginFlag){
            if(selectFlag){
                if(user0.getType()==0 && orFlag==false){
                    int num=course0.vmIdList.lastIndexOf(user0.getId());
                    if(num!=-1){
                        vm0=course0.vmList.get(num);
                        vm1=vm0;
                        System.out.println("welcome to "+vm0.theType);
                        while(true){
                            com=sc.nextLine();
                            if(com.equals("EOF"))
                                break;
                            vm1.coms.add(com);
                        }
                        course0.vmList.set(course0.vmList.lastIndexOf(vm0),vm1);
                        arr_courses.set(arr_courseIds.lastIndexOf(course0.getCourseId()),course0);//course0写入总表
                        System.out.println("quit "+vm1.theType);
                        return;
                    }else System.out.println("no VM");
                }else System.out.println("permission denied");
            }else System.out.println("no course selected");
        }else System.out.println("not logged in");
    }
}
