package com.example.shiro;

import com.example.entity.User;
import com.example.service.AdminUserService;
import com.example.service.StuUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private StuUserService userService;

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         *    常用的过滤器：
         *       anon: 无需认证（登录）可以访问
         *       authc: 必须认证才可以访问
         *       user: 如果使用rememberMe的功能可以直接访问
         *       perms： 该资源必须得到资源权限才可以访问
         *       role: 该资源必须得到角色权限才可以访问
         */
        Map<String,String> filterMap = new LinkedHashMap<String,String>();

        filterMap.put("/static/**", "anon");
        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        filterMap.put("/v2/**", "anon");
        filterMap.put("/webjars/**", "anon");

        filterMap.put("/stu/tologin", "anon");
        filterMap.put("/stu/loginsubmit", "anon");
        filterMap.put("/sturegister", "anon");
        filterMap.put("/StuRegister", "anon");
        filterMap.put("/findReisterUsername","anon");
        filterMap.put("/alogin", "anon");
        filterMap.put("/adminloginsubmit", "anon");

        //修改调整的登录页面
        shiroFilterFactoryBean.setLoginUrl("/stu/tologin");
        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterMap.put("/logout", "logout");
        //放最后
        filterMap.put("/*", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);


        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建Realm
     */
    @Bean(name="userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }

    public String authentication(String username, String password, Model model, HttpSession session) {
        /**
         * 使用Shiro编写认证操作
         */
        if(!userService.findReisterUsername(username)){
            model.addAttribute("msg", "用户名不存在");
            return "admin/adminLogin";
        }

        User user = adminUserService.findByUsername(username);
        if (!(user.getRoles().equals("teacher")||user.getRoles().equals("admin"))) {
            model.addAttribute("msg", "登录失败");
            return "admin/adminLogin";
        }
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        //进行Md5加密，第一个参数为密码，第二参数是以用户名为盐，第三个参数是加密的次数
        Md5Hash md5password = new Md5Hash(password, username, 5);
        System.out.println(md5password.toString());

        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, md5password.toString());
        //3.执行登录方法
        try {
            subject.login(token);


            //登录成功
            //根据用户权限，跳转到对应的页面
            if(user.getRoles().equals("teacher")) {
                session.setAttribute("TeaUsername", username);
                return "redirect:/teaindex";
            }

            if(user.getRoles().equals("admin")) {
                return "redirect:/adminindex";
            }
            return "admin/adminLogin";
        } catch (UnknownAccountException e) {
            //e.printStackTrace();
            //登录失败:用户名不存在
            model.addAttribute("msg", "用户名不存在");
            return "admin/adminLogin";
        } catch (IncorrectCredentialsException e) {
            //e.printStackTrace();
            //登录失败:密码错误
            model.addAttribute("msg", "密码错误");
            return "admin/adminLogin";
        }

    }
}