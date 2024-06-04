package aggregate;

import aggregate.FVM.IVM;

import java.util.ArrayList;

public class Course {
    private String courseId;

    public String getCourseId() {
        return courseId;
    }
    private String course;

    public String getCourse() {
        return course;
    }
    private int TeacherNum=0;//老师数量
    public int getTeacherNum() {
        return TeacherNum;
    }

    public void setTeacherNum() {
        TeacherNum = this.teacherList.size();
    }
    public ArrayList<String> teacherList=new ArrayList<>();//老师存储
    private int AssistantNum=0;//助教数量

    public int getAssistantNum() {
        return AssistantNum;
    }

    public void setAssistantNum() {
        AssistantNum = this.assistantList.size();
    }
    public ArrayList<String> assistantList=new ArrayList<>();//助教存储
    private int StuNum;//学生数量

    public int getStuNum() {
        return StuNum;
    }

    public void setStuNum() {
        StuNum = this.stuList.size();
    }

    public ArrayList<String> stuList=new ArrayList<>();//学生存储
    public ArrayList<Users> adminList=new ArrayList<>();//管理员存储

    public ArrayList<String> adminIdList=new ArrayList<>();//管理员ID

    public ArrayList<Ware> wareList=new ArrayList<>();//课程资料存储
    public ArrayList<String> wareIdList=new ArrayList<>();//课程资料ID

    public ArrayList<Task> taskList =new ArrayList<>();//作业存储
    public ArrayList<String> taskIdList=new ArrayList<>();//作业ID

    public ArrayList<IVM> vmList=new ArrayList<>();
    public ArrayList<String> vmIdList=new ArrayList<>();
    public Course(){}
    public Course(String courseId, String course) {
        this.courseId = courseId;
        this.course = course;
    }

    public static Course course0=new Course();
}
