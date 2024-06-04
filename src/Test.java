import Command.*;
import Command.ForA_Basic.*;
import Command.ForAdmin.*;
import Command.ForCourse.*;
import Command.ForFiles.downloadFile;
import Command.ForFiles.openFile;
import Command.ForStudent.*;
import Command.ForTask.*;
import Command.ForVM.*;
import Command.ForWare.*;
import aggregate.*;
import aggregate.Course;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) throws IOException {
        ArrayList<Users> arr_User = new ArrayList<>();
        ArrayList<String> arr_UsersId=new ArrayList<>();
        ArrayList<String> arr_UsersPassword=new ArrayList<>();

        ArrayList<Course> arr_courses=new ArrayList<>();
        ArrayList<String> arr_courseIds=new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            String pat = "\\S+";
            Pattern rr=Pattern.compile(pat);
            Matcher mm= rr.matcher(command);
            if(!mm.find())/*空白输入*/{
                continue;
            }
            String get0=mm.group();
            if (Objects.equals(get0,"QUIT")){
                QUIT.run();
            }
            else if (Objects.equals(get0, "printInfo")){
                printInfo.run(command,arr_User,arr_UsersId);
            }
            else if (Objects.equals(get0, "logout")){
                logout.run();
            }
            else if(Objects.equals(get0,"register")){
                register.run(command,arr_User,arr_UsersId,arr_UsersPassword);
            }
            else if (Objects.equals(get0,"login")){
                login.run(command,arr_User,arr_UsersId,arr_UsersPassword);
            }
            else if(Objects.equals(get0,"addCourse")){
                addCourse.run(command,arr_courses,arr_courseIds,arr_User,arr_UsersId);
            }
            else if(Objects.equals(get0,"removeCourse")){
                removeCourse.run(command,arr_courses,arr_courseIds,arr_User,arr_UsersId);
            }
            else if(Objects.equals(get0,"listCourse")){
                listCourse.run(command,arr_courses,arr_courseIds,arr_User,arr_UsersId);
            }
            else if(Objects.equals(get0,"selectCourse")){
                selectCourse.run(command,arr_courses,arr_courseIds,arr_User,arr_UsersId);
            }
            else if(Objects.equals(get0,"addAdmin")){
                addAdmin.run(command,arr_courses,arr_courseIds,arr_User,arr_UsersId);
            }
            else if(Objects.equals(get0,"removeAdmin")){
                removeAdmin.run(command,arr_courses,arr_courseIds,arr_User,arr_UsersId);
            }
            else if(Objects.equals(get0,"listAdmin")){
                listAdmin.run(command,arr_courses,arr_courseIds,arr_User,arr_UsersId);
            }
            else if(Objects.equals(get0,"changeRole")){
                changeRole.run(command,arr_courses,arr_courseIds,arr_User,arr_UsersId);
            }
            else if(Objects.equals(get0,"addWare")){
                addWare.run(command,arr_courses,arr_courseIds,arr_User,arr_UsersId);
            }
            else if(Objects.equals(get0,"removeWare")){
                removeWare.run(command,arr_courses,arr_courseIds,arr_User,arr_UsersId);
            }
            else if(Objects.equals(get0,"listWare")){
                listWare.run(command,arr_courses,arr_courseIds,arr_User,arr_UsersId);
            }
            else if(Objects.equals(get0,"addTask")){
                addTask.run(command,arr_courses,arr_courseIds,arr_User,arr_UsersId);
            }
            else if(Objects.equals(get0,"removeTask")){
                removeTask.run(command,arr_courses,arr_courseIds,arr_User,arr_UsersId);
            }
            else if(Objects.equals(get0,"listTask")){
                listTask.run(command,arr_courses,arr_courseIds,arr_User,arr_UsersId);
            }
            else if(Objects.equals(get0,"addStudent")){
                addStudent.run(command,arr_courses,arr_courseIds,arr_User,arr_UsersId);
            }
            else if(Objects.equals(get0,"removeStudent")){
                removeStudent.run(command,arr_courses,arr_courseIds,arr_User,arr_UsersId);
            }
            else if(Objects.equals(get0,"listStudent")){
                listStudent.run(command,arr_courses,arr_courseIds,arr_User,arr_UsersId);
            }
            else if(Objects.equals(get0,"downloadFile")){
                downloadFile.run(command,arr_courses,arr_courseIds,arr_User,arr_UsersId);
            }
            else if(Objects.equals(get0,"openFile")){
                openFile.run(command,arr_courses,arr_courseIds,arr_User,arr_UsersId);
            }
            else if(Objects.equals(get0,"submitTask")){
                submitTask.run(command,arr_courses,arr_courseIds,arr_User,arr_UsersId,sc);
            }
            else if(Objects.equals(get0,"addAnswer")){
                addAnswer.run(command,arr_courses,arr_courseIds,arr_User,arr_UsersId);
            }
            else if(Objects.equals(get0,"queryScore")){
                queryScore.run(command,arr_courses,arr_courseIds,arr_User,arr_UsersId);
            }
            else if(Objects.equals(get0,"requestVM")){
                requestVM.run(command,arr_courses,arr_courseIds,arr_User,arr_UsersId);
            }
            else if(Objects.equals(get0,"startVM")){
                startVM.run(command,arr_courses,arr_courseIds,arr_User,arr_UsersId,sc);
            }
            else if(Objects.equals(get0,"clearVM")){
                clearVM.run(command,arr_courses,arr_courseIds,arr_User,arr_UsersId,sc);
            }
            else if(Objects.equals(get0,"logVM")){
                logVM.run(command,arr_courses,arr_courseIds,arr_User,arr_UsersId,sc);
            }
            else if(Objects.equals(get0,"uploadVM")){
                uploadVM.run(command,arr_courses,arr_courseIds,arr_User,arr_UsersId,sc);
            }
            else if(Objects.equals(get0,"downloadVM")){
                downloadVM.run(command,arr_courses,arr_courseIds,arr_User,arr_UsersId,sc);
            }
            else{
                wrong_command.run(command);
            }
        }
    }
}