package experiments;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * User: chandanpr
 * Date: 27/08/13
 * Time: 3:30 PM
 *
 * Trying to understand LinkedBlockingQueue visibility effects:
 *
 * http://jsr166-concurrency.10961.n7.nabble.com/Reader-writer-visibility-in-LinkedBlockingQueue-tt10049.html
 *
 *
 * To change this template use File | Settings | File Templates.
 */
public class LinkedBlockingQueueExperiment {


    public static void main(String[] args) {
        final LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);
        Thread reader = new Thread(){
            @Override
            public void run() {
                for(int i=0; i<5; i++)
                    System.out.println("Getting : "+queue.poll());
            }
        };

        Thread writer = new Thread(){
            @Override
            public void run() {
                for(int i=0; i<5; i++){
                    System.out.println("Adding "+i);
                    try {
                        queue.put(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        writer.start();
        reader.start();

    }
}
