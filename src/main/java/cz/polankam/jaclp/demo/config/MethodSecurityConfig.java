package cz.polankam.jaclp.demo.config;

import cz.polankam.security.acl.AclPermissionEvaluator;
import cz.polankam.security.acl.JaclpSpringConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 * Enable and set method security, most importantly define custom behavior for
 * <code>hasPermission</code> authorization methods within authorize
 * annotations.
 */
@Configuration
@Import(JaclpSpringConfiguration.class)
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    private final AclPermissionEvaluator permissionEvaluator;

    @Autowired
    public MethodSecurityConfig(@Lazy AclPermissionEvaluator permissionEvaluator) {
        this.permissionEvaluator = permissionEvaluator;
    }

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        // set custom permission evaluator for hasPermission expressions
        DefaultMethodSecurityExpressionHandler handler = new DefaultMethodSecurityExpressionHandler();
        handler.setPermissionEvaluator(permissionEvaluator);
        return handler;
    }
}