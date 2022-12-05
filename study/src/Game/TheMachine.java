package Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;

public class TheMachine extends Character/*机体类*/{
    public int for_player=1;
    private int x, y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    private int Width=50,Height=50;

    public int getWidth() {
        return Width;
    }

    public int getHeight() {
        return Height;
    }
    private int HP=100,MP=100;

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getMP() {
        return MP;
    }

    public void setMP(int MP) {
        this.MP = MP;
    }

    public int speed = 3;//机体移动速度
    public int spin_speed=10;//机体旋转速度(一定要是360的因数)
    private TheFlame tf=null;
    /*移动判断*/
    private boolean moving=false;
    public boolean flag_up=false;
    public boolean flag_down=false;
    public boolean flag_left=false;
    public boolean flag_right=false;
    public boolean K_aim=false;
    /*end_移动判断*/
    public void setAllFlag(boolean f1,boolean f2,boolean f3,boolean f4) {
        this.flag_up = f1;
        this.flag_down = f2;
        this.flag_left = f3;
        this.flag_right = f4;
        if (flag_up && flag_down) flag_up = flag_down = false;
        if (flag_left && flag_right) flag_left = flag_right = false;
    }
    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }


    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public boolean Flag_selectable=true;

    /***
     *
     * @param x
     * @param y
     * @param tf
     * @param for_player
     * 此处传入for_player值是为了分别主副机体
     */
    public TheMachine(int x, int y, TheFlame tf,int for_player) {
        this.x = x;
        this.y = y;
        this.tf=tf;
        this.for_player=for_player;
        if (for_player==1) degree=0;
        else degree=180;
    }
    int degree;
    public void paint(Graphics g) {
        //Color c0=g.getColor();
        //g.setColor(Color.lightGray);
        //g.fillRect(x, y, 50, 50);
        //g.setColor(c0);
        BufferedImage image2 = ForImage.rotateImage(ForImage.Mac1,degree);
        g.drawImage(image2,x,y,Width,Height,null);
        Color c=g.getColor();
        g.setColor(Color.cyan);
        g.fillOval(x+20,y+20,10,10);
        if(for_player==1){
            g.setColor(Color.red);
            g.fillRect(100, 170, 2 * HP, 50);
            g.setColor(Color.CYAN);
            g.fillRect(100, 270, 2 * MP, 50);
        }else {
            g.setColor(Color.red);
            g.fillRect(1320, 170, 2 * HP, 50);
            g.setColor(Color.CYAN);
            g.fillRect(1320, 270, 2 * MP, 50);
        }
        g.setColor(c);
        move();
    }

    private void move() {
        if(!moving) return;
        if (!K_aim)
            spin();
        if(K_aim) {
            spin();
            if((y - tf.enemy.getY())!=0){
                double deta_x = (double) (tf.enemy.getX() - x);
                double deta_y = (double) (y - tf.enemy.getY());
                double re = div(deta_x, deta_y, 6);
                degree = (int) Math.toDegrees(Math.atan(re));
                if (deta_y < 0) {
                    degree -= 180;
                    revise();
                }
            }else{
                if (tf.enemy.getX() > x) degree=90;
                else degree=270;
            }
        }
        if(flag_up)    y -= speed;
        if(flag_down)  y += speed;
        if(flag_left)  x -= speed;
        if(flag_right) x += speed;
        if(x<=10+TheFlame.dim.width/5) x=TheFlame.dim.width/5+10;
        if(x>=TheFlame.dim.width/5*4-50) x=TheFlame.dim.width/5*4-50;
        if(y<=0) y=0;
        if(y>=TheFlame.dim.height-50) y=TheFlame.dim.height-50;
    }

    /***
     *
     * @param type
     * 注意：
     * 弹幕类型可加为：
     * Z弹幕  辅助瞄准状态下Z弹幕
     * X弹幕  辅助瞄准状态下X弹幕
     * C弹幕  辅助瞄准状态下C弹幕
     */
    public void fire(int type) {
        if(for_player==1){
            if (type == 1) {
                tf.B1 = new Barrage(this.x + 20, this.y + 20, tf, this, type);
                judgeDir(tf.B1);
                tf.barrages1.add(tf.B1);
            } else if (type == 2) {
                tf.B1 = new Barrage(this.x + 20, this.y + 20, tf, this, type);
                judgeDir(tf.B1);
                tf.barrages1.add(tf.B1);
            } else if (type == 3) {
                tf.B1 = new Barrage(this.x + 20, this.y + 20, tf, this, type);
                judgeDir(tf.B1);
                tf.barrages1.add(tf.B1);
            }
        }else{
            if (type == 1) {
                tf.B2 = new Barrage(this.x + 20, this.y + 20, tf, this, type);
                judgeDir(tf.B2);
                tf.barrages2.add(tf.B2);
            } else if (type == 2) {
                tf.B2 = new Barrage(this.x + 20, this.y + 20, tf, this, type);
                judgeDir(tf.B2);
                tf.barrages2.add(tf.B2);
            } else if (type == 3) {
                tf.B2 = new Barrage(this.x + 20, this.y + 20, tf, this, type);
                judgeDir(tf.B2);
                tf.barrages2.add(tf.B2);
            }
        }
    }

    /***
     * 判定弹幕方向与机体目前方向一致
     * @param B
     * 传入的弹幕
     */
    public void judgeDir(Barrage B){
        B.degree=degree;
        B.setAllFlag(flag_up,flag_down,flag_left,flag_right);
    }
    private void revise(){
        while(degree<0){
            degree+=360;
        }
        while(degree>=360){
            degree-=360;
        }
    }
    private void spin(){
        //up
        if(flag_up&&!flag_down&&!flag_left&&!flag_right){
            revise();
            if((degree<=180 && degree>0)&& degree!=0) {
                degree -= spin_speed;
                revise();
            }
            if(degree>180 && degree!=360 && degree!=0) {
                degree += spin_speed;
                revise();
            }
        }
        //down
        if(!flag_up&&flag_down&&!flag_left&&!flag_right){
            revise();
            if((degree<=180 && degree>=0)&& degree!=180) {
                degree += spin_speed;
                revise();
            }
            if(degree>180 && degree!=180) {
                degree -= spin_speed;
                revise();
            }
        }
        //left
        if(!flag_up&&!flag_down&&flag_left&&!flag_right){
            revise();
            if(((degree<=90 && degree>=0) || degree>270)&& degree!=270) {
                degree -= spin_speed;
                revise();
            }
            if((degree>90 && degree<270)&& degree!=270) {
                degree += spin_speed;
                revise();
            }
        }
        //right
        if(!flag_up&&!flag_down&&!flag_left&&flag_right){
            revise();
            if(((degree<=90 && degree>=0) || degree>270)&& degree!=90) {
                degree += spin_speed;
                revise();
            }
            if((degree>90 && degree<=270)&& degree!=90) {
                degree -= spin_speed;
                revise();
            }
        }
        //up+right
        if(flag_up&&!flag_down&&!flag_left&&flag_right){
            revise();
            if(((degree<=45 && degree>=0) || degree>225)&& degree!=45) {
                degree += spin_speed;
                revise();
            }
            if((degree>45 && degree<=225)&& degree!=45) {
                degree -= spin_speed;
                revise();
            }
        }
        //up+left
        if(flag_up&&!flag_down&&flag_left&&!flag_right){
            revise();
            if(((degree<=135 && degree>=0) || degree>315)&& degree!=315) {
                degree -= spin_speed;
                revise();
            }
            if((degree>135 && degree<315)&& degree!=315) {
                degree += spin_speed;
                revise();
            }
        }
        //down+right
        if(!flag_up&&flag_down&&!flag_left&&flag_right){
            revise();
            if(((degree<135 && degree>=0) || degree>315)&& degree!=135) {
                degree += spin_speed;
                revise();
            }
            if((degree>135 && degree<=315)&& degree!=135) {
                degree -= spin_speed;
                revise();
            }
        }
        //down+left
        if(!flag_up&&flag_down&&flag_left&&!flag_right){
            revise();
            if(((degree<=45 && degree>=0) || degree>225)&& degree!=225) {
                degree -= spin_speed;
                revise();
            }
            if((degree>45 && degree<225)&& degree!=225) {
                degree += spin_speed;
                revise();
            }
        }
    }
    public static double div(double v1, double v2, int scale)  {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, 4).doubleValue(); //ROUND_HALF_UP:四舍五入
    }
}
