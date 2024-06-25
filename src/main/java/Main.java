import java.util.ArrayList;
import java.util.List;

public class Main {
    public final static int threadNumber = 10000;
    public static List<Road> roads;
    public static long[] times = new long[threadNumber];
    public static void main(String[] args) {
        roads = new ArrayList<>();
        roads.add(new Freeway('A','X'));
        roads.add(new Freeway('Y','B'));
        roads.add(new LocalRoad('A','Y'));
        roads.add(new LocalRoad('X','B'));
        List<Thread> threads = new ArrayList<>();
        for(int i = 0 ; i <threadNumber;i++){
            threads.add(new Thread(new Car(roads,i)));

        }
        long startTime = System.currentTimeMillis();
        for(Thread t : threads){
            t.start();
            try {
                Thread.sleep(0,250000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(Thread t : threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        double totalTime = 0;
        for(int i=0; i<threadNumber; i++){
            totalTime = totalTime + times[i];
        }
        System.out.println("Average time: " + totalTime/threadNumber + " milis");
        roads.add(new Freeway('Y','X'));
        threads.clear();
        threads = new ArrayList<>();
        for(int i = 0 ; i <threadNumber;i++){
            threads.add(new Thread(new Car(roads,i)));

        }
        startTime = System.currentTimeMillis();
        for(Thread t : threads){
            t.start();
            try {
                Thread.sleep(0,250000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(Thread t : threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        totalTime = 0;
        for(int i=0; i<threadNumber; i++){
            totalTime = totalTime + times[i];
        }
        System.out.println("Average time: " + totalTime/threadNumber + " milis");
    }
}
