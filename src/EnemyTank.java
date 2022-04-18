import java.util.Vector;

/**
 * @author 刘文长
 * @version 1.0
 */
public class EnemyTank extends Tank implements Runnable {
    private Shot shot = null;

    //在敌人坦克类，使用Vector保存多个Shot
    private Vector<Shot> shots = new Vector<>();

    private boolean isLive = true;

    //增加成员,EnemyTank 可以得到敌人坦克的Vector
    Vector<EnemyTank> enemyTanks = new Vector<>();

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }


    //判断当前敌人坦克，是否和enemyTanks的其他坦克发生碰撞
    public boolean isTouchEnemyTank() {

        //判断当前敌人坦克的方向
        switch (this.getDirect()) {
            case 0:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            if (this.getX() +40>= enemyTank.getX()
                                    && this.getX() +40<= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }

                        } else if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            if (this.getX() >= enemyTank.getX() -10
                                    && this.getX() <= enemyTank.getX() + 50
                                    && this.getY() >= enemyTank.getY() +10
                                    && this.getY() <= enemyTank.getY() + 50) {
                                return true;
                            }
                            if (this.getX() +40>= enemyTank.getX() - 10
                                    && this.getX() +40<= enemyTank.getX() + 50
                                    && this.getY() >= enemyTank.getY() +10
                                    && this.getY() <= enemyTank.getY() + 50) {
                                return true;
                            }
                        }
                    }
                }
                if (hero.getDirect() == 0 || hero.getDirect() == 2) {
                    if (this.getX() >= hero.getX()
                            && this.getX() <= hero.getX() + 40
                            && this.getY() >= hero.getY()
                            && this.getY() <= hero.getY() + 60) {
                        return true;
                    }
                    if (this.getX() +40>= hero.getX()
                            && this.getX() +40<= hero.getX() + 40
                            && this.getY() >= hero.getY()
                            && this.getY() <= hero.getY() + 60) {
                        return true;
                    }

                } else if (hero.getDirect() == 1 || hero.getDirect() == 3) {
                    if (this.getX() >= hero.getX() -10
                            && this.getX() <= hero.getX() + 50
                            && this.getY() >= hero.getY() +10
                            && this.getY() <= hero.getY() + 50) {
                        return true;
                    }
                    if (this.getX() +40>= hero.getX() - 10
                            && this.getX() +40<= hero.getX() + 50
                            && this.getY() >= hero.getY() +10
                            && this.getY() <= hero.getY() + 50) {
                        return true;
                    }
                }
                break;
            case 1:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            if (this.getX() + 50>= enemyTank.getX()
                                    && this.getX() +50<= enemyTank.getX() + 40
                                    && this.getY() +10>= enemyTank.getY()
                                    && this.getY() +10<= enemyTank.getY() + 60) {
                                return true;
                            }
                            if (this.getX() +50>= enemyTank.getX()
                                    && this.getX() +50<= enemyTank.getX() + 40
                                    && this.getY() +50>= enemyTank.getY()
                                    && this.getY() +50<= enemyTank.getY() + 60) {
                                return true;
                            }

                        } else if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            if (this.getX() + 50>= enemyTank.getX() - 10
                                    && this.getX() +50<= enemyTank.getX() + 50
                                    && this.getY() +10>= enemyTank.getY() + 10
                                    && this.getY() +10<= enemyTank.getY() + 50) {
                                return true;
                            }
                            if (this.getX() +50>= enemyTank.getX() - 10
                                    && this.getX() +50<= enemyTank.getX() + 50
                                    && this.getY() +50>= enemyTank.getY() + 10
                                    && this.getY() +50<= enemyTank.getY() + 50) {
                                return true;
                            }
                        }
                    }
                }
                if (hero.getDirect() == 0 || hero.getDirect() == 2) {
                    if (this.getX() + 50>= hero.getX()
                            && this.getX() +50<= hero.getX() + 40
                            && this.getY() +10>= hero.getY()
                            && this.getY() +10<= hero.getY() + 60) {
                        return true;
                    }
                    if (this.getX() +50>= hero.getX()
                            && this.getX() +50<= hero.getX() + 40
                            && this.getY() +50>= hero.getY()
                            && this.getY() +50<= hero.getY() + 60) {
                        return true;
                    }

                } else if (hero.getDirect() == 1 || hero.getDirect() == 3) {
                    if (this.getX() + 50>= hero.getX() - 10
                            && this.getX() +50<= hero.getX() + 50
                            && this.getY() +10>= hero.getY() + 10
                            && this.getY() +10<= hero.getY() + 50) {
                        return true;
                    }
                    if (this.getX() +50>= hero.getX() - 10
                            && this.getX() +50<= hero.getX() + 50
                            && this.getY() +50>= hero.getY() + 10
                            && this.getY() +50<= hero.getY() + 50) {
                        return true;
                    }
                }
                break;
            case 2:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() +60>= enemyTank.getY()
                                    && this.getY() +60<= enemyTank.getY() + 60) {
                                return true;
                            }
                            if (this.getX() +40>= enemyTank.getX()
                                    && this.getX() +40<= enemyTank.getX() + 40
                                    && this.getY() +60>= enemyTank.getY()
                                    && this.getY() +60<= enemyTank.getY() + 60) {
                                return true;
                            }

                        } else if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            if (this.getX() >= enemyTank.getX() -10
                                    && this.getX() <= enemyTank.getX() + 50
                                    && this.getY() +60>= enemyTank.getY() +10
                                    && this.getY() +60<= enemyTank.getY() + 50) {
                                return true;
                            }
                            if (this.getX() +40>= enemyTank.getX() - 10
                                    && this.getX() +40<= enemyTank.getX() + 50
                                    && this.getY() +60>= enemyTank.getY() +10
                                    && this.getY() +60<= enemyTank.getY() + 50) {
                                return true;
                            }
                        }
                    }
                }
                if (hero.getDirect() == 0 || hero.getDirect() == 2) {
                    if (this.getX() >= hero.getX()
                            && this.getX() <= hero.getX() + 40
                            && this.getY() +60>= hero.getY()
                            && this.getY() +60<= hero.getY() + 60) {
                        return true;
                    }
                    if (this.getX() +40>= hero.getX()
                            && this.getX() +40<= hero.getX() + 40
                            && this.getY() +60>= hero.getY()
                            && this.getY() +60<= hero.getY() + 60) {
                        return true;
                    }

                } else if (hero.getDirect() == 1 || hero.getDirect() == 3) {
                    if (this.getX() >= hero.getX() -10
                            && this.getX() <= hero.getX() + 50
                            && this.getY() +60>= hero.getY() +10
                            && this.getY() +60<= hero.getY() + 50) {
                        return true;
                    }
                    if (this.getX() +40>= hero.getX() - 10
                            && this.getX() +40<= hero.getX() + 50
                            && this.getY() +60>= hero.getY() +10
                            && this.getY() +60<= hero.getY() + 50) {
                        return true;
                    }
                }
                break;
            case 3:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            if (this.getX() - 10>= enemyTank.getX()
                                    && this.getX() -10<= enemyTank.getX() + 40
                                    && this.getY() +10>= enemyTank.getY()
                                    && this.getY() +10<= enemyTank.getY() + 60) {
                                return true;
                            }
                            if (this.getX() -10>= enemyTank.getX()
                                    && this.getX() -10<= enemyTank.getX() + 40
                                    && this.getY() +50>= enemyTank.getY()
                                    && this.getY() +50<= enemyTank.getY() + 60) {
                                return true;
                            }

                        } else if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            if (this.getX() - 10>= enemyTank.getX() - 10
                                    && this.getX() -10<= enemyTank.getX() + 50
                                    && this.getY() +10>= enemyTank.getY() + 10
                                    && this.getY() +10<= enemyTank.getY() + 50) {
                                return true;
                            }
                            if (this.getX() -10>= enemyTank.getX() - 10
                                    && this.getX() -10<= enemyTank.getX() + 50
                                    && this.getY() +50>= enemyTank.getY() + 10
                                    && this.getY() +50<= enemyTank.getY() + 50) {
                                return true;
                            }
                        }
                    }
                }
                if (hero.getDirect() == 0 || hero.getDirect() == 2) {
                    if (this.getX() - 10>= hero.getX()
                            && this.getX() -10<= hero.getX() + 40
                            && this.getY() +10>= hero.getY()
                            && this.getY() +10<= hero.getY() + 60) {
                        return true;
                    }
                    if (this.getX() -10>= hero.getX()
                            && this.getX() -10<= hero.getX() + 40
                            && this.getY() +50>= hero.getY()
                            && this.getY() +50<= hero.getY() + 60) {
                        return true;
                    }

                } else if (hero.getDirect() == 1 || hero.getDirect() == 3) {
                    if (this.getX() - 10>= hero.getX() - 10
                            && this.getX() -10<= hero.getX() + 50
                            && this.getY() +10>= hero.getY() + 10
                            && this.getY() +10<= hero.getY() + 50) {
                        return true;
                    }
                    if (this.getX() -10>= hero.getX() - 10
                            && this.getX() -10<= hero.getX() + 50
                            && this.getY() +50>= hero.getY() + 10
                            && this.getY() +50<= hero.getY() + 50) {
                        return true;
                    }
                }
                break;
            default:
        }
        return false;
    }


    public Vector<Shot> getShots() {
        return shots;
    }

    public void setShots(Vector<Shot> shots) {
        this.shots = shots;
    }

    //传入己方坦克，实现防重叠
    Hero hero = null;
    public EnemyTank(int x, int y) {
        super(x, y);
        //开火
        new Thread(new EnemyTankFire(shots, this)).start();
    }

    public void setHero(Hero hero) {
        this.hero = hero;
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

                        if (getY() > 0 &&!isTouchEnemyTank()) {
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

                        if (getX() - 10 + 60 < 1000 &&!isTouchEnemyTank()) {
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

                        if (getY() + 60 < 750 &&!isTouchEnemyTank()) {
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

                        if (getX() - 10 > 0 &&!isTouchEnemyTank()) {
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
