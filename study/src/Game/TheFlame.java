package Game;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class TheFlame extends Frame /*窗口类*/{

    int time1 =0;//enemy无敌时间
    int time2 =0;//myMAC无敌时间
    int degree=0;//旋转角度（0——360）

    static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();// 主窗口设定
    List<Barrage> barrages1=new ArrayList<>();//存储弹幕的表
    List<Barrage> barrages2=new ArrayList<>();//存储弹幕的表
    TheMachine myMac = new TheMachine(dim.width/2, dim.height/3*2, this,1);//主机体的一个实例
    TheMachine enemy=new TheMachine(dim.width/2, dim.height/3, this,2);//副机体的一个实例
    Barrage B1 =new Barrage(myMac.getX()+20, myMac.getY()+20,this,myMac,0);//myMac弹幕
    Barrage B2 =new Barrage(enemy.getX()+20, enemy.getY()+20,this,enemy,0);//enemy弹幕
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
        gOffScreen.drawImage(ForImage.Background,0,0,dim.width,dim.height,null);
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
        g.drawString((String) For_Proper.get("name_p1"),1/10* dim.width+25,100);
        g.drawString("HP",30,200);
        g.draw3DRect(100,170,200,50,true);
        g.drawString("MP",30,300);
        g.draw3DRect(100,270,200,50,true);
        g.drawString((String) For_Proper.get("name_p2"),2/5* dim.width+1255,100);
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
        for(int i=0;i<barrages1.size();i++){
            barrages1.get(i).paint(g);
        }
        for(int i=0;i<barrages2.size();i++){
            barrages2.get(i).paint(g);
        }
        /***
         * 这边不联机得写AI了有点致命
         */
        //画出敌方机体
        enemy.paint(g);
        //统计无敌时间（1s：20ms*50times）
        if(enemy.Flag_selectable==false && time1 <=50) time1++;
        else{
            time1 =0;
            enemy.Flag_selectable=true;
        }
        if(myMac.Flag_selectable==false && time2 <=50) time2++;
        else{
            time2 =0;
            myMac.Flag_selectable=true;
        }
        //加上撞击判断
        if(enemy.Flag_selectable){
            for (int i = 0; i < barrages1.size(); i++) {
                barrages1.get(i).lengthwith(enemy);
            }
        }
        if(myMac.Flag_selectable){
            for (int i = 0; i < barrages2.size(); i++) {
                barrages2.get(i).lengthwith(myMac);
            }
        }
    }

    class MyKeyListener extends KeyAdapter {
        boolean K_up1 = false;
        boolean K_down1 = false;
        boolean K_left1 = false;
        boolean K_right1 = false;
        boolean K_fire1 =false;
        boolean K_aim1 =false;
        int type1;//myMac弹幕类型设置


        boolean K_up2 = false;
        boolean K_down2 = false;
        boolean K_left2 = false;
        boolean K_right2 = false;
        boolean K_fire2 =false;
        boolean K_aim2 =false;
        int type2;//enemy弹幕类型设置
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                /***
                 * myMac的操纵
                 */
                case KeyEvent.VK_W:
                    K_up1 = true;
                    break;
                case KeyEvent.VK_S:
                    K_down1 = true;
                    break;
                case KeyEvent.VK_A:
                    K_left1 = true;
                    break;
                case KeyEvent.VK_D:
                    K_right1 = true;
                    break;
                case KeyEvent.VK_J/*J__1类弹幕*/:
                    K_fire1 = true;
                    type1 =1;
                    break;
                case KeyEvent.VK_K/*K__2类弹幕*/:
                    K_fire1 = true;
                    type1 =2;
                    break;
                case KeyEvent.VK_L/*L__3类弹幕*/:
                    K_fire1 = true;
                    type1 =3;
                    break;
                case KeyEvent.VK_SHIFT/*辅助瞄准*/:
                    myMac.K_aim=true;
                    K_aim1 =false;
                    break;
                /***
                 * enemy的操纵
                  */
                case KeyEvent.VK_UP:
                    K_up2 = true;
                    break;
                case KeyEvent.VK_DOWN:
                    K_down2 = true;
                    break;
                case KeyEvent.VK_LEFT:
                    K_left2 = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    K_right2 = true;
                    break;
                case KeyEvent.VK_NUMPAD1:/*1__1类弹幕*/
                    K_fire2 = true;
                    type2 =1;
                    break;
                case KeyEvent.VK_NUMPAD2:/*2__2类弹幕*/
                    K_fire2 = true;
                    type2 =2;
                    break;
                case KeyEvent.VK_NUMPAD3:/*3__3类弹幕*/
                    K_fire2 = true;
                    type2 =3;
                    break;
                case KeyEvent.VK_ENTER/*辅助瞄准*/:
                    enemy.K_aim=true;
                    K_aim2 =false;
                    break;
                case KeyEvent.VK_ADD://speed++
                    myMac.speed+=5;
                    enemy.speed+=5;
                    break;
                case KeyEvent.VK_SUBTRACT://speed--
                    myMac.speed-=5;
                    enemy.speed-=5;
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
                /***
                 * myMac的操纵
                 */
                case KeyEvent.VK_W:
                    K_up1 = false;
                    break;
                case KeyEvent.VK_S:
                    K_down1 = false;
                    break;
                case KeyEvent.VK_A:
                    K_left1 = false;
                    break;
                case KeyEvent.VK_D:
                    K_right1 = false;
                    break;
                case KeyEvent.VK_J:
                    K_fire1 = false;
                    break;
                case KeyEvent.VK_K:
                    K_fire1 = false;
                    break;
                case KeyEvent.VK_L:
                    K_fire1 = false;
                    break;
                /***
                 * enemy的操纵
                  */
                case KeyEvent.VK_UP:
                    K_up2 = false;
                    break;
                case KeyEvent.VK_DOWN:
                    K_down2 = false;
                    break;
                case KeyEvent.VK_LEFT:
                    K_left2 = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    K_right2 = false;
                    break;
                case KeyEvent.VK_NUMPAD1:
                    K_fire2 = false;
                    break;
                case KeyEvent.VK_NUMPAD2:
                    K_fire2 = false;
                    break;
                case KeyEvent.VK_NUMPAD3:
                    K_fire2 = false;
                    break;
                case KeyEvent.VK_ENTER:
                    K_aim2 =false;
                    enemy.K_aim=false;
                    break;
                default:
                    break;
            }
            setTankDirection();
        }

        private void setTankDirection() {
            /***
             * myMac判定
             */
            if(K_fire1)
                myMac.fire(type1);
            if(!K_up1 && !K_down1 && !K_left1 && !K_right1 && !K_aim1) {
                myMac.setMoving(false);
            } else{
                myMac.setMoving(true);
                myMac.setAllFlag(K_up1, K_down1, K_left1, K_right1);
            }
            /***
             * enemy判定
             */
            if(K_fire2)
                enemy.fire(type2);
            if(!K_up2 && !K_down2 && !K_left2 && !K_right2 && !K_aim2) {
                enemy.setMoving(false);
            } else{
                enemy.setMoving(true);
                enemy.setAllFlag(K_up2, K_down2, K_left2, K_right2);
            }
        }

    }
}