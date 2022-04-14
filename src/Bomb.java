/**
 * @author 刘文长
 * @version 1.0
 */
public class Bomb {
    //炸弹的坐标
    private int x,y;
    //炸弹的生命周期
    private int life = 15;
    //是否还存活
    private boolean isLive = true;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }


    //减少生命值
    public void lifeDown(){
        if(life > 0){
            life--;
        }else {
            isLive = false;
        }
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }
}
