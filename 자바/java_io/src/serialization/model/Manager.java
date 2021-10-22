package serialization.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manager implements Serializable {
    private static final long serialVersionUID=-2;
    private String name;
    private Integer salary;
}
