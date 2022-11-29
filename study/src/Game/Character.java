package Game;

import java.awt.*;

abstract public class Character {
    public void paint(Graphics g){}

    /***用以发射弹幕
     * 弹幕类型
     * @param type
     * 注意：
     * 弹幕类型可加为：
     * Z弹幕  辅助瞄准状态下Z弹幕
     * X弹幕  辅助瞄准状态下X弹幕
     * C弹幕  辅助瞄准状态下C弹幕
     */
    public void fire(int type){}

    /*
    * 下列方法只是表明机体类里需要重写，实际无法从抽象类继承重写
    * 实际上可直接复制啦，但是改成普通父类继承后有bug呜哇晰不会改
    * 所以写自己的机体时不如直接从TheMachine类复制过去
    */
    private void move(){}
    private void revish(){}
    private void spin(){}
}
