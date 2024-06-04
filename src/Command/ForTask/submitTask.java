package Command.ForTask;

import Command.Isgood;
import aggregate.Course;
import aggregate.Users;
import aggregate.sTask;

import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static aggregate.Course.*;
import static aggregate.Users.*;
import static aggregate.sTask.*;
public class submitTask {
    public static void run(String command, ArrayList<Course> arr_courses, ArrayList<String> arr_courseIds, ArrayList<Users> arr_User, ArrayList<String> arr_UsersId,Scanner sc) {
        String p="\\S+";
        Pattern r=Pattern.compile(p);
        Matcher m=r.matcher(command);
        String[] mm=new String[10];
        int i=0;
        int flag=0;

        int date = 0;
        double score=-1.0f;
        double sum = 0.0f;
        double for_sum = 0.0f;
        boolean ffll=false;
        while(m.find()) {
            mm[i] = m.group();
            i++;
        }
        i-=1;
        if(!command.contains("<")){
            if(i>=3 || i<=1) {
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
            if(i>=5 || i<=2){
                System.out.println("arguments illegal");
                return;
            }
        }
        //正式执行
        if(loginFlag){
            if(user0.getStu_type()==0){
                if(selectFlag){
                        String taskId = "";
                        String path="";
                        if(flag==1)/*第一类*/{
                            taskId=mm[2];
                            path=mm[1];
                        }
                        else if(i==3)/*第二类*/{
                            taskId=mm[1];
                            path=mm[3];
                        }
                        else /*第三类*/{
                            path=mm[1];
                            taskId=mm[2];
                        }
                        if(Isgood.IsbadtaskId(taskId,course0.getCourseId())){
                            System.out.println("task not found");
                            return;
                        }
                        int num=course0.taskIdList.lastIndexOf(taskId);
                        if(num==-1){
                            System.out.println("task not found");
                            return;
                        }
                        if(course0.taskList.get(num).staskIds.lastIndexOf(user0.getId())!=-1){
                            System.out.println("task already exists, do you want to overwrite it? (y/n)");
                            String com=sc.nextLine();
                            if(com.matches("[^ Yy\n]+")){
                                System.out.println("submit canceled");
                                return;
                            }
                            else {
                                course0.taskList.get(num).stasks.remove(course0.taskList.get(num).staskIds.lastIndexOf(user0.getId()));
                                course0.taskList.get(num).staskIds.remove(user0.getId());
                            }
                        }
                        try{
                            String data=""+path;
                            File f1=new File(data);
                            File f22=new File("./data/"+course0.getCourseId()+"/tasks/"+taskId+"/");
                            File f0=new File("./data/"+course0.getCourseId()+"/tasks/"+taskId+"/"+user0.getId()+".task");
                            if(f0.exists()) f0.delete();
                            File f2=new File("./data/"+course0.getCourseId()+"/tasks/"+taskId+"/"+user0.getId()+".task");
                            f22.mkdirs();
                            Files.copy(f1.toPath(),f2.toPath());
                            //批作业
                            File f_ans=new File("./data/"+course0.getCourseId()+"/answers/"+taskId+".ans");
                            if(f_ans.exists()) {
                                FileReader fr1 = new FileReader(f_ans);
                                FileReader fr2 = new FileReader(f2);
                                while ((date = fr1.read()) != -1) {
                                    int date0 = fr2.read();
                                    char dd1=((char) date);
                                    char dd2=((char) date0);
                                    char d1=Character.toUpperCase(dd1);
                                    char d2=Character.toUpperCase(dd2);
                                    if (d1 != d2) ffll=true;

                                    if (((char) date) == '\n') {
                                        sum++;
                                        if(ffll==false) {
                                            for_sum++;
                                        }
                                        ffll=false;
                                    }
                                }
                                fr1.close();
                                fr2.close();
                                score=div(100*for_sum,sum,1);
                            }
                            //end_批作业
                            sTask0=new sTask(user0.getId(), -1.0f);
                            sTask0.stuName= user0.getLastName()+" "+user0.getFirstName();
                            sTask0.score=score;
                            sTask0.taskId=taskId;
                            int any=user0.MystaskIds.lastIndexOf(taskId);
                            if(any==-1){
                                user0.Mystasks.add(sTask0);
                                user0.MystaskIds.add(sTask0.taskId);
                            }
                            else {
                                sTask0.score=sTask0.score > user0.Mystasks.get(any).score? sTask0.score :user0.Mystasks.get(any).score;
                                user0.Mystasks.set(user0.MystaskIds.lastIndexOf(taskId),sTask0);
                            }
                            arr_User.set(arr_UsersId.lastIndexOf(user0.getId()), user0);//user0写入总表
                            course0.taskList.get(num).stasks.add(sTask0);
                            course0.taskList.get(num).staskIds.add(sTask0.stuId);
                            int nn=course0.taskList.get(num).stasks.size();
                            System.out.println("submit success\n" +
                                    "your score is: "+course0.taskList.get(num).stasks.get(nn-1).getScore());
                            arr_courses.set(arr_courseIds.lastIndexOf(course0.getCourseId()),course0);//course0写入总表
                        }catch (Exception e){
                            System.out.println("file operation failed");
                        }
                }else System.out.println("no course selected");
            }else System.out.println("operation not allowed");
        }else System.out.println("not logged in");
    }

    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return
                b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
