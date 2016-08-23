package projectthree.app.server.service.appbasicsetup.usermanagement;
import projectthree.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projectthree.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import projectthree.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import projectthree.app.server.service.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.springframework.mock.web.MockServletContext;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import org.springframework.web.context.request.RequestContextHolder;
import com.spartan.pluggable.logger.event.RequestHeaderBean;
import com.spartan.pluggable.logger.api.RuntimeLogUserInfoBean;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.junit.Assert;
import com.athena.server.pluggable.interfaces.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { projectthree.app.config.JPAConfig.class, projectthree.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class UserAccessDomainTestCase extends EntityTestCriteria {

    /**
     * UserAccessDomainRepository Variable
     */
    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    /**
     * RuntimeLogInfoHelper Variable
     */
    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    /**
     * EntityValidator Variable
     */
    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    /**
     * RandomValueGenerator Variable
     */
    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    /**
     * List of EntityCriteria for negative Testing
     */
    private static List<EntityTestCriteria> entityConstraint;

    /**
     *  Variable to calculate health status
     */
    @Autowired
    private ArtMethodCallStack methodCallStack;

    /**
     * MockHttpSession Variable
     */
    protected MockHttpSession session;

    /**
     * MockHttpServletRequest Variable
     */
    protected MockHttpServletRequest request;

    /**
     * MockHttpServletResponse Variable
     */
    protected MockHttpServletResponse response;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        final MockServletContext mockServletContext = new MockServletContext("file:src/main/webapp");
        try {
            final String _path = mockServletContext.getRealPath("/WEB-INF/conf/");
            LogManagerFactory.createLogManager(_path, AppLoggerConstant.LOGGER_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).requestCompleted();
        RequestContextHolder.resetRequestAttributes();
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo("customer", "AAAAA", request.getRemoteHost());
        Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityConstraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private UserAccessDomain createUserAccessDomain(Boolean isSave) throws Exception {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainHelp("H70vMNCRfHxGBzn43bxw76Vu7B9pNJdFM5Ao9OXjssm1xHEGMf");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainName("W8dn73GxS4IvRjn9sjbVDZfaNOT2b5fBCp1OlNQUsJnMlkHkWh");
        useraccessdomain.setDomainIcon("BCFF4GU1ruv5Rov51Tq3WHIwS9rO4dXjqLPYaqLF9MzuL5xKf2");
        useraccessdomain.setDomainDescription("Xbd8pK06NXOJ9wXYsiHk6fijdkpDTWxpDUNaTqLJ8s1YFJOvbo");
        useraccessdomain.setEntityValidator(entityValidator);
        return useraccessdomain;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessDomain useraccessdomain = createUserAccessDomain(true);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccessdomain.isValid();
            useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            UserAccessDomain useraccessdomain = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            useraccessdomain.setDomainHelp("zx21IRVOY95j88I8V9L99eJiKxWtNgrj3unphicrJ6Nuw5VQVa");
            useraccessdomain.setVersionId(1);
            useraccessdomain.setUserAccessDomain(15529);
            useraccessdomain.setDomainName("X0KpC9ISqX6GvdE4NS4Ma2G1Zpdt7zGBn7YuHas8p0v8f5A0wG");
            useraccessdomain.setDomainIcon("ppRQQpqQS1tF1dPoiJbaSvgffMKTmSWQQbB9IdCwPT0W4L6fxK");
            useraccessdomain.setDomainDescription("yRZMNQaJCDVknYUk170YcyDGRPfGGY1FBduaqaIgPUOLKijnLE");
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccessdomainRepository.update(useraccessdomain);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessDomain(EntityTestCriteria contraints, UserAccessDomain useraccessdomain) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccessdomainRepository.save(useraccessdomain);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessDomain", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 133199));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "pSpuYdabQ0u46mlPOAfdubx7uT53Vr6vOVcSgWIovbOXwEOwZDitgdSZnCOD0sJd3YUZ9E05MMQqYlNkzSLPNG71PNI39PjnvDvJxhKMXetFwujhGGZcvEoEP4AGwKePD52Mr1xMMeMoqfvEk2e2sD5pc4eIf25bJDrFvYliHvx6zrMCrLkp5NO7TQFFhpmOo3wFUwFOq5BDnWHqPQ0iyM0WVTvpqSlXfkXEI9FK7Gt0S5yaHMj9f2VTOtEVM5gf3"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "MFqvH9hijT68tWg3rtMyrwtJspmuOrQY2MoUE4mG92gIEzx7UlWbn26LfOMSvp2ht6mRQavPzk7IypCoZxUPukSkDajkomeHaBf67g9ltGaeh0i1fVINgZIyrwVZZTBs23Ntn0RrqILT7LbVQNSwhxjUcXBFO13jeHIHUSzKNOyRIP5vH0bCH3SYASgWL9QRVyXgnxB1lXT4GfbrWOrBV1hkNJI3EGDqJu1sojIGuD4P0Kyz530xzjKKqPRgNCTPF"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "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"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "re1Eiqfr7FxnYUBYjuspWj2nBgzo3cgLno0ysL6dgi6Xf08VctusAoOj1c4so11bUYpFD1E3YsIKMpoqyekSuuegiHxcCjQpRJMYxGi1LbA723sHApIzfNt42lnQRweST1ithcHatn0zzjLqI8eEDAkqccG8bE3aQOc5Eea6NU5xWGqhKjyGqcOwkHJAZGUNDDyDuFAuUnPcEbiAd9mteghs4s9FSH6YAAsSFaCdMmNi5IP1bc2pvQlIZ1Kgaks4P"));
        entityConstraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessDomain useraccessdomainUnique = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                UserAccessDomain useraccessdomain = createUserAccessDomain(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccessdomain.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 2:
                        useraccessdomain.setUserAccessDomain(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 4:
                        useraccessdomain.setDomainName(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 6:
                        useraccessdomain.setDomainDescription(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 7:
                        useraccessdomain.setDomainHelp(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 8:
                        useraccessdomain.setDomainIcon(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 9:
                        useraccessdomain.setUserAccessDomain(useraccessdomainUnique.getUserAccessDomain());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            Assert.fail();
        }
    }
}
