package sumdu.edu.ua;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Включає в себе @EnableAutoConfiguration та @ComponentScan
public class ApplicationStarter {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStarter.class, args);

        try {
            openHomePage();
        } catch (IOException ex) {
            Logger.getLogger(ApplicationStarter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void openHomePage() throws IOException {
        Runtime rt = Runtime.getRuntime();
        rt.exec("rundll32 url.dll,FileProtocolHandler " + "http://localhost:8080/");
    }
}
