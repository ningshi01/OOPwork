package Game;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TheFlame tf=new TheFlame();
//        tf.getGraphics().drawImage(ForImage.background,0,0,null);
        while(true){
            Thread.sleep(20);
            tf.repaint();
        }
    }
}
