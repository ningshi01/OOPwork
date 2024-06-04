package Command;

import aggregate.Course;
import aggregate.Task;
import aggregate.Users;
import aggregate.sTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static aggregate.Course.*;
import static aggregate.Users.*;
import static aggregate.Task.*;

public class queryScore {
    public static void run(String command, ArrayList<Course> arr_courses, ArrayList<String> arr_courseIds, ArrayList<Users> arr_User, ArrayList<String> arr_UsersId) {
        //排个序
        Comparator cmp5=new Comparator<sTask>() {
            @Override
            public int compare(sTask p1, sTask p2) {
                if(p1.taskId.compareTo(p2.taskId)!=0){
                    return p1.taskId.compareTo(p2.taskId);
                }
                else if(p2.score!=p1.score){
                    return (int)(p2.score-p1.score);
                }
                else{
                    return p1.stuId.compareTo(p2.stuId);
                }
            }
        };
        //
        String p="\\S+";
        Pattern r=Pattern.compile(p);
        Matcher m=r.matcher(command);
        String[] mm=new String[10];
        int i=0;
        int flag=-1;
        while(m.find())
            mm[i++]=m.group();
        i-=1;
        if(i>=3){
            System.out.println("arguments illegal");
            return;
        }
        String taskId="";
        String Id="";
        if(i==0) flag=0;//无指定
        if(i==1){
            if(user0.getType()==1 || orFlag){
                if(!mm[1].startsWith("T")){
                    Id=mm[1];
                    flag=1;//管理只有学号
                }
                else {
                    taskId=mm[1];
                    flag=2;//管理只有课程编号
                }
            }
            else {
                taskId=mm[1];
                flag=3;//学生有课程编号
            }
        }
        if(i==2){
            taskId=mm[1];
            Id=mm[2];
            flag=4;//管理齐活
        }
        if(loginFlag){
            if(selectFlag){
                if(user0.getType()==1 || orFlag)/*///管理端///*/{
                    if(flag==0)/*无指定*/{
                        int wx=0,xx=1;
                        for(int j=0;j<course0.taskList.size();j++){
                            Task wkwk=course0.taskList.get(j);
                            Collections.sort(wkwk.stasks,cmp5);
                            Collections.sort(wkwk.staskIds);
                            for(int k=0;k<wkwk.stasks.size();k++){
                                sTask pq=wkwk.stasks.get(k);
                                wx++;
                            }
                        }
                        System.out.println("total "+wx+" results");
                        for(int j=0;j<course0.taskList.size();j++){
                            Task wkwk=course0.taskList.get(j);
                            for(int k=0;k<wkwk.stasks.size();k++){
                                sTask pq=wkwk.stasks.get(k);
                                System.out.println("["+(xx++)+"] [ID:"+pq.stuId+"] [Name:"+pq.stuName+"] [Task_ID:"+pq.taskId+"] [Score:"+pq.getScore()+"]");
                            }
                        }
                        return;
                    }
                    if(flag!=1){//课程编号不合法
                        if (Isgood.IsbadtaskId(taskId, course0.getCourseId())) {
                            System.out.println("task not found");
                            return;
                        }
                        //课程编号不存在
                        int num = course0.taskIdList.lastIndexOf(taskId);
                        if (num == -1) {
                            System.out.println("task not found");
                            return;
                        }
                    }
                    if(flag!=2){//学号judge
                        int num = course0.stuList.lastIndexOf(Id);
                        if (Isgood.IsBadId(Id) || num == -1) {
                            System.out.println("student not found");
                            return;
                        }
                    }
                    if(flag==1)/*指定+学号*/ {
                        user1 =arr_User.get(arr_UsersId.lastIndexOf(Id));
                        int wx=0;
                        Collections.sort(user1.Mystasks,cmp5);
                        Collections.sort(user1.MystaskIds);
                        for(int j = 0, jj = 1; j<user1.Mystasks.size(); j++){
                            sTask wkwk=user1.Mystasks.get(j);
                            if(!Isgood.IsbadtaskId(wkwk.taskId,course0.getCourseId()))
                                wx++;
                        }
                        System.out.println("total "+wx+" results");
                        for(int j = 0, jj = 1; j<user1.Mystasks.size(); j++){
                            sTask wkwk=user1.Mystasks.get(j);
                            if(!Isgood.IsbadtaskId(wkwk.taskId,course0.getCourseId()))
                            System.out.println("["+(jj++)+"] [ID:"+ user1.getId()+"] [Name:"+ user1.getLastName()+" "+ user1.getFirstName()+"] [Task_ID:"+wkwk.taskId+"] [Score:"+wkwk.getScore()+"]");
                        }
                    }
                    else if(flag==2)/*指定+课程作业编号*/{
                        int isHave=course0.taskList.get(course0.taskIdList.lastIndexOf(taskId)).getTaskNum();
                        int num=course0.taskIdList.lastIndexOf(taskId);
                        if(isHave==0) System.out.println("total 0 result");
                        else {
                            task1=course0.taskList.get(num);
                            Collections.sort(task1.stasks,cmp5);
                            Collections.sort(task1.staskIds);
                            System.out.println("total "+isHave+" results");
                            task1.stasks.forEach(e->{
                                System.out.println("["+(task1.stasks.lastIndexOf(e)+1)+"] [ID:"+e.stuId+"] [Name:"+e.stuName+"] [Task_ID:"+e.taskId+"] [Score:"+e.getScore()+"]");
                            });
                        }
                    }
                    else/*齐活*/{
                        int isHave=course0.taskList.get(course0.taskIdList.lastIndexOf(taskId)).getTaskNum();
                        int num=course0.taskIdList.lastIndexOf(taskId);
                        if(isHave==0) System.out.println("total 0 result");
                        else {
                            task1=course0.taskList.get(num);
                            System.out.println("total 1 result");
                            sTask e=task1.stasks.get(task1.staskIds.lastIndexOf(Id));
                            System.out.println("[1] [ID:"+e.stuId+"] [Name:"+e.stuName+"] [Task_ID:"+e.taskId+"] [Score:"+e.getScore()+"]");
                        }
                    }
                }
                else/*///学生端///*/{
                    if(i==2){
                        System.out.println("permission denied");
                        return;
                    }
                    if(flag==3)/*指定课程编号*/{
                        //课程编号不合法
                        if (Isgood.IsbadtaskId(taskId, course0.getCourseId())) {
                            System.out.println("task not found");
                            return;
                        }
                        //课程编号不存在
                        if (course0.taskIdList.lastIndexOf(taskId) == -1) {
                            System.out.println("task not found");
                            return;
                        }
                        /*指定+课程编号*/
                        int isHave=course0.taskList.get(course0.taskIdList.lastIndexOf(taskId)).staskIds.lastIndexOf(user0.getId());
                        if(isHave==-1) System.out.println("total 0 result");
                        else {
                            System.out.println("total 1 result");
                            sTask ss=new sTask();
                            ss=course0.taskList.get(course0.taskIdList.lastIndexOf(taskId)).stasks.get(isHave);
                            System.out.println("[1] [ID:"+ss.stuId+"] [Name:"+ss.stuName+"] [Task_ID:"+ss.taskId+"] [Score:"+ss.getScore()+"]");
                        }
                        return;
                    }
                    if(flag==0)/*无指定*/{
                        int wx=0;
                        for(int j = 0, jj = 1; j<user0.Mystasks.size(); j++){
                            sTask wkwk=user0.Mystasks.get(j);
                            if(!Isgood.IsbadtaskId(wkwk.taskId, course0.getCourseId()))
                                wx++;
                        }
                        Collections.sort(user0.Mystasks,cmp5);
                        Collections.sort(user0.MystaskIds);
                        System.out.println("total "+wx+" results");
                        for(int j = 0, jj = 1; j<user0.Mystasks.size(); j++){
                            sTask wkwk=user0.Mystasks.get(j);
                            if(!Isgood.IsbadtaskId(wkwk.taskId, course0.getCourseId()))
                            System.out.println("["+(jj++)+"] [ID:"+user0.getId()+"] [Name:"+user0.getLastName()+" "+user0.getFirstName()+"] [Task_ID:"+wkwk.taskId+"] [Score:"+wkwk.getScore()+"]");
                        }
                        return;
                    }

                }
            }else System.out.println("no course selected");
        }else System.out.println("not logged in");
    }
}