package string;

public class StringTest {

    public static void main(String[] args){
        comparePlusOperation();
    }

    private static void comparePlusOperation() {
        long startTime1 = System.nanoTime();

        //String str="a"+"b"+"c"+"d"+"e"+"f"+"g"+"h";
        String str="a";
        str+="b";
        str+="c";
        str+="d";
        str+="e";
        str+="f";
        str+="g";
        str+="h";

        long totalTime1 = System.nanoTime() - startTime1;

        long startTime2 = System.nanoTime();

        StringBuilder sb=new StringBuilder();
        sb.append("a");
        sb.append("b");
        sb.append("c");
        sb.append("d");
        sb.append("e");
        sb.append("f");
        sb.append("g");
        sb.append("h");

        long totalTime2 = System.nanoTime() - startTime2;

        System.out.println("String: "+totalTime1+" , StringBuilder: "+totalTime2);

    }

}
