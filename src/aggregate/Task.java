package aggregate;

import java.util.ArrayList;

public class Task {
    private String taskId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    private  String task;

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    private  String start;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    private  String ddl;

    public String getDdl() {
        return ddl;
    }

    public void setDdl(String ddl) {
        this.ddl = ddl;
    }
    public ArrayList<sTask> stasks = new ArrayList<>();//提交作业列表
    public ArrayList<String> staskIds =new ArrayList<>();//提交作业Id列表

    public String answer=null;
    private int taskNum=0;

    public int getTaskNum() {
        this.taskNum= staskIds.size();
        return taskNum;
    }

    public double maxScore=0.0f;
    public sTask max_sTask=new sTask();
    public Task() { }

    public Task(String taskId, String task, String start, String ddl) {
        this.taskId = taskId;
        this.task = task;
        this.start = start;
        this.ddl = ddl;
    }
    public static Task task0=new Task();
    public static Task task1=new Task();//task0的一个替身
}
