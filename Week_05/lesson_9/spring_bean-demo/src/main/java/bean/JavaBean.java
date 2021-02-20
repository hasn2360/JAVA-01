package bean;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: song.huai
 * @Date: 2021/2/17 23:41
 * @Description:
 */
@Data
@Configuration
public class JavaBean {

    @Bean(name = "personBean")
    public PersonBean getPersonBean(){
        PersonBean personBean = new PersonBean();
        personBean.setAge(12);
        personBean.setName("王五");
        return personBean;
    }
}
