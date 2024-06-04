package Command.ForVM;

import aggregate.Course;
import aggregate.Users;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static aggregate.Course.course0;
import static aggregate.Users.*;

public class uploadVM {
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
                    try{
                        File f=new File(path);
                        int num=course0.vmIdList.lastIndexOf(user0.getId());
                        if(num!=-1){
                            insert(f,course0.vmList.get(num));
                        }
                        else{
                            System.out.println("You have no VM!");
                            return;
                        }
                        System.out.println("uploadVM success");
                    }catch (Exception e){
                        System.out.println("Files open failed");
                    }
                }else System.out.println("permission denied");
            }else System.out.println("no course selected");
        }else System.out.println("not logged in");
    }
    //insert实现序列化的方法
    //f目标文件
    //o 需要序列化的对象
    public static void insert(File f, Object o)throws Exception{
        //判断目录是否存在
        if (!f.getParentFile().exists()){
            f.getParentFile().mkdirs();
        }
        //判断文件是否存在
        if (!f.exists()){
            f.createNewFile();
        }
        //创建一个字节输出流对象
        OutputStream ops=new FileOutputStream(f);
        //创建一个实现序列化的对象
        ObjectOutputStream oos=new ObjectOutputStream(ops);
        //实现序列化
        oos.writeObject(o);
        oos.close();
        ops.close();
    }
}