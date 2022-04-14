import java.util.Vector;

/**
 * @author 刘文长
 * @version 1.0
 */
public class Hero extends Tank {

    Shot shot = null;

    //发射多个子弹
    Vector<Shot> shots = new Vector<>();

    public Hero(int x, int y) {
        super(x, y);
    }

    public void shotEnemyTank() {
        //三连发
        if (shots.size() == 3){
            return;
        }
        //if (shot == null || !shot.isLive()) {
            switch (getDirect()) {
                case 0:
                    shot = new Shot(getX() + 20, getY(), 0);
                    break;
                case 1:
                    shot = new Shot(getX() + 50, getY() + 30, 1);
                    break;
                case 2:
                    shot = new Shot(getX() + 20, getY() + 60, 2);
                    break;
                case 3:
                    shot = new Shot(getX() - 10, getY() + 30, 3);
                    break;
                default:
            }

                shots.add(shot);


                new Thread(shot).start();

        //}
    }
}
