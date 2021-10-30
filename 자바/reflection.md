# reflection
* 런타임시에 객체내용을 조사하거나 해당 객체의 임의의 메서드를 호출할 수 있는 것을 
말한다.
* 배열의 길이를 변경하는 메서드에서 새로운 배열 Object[]을 해당클래스의 배열로
cast하는 것이 불가능하므로 java.lang.reflect.Array 클래스의 newInstance 메서드
를 사용한다. 
```java
    public static Object copyOf(Object array,int newLength){

        Class<?> cl=array.getClass();
        if(!cl.isArray()) return null;

        int length= Array.getLength(array);
        Class<?> componentType = cl.getComponentType();
        Object newArray=Array.newInstance(componentType,newLength);
        
        for(int i=0;i<Math.min(length,newLength);i++){
            Array.set(newArray,i,Array.get(array,i));
        }
        
        return newArray;
    }
```