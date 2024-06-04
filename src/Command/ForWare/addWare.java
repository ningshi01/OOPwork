package Command.ForWare;

import Command.ForA_Basic.QUIT;
import Command.Isgood;
import aggregate.Course;
import aggregate.Users;
import aggregate.Ware;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static aggregate.Course.course0;
import static aggregate.Users.user0;
import static aggregate.Ware.ware0;

public class addWare {
    public static void run(String command, ArrayList<Course> arr_courses, ArrayList<String> arr_courseIds, ArrayList<Users> arr_User, ArrayList<String> arr_UsersId) {
        String patwrong="addWare\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s*";
        String pat="addWare\\s+(\\S+)\\s+(\\S+)\\s*";
        Pattern r_wrong = Pattern.compile(patwrong);
        Pattern r = Pattern.compile(pat);
        Matcher m_wrong = r_wrong.matcher(command);
        Matcher m = r.matcher(command);
        if(m_wrong.find()) {
            System.out.println("arguments illegal");
            return;
        }
        if(m.find()) {
            String m2;
            if(m.group(2).startsWith("./")) {
                m2= m.group(2).replaceFirst("./", "");
            }
            else {
                m2=m.group(2);
            }

            if (Users.loginFlag) {
                if (user0.getType() == 1 || user0.orFlag) {
                    if (Users.selectFlag) {
                        if(!Isgood.IsbadwareId(m.group(1),course0.getCourseId())){
                            int num=course0.wareIdList.lastIndexOf(m.group(1));
                            if(num==-1)/*无重复*/{
                                if(!Isgood.Isbadware(m2)){
                                    try{
                                        String data="./data/"+course0.getCourseId()+"/wares/";
                                        File f1=new File(data);
                                        File f2=new File(m2);
                                        f1.mkdirs();
                                        if(!f2.exists()){
                                            System.out.println("ware file does not exist");
                                            return;
                                        }
                                        data+=m.group(1)+"_"+m2;
                                        File dest=new File(data);
                                        if(dest.exists()) dest.delete();
                                        Files.copy(f2.toPath(),dest.toPath());

                                        ware0=new Ware(m.group(1),m2);
                                        course0.wareIdList.add(m.group(1));
                                        course0.wareList.add(ware0);
                                        arr_courses.set(arr_courseIds.lastIndexOf(course0.getCourseId()),course0);//course0写入总表

                                        System.out.println("add ware success");
                                    }catch (IOException e){
                                        System.out.println("ware file operation failed");
                                    }catch (Exception e){
                                        System.out.println("unexpected error");
                                    }
                                } else System.out.println("ware name illegal");
                            }
                            else/*有重复需删除和重命名*/ {
                                if(!Isgood.Isbadware(m2)) {
                                    try{
                                        String data="./data/"+course0.getCourseId()+"/wares/";
                                        File f1=new File(data);
                                        File f2=new File(m2);
                                        f1.mkdirs();
                                        File[] flist=f1.listFiles();
                                        if(flist!=null){
                                            for(File f:flist){
                                                if(f.getName().contains(m.group(1))) {
                                                    QUIT.deleteFolder(f);
                                                }
                                            }
                                        }
                                        if(!f2.exists()){
                                            System.out.println("ware file does not exist");
                                            return;
                                        }
                                        data+=m.group(1)+"_"+m2;
                                        File dest=new File(data);
                                        if(dest.exists()) dest.delete();
                                        Files.copy(f2.toPath(),dest.toPath());

                                        course0.wareList.remove(num);
                                        course0.wareIdList.remove(num);

                                        ware0 = new Ware(m.group(1), m2);
                                        course0.wareIdList.add(m.group(1));
                                        course0.wareList.add(ware0);
                                        arr_courses.set(arr_courseIds.lastIndexOf(course0.getCourseId()), course0);

                                        System.out.println("add ware success");
                                    }catch (IOException e){
                                        System.out.println("ware file operation failed");
                                    }catch (Exception e){
                                        System.out.println("unexpected error");
                                    }
                                }else System.out.println("ware name illegal");
                            }
                        } else System.out.println("ware id illegal");
                    } else System.out.println("no course selected");
                } else System.out.println("permission denied");
            }else System.out.println("not logged in");
        }else System.out.println("arguments illegal");
    }
}