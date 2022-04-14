import java.util.Vector;

/**
 * @author 刘文长
 * @version 1.0
 *
 * 解决子弹贴墙快速连发问题
 */
public class EnemyTankFire implements Runnable {
    private Shot shot = null;
    private Vector<Shot> shots = new Vector<>();

    private EnemyTank enemyTank;

    public EnemyTankFire(Vector<Shot> shots, EnemyTank enemyTank) {
        this.shots = shots;
        this.enemyTank = enemyTank;
    }

    @Override
    public void run() {
        while (true) {

            //允许发射子弹数
            if (enemyTank.isLive() && shots.size() < 1) {

                //判断坦克的方向,创建对应的子弹
                switch (enemyTank.getDirect()) {
                    case 0:
                        shot = new Shot(enemyTank.getX() + 20, enemyTank.getY(), 0);
                        break;
                    case 1:
                        shot = new Shot(enemyTank.getX() + 50, enemyTank.getY() + 30, 1);
                        break;
                    case 2:
                        shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, 2);
                        break;
                    case 3:
                        shot = new Shot(enemyTank.getX() - 10, enemyTank.getY() + 30, 3);
                        break;
                    default:
                }
                shots.add(shot);



                new Thread(shot).start();


                //解决子弹贴墙快速连发问题
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }
}
