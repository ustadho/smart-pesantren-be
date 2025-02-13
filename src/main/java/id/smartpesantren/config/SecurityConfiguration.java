package id.smartpesantren.config;

import id.smartpesantren.repository.PersistentTokenRepository;
import id.smartpesantren.repository.PersistentTokensJPARepository;
import id.smartpesantren.repository.UserRepository;
import id.smartpesantren.security.AuthoritiesConstants;
import id.smartpesantren.security.PersistentTokenRememberMeServices;
import id.smartpesantren.security.jwt.JWTConfigurer;
import id.smartpesantren.security.jwt.TokenProvider;
import io.github.jhipster.config.JHipsterProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Import(SecurityProblemSupport.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final UserDetailsService userDetailsService;

    private final TokenProvider tokenProvider;

    private final CorsFilter corsFilter;

    private final SecurityProblemSupport problemSupport;

    private final PersistentTokenRepository persistentTokenRepository;
    private final JHipsterProperties jHipsterProperties;
    private final UserRepository userRepository;


    public SecurityConfiguration(AuthenticationManagerBuilder authenticationManagerBuilder, UserDetailsService userDetailsService, TokenProvider tokenProvider, CorsFilter corsFilter, SecurityProblemSupport problemSupport, PersistentTokenRepository persistentTokenRepository, JHipsterProperties jHipsterProperties, UserRepository userRepository) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userDetailsService = userDetailsService;
        this.tokenProvider = tokenProvider;
        this.corsFilter = corsFilter;
        this.problemSupport = problemSupport;
        this.persistentTokenRepository = persistentTokenRepository;
        this.jHipsterProperties = jHipsterProperties;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        try {
            authenticationManagerBuilder
                    .userDetailsService(userDetailsService)
                    .passwordEncoder(passwordEncoder());
        } catch (Exception e) {
            throw new BeanInitializationException("Security configuration failed", e);
        }
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers("/app/**/*.{js,html}")
                .antMatchers("/i18n/**")
                .antMatchers("/content/**")
                .antMatchers("/h2-console/**")
                .antMatchers("/swagger-ui/index.html")
                .antMatchers("/test/**");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(problemSupport)
                .accessDeniedHandler(problemSupport)
                .and()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/register").permitAll()
                .antMatchers("/api/api/master/kota").permitAll()
                .antMatchers("/api/activate").permitAll()
                .antMatchers("/api/authenticate").permitAll()
                .antMatchers("/api/account/reset-password/init").permitAll()
                .antMatchers("/api/account/reset-password/finish").permitAll()
                .antMatchers("/api/academic-year/all").permitAll()
                .antMatchers("/api/**").authenticated()
                .antMatchers("/management/health").permitAll()
                .antMatchers("/management/info").permitAll()
                .antMatchers("/management/**").hasAuthority(AuthoritiesConstants.SUPERADMIN)
                .and()
                .rememberMe()
                .rememberMeParameter("remember-me")
                .rememberMeServices(rememberMeServices())
                .key(jHipsterProperties.getSecurity().getRememberMe().getKey())
                .tokenValiditySeconds(86400)
                .and()
                .apply(securityConfigurerAdapter());

    }

    @Bean
    public PersistentTokenRememberMeServices rememberMeServices() {
        log.info("Initializing PersistentTokenRememberMeServices...");
        if (jHipsterProperties == null) {
            log.error("jHipsterProperties is null");
        }
        if (userDetailsService == null) {
            log.error("userDetailsService is null");
        }
        if (persistentTokenRepository == null) {
            log.error("persistentTokenRepository is null");
        }
        if (userRepository == null) {
            log.error("userRepository is null");
        }

        PersistentTokenRememberMeServices services = new PersistentTokenRememberMeServices(
                jHipsterProperties,
                userDetailsService,
                persistentTokenRepository,
                userRepository
        );
        log.info("PersistentTokenRememberMeServices created successfully.");
        return services;
    }

    private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(tokenProvider);
    }
}
