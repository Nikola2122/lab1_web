package mk.ukim.finki.wp.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class Lab1WpApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab1WpApplication.class, args);
    }
}
