package userservice.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@ToString
public class User {

    @Id
    private Integer id;
    private String name;
    private Integer balance;
}
