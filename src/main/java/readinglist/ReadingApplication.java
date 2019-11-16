package readinglist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
// 컨트롤러에서 파라미터를 바인딩해주는 역할을 한다. 또한 작업을 수행한 후 Object를 반환한다.
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@SpringBootApplication
public class ReadingApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(ReadingApplication.class, args);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        // ViewControllerRegistry는 뭔가 웹을 띄우게 하는 도구 같음...
        registry.addViewController("/login").setViewName("login");
    }// /login으로 들어가면 "login"의 url을 보여준다.

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
        argumentResolvers.add(new ReaderHandlerMethodArgumentResolver());
    }
}
