import java.util.Vector;

/**
 * @author 刘文长
 * @version 1.0
 */
public class EnemyTank extends Tank implements Runnable {
    private Shot shot = null;

    private Vector<Shot> shots = new Vector<>();

    private boolean isLive = true;




    public Vector<Shot> getShots() {
        return shots;
    }

    public void setShots(Vector<Shot> shots) {
        this.shots = shots;
    }

    public EnemyTank(int x, int y) {
        super(x, y);
        //开火
        new Thread(new EnemyTankFire(shots,this)).start();
    }

    //Thread thread = new Thread();
    //EnemyTankFire enemyTankFire =new EnemyTankFire(isLive(),shots,getX(),getY(),getDirect())

//    private void fireShot() {
//        new Thread(new EnemyTankFire(isLive(),shots,getX(),getY(),getDirect())).start();
////        if (isLive() && shots.size() < 1) {
////            //判断坦克的方向,创建对应的子弹
////            switch (getDirect()) {
////                case 0:
////                    shot = new Shot(getX() + 20, getY(), 0);
////                    break;
////                case 1:
////                    shot = new Shot(getX() + 50, getY() + 30, 1);
////                    break;
////                case 2:
////                    shot = new Shot(getX() + 20, getY() + 60, 2);
////                    break;
////                case 3:
////                    shot = new Shot(getX() - 10, getY() + 30, 3);
////                    break;
////                default:
////            }
////            shots.add(shot);
////
////
////            new Thread(shot).start();
////
////        }
////        try {
////            Thread.sleep(10);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//    }


    @Override
    public void run() {
        setSpeed(2);
        while (true) {



            //随机坦克每次移动距离21-30
            int distance = (int) (Math.random() * 20) + 81;
            //根据坦克的方向来继续移动
            switch (getDirect()) {
                case 0:
                    for (int i = 0; i < distance; i++) {

                        if (getY() > 0) {
                            moveUp();
                        }
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < distance; i++) {

                        if (getX() - 10 + 60 < 1000) {
                            moveRight();
                        }
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < distance; i++) {

                        if (getY() + 60 < 750) {
                            moveDown();
                        }
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < distance; i++) {

                        if (getX() - 10 > 0) {
                            moveLeft();
                        }
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                default:
            }


            //随机改变坦克方向
            setDirect((int) (Math.random() * 4));

            if (!isLive) {
                break;
            }
        }
    }
}
