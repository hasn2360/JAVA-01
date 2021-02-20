package bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @Auther: song.huai
 * @Date: 2021/2/17 22:56
 * @Description:
 */
@Component(value = "personAnno")
@Data
@ToString
public class PersonAnno {

    @Value("20")
    private int age;
    @Value("李四")
    private String name;
}
