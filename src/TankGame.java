import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
        this.setSize(1300,795);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //增加响应监听关闭窗口
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                System.exit(0);
            }
        });
    }
}