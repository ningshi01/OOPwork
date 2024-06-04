package Command.ForVM;

import aggregate.Course;
import aggregate.FVM.IVM;
import aggregate.FVM.VM_Factory;
import aggregate.Users;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static aggregate.Users.*;
import static aggregate.FVM.IVM.*;
import static aggregate.Course.*;
import static aggregate.Task.*;

public class requestVM {
    public static void run(String command, ArrayList<Course> arr_courses, ArrayList<String> arr_courseIds, ArrayList<Users> arr_User, ArrayList<String> arr_UsersId) {
        String p="\\S+";
        Pattern r=Pattern.compile(p);
        Matcher m=r.matcher(command);
        String[] mm=new String[10];
        int i=0;
        while(m.find())
            mm[i++]=m.group();
        i-=1;
        if(i>=2 && i==0){
            System.out.println("arguments illegal");
            return;
        }
        if(loginFlag){
            if(selectFlag){
                if(user0.getType()==0 && orFlag==false){
                    VM_Factory fac = new VM_Factory();
                    vm0 = fac.createVM(mm[1]);
                    if(vm0==null) return;
                    vm0.stuId = user0.getId();
                    int num=course0.vmIdList.lastIndexOf(user0.getId());
                    if(num!=-1){
                        course0.vmIdList.set(num,"null");//将Id搜寻表里的数据置空但不删除
                    }
                    course0.vmIdList.add(user0.getId());
                    course0.vmList.add(vm0);
                    arr_courses.set(arr_courseIds.lastIndexOf(course0.getCourseId()),course0);//course0写入总表
                    System.out.println("requestVM success");
                }else System.out.println("permission denied");
            }else System.out.println("no course selected");
        }else System.out.println("not logged in");
    }
}
