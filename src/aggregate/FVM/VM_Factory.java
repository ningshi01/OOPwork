package aggregate.FVM;

import aggregate.FVM.IVM;

public class VM_Factory {
    //创建构造VM的方法
    public IVM createVM(String type) {
        IVM vm=null;
        if(type.equals("Windows"))
            return vm=new Windows();
        else if(type.equals("Linux"))
            return vm=new Linux();
        else if(type.equals("MacOS"))
            return vm=new MacOS();
        else
            System.out.println("VM类型错误！");
        return vm;
    }
}
/*
Windows,
 Linux,
 MacOS
*/