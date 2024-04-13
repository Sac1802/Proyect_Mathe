package proyect_U.Security;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Security {

    @Bean
    SecurityFilterChain web(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authz -> {
                    try{
                        authz
                                .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
                                .requestMatchers(HttpMethod.POST, "/Login").permitAll()
                                .requestMatchers(HttpMethod.POST, "/register/user").permitAll()
                                .requestMatchers(HttpMethod.POST, "/save/user").permitAll()
                                .requestMatchers(HttpMethod.POST, "/save/average").permitAll()
                                .requestMatchers(HttpMethod.GET, "/getId/averages/{id}").permitAll();
                    }catch (Exception e){
                        throw  new RuntimeException(e);
                    }
                }
                ));
        return httpSecurity.build();
    }

}
