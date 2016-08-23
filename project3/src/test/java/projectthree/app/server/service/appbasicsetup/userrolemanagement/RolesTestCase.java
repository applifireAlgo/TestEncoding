package projectthree.app.server.service.appbasicsetup.userrolemanagement;
import projectthree.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projectthree.app.server.repository.appbasicsetup.userrolemanagement.RolesRepository;
import projectthree.app.shared.appbasicsetup.userrolemanagement.Roles;
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
import projectthree.app.shared.appbasicsetup.userrolemanagement.RoleMenuBridge;
import projectthree.app.shared.appbasicsetup.userrolemanagement.AppMenus;
import projectthree.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { projectthree.app.config.JPAConfig.class, projectthree.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class RolesTestCase extends EntityTestCriteria {

    /**
     * RolesRepository Variable
     */
    @Autowired
    private RolesRepository<Roles> rolesRepository;

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

    private Roles createRoles(Boolean isSave) throws Exception {
        Roles roles = new Roles();
        roles.setRoleDescription("ke3E2KTN2wFbq0HIQ3NVCM1foruSQPvXvAzSPKsE9SaWvVaA2n");
        roles.setRoleIcon("91fV9usXdsEcL7LWdiPBwdRGRNFkHxVMvUBNyVwxKWjCOOVqSf");
        roles.setRoleName("wmblZO77xn69mLasgX2ErldP4o5GU1UCeZKwIsHyleZaNRbvaX");
        roles.setRoleHelp("ZnsWHFpMrLDg0jJG9v9C8bSazgjEdOZiq6qMrKTuaX8phlwgeV");
        java.util.List<RoleMenuBridge> listOfRoleMenuBridge = new java.util.ArrayList<RoleMenuBridge>();
        RoleMenuBridge rolemenubridge = new RoleMenuBridge();
        rolemenubridge.setIsExecute(true);
        rolemenubridge.setIsRead(true);
        AppMenus appmenus = new AppMenus();
        appmenus.setMenuCommands("Roa80uijLqe2i7851QfFhEqYCt6ud2qOpoeYZ8GLVy56DbHSgb");
        appmenus.setAppId("aNs5DI4XFPgUxw431ZDma6WRZfCd9v8NvGCIQwnN6CXI3KYR3M");
        appmenus.setUiType("DZ8");
        appmenus.setExpiryDate(new java.sql.Timestamp(1471935878567l));
        appmenus.setGoLiveDate(new java.sql.Timestamp(1471935878567l));
        appmenus.setMenuDisplay(true);
        appmenus.setAutoSave(true);
        appmenus.setMenuLabel("LNKkV8wOalsziEN0COZHySrgpwHZUA1IsDjhqHO8ZO33uC6oFP");
        appmenus.setMenuIcon("k5MYdHJH6Ks8iT2xmVl2f4uh1j0AiYb3RstftMYuqIktfVrCeC");
        appmenus.setStartDate(new java.sql.Timestamp(1471935878568l));
        appmenus.setMenuHead(true);
        appmenus.setRefObjectId("3taOooamuYeep4USuZM7Q6jOmbhqKTW39esKxEvadEQIBm6kaA");
        appmenus.setMenuAction("gDYt0N9iqxbYaFTDbFKiNiRtmw6XgBaub2OcepuxKYdsMiS9FN");
        appmenus.setMenuTreeId("vWSP7LsrhFcW1CFShc9V1i83pcVXVWMP9g13dk1AoPaay43r1c");
        appmenus.setAppType(2);
        appmenus.setMenuAccessRights(9);
        AppMenus AppMenusTest = new AppMenus();
        if (isSave) {
            AppMenusTest = appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        }
        rolemenubridge.setIsExecute(true);
        rolemenubridge.setIsRead(true);
        rolemenubridge.setMenuId((java.lang.String) AppMenusTest._getPrimarykey());
        rolemenubridge.setRoles(roles);
        rolemenubridge.setIsWrite(true);
        listOfRoleMenuBridge.add(rolemenubridge);
        roles.addAllRoleMenuBridge(listOfRoleMenuBridge);
        roles.setEntityValidator(entityValidator);
        return roles;
    }

    @Test
    public void test1Save() {
        try {
            Roles roles = createRoles(true);
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            roles.isValid();
            rolesRepository.save(roles);
            map.put("RolesPrimaryKey", roles._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("RolesPrimaryKey"));
            Roles roles = rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
            roles.setRoleDescription("wowf4ulVGRT9hL4aYTzoGmJLz5MidcvBmNqOx31yRsk67XDddS");
            roles.setRoleIcon("Y5XL2soWKUTsJ3JAGbt7LxpBEd4lZ4etaCoRjzTF1gQw3NBOVN");
            roles.setRoleName("IBfBFHqZUPY7LCDvWymAVTob5s74HwFYzvL57UwoU0iA8MQeFB");
            roles.setRoleHelp("xkoXxpFW7foqNeVFrEX12UblJ2wKuHbVosy7nGbsbZKzXpsjwJ");
            roles.setVersionId(1);
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            rolesRepository.update(roles);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.delete((java.lang.String) map.get("RolesPrimaryKey")); /* Deleting refrenced data */
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateRoles(EntityTestCriteria contraints, Roles roles) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            roles.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            roles.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            roles.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            rolesRepository.save(roles);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "RoleName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "roleName", "sJgDZ8DpX4acHvBF1T6iz5bya64xoDTaxBavisIN0bzJYZTsP5SFzanlXrmLgAMCk1t22qgLM4VMablMGtErfeBHlF2FWMAQaKdiiDhJ9xRoTF1Cx0q7vXF7O3btpTSJunMsXnbyahrH0ImqzTq50rZqbnmhlWoyeSDSPI1eQvEl1cAPxlnFOE7WvQabkb7DwGZgy9IuJBv3nLTMHsUV6qiGmlulZRDDbAjIUsIJ7oXTlejyVxULMeSinkrY088vu"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "RoleDescription", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "roleDescription", "CxgYHMVZnyHMjBUcbIAMZAKoy0Ysg6yD9QXBCcHmSPV94d6Bgvkrplq9im1ck7HhahzfrFvS4zB9rY3obiVZolMac4QCSBv4t7mvxddUqVHRvGo9ZvTJ4dZG9fSiu2vb5fAZD1TaBgnoVnvzpTiM1QlSwrvC6g7TbU1NttvGXNivsnzV5GI7uu1ZO7ADaXEAWU8afT4ejh6SmJoB3vK33PqQSvjcLzf0M9Ja9dkukf3WJ77ax0wJkvJ7sOw1UwkMi"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "roleIcon", "HKQNZ2Im6jRtYKp8WQHIkhNAMDcDYiFlSXZYVdx2nEX1cqww5knZwE0D45PQ9onZ3"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "roleHelp", "yoZrqGt5hFaHSpLTeRxlj5wQw3r8ZrJ7M0KF8yJj6azZ8JwNVTh57HAueJo56PKnxKSSfnx0NYAh7dwxhtf2wscjbN67ESiTTFyc8xgGEzPfQv8bavLHvwI6vVn7yHGgWc7yW9Ya3cgICYBC8mQcy2qQzOXJwYlcGORYTF28nDzbA0btB4iQoHPg9X5ufhRQZY06Ie7J63NYSgO5ExVFt1OnbebrGtz6O2YhpNSlmehBZX1HvM46hVe6bxVcsbm7p"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Roles roles = createRoles(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = roles.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 2:
                        roles.setRoleName(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 4:
                        roles.setRoleDescription(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 5:
                        roles.setRoleIcon(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 6:
                        roles.setRoleHelp(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
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
