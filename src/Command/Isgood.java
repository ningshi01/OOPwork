package Command;

import java.util.Objects;
public class Isgood{
    public static boolean For_register(String id, String firstName, String lastName, String email, String password, String ppassword) {

        if(IsBadId(id))
        {
            System.out.println("user id illegal");
            return false;
        }
        else if (IsBadFirstName(firstName))
        {
            System.out.println("user name illegal");
            return false;
        }
        else if (IsBadLastName(lastName))
        {
            System.out.println("user name illegal");
            return false;
        }
        else if(IsBadEmail(email))
        {
            System.out.println("email address illegal");
            return false;
        }
        else if (Isbadpassword(password))
        {
            System.out.println("password illegal");
            return false;
        }
        else if(Isbadppassword(password,ppassword))
        {
            System.out.println("passwords inconsistent");
            return false;
        }
        return true;
    }
    public static boolean For_login(String id,String password) {
        if(IsBadId(id)) {
            System.out.println("user id illegal");
            return false;
        }
        else if (Isbadpassword(password)) {
            System.out.println("wrong password");
            return false;
        }
        return true;
    }
    public static boolean For_printInfo(String id) {
        if(IsBadId(id))
        {
            System.out.println("user id illegal");
            return false;
        }
        return true;
    }
    public static boolean IsBadId(String id) {
        //检查id
        boolean flag_id=false;
        if(id.length()==8 || id.length()==9) {
            if(id.length()==8 && id.matches("\\d{8}")) //本科生判断
            {
                char[] arr = id.toCharArray();
                int year = 0, xuey = 0, daban = 0,bianh = 0;
                for (int i = 0; i < 2; i++) {
                    year *= 10;
                    year += Integer.parseInt(arr[i] + "");
                }
                if (year < 17 || year > 22)
                    flag_id=true;//System.out.println("user id illegal");
                for (int i = 2; i < 4; i++) {
                    xuey *= 10;
                    xuey += Integer.parseInt(arr[i] + "");
                }
                if (xuey < 1 || xuey > 43)
                    flag_id=true;//System.out.println("user id illegal");
                daban = Integer.parseInt(arr[4] + "");
                if (daban < 1 || daban > 6)
                    flag_id=true;//System.out.println("user id illegal");
                for(int i=5;i<8;i++)
                {
                    bianh*=10;
                    bianh+=Integer.parseInt(arr[i]+"");
                }
                if(bianh==0)
                    flag_id=true;//System.out.println("user id illegal");
            }
            else if(id.length()==9 && id.matches("[A-Z]{2}\\d{7}")){
                char[] arr=id.toCharArray();
                String lx="";
                lx+=arr[0];
                lx+=arr[1];
                if(Objects.equals(lx,"SY") || Objects.equals(lx,"ZY"))
                {
                    int year = 0, xuey = 0, daban = 0,bianh=0;
                    for (int i = 2; i < 4; i++) {
                        year *= 10;
                        year += arr[i]-'0';
                    }
                    if (year < 19 || year > 22)
                        flag_id=true;//System.out.println("user id illegal");
                    for (int i = 4; i < 6; i++) {
                        xuey *= 10;
                        xuey += Integer.parseInt(arr[i] + "");
                    }
                    if (xuey < 1 || xuey > 43)
                        flag_id=true;//System.out.println("user id illegal");
                    daban = Integer.parseInt(arr[6] + "");
                    if (daban < 1 || daban > 6)
                        flag_id=true;//System.out.println("user id illegal");
                    for(int i=7;i<9;i++)
                    {
                        bianh*=10;
                        bianh+=Integer.parseInt(arr[i]+"");
                    }
                    if(bianh==0)
                        flag_id=true;//System.out.println("user id illegal");
                }
                else if(Objects.equals(lx,"BY"))
                {
                    int year = 0, xuey = 0, daban = 0, bianh = 0;
                    for (int i = 2; i < 4; i++) {
                        year *= 10;
                        year += arr[i]-'0';
                    }
                    if (year < 17 || year > 22)
                        flag_id=true;//System.out.println("user id illegal");
                    for (int i = 4; i < 6; i++) {
                        xuey *= 10;
                        xuey += Integer.parseInt(arr[i] + "");
                    }
                    if (xuey < 1 || xuey > 43)
                        flag_id=true;//System.out.println("user id illegal");
                    daban = Integer.parseInt(arr[6] + "");
                    if (daban < 1 || daban > 6)
                        flag_id=true;//System.out.println("user id illegal");
                    for(int i=7;i<9;i++)
                    {
                        bianh*=10;
                        bianh+=Integer.parseInt(arr[i]+"");
                    }
                    if(bianh==0)
                        flag_id=true;//System.out.println("user id illegal");
                }
                else
                    flag_id=true;//System.out.println("user id illegal");

            }
            else
                flag_id=true;
        }
        else if(id.length()==5)//判断老师id
        {
            if(id.matches("\\d{5}"))
            {
                char[] arr=id.toCharArray();
                int num=0;
                for(int i=0;i<id.length();i++)
                {
                    num*=10;
                    num+=arr[i]-'0';
                }
                if(num==0 || num >99999) flag_id=true;
            }
            else
                flag_id=true;
        }
        else
            return true;
        return flag_id;
    }
    public static boolean IsBadFirstName(String firstName){
        //检查名字
        boolean flag_firstName=false;
        if (firstName.length()<1 || firstName.length()>20)
            flag_firstName=true;
        else
        {
            if(firstName.matches("[A-Z][a-z]+")==false)
                flag_firstName=true;
        }
        return flag_firstName;
    }
    public static boolean IsBadLastName(String lastName){
        //检查姓氏
        boolean flag_lastName=false;
        if (lastName.length()<1 || lastName.length()>20)
            flag_lastName=true;
        else
        {
            if(lastName.matches("[A-Z][a-z]+")==false)
                flag_lastName=true;
        }
        return flag_lastName;
    }
    public static boolean IsBadEmail(String email){
        //检查邮箱
        boolean flag_email=false;
        if(email.matches("\\w+@\\w+(\\.\\w+)+"))
        {
            flag_email=false;
        }
        else
        {
            flag_email=true;
        }
        return flag_email;
    }
    public static boolean Isbadpassword(String password){
        //检查密码
        boolean flag_password=false;
        if(password.matches("[A-Za-z]+\\w+"))
        {
            int num=password.length();
            if(num<8 || num>16)
                flag_password=true;
        }
        else
        {
            flag_password=true;
        }
        return flag_password;
    }
    public static boolean Isbadppassword(String password,String ppassword){
        //检查确认密码
        boolean flag_ppassword=false;
        if(Objects.equals(password,ppassword)==false)
            flag_ppassword=true;
        return flag_ppassword;
    }


