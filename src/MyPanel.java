import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * @author 刘文长
 * @version 1.0
 * 坦克大战的绘图区域
 */
//为了监听 键盘事件，实现KeyListener
//为了让Panel 不停的重绘子弹，需要将MyPanel 实现Runnable，作为一个线程
public class MyPanel extends JPanel implements KeyListener, Runnable {
    //定义我的坦克
    Hero hero = null;

    //定义地方坦克
    Vector<EnemyTank> enemyTanks = new Vector<>();
    //定义一个Vector用于存放炸弹
    //当子弹击中坦克时，加入一个Bomb对象到bombs
    Vector<Bomb> bombs = new Vector<>();


    //敌坦克数量
    int enemyTankSize = 5;

    //定义三张炸弹图片，用于显示爆炸效果
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;


    public MyPanel() {
        hero = new Hero(800, 600);
        //初始化一个坦克

        //hero.setSpeed(10);


        //初始化敌人坦克
        for (int i = 0; i < enemyTankSize; i++) {

            EnemyTank enemyTank = new EnemyTank((100 * (i + 1)), 0);
            enemyTank.setHero(hero);
            //将enemyTanks 赋给每个enemyTank
            enemyTank.setEnemyTanks(enemyTanks);
            //设置方向
            enemyTank.setDirect(2);
            //启动敌人坦克引擎，动起来
            new Thread(enemyTank).start();
            //给该enemyTank 加入一个子弹
            //Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
            //enemyTank.getShots().add(shot);
            //启动shot 对象
            //new Thread(shot).start();

            enemyTanks.add(enemyTank);


        }
        //存盘
        Recorder.setEnemyTanks(enemyTanks);

        //初始化图片对象
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
    }


    //编写方法，显示我方击毁地方坦克的信息
    public void showInfo(Graphics g) {
        //画出玩家的总成绩
        g.setColor(Color.BLACK);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);
        g.drawString("您累计击毁敌方坦克", 1020, 30);
        //画出一个地方坦克
        drawTank(1020, 60, g, 0, 0);
        g.setColor(Color.BLACK);
        g.drawString(Recorder.getAllEnemyTankNum() + "", 1080, 100);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //填充矩形默认黑色
        g.fillRect(0, 0, 1000, 750);


        showInfo(g);


