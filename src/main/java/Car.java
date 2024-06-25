import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Car implements Runnable{
    private List<Road> roads;
    private char location;
    private int id;
    public long time;

    public Car(List<Road> roads,int id) {
        this.id = id;
        this.roads = roads;
        this.location = 'A';
        this.time = 0;
    }

    public MyResult chooseRoad(char start){
        long time = 2147483647;
        char direction = '?';
        for(Road ok : roads){
            if(ok.getStart() == start){
                if(ok.getEnd() != 'B'){
                    MyResult x = chooseRoad(ok.getEnd());
                    long newTime = x.getTime() + ok.getTime();
                    if( newTime < time){
                        time = newTime;
                        direction = ok.getEnd();
                    }
                }
                else {
                    long newTime = ok.getTime();
                    if( newTime < time){
                        time = newTime;
                        direction = ok.getEnd();
                    }
                }
            }
        }
        return new MyResult(direction,(int)time);
    }

    @Override
    public void run() {
        Main.times[id] = 0;
        while(location != 'B'){
            MyResult x = chooseRoad(location);
            //System.out.println("jade do "+x.getEndOfRoad());
            for(Road ok : roads){
                if(ok.getEnd() == x.getEndOfRoad()){
                    long a =System.currentTimeMillis();
                    try {
                        ok.EnterToRoad(this);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //System.out.println("jade do "+x.getEndOfRoad());
                    ok.Drive(this);
                    //System.out.println("dojechalem do "+x.getEndOfRoad());
                    this.location = x.getEndOfRoad();
                    long b =System.currentTimeMillis() - a;
                    Main.times[id] = Main.times[id] + b;
                    break;
                }
            }
        }
    }
}
