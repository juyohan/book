package readinglist.Read;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collection;

@Entity // (name = "hello")를 붙이게되면 테이블의 이름이 hello로 저장이 된다.
// 만약 뒤의 속성을 사용하지 않는다면 저장되는 테이블 명칭은 기본 클래스명이 된다.
public class ReaderDB implements UserDetails { // UserDetails 인터페이스를 통해 구현하게 되면
    // security에서 구현한 클래스를 사용자 정보로 인식하고 인증 작업을 한다. 즉, VO역할을 한다고 보면 된다.

    // @Table은 엔티티와 매핑할 테이블을 지정한다.
    // 생략하게 되면 매핑하는 엔티티이름(속성을 사용하지 않을 시 클래스명)으로 대신함

    private static final long serialVersionUID=1L;

    @Id // 기본키를 지정
    // @Colume 매핑에는 nullable속성과 length등의 속성을 지정가능.
    // @Colume(name = "username" , length = 11, nullable = false)를 할 경우
    private String username;
    // username VARCHAR(11) NOT NULL과 매핑한다.
    private String fullname;
    private String password;

    // @JoinColumn(name = "외래키") 는 외래키를 매핑할 때 사용한다.
    // 만약 생략할 경우엔 Fieldname_Columnname 이렇게 해서 찾는다.
    // 필드명 _ 테이블의 칼럼명

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = "{noop}" + password;
    }

    @Override
    public Collection <? extends GrantedAuthority> getAuthorities(){
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_READER"));
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }
}
