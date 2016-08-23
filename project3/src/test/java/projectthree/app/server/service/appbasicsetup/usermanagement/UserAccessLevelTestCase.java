package projectthree.app.server.service.appbasicsetup.usermanagement;
import projectthree.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projectthree.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import projectthree.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
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
public class UserAccessLevelTestCase extends EntityTestCriteria {

    /**
     * UserAccessLevelRepository Variable
     */
    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

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

    private UserAccessLevel createUserAccessLevel(Boolean isSave) throws Exception {
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelName("NEoJ4DNHf4eh7JTbwPoZBp8tQ8bmz3vytWphgrUFF3CBBnifrZ");
        useraccesslevel.setLevelDescription("FAKfZiUAxQUhJ2wIMCsHo0hoVdmfRa1i3wmC302s70mfYZEu8w");
        useraccesslevel.setLevelHelp("t8lFxbmYWiMM1cavZlqbp8SxR7ajaWjignvVGNDhfFxvttYIPo");
        useraccesslevel.setLevelIcon("hTNgLNechEfSir78N3eh9e6FiFFPF6SFQ9l3lKyV3A4k4zHcZp");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setEntityValidator(entityValidator);
        return useraccesslevel;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessLevel useraccesslevel = createUserAccessLevel(true);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccesslevel.isValid();
            useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            UserAccessLevel useraccesslevel = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            useraccesslevel.setLevelName("Tbb8CvSnC1I1cJgaelTBrpYG5IIg995YyiceYIA0sWFKLfX5u9");
            useraccesslevel.setVersionId(1);
            useraccesslevel.setLevelDescription("1nBYPR7Xxx7GvWxKlyQ9Mmf0pkbq7f3JSN5aMpbfCnECzTGkek");
            useraccesslevel.setLevelHelp("6wQ9v8hzkq3INaD3tgTl3DNWB6ZYVoe34Gg4V16rQOLmqKFPqF");
            useraccesslevel.setLevelIcon("dloNXynLQ6hLFC9vgDlB03J6iS1DTgEQO9xSfwSqsZqx5F92lw");
            useraccesslevel.setUserAccessLevel(19616);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccesslevelRepository.update(useraccesslevel);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessLevel(EntityTestCriteria contraints, UserAccessLevel useraccesslevel) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccesslevelRepository.save(useraccesslevel);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessLevel", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 137254));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "Vn8epNIOFZdoLcocfGAzCSx1ggMmdN9OA9OCzD5oCgKEkLff2etgLVVFSCymVfGYyREXvdlPjmLNOr9uIvSL49WJUfuGAfYWbjYEbEjRnzPF7pe1I9C6ZJXB39eBraVsWGIcMJAQnB9JkkalQnMQktsc3ifJafA1n0hcRp8aBoJhyZKu86nu4zHdrCi40vvu4GLEbNRFRISoX2KcZS4neKaC8RhZyBoPQqhSQijPvhhkA0EEj5wPROtpZQrTBAtIX"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "HUBlBIhYr3s6uGL08PgdAj9mjs3VjOoBJfY9HHbEvjX4ta98Y6HPHkS9p4DxjirKp1vAMQdz57Jz5rQXJyvouI8l0hdyLPp7fakXoDt2ueNDerS2xdSgAXGCjBmyJrnD7dxdiW4H4P3GpGgu44u84d1asR3Coyh3JJkQSp964f5FHSNVlbOrvF7QQN49ychZB9fputuMmvcrRZx36Rz0cBAaqGlvCtZXKqhdrmAXH7tv6aM6icTt4V9O8HnLucFhS"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "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"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "MrK4O3n14CPNjH5kjjgSEfxRGoHomqRQpPSr4bUjCNiPRshsw1hc9L6lGuOo9ihKvS0xETDQs2HrMhyROPISV2TrQWKAHssAMSA58BW4C9H9GLP2tImPRjYKtfwBubKSsvlZxLLVfjdZ72MAEfO1LKgjgLLBntg0sB4dym8QbthO4yl4cIZjBH2nMpQNFg5CRcY5qxhN6s2ygFszFS1aycQNmweyMhJXSZAlmZ1VuM9vFq7cSepbHhMls3w3moW46"));
        entityConstraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessLevel useraccesslevelUnique = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                UserAccessLevel useraccesslevel = createUserAccessLevel(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccesslevel.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 2:
                        useraccesslevel.setUserAccessLevel(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 4:
                        useraccesslevel.setLevelName(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 6:
                        useraccesslevel.setLevelDescription(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 7:
                        useraccesslevel.setLevelHelp(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 8:
                        useraccesslevel.setLevelIcon(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 9:
                        useraccesslevel.setUserAccessLevel(useraccesslevelUnique.getUserAccessLevel());
                        validateUserAccessLevel(contraints, useraccesslevel);
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
