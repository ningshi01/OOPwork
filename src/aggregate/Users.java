package aggregate;

import java.util.ArrayList;

public class Users {

    private String id;//学工号
    public String getId() {
        return id;
    }

    private String firstName;//名字
    public String getFirstName() {
        return firstName;
    }

    private String lastName;//姓
    public String getLastName() {
        return lastName;
    }

    private String email;//邮箱

    public String getEmail() {
        return email;
    }

    private String password;//密码
    public String getPassword() {
        return password;
    }

    private String ppassword;//确认密码

    //区别老师（1）和学生（0）
    private int type;

    public int getType() {
        return type;
    }

    //区别助教（>0）和普通学生（0）
    private int stu_type=0;

    public int getStu_type() {
        return stu_type;
    }

    public void setStu_type() {
        this.stu_type =this.helpCourse.size();
    }

    public ArrayList<Course> MyCourse=new ArrayList<>();//老师的课表和学生的课表
    public ArrayList<String> MyCourseId=new ArrayList<>();//课表ID
    public ArrayList<Course> helpCourse=new ArrayList<>();//助教管理课表
    public ArrayList<String> helpCourseId=new ArrayList<>();//课表ID

    public ArrayList<sTask> Mystasks =new ArrayList<>();//我交的作业
    public ArrayList<String> MystaskIds =new ArrayList<>();//我交的作业Id
    public Users(){}
    public Users(String id, String firstName, String lastName, String email, String password, String ppassword)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.ppassword = ppassword;
        if(id.length()==5)
            this.type=1;//老师
        else
            this.type=0;//学生
    }
    public static Users user0 =new Users();
    public static Users user1 =new Users();//临时用来找一个user0的替身
    public static boolean loginFlag=false;//登陆
    public static boolean selectFlag=false;//选择课程
    public static boolean orFlag=false;//切换身份
}
