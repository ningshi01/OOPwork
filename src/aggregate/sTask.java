package aggregate;

public class sTask {
    public String stuId;
    public String stuName;
    public String taskId;
    public double score=-1.0f;

    public String getScore() {
        if(score>=0)
            return this.score+"";
        else
            return "None";
    }

    public sTask(){}
    public sTask(String stuId, float score) {
        this.stuId = stuId;
        this.score = score;
    }
    public static sTask sTask0=new sTask();
}
