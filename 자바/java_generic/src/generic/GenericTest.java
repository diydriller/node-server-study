package generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericTest {

    static <T> List<T> pickList(T a) {
        return Arrays.asList(a);
    }

    static <T> T[] toArray(T... args) {
        return args;
    }

    static <T> T[] pickArray(T a) {
        return toArray(a);
    }

    static void print(List<? super String> obList){
        for(Object el:obList){
            System.out.println(el.toString());
        }
    }

    public static void main(String[] args){
        // 1. 타입제거
        List<String> stringList = pickList("자바");
        //String[] stringArray = pickArray("자바");


        // 2. 공변성
        List<Object> strList=new ArrayList<Object>(){{add("first");add("second");}};
        print(strList);

    }
}
