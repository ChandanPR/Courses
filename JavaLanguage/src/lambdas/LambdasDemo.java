package lambdas;

/**
 * Created with IntelliJ IDEA.
 * User: chandanpr
 * Date: 01/09/13
 * Time: 11:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class LambdasDemo {


    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Test");
            }
        }).start();

        new Thread(() -> System.out.println("Lambdas demo")).start();

    }
}
