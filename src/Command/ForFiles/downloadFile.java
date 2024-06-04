package Command.ForFiles;

import Command.Isgood;
import aggregate.Course;
import aggregate.Users;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static aggregate.Course.course0;
import static aggregate.Users.*;

public class downloadFile{
    public static void run(String command, ArrayList<Course> arr_courses, ArrayList<String> arr_courseIds, ArrayList<Users> arr_User, ArrayList<String> arr_UsersId) throws IOException {
        String pat1="downloadFile\\s+(\\S+)\\s+(\\S+)\\s*";
        int justit=0;
        int justit_num=0;
        int flag=0;
        int omo=0;
        String[] mm=new String[10];
        int i=0;
//        String pat2="downloadFile\\s+(\\S+)\\s+(>{1,2})\\s+(\\S*)\\s*";
//        String pat3="downloadFile\\s+(\\S+)\\s+(\\S+)\\s+(>{1,2})\\s+(\\S*)\\s*";
//        String patwrong="downloadFile\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s*";
        if(!command.contains(">"))/*不重定向*/{
            if(command.matches("downloadFile\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s*")){
                System.out.println("arguments illegal");
                return;
            }
            flag=1;
            Pattern r=Pattern.compile(pat1);
            Matcher m= r.matcher(command);
            if(!m.find()){
                System.out.println("arguments illegal");
                return;
            }
        }
        else/*重定向*/{
            String p="\\S+";
            Pattern r=Pattern.compile(p);
            Matcher m= r.matcher(command);
            while(m.find()){
                mm[i]=m.group();
                i++;
            }
            i-=1;
            if (mm[i].matches("[>]{1,2}")) {
                System.out.println("please input the path to redirect the file");
                return;
            }
            if (i == 4 && mm[1].equals(mm[4])) {
                System.out.println("input file is output file");
                return;
            }
            if(!mm[i-1].matches("[>]{1,2}")){
                System.out.println("arguments illegal");
                return;
            }
            else{
                //记录下重定向方式（复制写入/追加写入）
                justit_num=mm[i-1].length();
                if(justit_num==2) justit=1;
                try {
                    File f=new File(mm[i]);
                    FileWriter owo=new FileWriter(f,true);
                    if (i >= 5) {
                        owo.write("arguments illegal\n");
                        owo.flush();
                        owo.close();
                        return;
                    }
                    if (i <= 2) {
                        owo.write("arguments illegal\n");
                        owo.flush();
                        owo.close();
                        return;
                    }
                }catch (IOException e){
                    File f=new File(mm[i]);
                    FileWriter owo=new FileWriter(f);
                    owo.write("file operation failed\n");
                }
            }
        }
        //正式执行

        if(loginFlag){
            if(selectFlag){
                if (flag == 1)/*第一类*/ {
                    Pattern r = Pattern.compile(pat1);
                    Matcher m = r.matcher(command);
                    m.find();
                    //失败执行
                    if (Isgood.IsbadwareId(m.group(2), course0.getCourseId()) && Isgood.IsbadtaskId(m.group(2), course0.getCourseId())) {
                        System.out.println("file not found");
                        return;
                    }
                    int num = course0.taskIdList.lastIndexOf(m.group(2)) + course0.wareIdList.lastIndexOf(m.group(2)) + 1;
                    if (num == -1) {
                        System.out.println("file not found");
                        return;
                    }
                    if (m.group(2).startsWith("T"))
                        omo = 1;
                    try {
                        String data = "";
                        if (omo == 0) {
                            data = "./data/" + course0.getCourseId() + "/wares/";
                        } else {
                            data = "./data/" + course0.getCourseId() + "/tasks/"+m.group(2)+"/";
                        }
                        File f1 = new File(data);
                        File f2 = new File(m.group(1));
                        if(f2.exists())
                            f2.delete();
                        File ff2 = new File(m.group(1));
                        f1.mkdirs();
                        File[] flist = f1.listFiles();
                        if (flist != null) {
                            if(omo==0){
                                for (File f : flist) {
                                    if (f.getName().contains(m.group(2))) {
                                        Files.copy(f.toPath(), ff2.toPath());
                                        FileReader fr = new FileReader(f);
                                        int date = 0;
                                        while ((date = fr.read()) != -1) {
                                            System.out.print((char) date);
                                        }
                                        fr.close();
                                    }
                                }
                            }else{
                                for (File f : flist) {
                                        Files.copy(f.toPath(), ff2.toPath());
                                        FileReader fr = new FileReader(f);
                                        int date = 0;
                                        while ((date = fr.read()) != -1) {
                                            System.out.print((char) date);
                                        }
                                        fr.close();
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("file operation failed");
                    }
                } else {
                    if (i == 3) /*第二类*/ {
                        try {
                            File fout=new File(mm[i]);
                            FileWriter owo=new FileWriter(fout);
                            if(justit==1) owo=new FileWriter(fout,true);
                            if (Isgood.IsbadwareId(mm[1], course0.getCourseId()) && Isgood.IsbadtaskId(mm[1], course0.getCourseId())) {
                              owo.write("file not found\n");
                              owo.flush();
                              owo.close();
                              return;
                            }
                            int num = course0.taskIdList.lastIndexOf(mm[1]) + course0.wareIdList.lastIndexOf(mm[1]) + 1;
                            if (num == -1) {
                                owo.write("file not found\n");
                                owo.flush();
                                owo.close();
                                return;
                            }
                            owo.flush();
                            owo.close();
                            if (mm[1].startsWith("T"))
                                omo = 1;
                            String data = "";
                            if (omo == 0) {
                                data = "./data/" + course0.getCourseId() + "/wares/";
                            } else {
                                data = "./data/" + course0.getCourseId() + "/tasks/"+mm[1]+"/";
                            }
                            File f1 = new File(data);
                            File f2 = new File(mm[i]);
                            if(f2.exists())
                                f2.delete();
                            File ff2 = new File(mm[i]);
                            f1.mkdirs();
                            File[] flist = f1.listFiles();
                            if (flist != null) {
                                if(omo==0){
                                    for (File f : flist) {
                                        if (f.getName().contains(mm[1])) {
                                            FileWriter fw = new FileWriter(ff2);
                                            FileReader fr = new FileReader(f);
                                            int date = 0;
                                            while ((date = fr.read()) != -1) {
//                                            System.out.print((char) date);
                                                fw.write((char) date);
                                            }
                                            fr.close();
                                            fw.flush();
                                            fw.close();
                                        }
                                    }
                                }
                                else {
                                    for (File f : flist) {
                                            FileWriter fw = new FileWriter(ff2);
                                            FileReader fr = new FileReader(f);
                                            int date = 0;
                                            while ((date = fr.read()) != -1) {
//                                            System.out.print((char) date);
                                                fw.write((char) date);
                                            }
                                            fr.close();
                                            fw.flush();
                                            fw.close();
                                    }
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("file operation failed");
                        }
                    }
                    if (i == 4) /*第三类*/ {
                        try {
                            File fout=new File(mm[i]);
                            FileWriter owo=new FileWriter(fout);
                            if(justit==1) owo=new FileWriter(fout,true);
                            if (Isgood.IsbadwareId(mm[2], course0.getCourseId()) && Isgood.IsbadtaskId(mm[2], course0.getCourseId())) {
                                owo.write("file not found\n");
                                owo.flush();
                                owo.close();
                                return;
                            }
                            int num = course0.taskIdList.lastIndexOf(mm[2]) + course0.wareIdList.lastIndexOf(mm[2]) + 1;
                            if (num == -1) {
                                owo.write("file not found\n");
                                owo.flush();
                                owo.close();
                                return;
                            }
                            owo.flush();
                            owo.close();
                            if (mm[2].startsWith("T"))
                                omo = 1;
                            String data = "";
                            if (omo == 0) {
                                data = "./data/" + course0.getCourseId() + "/wares/";
                            } else {
                                data = "./data/" + course0.getCourseId() + "/tasks/"+mm[2]+"/";
                            }
                            File f1 = new File(data);
                            File f2 = new File(mm[1]);
                            if(f2.exists())
                                f2.delete();
                            File ff2 = new File(mm[1]);
                            File f3 = new File(mm[i]);
                            f1.mkdirs();
                            File[] flist = f1.listFiles();
                            if (flist != null) {
                                if(omo==0){
                                    for (File f : flist) {
                                        if (f.getName().contains(mm[2])) {
                                            FileWriter fw = new FileWriter(f3);
                                            FileReader fr = new FileReader(f);
                                            Files.copy(f.toPath(), ff2.toPath());
                                            int date = 0;
                                            while ((date = fr.read()) != -1) {
                                                System.out.print((char) date);
                                                fw.write((char) date);
                                                fw.flush();
                                            }
                                            fr.close();
                                            fw.close();
                                        }
                                    }
                                }
                                else {
                                    for (File f : flist) {
                                            FileWriter fw = new FileWriter(f3);
                                            FileReader fr = new FileReader(f);
                                            Files.copy(f.toPath(), ff2.toPath());
                                            int date = 0;
                                            while ((date = fr.read()) != -1) {
                                                System.out.print((char) date);
                                                fw.write((char) date);
                                                fw.flush();
                                            }
                                            fr.close();
                                            fw.close();
                                    }
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("file operation failed");
                        }
                    }
                }
            }else if(flag==1){
                System.out.println("no course selected");
            }
            else {
                try{
                    File f=new File(mm[i]);
                    FileWriter fw=new FileWriter(f);
                    fw.write("no course selected\n");
                    fw.flush();
                    fw.close();
                }catch (Exception e){}
            }
        }else if(flag==1){
            System.out.println("not logged in");
        }else{
            try{
                File f=new File(mm[i]);
                FileWriter fw=new FileWriter(f);
                fw.write("not logged in\n");
                fw.flush();
                fw.close();
            }catch (Exception e){}
        }

    }
}
