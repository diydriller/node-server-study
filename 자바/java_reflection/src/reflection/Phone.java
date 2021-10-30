package reflection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Phone {
    private int age;
    private int version;

    public Phone(int age) {
        this.age = age;
    }
}