        if (hero != null && hero.isLive()) {
            //画出己方坦克-封装方法
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 0);
        }

        //画出hero子弹
        for (int i = 0; i < hero.getShots().size(); i++) {
            Shot shot = hero.getShots().get(i);
            if (shot != null && shot.isLive()) {
                //g.fill3DRect(hero.shot.getX(), hero.shot.getY(), 1, 1, false);
                g.draw3DRect(shot.getX(), shot.getY(), 2, 2, false);
            } else {
                hero.getShots().remove(shot);
            }
        }


        //如果Bombs集合中有对象,就画出
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            //根据当前对象的life值去画出对应图片
            if (bomb.getLife() > 10) {
                g.drawImage(image1, bomb.getX(), bomb.getY(), 60, 60, this);
            } else if (bomb.getLife() > 5) {
                g.drawImage(image2, bomb.getX(), bomb.getY(), 60, 60, this);
            } else {
                g.drawImage(image3, bomb.getX(), bomb.getY(), 60, 60, this);
            }
            bomb.lifeDown();
            //如果lifo 为0,就从bombs中删除
            if (bomb.getLife() == 0) {
                bombs.remove(bomb);
            }
        }


        //画出敌人坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            //当敌人坦克是存活的才画
            if (enemyTank.isLive()) {
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);
                //画出所有子弹
                for (int j = 0; j < enemyTank.getShots().size(); j++) {
                    //取出子弹
                    Shot shot = enemyTank.getShots().get(j);
                    if (shot.isLive()) {
                        g.draw3DRect(shot.getX(), shot.getY(), 2, 2, false);
                    } else {
                        enemyTank.getShots().remove(shot);
                    }
                }
            }
        }
    }

    /**
     * @param x      坦克左上方x坐标
     * @param y      坦克左上方y坐标
     * @param g      画笔
     * @param direct 坦克方向（上下左右）
     * @param type   坦克类型
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        switch (type) {
            //我们的坦克
            case 0:
                g.setColor(Color.cyan);
                break;
            //敌人的坦克
            case 1:
                g.setColor(Color.yellow);
                break;
            default:
        }

        //根据坦克方向，来绘制坦克
        switch (direct) {
            case 0:
                //画出坦克左边履带
                g.fill3DRect(x, y, 10, 60, false);
                //画出坦克右边履带
                g.fill3DRect(x + 30, y, 10, 60, false);
                //画出坦克盖子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                //画出圆盖
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y);
                break;
            case 1:
                //画出坦克左边履带
                g.fill3DRect(x - 10, y + 10, 60, 10, false);
                //画出坦克右边履带
                g.fill3DRect(x - 10, y + 40, 60, 10, false);
                //画出坦克盖子
                g.fill3DRect(x, y + 20, 40, 20, false);
                //画出圆盖
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 50, y + 30);
                break;
            case 2:
                //画出坦克左边履带
                g.fill3DRect(x, y, 10, 60, false);
                //画出坦克右边履带
                g.fill3DRect(x + 30, y, 10, 60, false);
                //画出坦克盖子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                //画出圆盖
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y + 60);
                break;
            case 3:
                //画出坦克左边履带
                g.fill3DRect(x - 10, y + 10, 60, 10, false);
                //画出坦克右边履带
                g.fill3DRect(x - 10, y + 40, 60, 10, false);
                //画出坦克盖子
                g.fill3DRect(x, y + 20, 40, 20, false);
                //画出圆盖
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x - 10, y + 30);
                break;
            default:
                System.out.println("坦克方向错误");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    public boolean isTouchEnemyTank() {

        //判断当前敌人坦克的方向
        switch (hero.getDirect()) {
            case 0:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                        if (hero.getX() >= enemyTank.getX()
                                && hero.getX() <= enemyTank.getX() + 40
                                && hero.getY() >= enemyTank.getY()
                                && hero.getY() <= enemyTank.getY() + 60) {
                            return true;
                        }
                        if (hero.getX() + 40 >= enemyTank.getX()
                                && hero.getX() + 40 <= enemyTank.getX() + 40
                                && hero.getY() >= enemyTank.getY()
                                && hero.getY() <= enemyTank.getY() + 60) {
                            return true;
                        }

                    } else if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                        if (hero.getX() >= enemyTank.getX() - 10
                                && hero.getX() <= enemyTank.getX() + 50
                                && hero.getY() >= enemyTank.getY() + 10
                                && hero.getY() <= enemyTank.getY() + 50) {
                            return true;
                        }
                        if (hero.getX() + 40 >= enemyTank.getX() - 10
                                && hero.getX() + 40 <= enemyTank.getX() + 50
                                && hero.getY() >= enemyTank.getY() + 10
                                && hero.getY() <= enemyTank.getY() + 50) {
                            return true;
                        }
                    }
                }
                break;
            case 1:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);

                    if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                        if (hero.getX() + 50 >= enemyTank.getX()
                                && hero.getX() + 50 <= enemyTank.getX() + 40
                                && hero.getY() + 10 >= enemyTank.getY()
                                && hero.getY() + 10 <= enemyTank.getY() + 60) {
                            return true;
                        }
                        if (hero.getX() + 50 >= enemyTank.getX()
                                && hero.getX() + 50 <= enemyTank.getX() + 40
                                && hero.getY() + 50 >= enemyTank.getY()
                                && hero.getY() + 50 <= enemyTank.getY() + 60) {
                            return true;
                        }

                    } else if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                        if (hero.getX() + 50 >= enemyTank.getX() - 10
                                && hero.getX() + 50 <= enemyTank.getX() + 50
                                && hero.getY() + 10 >= enemyTank.getY() + 10
                                && hero.getY() + 10 <= enemyTank.getY() + 50) {
                            return true;
                        }
                        if (hero.getX() + 50 >= enemyTank.getX() - 10
                                && hero.getX() + 50 <= enemyTank.getX() + 50
                                && hero.getY() + 50 >= enemyTank.getY() + 10
                                && hero.getY() + 50 <= enemyTank.getY() + 50) {
                            return true;
                        }

                    }
                }
                break;
            case 2:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                        if (hero.getX() >= enemyTank.getX()
                                && hero.getX() <= enemyTank.getX() + 40
                                && hero.getY() + 60 >= enemyTank.getY()
                                && hero.getY() + 60 <= enemyTank.getY() + 60) {
                            return true;
                        }
                        if (hero.getX() + 40 >= enemyTank.getX()
                                && hero.getX() + 40 <= enemyTank.getX() + 40
                                && hero.getY() + 60 >= enemyTank.getY()
                                && hero.getY() + 60 <= enemyTank.getY() + 60) {
                            return true;
                        }

                    } else if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                        if (hero.getX() >= enemyTank.getX() - 10
                                && hero.getX() <= enemyTank.getX() + 50
                                && hero.getY() + 60 >= enemyTank.getY() + 10
                                && hero.getY() + 60 <= enemyTank.getY() + 50) {
                            return true;
                        }
                        if (hero.getX() + 40 >= enemyTank.getX() - 10
                                && hero.getX() + 40 <= enemyTank.getX() + 50
                                && hero.getY() + 60 >= enemyTank.getY() + 10
                                && hero.getY() + 60 <= enemyTank.getY() + 50) {
                            return true;
                        }

                    }
                }
                break;
            case 3:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                        if (hero.getX() - 10 >= enemyTank.getX()
                                && hero.getX() - 10 <= enemyTank.getX() + 40
                                && hero.getY() + 10 >= enemyTank.getY()
                                && hero.getY() + 10 <= enemyTank.getY() + 60) {
                            return true;
                        }
                        if (hero.getX() - 10 >= enemyTank.getX()
                                && hero.getX() - 10 <= enemyTank.getX() + 40
                                && hero.getY() + 50 >= enemyTank.getY()
                                && hero.getY() + 50 <= enemyTank.getY() + 60) {
                            return true;
                        }

                    } else if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                        if (hero.getX() - 10 >= enemyTank.getX() - 10
                                && hero.getX() - 10 <= enemyTank.getX() + 50
                                && hero.getY() + 10 >= enemyTank.getY() + 10
                                && hero.getY() + 10 <= enemyTank.getY() + 50) {
                            return true;
                        }
                        if (hero.getX() - 10 >= enemyTank.getX() - 10
                                && hero.getX() - 10 <= enemyTank.getX() + 50
                                && hero.getY() + 50 >= enemyTank.getY() + 10
                                && hero.getY() + 50 <= enemyTank.getY() + 50) {
                            return true;
                        }

                    }
                }
                break;
            default:
        }
        return false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            hero.setDirect(0);
            if (hero.getY() > 0 && !isTouchEnemyTank()) {
                hero.moveUp();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirect(1);
            if (hero.getX() - 10 + 60 < 1000 && !isTouchEnemyTank()) {
                hero.moveRight();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirect(2);
            if (hero.getY() + 60 < 750 && !isTouchEnemyTank()) {
                hero.moveDown();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirect(3);
            if (hero.getX() - 10 > 0 && !isTouchEnemyTank()) {
                hero.moveLeft();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {

            hero.shotEnemyTank();
        }
        this.repaint();

    }


    //如果我们坦克可以发射多个子弹
    //在预判我方子弹是否击中敌人坦克时，就需要把我们的子弹集合中所有的子弹
    //都取出和敌人的所有坦克进行判断

    public void hitEnemyTank() {
        for (int j = 0; j < hero.getShots().size(); j++) {
            Shot shot = hero.getShots().get(j);
            //判断是否击中了敌人坦克
            if (shot != null && shot.isLive()) {
                //遍历敌人所有的坦克
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    hitTank(shot, enemyTank);
                }
            }

        }
    }

    //判断敌人是否击中我方坦克
    public void hitHero() {
        //遍历所有敌人的坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            //遍历坦克子弹
            for (int j = 0; j < enemyTank.getShots().size(); j++) {
                Shot shot = enemyTank.getShots().get(j);
                //判断shot是否击中我的坦克
                if (hero.isLive() && shot.isLive()) {
                    hitTank(shot, hero);
                }

            }
        }
    }


    //判断我方的子弹是否击中敌人坦克
    public void hitTank(Shot s, Tank enemyTank) {
        switch (enemyTank.getDirect()) {
            case 0:
            case 2:
                if (s.getX() > enemyTank.getX() && s.getX() < enemyTank.getX() + 40
                        && s.getY() > enemyTank.getY() && s.getY() < enemyTank.getY() + 60) {
                    s.setLive(false);
                    enemyTank.setLive(false);
                    enemyTanks.remove(enemyTank);
                    if (enemyTank instanceof EnemyTank) {
                        Recorder.addAllEnemyTankNum();
                    }

                    //创建Bomb对象，加入到bombs集合
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                }
                break;
            case 1:
            case 3:
                if (s.getX() > (enemyTank.getX() - 10) && s.getX() < (enemyTank.getX() + 50)
                        && s.getY() > (enemyTank.getY() + 10) && s.getY() < (enemyTank.getY() + 50)) {
                    s.setLive(false);
                    enemyTank.setLive(false);
                    enemyTanks.remove(enemyTank);

                    if (enemyTank instanceof EnemyTank) {
                        Recorder.addAllEnemyTankNum();
                    }

                    Bomb bomb = new Bomb(enemyTank.getX() - 10, enemyTank.getY() + 10);
                    bombs.add(bomb);
                }
                break;
            default:
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {//每隔10ms 刷新绘图区域
        while (true) {

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //判断是否击中敌人坦克
            hitEnemyTank();
            //判断是否击中己方坦克
            hitHero();
            this.repaint();

        }
    }
}
