package com.spring.batch.adder.reader;



import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.AbstractItemStreamItemReader;
import java.util.Arrays;
import java.util.List;

public class InMemoryReader extends AbstractItemStreamItemReader {
    Integer[] intArr={1,2,3,4,5,6,7,8,9};
    List<Integer> intList= Arrays.asList(intArr);

    int index=0;

    @Override
    public Object read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        Integer nextItem=null;
        if(index<intList.size()){
            nextItem=intList.get(index);
            index++;
        }
        else{
            index=0;
        }
        return nextItem;
    }
}
