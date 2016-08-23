package projectthree.app.server.service.aspect.testboundedcontext;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import projectthree.app.server.businessservice.testboundedcontext.testdomain.CreateCountryService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Component
public class SampleAspect {

    @Autowired
    private CreateCountryService createcountryservice;

    @After("afterauthenticate()")
    public void afterAuthenticateServiceauthenticate(JoinPoint joinPoint) throws Throwable {
        createcountryservice.createCountryService();
    }

    @Pointcut("execution(* projectthree.app.server.service.appbasicsetup.aaa.AuthenticateService.authenticate(..))")
    public void afterauthenticate() {
    }
}