    public static boolean IsbadcourseId(String courseId){
        boolean flag_courseId=false;
        if(courseId.matches("C((1[7-9])|(2[0-2]))((0[1-9])|[1-9]\\d)")==false)
            flag_courseId=true;
        return flag_courseId;
    }
    public static boolean Isbadcourse(String course){
        boolean flag_course =false;
        if ((course.length() < 6) || (course.length() > 16)) {
            flag_course = true;
        }
        else if(course.matches("\\S*\\W+\\S*"))
            flag_course=true;
        return flag_course;
    }
    public static boolean IsbadwareId(String wareId,String courseId){
        boolean flag_wareId=false;
        if(wareId.matches("W\\d{6}")){
            char[] arr=wareId.toCharArray();
            char[] aarr=courseId.toCharArray();
            for(int i=1;i<5;i++){
                if(arr[i]!=aarr[i]){
                    return true;
                }
            }
            int num=0;
            for(int i=5;i<7;i++){
                num*=10;
                num+=arr[i]-'0';
            }
            if(num==0) flag_wareId=true;
        }else flag_wareId=true;
        return flag_wareId;
    }
    public static boolean Isbadware(String ware){
        boolean flag_ware=false;
        if(ware.matches("\\S+\\.\\S+")){
            if (((ware.length() < 6) || (ware.length() > 16)) || (ware.matches("\\w+.[A-Za-z0-9]+")) == false) {
                flag_ware = true;
            }
        }
        else flag_ware=true;
        return flag_ware;
    }
    public static boolean IsbadtaskId(String taskId,String courseId){
        boolean flag_taskId=false;
        if(taskId.matches("T\\d{6}")){
            char[] arr=taskId.toCharArray();
            char[] aarr=courseId.toCharArray();
            for(int i=1;i<5;i++){
                if(arr[i]!=aarr[i]){
                    return true;
                }
            }
            int num=0;
            for(int i=5;i<7;i++){
                num*=10;
                num+=arr[i]-'0';
            }
            if(num==0) flag_taskId=true;
        }
        else flag_taskId=true;
        return flag_taskId;
    }
    public static boolean Isbadtask(String task){
        boolean flag_task=false;
        if(task.matches("\\S+\\.\\S+")) {
            if (((task.length() < 6) || (task.length() > 16)) || (task.matches("\\w+.[A-Za-z0-9]+")) == false) {
                flag_task = true;
            }
        }
        else flag_task=true;
        return flag_task;
    }
    public static boolean Isbadtime(String time){
        boolean flag_time=false;
        if(time.matches("((19\\d{2})||([2-9]\\d{3}))-((0[1-9])||(1[0-2]))-\\d{2}-\\d{2}:\\d{2}:\\d{2}\\b")){
            char[] arr=time.toCharArray();
            int year=0,month=0,day=0,hour=0,minute=0,s=0;
            for(int i=0;i<4;i++){
                year*=10;
                year+=arr[i]-'0';
            }
            for(int i=5;i<7;i++){
                month*=10;
                month+=arr[i]-'0';
            }
            for(int i=8;i<10;i++){
                day*=10;
                day+=arr[i]-'0';
            }
            for(int i=11;i<13;i++){
                hour*=10;
                hour+=arr[i]-'0';
            }
            for(int i=14;i<16;i++){
                minute*=10;
                minute+=arr[i]-'0';
            }
            for(int i=17;i<19;i++){
                s*=10;
                s+=arr[i]-'0';
            }
            //判断日期
            int mouthDay[]= {31,28,31,30,31,30,31,31,30,31,30,31};
            if(month==2&&day<=29&&(year%400==0||(year%4==0&&year%100!=0))){
                flag_time=false;
            }
            else {
                if(day>mouthDay[month-1]){
                    flag_time=true;
                }
            }
            //判断时刻
            if(flag_time==false){
                if (hour >= 0 && hour < 24) {
                    if (minute >= 0 && minute < 60) {
                        if (s >= 0 && s < 60) {
                            flag_time = false;
                        } else flag_time = true;
                    } else flag_time = true;
                } else flag_time = true;
            }
        } else flag_time=true;
        return flag_time;
    }
    public static boolean Isbadddl(String start,String ddl){
        boolean flag_ddl=false;
        char[] arr1=start.toCharArray();
        char[] arr2=ddl.toCharArray();
        int year1=0,month1=0,day1=0,hour1=0,minute1=0,s1=0;
        int year2=0,month2=0,day2=0,hour2=0,minute2=0,s2=0;
        for(int i=0;i<4;i++){
            year1*=10;
            year1+=arr1[i]-'0';
            year2*=10;
            year2+=arr2[i]-'0';
        }
        for(int i=5;i<7;i++){
            month1*=10;
            month1+=arr1[i]-'0';
            month2*=10;
            month2+=arr1[i]-'0';
        }
        for(int i=8;i<10;i++){
            day1*=10;
            day1+=arr1[i]-'0';
            day2*=10;
            day2+=arr1[i]-'0';
        }
        for(int i=11;i<13;i++){
            hour1*=10;
            hour1+=arr1[i]-'0';
            hour2*=10;
            hour2+=arr2[i]-'0';
        }
        for(int i=14;i<16;i++){
            minute1*=10;
            minute1+=arr1[i]-'0';
            minute2*=10;
            minute2+=arr2[i]-'0';
        }
        for(int i=17;i<19;i++){
            s1*=10;
            s1+=arr1[i]-'0';
            s2*=10;
            s2+=arr2[i]-'0';
        }
        if(year2-year1>0) return false;
        else if(year2-year1<0) return true;
        else{
            if(month2-month1>0) return false;
            else if(month2-month1<0) return true;
            else{
                if(day2-day1>0) return false;
                else if(day2-day1<0) return true;
                else{
                    if(hour2-hour1>0) return false;
                    else if(hour2-hour1<0) return true;
                    else{
                        if(minute2-minute1>0) return false;
                        else if(minute2-minute1<0) return true;
                        else{
                            if(s2-s1>0) return false;
                            else if(s2-s1<=0) return true;
                        }
                    }
                }
            }
        }


        return flag_ddl;
    }


}
