package Command;

import aggregate.Course;
import aggregate.Users;
import aggregate.sTask;

import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static aggregate.Course.*;
import static aggregate.Users.*;
public class addAnswer {
    public static void run(String command, ArrayList<Course> arr_courses, ArrayList<String> arr_courseIds, ArrayList<Users> arr_User, ArrayList<String> arr_UsersId) {
        String p="\\S+";
        Pattern r=Pattern.compile(p);
        Matcher m=r.matcher(command);
        String[] mm=new String[10];
        int i=0;
        int flag=0;
        String taskId="";
        String path="";
        while(m.find())
            mm[i++]=m.group();
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
            if(user0.getType()==1 || orFlag){
                if(selectFlag){
                    taskId="";
                    path="";
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
                    course0.taskList.get(num).answer=path;
                    try{
                        String data=""+path;
                        File f1=new File(data);
                        File f2=new File("./data/"+course0.getCourseId()+"/answers/");
                        f2.mkdirs();
                        File f3=new File("./data/"+course0.getCourseId()+"/answers/"+taskId+".ans");
                        if(f3.exists()) f3.delete();
                        File f33=new File("./data/"+course0.getCourseId()+"/answers/"+taskId+".ans");
                        Files.copy(f1.toPath(),f33.toPath());
                        int nn=course0.taskList.get(num).stasks.size();
                        for(int ii=0;ii<course0.taskList.get(num).stasks.size();ii++){
                            //////
                            //批作业
                            sTask wkwk=course0.taskList.get(num).stasks.get(ii);
                            int date = 0;
                            double sum = 0;
                            double for_sum = 0;
                            boolean ffll = false;
                            File f_ans = new File("./data/" + course0.getCourseId() + "/answers/" + taskId + ".ans");
                            if (f_ans.exists()) {
                                FileReader fr1 = new FileReader(f_ans);
                                FileReader fr2 = new FileReader("./data/"+course0.getCourseId()+"/tasks/"+taskId+"/"+wkwk.stuId+".task");
                                while ((date = fr1.read()) != -1) {
                                    int date0 = fr2.read();
                                    char dd1 = ((char) date);
                                    char dd2 = ((char) date0);
                                    char d1 = Character.toUpperCase(dd1);
                                    char d2 = Character.toUpperCase(dd2);
                                    if (d1 != d2) ffll = true;
                                    if (((char) date) == '\n') {
                                        sum++;
                                        if (ffll == false) {
                                            for_sum++;
                                        }
                                        ffll = false;
                                    }
                                }
                                fr1.close();
                                fr2.close();
                                wkwk.score = div(100 * for_sum, sum, 1);
                            }
                            //end_批作业
                            //////
                        }
                        System.out.println("add answer success");
                        arr_courses.set(arr_courseIds.lastIndexOf(course0.getCourseId()),course0);//course0写入总表
                    }catch (Exception e){
                        System.out.println("file operation failed");
                    }
                }else System.out.println("no course selected");
            }else System.out.println("permission denied");
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