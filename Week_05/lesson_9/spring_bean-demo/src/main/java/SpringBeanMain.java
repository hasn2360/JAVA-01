import bean.PersonAnno;
import bean.PersonBean;
import bean.PersonXml;
import com.google.common.base.Joiner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Auther: song.huai
 * @Date: 2021/2/17 22:14
 * @Description:
 */
public class SpringBeanMain {

    public static void main(String[] args) {
        ApplicationContext applicationContextXml = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        //XML装配
        PersonXml personXml = applicationContextXml.getBean(PersonXml.class);
        System.out.println(personXml);
        //注解装配
        ApplicationContext applicationContextAnno = new AnnotationConfigApplicationContext("./");
        PersonAnno personAnno = applicationContextAnno.getBean("personAnno", PersonAnno.class);
        System.out.println(personAnno);
        //java代码装配bean
        PersonBean personBean = applicationContextAnno.getBean("personBean", PersonBean.class);
        System.out.println(personBean);

        System.out.println(Joiner.on(",").join(applicationContextXml.getBeanDefinitionNames()));
        System.out.println(Joiner.on(",").join(applicationContextAnno.getBeanDefinitionNames()));
    }
}
