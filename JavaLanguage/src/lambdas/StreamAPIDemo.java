package lambdas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chandanpr
 * Date: 01/09/13
 * Time: 11:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class StreamAPIDemo {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<10; i++)
            list.add(i);

        loopThroughOldForEach(list);

        loopThroughLambdaStyle(list);
    }

    private static void loopThroughLambdaStyle(List<Integer> list) {
        list.forEach(System.out::print);
        list.stream().filter(p -> p%2 == 0).forEach(System.out::println);
    }

    private static void loopThroughOldForEach(List<Integer> list) {
        for(int i: list){
            System.out.print(i);
        }
        System.out.println();
    }
}
