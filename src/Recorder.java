import javax.annotation.processing.Filer;
import java.io.*;
import java.util.Vector;

/**
 * @author 刘文长
 * @version 1.0
 * 记录相关信息和文件交互
 */
public class Recorder {

    //定义变量，记录我方击毁敌人坦克数
    private static int allEnemyTankNum = 0;
    //定义io对象
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static String recordFile = "src\\myRecord.txt";

    //定义Vector，只想Mypanel对象的敌人坦克Vector
    private static Vector<EnemyTank> enemyTanks = null;

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    public static boolean isExists(){
        boolean key = false;
        try {
            key =new File(recordFile).exists();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return key;
    }


    //定义一个Node 的Vector，用于保存敌人的信息node
    private static Vector<Node> nodes = new Vector<>();
    //用于读取recorFile ,恢复相关信息
    public static Vector<Node> getNodesAndEnemyTankRec(){
        try {
            br = new BufferedReader(new FileReader(recordFile));
            allEnemyTankNum = Integer.parseInt(br.readLine());
            //循环读取，生成nodes集合
            String line = "";
            while ((line = br.readLine() )!=null){
                String[] xyd = line.split(" ");
                nodes.add(new Node(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1]),
                        Integer.parseInt(xyd[2])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return nodes;
    }

    //存盘
    public static void keepRecord() {
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(allEnemyTankNum+"\r\n");

            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                if(enemyTank.isLive()){
                    String record = enemyTank.getX() + " " +enemyTank.getY()+" "+enemyTank.getDirect();
                    bw.write(record+"\r\n" +
                            "");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bw != null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }
    public static void addAllEnemyTankNum(){
        Recorder.allEnemyTankNum++;
    }
}
