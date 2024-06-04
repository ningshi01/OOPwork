package Command.ForA_Basic;

import java.io.File;

public class QUIT {
    public static void run() {
        File folder = new File("./data/");
        File f1=new File("./1.QAQ");
        File f2=new File("./9m.cc");
        File f3=new File("./error.c");
        File f4=new File("./task2.txt");
        deleteFolder(f1);
        deleteFolder(f2);
        deleteFolder(f3);
        deleteFolder(f4);
        deleteFolder(folder);
        System.out.println("----- Good Bye! -----");
        System.exit(0);
    }
    public static void deleteFolder(File folder) /*删除文件及目录*/{
        File[] files = folder.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
        folder.delete();
    }



}


