package Command.ForVM;

import aggregate.Course;
import aggregate.FVM.IVM;
import aggregate.Users;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static aggregate.Course.course0;
import static aggregate.FVM.IVM.*;
import static aggregate.Users.*;

public class downloadVM {
    public static void run(String command, ArrayList<Course> arr_courses, ArrayList<String> arr_courseIds, ArrayList<Users> arr_User, ArrayList<String> arr_UsersId, Scanner sc) {
        String p="\\S+";
        Pattern r=Pattern.compile(p);
        Matcher m=r.matcher(command);
        String[] mm=new String[10];
        int i=0;
        while(m.find())
            mm[i++]=m.group();
        i-=1;
        String path=mm[1];
        if(i>=2&&i==0){
            System.out.println("arguments illegal");
            return;
        }
        if(loginFlag){
            if(selectFlag){
                if(user0.getType()==0 && orFlag==false){
                    int num=course0.vmIdList.lastIndexOf(user0.getId());
                    if(num!=-1){
                        course0.vmIdList.set(num,"null");//将Id搜寻表里的数据置空但不删除
                        arr_courses.set(arr_courseIds.lastIndexOf(course0.getCourseId()),course0);//course0写入总表
                    }
                    try{
                        File f=new File(path);
                        vm0=deinsert(f);
                        vm0.stuId= user0.getId();

                        course0.vmIdList.add(user0.getId());
                        course0.vmList.add(vm0);
                        arr_courses.set(arr_courseIds.lastIndexOf(course0.getCourseId()),course0);//course0写入总表
                        System.out.println("downloadVM success");
                    }catch (Exception e){
                        System.out.println("Files open failed");
                    }
                }else System.out.println("permission denied");
            }else System.out.println("no course selected");
        }else System.out.println("not logged in");
    }
    public static IVM deinsert(File f) throws Exception{
        //创建一个字节输入流
        InputStream in=new FileInputStream(f);
        //创建一个实现反序列化的对象
        ObjectInputStream input=new ObjectInputStream(in);
        //实现反序列化
        Object obj=input.readObject();
        IVM vm= (IVM) obj;
        //关闭
        input.close();
        in.close();
        return vm;
    }
}