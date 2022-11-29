package Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Barrage /*弹幕类*/{
    private int type=0;
    public int speed = 5;
    public int degree=0;
    private int x,y;
    private TheFlame tf=null;
    private static int Width1=10,High1=10;
    private static int Width2=20,High2=20;
    private static int Width3=25,High3=25;
    public boolean flag_up=true;
    public boolean flag_down=false;
    public boolean flag_left=false;
    public boolean flag_right=false;
    private boolean live =true;
    public void setAllFlag(boolean f1,boolean f2,boolean f3,boolean f4){
        this.flag_up=f1;
        this.flag_down=f2;
        this.flag_left=f3;
        this.flag_right=f4;
    }
    public Barrage(){}
    public Barrage(int x, int y, TheFlame tf, int type) {
        this.x = x;
        this.y = y;
        this.tf=tf;
        this.degree=tf.degree;
        this.type=type;
    }
    public void paint(Graphics g){
        if(!live){
            tf.barrages.remove(this);
        }
        Color c=g.getColor();
        g.setColor(Color.red);
        g.setColor(c);
        if(type==1) {
//            g.fillOval(x,y,Width1,High1);
            BufferedImage bullet = ForImage.rotateImage(ForImage.bullet, degree);
            g.drawImage(bullet, x, y, Width1, High1, null);
        }
        else if(type==2) {
//            g.fillRect(x,y,Width2, High2);
            BufferedImage bullet = ForImage.rotateImage(ForImage.bullet, degree);
            g.drawImage(bullet, x, y, Width2, High2, null);
        }
        else if(type==3){
//            g.fillRoundRect(x,y,Width3,High3,60,60);
            BufferedImage bullet = ForImage.rotateImage(ForImage.bullet, degree);
            g.drawImage(bullet, x, y, Width3, High3, null);
        }
        move();
    }
    private void move() {
        if((flag_up||flag_right)&&!flag_down&&!flag_left)/*第一象限*/{
            y-=speed*2*Math.cos(Math.toRadians(degree));
            x+=speed*2*Math.sin(Math.toRadians(degree));
        }
        if((flag_right||flag_down)&&!flag_up&&!flag_left)/*第四象限*/{
            y-=speed*2*Math.cos(Math.toRadians(degree));
            x+=speed*2*Math.sin(Math.toRadians(degree));
        }
        if((flag_down||flag_left)&&!flag_up&&!flag_right/*第三象限*/){
            y-=speed*2*Math.cos(Math.toRadians(degree));
            x+=speed*2*Math.sin(Math.toRadians(degree));
        }
        if((flag_left||flag_up)&&!flag_down&&!flag_right)/*第四象限*/{
            y-=speed*2*Math.cos(Math.toRadians(degree));
            x+=speed*2*Math.sin(Math.toRadians(degree));
        }
        else {
            y-=speed*2*Math.cos(Math.toRadians(degree));
            x+=speed*2*Math.sin(Math.toRadians(degree));
        }
        if(x<=TheFlame.dim.width/5+5 || y<0 || x>= TheFlame.dim.width/5*4-30 || y> TheFlame.dim.height-50)
            live=false;
    }

    public void lengthwith(TheMachine enemy) {
        Rectangle r0=new Rectangle();
        switch (type) {
            case 1:
                r0 = new Rectangle(x, y, Width1,High1);
                break;
            case 2:
                r0 = new Rectangle(x, y, Width2,High2);
                break;
            case 3:
                r0 = new Rectangle(x, y, Width3,High3);
                break;
        }
        Rectangle r_enemy=new Rectangle(enemy.getX(),enemy.getY(),enemy.getWidth(),enemy.getHeight());
        if(r0.intersects(r_enemy)){
            enemy.setHP(enemy.getHP()-5);
            tf.barrages.remove(this);
            enemy.Flag_selectable=false;
        }
    }
}
