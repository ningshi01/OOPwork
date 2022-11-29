package Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ForImage {
    //存储图片的类
    public static BufferedImage background, image2, bullet,Background1;//新图直接加在这里
    static {
        try {
            //图片移入项目的Images文件夹再如下路径加入即可
            //注意一定要是原.jpeg文件否则绘图时加载崩掉（图片转jpeg格式网址：https://www.gaitubao.com/jpg-gif-png）
            //自机的角色贴图如果有底色会不能适配旋转方法（此处找到贴图需要去底色的话参考网址：https://tools.kalvinbg.cn/image/bgRemover）
            background = ImageIO.read(ForImage.class.getClassLoader().getResourceAsStream("images/1.jpeg"));
            image2     = ImageIO.read(ForImage.class.getClassLoader().getResourceAsStream("images/2（0）.jpeg"));
            bullet     = ImageIO.read(ForImage.class.getClassLoader().getResourceAsStream("images/3.gif"));
            Background1= ImageIO.read(ForImage.class.getClassLoader().getResourceAsStream("images/1.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 旋转图片为指定角度
     *
     * @param bufferedimage 目标图像
     * @param degree        旋转角度
     * @return buffered image
     */
public static BufferedImage rotateImage(BufferedImage bufferedimage, int degree){
        int w= bufferedimage.getWidth();// 得到图片宽度。
        int h= bufferedimage.getHeight();// 得到图片高度。
        int type= bufferedimage.getColorModel().getTransparency();// 得到图片透明度。
        BufferedImage img;// 空的图片。
        Graphics2D graphics2d;// 空的画笔。
        (graphics2d= (img= new BufferedImage(w, h, type)).createGraphics()).setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);// 旋转，degree是整型，度数，比如垂直90度。
        graphics2d.drawImage(bufferedimage, 0, 0, null);// 从bufferedimagecopy图片至img，0,0是img的坐标。
        graphics2d.dispose();
        return img;// 返回复制好的图片，原图片依然没有变，没有旋转，下次还可以使用。
    }
}
