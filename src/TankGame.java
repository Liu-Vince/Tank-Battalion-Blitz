import javax.swing.*;

/**
 * @author 刘文长
 * @version 1.0
 */
public class TankGame extends JFrame {
    MyPanel mp= null;
    public static void main(String[] args) {
        TankGame hspTankGame01 = new TankGame();
    }
    public TankGame(){
        mp = new MyPanel();
        //讲mp放入到Thread并启动
        new Thread(mp).start();
        this.add(mp);
        //游戏的绘图区域
        this.setSize(1015,795);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
}