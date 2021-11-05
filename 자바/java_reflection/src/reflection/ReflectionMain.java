package reflection;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionMain {

    public static void main(String[] args) throws Exception {
        Phone phone=new Phone(10);
        phone.setVersion(1);
        for(Field f:phone.getClass().getDeclaredFields()){
            // private 필드 접근
            f.setAccessible(true);
            // 필드이름 : 필드값
            System.out.println(f.getName()+" : "+f.get(phone));
            // 필드 값 변경
            f.setInt(phone, f.getInt(phone) * 2);
            System.out.println(f.getName()+" : "+f.get(phone));
        }

        // 메소드 호출
        Method m = phone.getClass().getDeclaredMethod("setVersion", int.class);
        m.invoke(phone,3);
        Field f = phone.getClass().getDeclaredField("version");
        f.setAccessible(true);
        System.out.println(f.getName()+" : "+f.get(phone));
        
        // 객체 생성
        Class<?> cl=Phone.class;
        Constructor<?> constructor = cl.getConstructor(int.class);
        Phone newPhone = (Phone) constructor.newInstance(50);
        newPhone.setVersion(5);


        // 배열 늘리거나 줄이기
        Integer[] intArr=new Integer[10];
        for(int i=0;i<10;i++){
            intArr[i]=i;
        }
        for(int el:intArr){
            System.out.print(el+" ");
        }
        System.out.println();
        Integer[] newArr = (Integer[])copyOf(intArr,20);
        for(int el:newArr){
            System.out.print(el+" ");
        }
        System.out.println();




    }

    public static Object copyOf(Object array,int newLength){

        Class<?> cl=array.getClass();
        if(!cl.isArray()) return null;

        int length= Array.getLength(array);
        Class<?> componentType = cl.getComponentType();
        Object newArray=Array.newInstance(componentType,newLength);

        int i=0;
        for(;i<Math.min(length,newLength);i++){
            Array.set(newArray,i,Array.get(array,i));
        }
        for(;i<newLength;i++){
            Array.set(newArray,i,0);
        }
        return newArray;
    }
}
