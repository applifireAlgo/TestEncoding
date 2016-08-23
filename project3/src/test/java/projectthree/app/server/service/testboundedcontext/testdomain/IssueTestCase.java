package projectthree.app.server.service.testboundedcontext.testdomain;
import projectthree.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projectthree.app.server.repository.testboundedcontext.testdomain.IssueRepository;
import projectthree.app.shared.testboundedcontext.testdomain.Issue;
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
public class IssueTestCase extends EntityTestCriteria {

    /**
     * IssueRepository Variable
     */
    @Autowired
    private IssueRepository<Issue> issueRepository;

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

    private Issue createIssue(Boolean isSave) throws Exception {
        Issue issue = new Issue();
        issue.setIssueName("420zEX93UDDE9wYN07suEE9NPoCtZNqvtxuvQmLHp6hXYmBbHN");
        issue.setComponent("xB643xiAOZsKlv0TPz246MBL85HTvR26luwvDObQLyZqIb1Jdm");
        issue.setEntityValidator(entityValidator);
        return issue;
    }

    @Test
    public void test1Save() {
        try {
            Issue issue = createIssue(true);
            issue.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            issue.isValid();
            issueRepository.save(issue);
            map.put("IssuePrimaryKey", issue._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("IssuePrimaryKey"));
            Issue issue = issueRepository.findById((java.lang.String) map.get("IssuePrimaryKey"));
            issue.setVersionId(1);
            issue.setIssueName("3iPH8KWYSB3qHrvlL1ooMMjnEDQf7cSVDWvkqUISjQF5Nm6wrF");
            issue.setComponent("kf10B2NXlr80LVZ7CTHjk5HYdzUMftuXDlDQc69ItfFvx0uWo0");
            issue.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            issueRepository.update(issue);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("IssuePrimaryKey"));
            issueRepository.findById((java.lang.String) map.get("IssuePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("IssuePrimaryKey"));
            issueRepository.delete((java.lang.String) map.get("IssuePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateIssue(EntityTestCriteria contraints, Issue issue) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            issue.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            issue.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            issue.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            issueRepository.save(issue);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "issueName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "issueName", "6FAX2jeXnEoW7dVa8Hy8Jz35GU20xMpaBQesXIYawppxvkLxnVQSyGINgByo8DC4zxMgsxl5mYro8mOB0htHn8Oq1pqluOazo2uHwukPX1SN8sKXGg5miTfhHB18Kzf1R2oS79mpX5FwU2pF3kqxFrwqY7OkVX88wq0BESNm0Rpvzwbho0xHdPTdeOnRXpZO3t2OgnRYYF2eyix90XLvz5W43rYIS1GWA4ZqEF9BdWdcbxUq7akoe7WClFuzRQMkL"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "component", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "component", "oxYfGXszGqHlrUXtWgFahUTUZULLD3suXBZGko7mKoZGH3BG2wUer6RmA2dx8kNt6cV0FUrnKVlrAUQafPfMr4hUtpnAfYd6UM5ipx2852Q4108V3Lh7FBt6ML3gaWO7hJe3XGQY2uA6IhbLT1xDqdpVU0HJvPMqkqYy3ydhJvig85isUqE9M0DZauvA7Awzr6ccBVX8tC9r9nBmlqxihZ0HuH7OqpvEu6XNVNfT3JqbJhQMNXgXG32H7fTmoEm70"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Issue issue = createIssue(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = issue.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(issue, null);
                        validateIssue(contraints, issue);
                        failureCount++;
                        break;
                    case 2:
                        issue.setIssueName(contraints.getNegativeValue().toString());
                        validateIssue(contraints, issue);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(issue, null);
                        validateIssue(contraints, issue);
                        failureCount++;
                        break;
                    case 4:
                        issue.setComponent(contraints.getNegativeValue().toString());
                        validateIssue(contraints, issue);
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
