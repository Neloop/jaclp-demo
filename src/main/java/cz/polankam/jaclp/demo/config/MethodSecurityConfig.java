package cz.polankam.jaclp.demo.config;

import cz.polankam.security.acl.AclPermissionEvaluator;
import cz.polankam.security.acl.AuthorizatorService;
import cz.polankam.security.acl.IPermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 * Enable and set method security, most importantly define custom behavior for
 * <code>hasPermission</code> authorization methods within authorize
 * annotations. Proxy target class property has to be allowed, otherwise
 * transactions on the permission evaluator will not work.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    private AclPermissionEvaluator permissionEvaluator;

    @Autowired
    public MethodSecurityConfig(IPermissionsService permissionsService) {
        permissionEvaluator = new AclPermissionEvaluator(permissionsService);
    }


    @Bean
    public AuthorizatorService authorizatorService() {
        return new AuthorizatorService(permissionEvaluator);
    }

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        // set custom permission evaluator for hasPermission expressions
        DefaultMethodSecurityExpressionHandler handler = new DefaultMethodSecurityExpressionHandler();
        handler.setPermissionEvaluator(permissionEvaluator);
        return handler;
    }
}