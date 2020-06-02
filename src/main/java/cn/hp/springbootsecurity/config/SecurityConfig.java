package cn.hp.springbootsecurity.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Ironhide
 * @create 2020-04-26-14:05
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //请求授权的规则
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");
        //http.formLogin() 设定没有权限默认去登录页面，需开启登录页面
        //设定登录后的认证url，获取表单用户名name与密码name
        http.formLogin().loginPage("/toLogin").loginProcessingUrl("/login").usernameParameter("userName").passwordParameter("pwd");
        //设置注销成功去往的页面路径
        http.csrf().disable();//csrf(跨站请求伪造) 注销时报404问题的原因之一
        http.logout().logoutSuccessUrl("/");
        //开启rememberme功能
        http.rememberMe().rememberMeParameter("remember");
    }

    //认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //inMemoryAuthentication() 从内存中获取认证信息
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1", "vip2", "vip3")
                .and()
                .withUser("zhangsan").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1")
                .and()
                .withUser("lisi").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1", "vip2");
    }
}
