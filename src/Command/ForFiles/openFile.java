package Command.ForFiles;

import aggregate.Course;
import aggregate.Users;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class openFile {
    public static void run(String command, ArrayList<Course> arr_courses, ArrayList<String> arr_courseIds, ArrayList<Users> arr_User, ArrayList<String> arr_UsersId){
        String p="\\S+";
        Pattern r=Pattern.compile(p);
        Matcher m=r.matcher(command);
        String[] mm=new String[10];
        int i=0;//计数
        int flag=0;//是否重定向
        while(m.find()){
            mm[i]=m.group();
            i++;
        }
        i-=1;
        if(!command.contains("<")){
            if(i==0){
                System.out.println("please input the path to open the file");
                return;
            }
            if(i>=2){
                System.out.println("arguments illegal");
                return;
            }
            flag=1;
        }
        else {
            if(mm[i].contains("<")){
                System.out.println("please input the path to redirect the file");
                return;
            }
            if(i>=4){
                System.out.println("arguments illegal");
                return;
            }
            if(!mm[i-1].contains("<")){
                System.out.println("arguments illegal");
                return;
            }
        }
        //正式执行
        if (flag == 1)/*第一类*/ {
            try {
                String data = ""+mm[1];
                File f1 = new File(data);
                FileReader fr = new FileReader(f1);
                int date=0;
                while((date= fr.read())!=-1){
                    System.out.print((char)date);
                }
                fr.close();
            } catch (IOException e) {
                System.out.println("file open failed");
            }
        } else {
            if (i == 2)/*第二类*/ {
                try {
                    String data = ""+mm[2];
                    File f1 = new File(data);
                    FileReader fr = new FileReader(f1);
                    int date=0;
                    while((date= fr.read())!=-1){
                        System.out.print((char)date);
                    }
                    fr.close();
                } catch (IOException e) {
                    System.out.println("file open failed");
                }
            } else /*第三类*/ {
                try {
                    String data = ""+mm[1];
                    File f1 = new File(data);
                    FileReader fr = new FileReader(f1);
                    int date=0;
                    while((date= fr.read())!=-1){
                        System.out.print((char)date);
                    }
                    fr.close();
                } catch (IOException e) {
                    System.out.println("file open failed");
                }
            }
        }

    }
}