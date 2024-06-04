package Command.ForTask;

import aggregate.Course;
import aggregate.Task;
import aggregate.Users;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static aggregate.Course.course0;
import static aggregate.Users.user0;

public class listTask {
    public static void run(String command, ArrayList<Course> arr_courses, ArrayList<String> arr_courseIds, ArrayList<Users> arr_User, ArrayList<String> arr_UsersId) {
        String pat = "\\S+";
        Pattern r = Pattern.compile(pat);
        Matcher m = r.matcher(command);
        m.find();
        if (m.find()) {
            System.out.println("arguments illegal");
            return;
        }
        Comparator cmp4=new Comparator<Task>() {
            @Override
            public int compare(Task p1, Task p2) {
                return p1.getTaskId().compareTo(p2.getTaskId());
            }
        };
        if (Users.loginFlag) {
                if(Users.selectFlag){
                    if(course0.taskList.size()!=0){
                        Collections.sort(course0.taskList, cmp4);
                        Collections.sort(course0.taskIdList);
                        if (Users.orFlag || user0.getType() == 1) /*管理端*/ {
                            course0.taskList.forEach(e -> {
                                System.out.println("[ID:" + e.getTaskId() + "] [Name:" + e.getTask() + "] [SubmissionStatus:" +e.stasks.size()+"/"+course0.stuList.size() + "] [StartTime:" + e.getStart() + "] [EndTime:" + e.getDdl() + "]");
                            });
                        } else/*学生端*/ {
                            course0.taskList.forEach(e -> {
                                System.out.println("[ID:" + e.getTaskId() + "] [Name:" + e.getTask() + "] [Status:" + ((e.staskIds.lastIndexOf(user0.getId())==-1)?"undone":"done") + "] [StartTime:" + e.getStart() + "] [EndTime:" + e.getDdl() + "]");
                            });
                        }
                        //[Status:undone/done]
                    }else System.out.println("total 0 task");
                }else System.out.println("no course selected");
        } else System.out.println("not logged in");
    }
}