package readinglist.Read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// security에 대한 설정 클래스이다. 로그인환경을 만들때 사용함.
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import readinglist.ReaderRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ReaderRepository readerRepository;

    // 두 가지의 configure의 메소드를 오버라이드 함.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // configure(HttpSecurity http)메소드는 WebSecurityConfigurerAdapter가 제공한다.

        http
                .authorizeRequests()
                // 요청에 대한 권한을 지정할 수 있다. 즉, 시큐리티 처리에 HttpServletRequest를 이용한다는 의미.
                .antMatchers("/").access("hasRole('READER')")
                // antMatchers는 특정한 경로(/)를 지정함(ReadingListController 메서드에 매핑되어있음.)
                // access는 주어진 SpEL표현식의 평가 결과가 true이면 접근을 허용.
                // hasRole은 주어진 역할이 있다면 접근한다. < READER이 있다면 접근을 허용
                .antMatchers("/**").permitAll()
                // / < 이 경로가 아니라면, permitAll()은 무조건 접근을 허용


                .and()
                // 메소드를 서로 연결해준다.

                .formLogin()
                // if랑 비슷한 문구이며 결과값에 따라 밑에하는게 다르다.
                .loginPage("/login")
                // 로그인에 성공이 된다면 /login < 경로로 이동(매핑이 되어있음)
                .failureUrl("/login?error=true");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        // security의 인증에 대한 지원을 설정하는 메소드를 가지고 있음. jdbc에 인증정보를 연
        auth.userDetailsService(new UserDetailsService() {
            // userDtailsService의 인터페이스는 DB에서 유저 정보를 가져오는 역할을 한다.
            @Override
            public UserDetails loadUserByUsername(String username)
            // loadUserByUsername() 메소드는 유저정보를 불러오는 작업을 함.(오버라이드)
                    throws UsernameNotFoundException {

                return readerRepository.findById(username).orElse(null);
                // getOne메서드는 JPA에서 데이터를 읽어오는 방식인데 getOne()메서드를 사용하려면
                // 데이터 저장을 하나의 세션으로 해야한다.
            }
        });
    }
}
