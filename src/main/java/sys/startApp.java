package sys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
 

@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan("sys.dao")
public class startApp {
	public static void main(String[] args) {
		SpringApplication.run(startApp.class, args);
	}
}