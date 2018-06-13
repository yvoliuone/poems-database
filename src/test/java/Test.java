import com.lys.model.Poem;
import com.lys.service.PoemService;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-mybatis.xml"})
public class Test {

    @Autowired
    private static PoemService poemService;

    @BeforeClass
    public static void init() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-mybatis.xml");
        poemService = (PoemService) context.getBean(PoemService.class);
    }

    @org.junit.Test
    public void testGetPoem() {
        try {
            Poem poem = poemService.queryPoem(1);

            System.out.println(poem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
