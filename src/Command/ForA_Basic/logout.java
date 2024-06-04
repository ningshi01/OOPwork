package Command.ForA_Basic;

import aggregate.*;

import static aggregate.Users.user0;

public class logout {
    public static void run(){
        if(Users.loginFlag) {
            Users.loginFlag=false;
            Users.selectFlag=false;
            Users.orFlag=false;
            System.out.println("Bye~");
        }
        else
            System.out.println("not logged in");
    }
}
