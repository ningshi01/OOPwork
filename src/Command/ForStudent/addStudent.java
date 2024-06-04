package Command.ForStudent;

import Command.Isgood;
import aggregate.Course;
import aggregate.Users;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static aggregate.Course.course0;
import static aggregate.Users.user0;

public class addStudent {
    public static void run(String command, ArrayList<Course> arr_courses, ArrayList<String> arr_courseIds, ArrayList<Users> arr_User, ArrayList<String> arr_UsersId) {
        String pat="\\S+";
        Pattern r= Pattern.compile(pat);
        Matcher m=r.matcher(command);
        Matcher mm=r.matcher(command);
        Matcher xx=r.matcher(command);
        mm.find();
        int flag=0;
        if(!mm.find()){
            System.out.println("arguments illegal");
            return;
        }
        m.find();
        xx.find();
        if(Users.loginFlag){
            if(user0.getType()==1 || Users.orFlag){
                if(Users.selectFlag){
                    while(m.find()){
                        if(Isgood.IsBadId(m.group())) {
                            flag=1;
                            break;
                        }
                        else if((m.group().length()!=5)&&(arr_UsersId.lastIndexOf(m.group())==-1)) {
                            flag = 2;
                            break;
                        }
                        else if((m.group().length()==5)&&(arr_UsersId.lastIndexOf(m.group())==-1)){
                            flag = 2;
                            break;
                        }
                        else if(m.group().length()==5) {
                            flag=3;
                            break;
                        }
                    }
                    if(flag==1) System.out.println("user id illegal");
                    else if(flag==2) System.out.println("user id not exist");
                    else if(flag==3) System.out.println("I'm professor rather than student!");
                    else {
                        while(xx.find()){
                            course0.stuList.add(xx.group());
                            course0.setStuNum();
                            arr_courses.set(arr_courseIds.lastIndexOf(course0.getCourseId()),course0);//course0写入总表
                            //user的改动写入总表
                            arr_User.get(arr_UsersId.lastIndexOf(xx.group())).MyCourse.add(course0);
                            arr_User.get(arr_UsersId.lastIndexOf(xx.group())).MyCourseId.add(course0.getCourseId());
                        }
                        System.out.println("add student success");
                    }
                }else System.out.println("no course selected");
            }else System.out.println("permission denied");
        }else System.out.println("not logged in");
    }
}