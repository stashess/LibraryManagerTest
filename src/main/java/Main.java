import main.Program;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Created by Stanislav Cheslavskyi on 05.10.2017.
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new GenericXmlApplicationContext("spring/spring-app.xml");
        Program program = (Program) context.getBean("program");
        program.run();
    }
}
