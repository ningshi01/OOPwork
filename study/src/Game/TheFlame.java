package Game;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class TheFlame extends Frame /*窗口类*/{

    int time=0;
    int degree=0;//旋转角度（0——360）

    static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();// 主窗口设定
    List<Barrage> barrages=new ArrayList<>();//存储弹幕的表
    TheMachine myMac = new TheMachine(dim.width/2, dim.height/3*2, this);//我的机体的一个实例
    Barrage B0=new Barrage(myMac.getX()+20, myMac.getY()+20,this,0);//我的弹幕的一个实例
    TheMachine enemy=new TheMachine(dim.width/2, dim.height/3, this);
    public TheFlame() {
        setResizable(false);
        setTitle("a small normal game");
        //实现全屏窗口（有标题栏）
        setSize(dim);
//        //实现全屏窗口（无标题栏）
//        getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
        this.setVisible(true);

        //为退出按钮赋退出值
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new MyKeyListener());
    }
    Image offScreenImage=null;

    /***
     * 窗口更新方法的重写
     * @param g the specified Graphics window
     * 传入画笔（其实不用管）
     */
    @Override
    public void update(Graphics g){
        if(offScreenImage==null){
            offScreenImage=this.createImage(dim.width, dim.height);
        }
        Graphics gOffScreen=offScreenImage.getGraphics();

        /*在以下区域set background*/
//这就是一个置黑屏背景的举例
//        Color c=gOffScreen.getColor();
//        gOffScreen.setColor(Color.black);
//        gOffScreen.fillRect(0,0,dim.width,dim.height);
//        gOffScreen.setColor(c);
        gOffScreen.drawImage(ForImage.Background1,0,0,dim.width,dim.height,null);
        Color c=gOffScreen.getColor();
        gOffScreen.setColor(Color.black);
        gOffScreen.fillRect(dim.width/5,0,3*dim.width/5,dim.height);
        gOffScreen.setColor(c);
        /*end_setbackground*/

        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
    public void paint(Graphics g) {

        /*写入字符串*/
        Color c=g.getColor();
        Font f=g.getFont();
        g.setFont(new Font("我的字体",9,25));
        g.setColor(Color.WHITE);
        g.drawString("玩家1",1/10* dim.width+25,100);
        g.drawString("HP",30,200);
        g.draw3DRect(100,170,200,50,true);
        g.drawString("MP",30,300);
        g.draw3DRect(100,270,200,50,true);
        g.drawString("玩家2",2/5* dim.width+1255,100);
        g.drawString("HP",1250,200);
        g.draw3DRect(1320,170,200,50,true);
        g.drawString("MP",1250,300);
        g.draw3DRect(1320,270,200,50,true);
        g.setColor(c);
        g.setFont(f);
        /*end_setString*/

        //画出我的机体
        myMac.paint(g);

        //画出我的弹幕
        for(int i=0;i<barrages.size();i++){
            barrages.get(i).paint(g);
        }
        /***
         * 这边不联机得写AI了有点致命
         */
        //画出敌方机体
        enemy.for_player=2;
        enemy.paint(g);
        //统计无敌时间（1s：20ms*50times）
        if(enemy.Flag_selectable==false && time<=50) time++;
        else{
            time=0;
            enemy.Flag_selectable=true;
        }
        //加上撞击判断
        if(enemy.Flag_selectable){
            for (int i = 0; i < barrages.size(); i++) {
                barrages.get(i).lengthwith(enemy);
            }
        }
    }

    class MyKeyListener extends KeyAdapter {
        boolean K_up = false;
        boolean K_down = false;
        boolean K_left = false;
        boolean K_right = false;
        boolean K_fire=false;
        int type;//弹幕类型设置
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_UP:
                    K_up = true;
                    break;
                case KeyEvent.VK_DOWN:
                    K_down = true;
                    break;
                case KeyEvent.VK_LEFT:
                    K_left = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    K_right = true;
                    break;
                case KeyEvent.VK_Z/*Z__1类弹幕*/:
                    K_fire = true;
                    type=1;
                    break;
                case KeyEvent.VK_X/*X__2类弹幕*/:
                    K_fire = true;
                    type=2;
                    break;
                case KeyEvent.VK_C/*C__3类弹幕*/:
                    K_fire = true;
                    type=3;
                    break;
                case KeyEvent.VK_SHIFT/*辅助瞄准*/:
                    myMac.K_aim=true;
                    break;
                case KeyEvent.VK_ADD://speed++
                    myMac.speed+=5;
                    break;
                case KeyEvent.VK_SUBTRACT://speed--
                    myMac.speed-=5;
                    break;
                case KeyEvent.VK_ESCAPE:
                    System.exit(0);
                    break;
                default:
                    break;
            }
            setTankDirection();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_UP:
                    K_up = false;
                    break;
                case KeyEvent.VK_DOWN:
                    K_down = false;
                    break;
                case KeyEvent.VK_LEFT:
                    K_left = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    K_right = false;
                    break;
                case KeyEvent.VK_Z:
                    K_fire = false;
                    break;
                case KeyEvent.VK_X:
                    K_fire = false;
                    break;
                case KeyEvent.VK_C:
                    K_fire = false;
                    break;
                case KeyEvent.VK_SHIFT:
                    myMac.K_aim=false;
                    break;
                default:
                    break;
            }
            setTankDirection();
        }

        private void setTankDirection() {
            if(K_fire)
                myMac.fire(type);
            if(!K_up && !K_down && !K_left && !K_right) {
                myMac.setMoving(false);
            }
            else{
                myMac.setMoving(true);
                myMac.setAllFlag(K_up,K_down,K_left,K_right);
            }
        }

    }
}