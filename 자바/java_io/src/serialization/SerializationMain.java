package serialization;

import serialization.model.Employee;
import serialization.model.Manager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SerializationMain {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

//        Manager park=new Manager("park",1000);
//        Employee lee=new Employee("lee",200,park);
//        Employee kim=new Employee("kim",250,park);

        Path path=Paths.get("./src/serialization/file/model.txt");
//        ObjectOutputStream out=new ObjectOutputStream(Files.newOutputStream(path));
//        out.writeObject(lee);
//        out.writeObject(kim);

        ObjectInputStream in=new ObjectInputStream(Files.newInputStream(path));
        Employee e1=(Employee) in.readObject();
        Employee e2=(Employee) in.readObject();

        System.out.println(e1);
        System.out.println(e2);
        System.out.println(e1.getBoss()==e2.getBoss()?"boss is same":"boss is different");

    }
}
