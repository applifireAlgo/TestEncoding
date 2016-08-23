package projectthree.app.server.service.testboundedcontext.testdomain;
import projectthree.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projectthree.app.server.repository.testboundedcontext.testdomain.BugRepository;
import projectthree.app.shared.testboundedcontext.testdomain.Bug;
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
public class BugTestCase extends EntityTestCriteria {

    /**
     * BugRepository Variable
     */
    @Autowired
    private BugRepository<Bug> bugRepository;

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

    private Bug createBug(Boolean isSave) throws Exception {
        Bug bug = new Bug();
        bug.setBugName("o06fJ8Nao7347VKxvazR4yF8crjUtd2lcAsXvvYJ0rIJGHBMme");
        bug.setBugComponent("r7jgH5q7bJlDEOiUS66RU5er64m5n2nMU3fjZT3MspONft0IeA");
        bug.setBugPriority("MJ1ULlHmG74YFZw6qOadioIuk5xLrQQ9ng4Fq6F7T9Cp2QSmu1");
        bug.setEntityValidator(entityValidator);
        return bug;
    }

    @Test
    public void test1Save() {
        try {
            Bug bug = createBug(true);
            bug.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            bug.isValid();
            bugRepository.save(bug);
            map.put("BugPrimaryKey", bug._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("BugPrimaryKey"));
            Bug bug = bugRepository.findById((java.lang.String) map.get("BugPrimaryKey"));
            bug.setBugName("GyevnI8xvrPoLdvPCFZq6h3aQF5ggWvdOAsvGzejRkgQv0aNam");
            bug.setBugComponent("lgNU38VYadSRRbK3reoLSlhBbvUkCPyJyqjXysPWaEtXOCdGpm");
            bug.setVersionId(1);
            bug.setBugPriority("njusPCdd6cT1S9FZc62VUKJP0cYRazCIJ46G15TwrIh4oRC4T5");
            bug.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            bugRepository.update(bug);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("BugPrimaryKey"));
            bugRepository.findById((java.lang.String) map.get("BugPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("BugPrimaryKey"));
            bugRepository.delete((java.lang.String) map.get("BugPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateBug(EntityTestCriteria contraints, Bug bug) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            bug.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            bug.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            bug.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            bugRepository.save(bug);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "bugName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "bugName", "NK1Umc1QfvzGOiKVcmhZSN7Gy7vlIZItDaDgRYgX6ZlFj0PZa1jvfDLzCZePj6Jq6zW9tLYoJZtPOd1xw0Z854rCDfo9esBp92dyWgT1fKzFwJYm0ex3MlxmeMjmrTmE9UNiPIZRERNEWZf94pwGV2DVVifqvHjj4LcCFvhcwmgEoRYCqCWBMx0UtU2PnwwkvI6k95UwSWq4cXfHHU0v0zJQdDG6o0v7DZ48sPiKcREb9jt9PolNdGiZJBjo5dvLC"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "bugComponent", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "bugComponent", "9tBhzHwgoBv7mcO7F9okZQeyGA6XX161SUPlHO407TBBc365VQxdbxCrT4xBlzFyWvw7nhYRqzQe0cMGv5WH0Z6eOhNnxxyoYUMAXJxQE13GQNNCK1AQcHUFGDHMPyrxmEGq9utVVKM9ITA9bG0rMqocA5LWOYbozsTBmeKeH7Z4jx1rhJOVYAFRYmysbg0rDu2kqIzGBU4qfTV7aNNFklBa9rJLcIOA4glBL1QSINGsy34EpySU0YUX28SKcmNRS"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "bugPriority", "DdltXpaw3LvU2TofMrbkpKj4TUz5FGQSsrDimwc7Dj81IkzxgEL8WrbiN6HhkJ76IbJ9xuyGNX4p9fecTwtVWnp3JV7DD68DjYq4MEBDEsFQiH60JXc7Qcy49NChKeokrxQr6dpi1lTtESnkd02UOXcJlxBdANlgziKG3JfWwCjQTkaHXDCwCNh382RLv3s5QelvVSn7he1DLP2A7y8zY3Q9bMv9xlrysEgkze0onqSoJIzuTSt0fqPwScsNRRMEU"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Bug bug = createBug(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = bug.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(bug, null);
                        validateBug(contraints, bug);
                        failureCount++;
                        break;
                    case 2:
                        bug.setBugName(contraints.getNegativeValue().toString());
                        validateBug(contraints, bug);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(bug, null);
                        validateBug(contraints, bug);
                        failureCount++;
                        break;
                    case 4:
                        bug.setBugComponent(contraints.getNegativeValue().toString());
                        validateBug(contraints, bug);
                        failureCount++;
                        break;
                    case 5:
                        bug.setBugPriority(contraints.getNegativeValue().toString());
                        validateBug(contraints, bug);
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
