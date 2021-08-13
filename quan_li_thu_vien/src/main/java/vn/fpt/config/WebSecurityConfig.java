package vn.fpt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import vn.fpt.service.impl.MyUserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailServiceImpl myUserDetailService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/books").hasRole("ADMIN")
                .antMatchers("/member").hasRole("ADMIN")
                .antMatchers("/categories").hasRole("ADMIN")
                .antMatchers("/authors").hasRole("ADMIN")
                .antMatchers("/viewborrow").hasRole("ADMIN")
                .antMatchers("/accountBook").hasRole("ADMIN")
                .antMatchers("/add").hasRole("ADMIN")
                .antMatchers("/update/{id}").hasRole("ADMIN")
                .antMatchers("/delete/{id}").hasRole("ADMIN")
                .antMatchers("/addCategory").hasRole("ADMIN")
                .antMatchers("/updateCategory/{id}").hasRole("ADMIN")
                .antMatchers("/addAuthor").hasRole("ADMIN")
                .antMatchers("/updateAuthor/{id}").hasRole("ADMIN");
        http.authorizeRequests().and().rememberMe()
                /* câu hình nơi lưu thông tin remember me và thời gian lưu cookie tại client*/
                .tokenRepository(this.persistentTokenRepository()).tokenValiditySeconds(60 * 60 * 10);
    }
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        /* dang cấu hình lưu tại memory của server */
        InMemoryTokenRepositoryImpl memoryTokenRepository = new InMemoryTokenRepositoryImpl();
        return memoryTokenRepository;
    }
}
