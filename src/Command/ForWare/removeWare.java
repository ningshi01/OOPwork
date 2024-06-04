package Command.ForWare;

import Command.Isgood;
import aggregate.Course;
import aggregate.Users;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static aggregate.Course.course0;
import static aggregate.Users.user0;

public class removeWare {
    public static void run(String command, ArrayList<Course> arr_courses, ArrayList<String> arr_courseIds, ArrayList<Users> arr_User, ArrayList<String> arr_UsersId) {
        String pat="\\S+";
        Pattern r= Pattern.compile(pat);
        Matcher m=r.matcher(command);
        m.find();
        if(!m.find()) {
            System.out.println("arguments illegal");
            return;
        }
        String wareId=m.group();
        if(!m.find()){
            if(Users.loginFlag){
                if(user0.getType()==1 || Users.orFlag) {
                    if(Users.selectFlag){
                        if(!Isgood.IsbadwareId(wareId,course0.getCourseId())){
                            int num=course0.wareIdList.lastIndexOf(wareId);
                            if(num!=-1){
                                course0.wareList.remove(num);
                                course0.wareIdList.remove(wareId);
                                arr_courses.set(arr_courseIds.lastIndexOf(course0.getCourseId()),course0);//course0写入总表

                                try{
                                    String data_w = "./data/" + course0.getCourseId() + "/wares/";
                                    File f1 = new File(data_w);
                                    f1.mkdirs();
                                    File[] flist=f1.listFiles();
                                    if(flist!=null){
                                        for(File f:flist){
                                            if(f.getName().contains(wareId)) f.delete();
                                        }
                                    }
                                    System.out.println("remove ware success");
                                }
                                catch (Exception e){
                                    System.out.println("delete file failed");
                                    throw e;
                                }
                            } else System.out.println("ware not found");
                        } else System.out.println("ware not found");
                    } else System.out.println("no course selected");
                } else System.out.println("permission denied");
            } else System.out.println("not logged in");
        }else System.out.println("arguments illegal");
    }
}
