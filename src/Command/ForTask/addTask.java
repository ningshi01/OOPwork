package Command.ForTask;

import Command.ForA_Basic.QUIT;
import Command.Isgood;
import aggregate.Course;
import aggregate.Task;
import aggregate.Users;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static aggregate.Course.course0;
import static aggregate.Task.task0;
import static aggregate.Users.user0;

public class addTask {
    public static void run(String command, ArrayList<Course> arr_courses, ArrayList<String> arr_courseIds, ArrayList<Users> arr_User, ArrayList<String> arr_UsersId) {
        String patwrong="addTask\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s*";
        String pat="addTask\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s*";
        Pattern r_wrong = Pattern.compile(patwrong);
        Pattern r = Pattern.compile(pat);
        Matcher m_wrong = r_wrong.matcher(command);
        Matcher m = r.matcher(command);
        if(m_wrong.find()) System.out.println("arguments illegal");
        else if(m.find()){
            String m2;
            if(m.group(2).startsWith("./")) {
                m2= m.group(2).replaceFirst("./", "");
            }
            else {
                m2=m.group(2);
            }
            if(Users.loginFlag){
                if(user0.getType()==1 || Users.orFlag){
                    if(Users.selectFlag){
                        if(!Isgood.IsbadtaskId(m.group(1),course0.getCourseId())){
                            int num=course0.taskIdList.lastIndexOf(m.group(1));
                            if(num==-1)/*无重复*/{
                                if(!Isgood.Isbadtask(m2)){
                                    if((!Isgood.Isbadtime(m.group(3)))&&(!Isgood.Isbadtime(m.group(4)))){
                                        if(!Isgood.Isbadddl(m.group(3),m.group(4))){

                                            task0=new Task(m.group(1),m2,m.group(3),m.group(4));
                                            course0.taskIdList.add(m.group(1));
                                            course0.taskList.add(task0);
                                            arr_courses.set(arr_courseIds.lastIndexOf(course0.getCourseId()),course0);//写入总表

                                            try{
                                                String data="./data/"+course0.getCourseId()+"/tasks/"+m.group(1)+"/";
                                                File f1=new File(data);
                                                File f2=new File(m2);
                                                f1.mkdirs();
                                                if(!f2.exists()){
                                                    System.out.println("task file not found");
                                                    return;
                                                }
                                                data+=m2;
                                                File dest=new File(data);
                                                if(dest.exists()) dest.delete();
                                                Files.copy(f2.toPath(),dest.toPath());
                                                System.out.println("add task success");
                                            }catch (IOException e){
                                                System.out.println("file operation failed");
                                            }catch (Exception e){
                                                System.out.println();
                                            }
                                        }else System.out.println("task time illegal");
                                    }else System.out.println("task time illegal");
                                }else System.out.println("task name illegal");
                            }
                            else/*有重复*/{
                                if(!Isgood.Isbadtask(m2)){
                                    if((!Isgood.Isbadtime(m.group(3)))&&(!Isgood.Isbadtime(m.group(4)))){
                                        if(!Isgood.Isbadddl(m.group(3),m.group(4))){
                                            course0.taskList.remove(num);
                                            course0.taskIdList.remove(num);

                                            task0=new Task(m.group(1),m2,m.group(3),m.group(4));
                                            course0.taskIdList.add(m.group(1));
                                            course0.taskList.add(task0);
                                            arr_courses.set(arr_courseIds.lastIndexOf(course0.getCourseId()),course0);//写入总表

                                            try{
                                                String data="./data/"+course0.getCourseId()+"/tasks/"+m.group(1)+"/";
                                                File f1=new File(data);
                                                File f2=new File(m2);
                                                f1.mkdirs();
                                                File[] flist=f1.listFiles();
                                                if(flist!=null){
                                                    for(File f:flist){
                                                        QUIT.deleteFolder(f);
                                                    }
                                                }
                                                if(!f2.exists()){
                                                    System.out.println("task file not found");
                                                    return;
                                                }
                                                data+=m2;
                                                File dest=new File(data);
                                                if(dest.exists()) dest.delete();
                                                Files.copy(f2.toPath(),dest.toPath());
                                                System.out.println("add task success");
                                            }catch (IOException e){
                                                System.out.println("task file not found");
                                            }catch (Exception e){
                                                System.out.println("file operation failed");
                                            }
                                        }else System.out.println("task time illegal");
                                    }else System.out.println("task time illegal");
                                }else System.out.println("task name illegal");
                            }
                        }else System.out.println("task id illegal");
                    }else System.out.println("no course selected");
                }else System.out.println("permission denied");
            } else System.out.println("not logged in");
        } else System.out.println("arguments illegal");
    }
}