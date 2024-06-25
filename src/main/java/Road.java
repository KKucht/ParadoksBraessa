import java.util.ArrayList;
import java.util.List;

public class Road {
    private final int capacity;
    private final int timeOfDriving;
    private int drvingCars;
    private int waitingCars;
    private char start;
    private char end;
    public  List<Long> times;
    private Object lock = new Object () ;

    public void setDrvingCars(int drvingCars) {
        this.drvingCars = drvingCars;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getTimeOfDriving() {
        return timeOfDriving;
    }

    public int getDrvingCars() {
        return drvingCars;
    }

    public int getWaitingCars() {
        return waitingCars;
    }

    public char getStart() {
        return start;
    }

    public char getEnd() {
        return end;
    }

    public void setWaitingCars(int waitingCars) {
        this.waitingCars = waitingCars;
    }

    public void setStart(char start) {
        this.start = start;
    }

    public void setEnd(char end) {
        this.end = end;
    }

    synchronized public long getTime(){
        long sum = 0;
        for(int i =0;i<times.size();i++){
            sum += times.get(i);
        }
        return sum/times.size();
    }

    public Road(int capacity, int timeOfDriving, char start, char end) {
        this.capacity = capacity;
        this.timeOfDriving = timeOfDriving;
        this.drvingCars = 0;
        this.waitingCars = 0;
        this.start = start;
        this.end = end;
        this.times = new ArrayList<>();
        for(int i = 0; i <10;i++){
            this.times.add((long)timeOfDriving);
        }
    }

    synchronized public void EnterToRoad(Car car) throws InterruptedException {
        car.time =System.currentTimeMillis();
        waitingCars++;
        while(drvingCars == capacity){
            wait();
        }
        waitingCars--;
        synchronized(lock){
            drvingCars++;
        }

    }

    public void Drive(Car car){
        try{
            Thread.sleep(timeOfDriving);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            synchronized (lock)
            {
               drvingCars--;
            }
            car.time = System.currentTimeMillis() - car.time;

            synchronized (this)
            {
                this.times.remove(0);
                this.times.add(car.time);
                notify();
            }
        }

    }

}
