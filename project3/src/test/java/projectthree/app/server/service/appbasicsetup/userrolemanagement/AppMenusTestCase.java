package projectthree.app.server.service.appbasicsetup.userrolemanagement;
import projectthree.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projectthree.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;
import projectthree.app.shared.appbasicsetup.userrolemanagement.AppMenus;
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
public class AppMenusTestCase extends EntityTestCriteria {

    /**
     * AppMenusRepository Variable
     */
    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

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

    private AppMenus createAppMenus(Boolean isSave) throws Exception {
        AppMenus appmenus = new AppMenus();
        appmenus.setMenuCommands("HSRVfMeKD3Xg2AkhCiydGrFre2FC2ficlGXUUGbn89cEUEAZON");
        appmenus.setAppId("xCRHTT5IOml7MDmwQwHEVIYls8nOkCnHk5glCwk9GAudp21ubY");
        appmenus.setUiType("0Tc");
        appmenus.setExpiryDate(new java.sql.Timestamp(1471935879539l));
        appmenus.setGoLiveDate(new java.sql.Timestamp(1471935879539l));
        appmenus.setMenuDisplay(true);
        appmenus.setAutoSave(true);
        appmenus.setMenuLabel("pmI5lfRhM2SLjtZ3jdPr6cYL1BywazQIgoWVmfBSHrKJC1Ya8g");
        appmenus.setMenuIcon("teSXFtEFiB4yMYTFqht1lrAQ3lAP6iGEt4uOLwCgEi7VQYKwea");
        appmenus.setStartDate(new java.sql.Timestamp(1471935879539l));
        appmenus.setMenuHead(true);
        appmenus.setRefObjectId("kcEVAwJxMFbOUTvHOnAWrwyu58OFsA0QkKPAkRrZ7tLQpnQNQy");
        appmenus.setMenuAction("VTTe1o6Dn2nZvxSjZdoR9qBuKnLJtqNZ5GoQLW7GgTVtvefdtG");
        appmenus.setMenuTreeId("fznI1BpAg3WVlQJMZ1YgUxuuVDuDsUFME2JJ8osbpAofDFCdvL");
        appmenus.setAppType(1);
        appmenus.setMenuAccessRights(6);
        appmenus.setEntityValidator(entityValidator);
        return appmenus;
    }

    @Test
    public void test1Save() {
        try {
            AppMenus appmenus = createAppMenus(true);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            appmenus.isValid();
            appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            AppMenus appmenus = appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
            appmenus.setMenuCommands("crDu4Gi0IiyQEoxNNKlvGkYCNeLaNgPl96NpmoMsnNmBDwxHnm");
            appmenus.setAppId("mEo6dCfkpHguvDaBQZn40tJC4TWXhIar3kqg8g9Svawjmex0sS");
            appmenus.setUiType("KVF");
            appmenus.setExpiryDate(new java.sql.Timestamp(1471935879563l));
            appmenus.setGoLiveDate(new java.sql.Timestamp(1471935879563l));
            appmenus.setMenuLabel("oMZScHl7Oohux901pB6JjgdPMStQnKTGmh8fRXiGatrhQzEc8S");
            appmenus.setMenuIcon("gSqSzsQlrcKqJCkMqd7cfLSHpDnenBgxG9Ya0cB83E0O0NqHqH");
            appmenus.setStartDate(new java.sql.Timestamp(1471935879566l));
            appmenus.setVersionId(1);
            appmenus.setRefObjectId("2jnCYekXr92gcrak5HOcw7lufZOP1uhfnLrGdlf5UFLXBh6VgH");
            appmenus.setMenuAction("4hQ9pX5NA3lRU5VSB6RUizYuXaCD8aplgriker4omoHJIkkHl4");
            appmenus.setMenuTreeId("thVdTqTlyyqBfsB0tYYyWeonNCVszbRtCGuhxT1WeSwD6W4ov5");
            appmenus.setAppType(2);
            appmenus.setMenuAccessRights(4);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appmenusRepository.update(appmenus);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateAppMenus(EntityTestCriteria contraints, AppMenus appmenus) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            appmenusRepository.save(appmenus);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "menuTreeId", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "j3xzthE6Mn65NGpt22KHBZzrOONevuVBQijosOZ7bNT0dIJ4LSsEiKaPqhB2Iy9D6vs3EhlJAGohmzhwz35hn4VDv66ACUA5LNYF3QX5slthyc9PQUpcENTBVxqEiBqtvKBNRyGvbJqioAvXKtAsgPQpcThgO8ULtA7rr2DP0TPxfbZxXjGFIbPPNmjrlkqzOC2WOk7jt4gV3GXILCB9kC87fSInBLH4mEsUB4vN8B1w6qEGxeQIQ0kIm96TdEB8L"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "UWPmmeKCX67Fz5KMULGnwCxgtuQ60TYMFuZhOTgO5RE947i37lFKB18RnV3E12Trb1yle6LYL0mdao0HWqkVuYgCmVvjsLqSKNZlO8VKR8zr1H1TadcFOSuAEBuSW5qGg5cvev0FqlHf2ofpGcNHxuUPZMTy2tPXDbuZyEY5QgzCMad2V45RS9PdZIZPM68RZ9EaOVsBfV3Fkt6WtOxd9O2LuUng5XDoSnlNQrY32JGOwSaV82zQLCLg5eAIzZtyX"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "tWCWpwjI48jPFbe9sEfhOE4kS0N50s7FbrCsimkMpfAHcWjGetmjKrjfaoHS0DEnXB0ZcoXujYX1czToEGwQfKDVOpP0QDT9VResN1qHe5KGe0xdpSptT8Ni4ggRuo4tZsCFhBzdcxwMbJnXurL5rDoLGy8ZyyOHnhv9iif75Mm78AjBHX6m3TQKChMcaR5JjyyoRoUzxLwTL5f1bIcDy6RBQmhyFl3gQGLhbJhZ41UmeNUpPgNVuNuNm8X1jRCo6"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "o1rhMIMwKbcGoT7PWbEJGKSKCW9MNovQ7l9uzXEqn47aIqJweozCEiMGtZ0fd7IYC"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "JDphV1jNeNJTFaO5IRi83PgslA3DM1ZTRKcVqt9l9uTH0ZGKEiee49mSKNTQVXbx072QHJVSzvDd0mMyapnkHVZdJtC6OSsKF2FJrYVUTiNtKvbox3AlY6nsOJbH7w04miwmMzyqun0Xgmp6wea9oFz2UxF9Fe1pyAf0w6oWw7cfKzmxbRVMGGQ12Je8BKCd5jNNjsCGAR8X3bGSQZXIv3qQoqO9JaOFm11uS0E5sDt8cOvkIEJBGCcAqnMPESkX6"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "UWjY"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "oUEikIk5uRQ2jYCmSdy7OcyuWhwFxl5rYA3uHMIaEsO0NJl9eBnE8y5vHnxXy3fhiSXCzw1NgW1BVSDRjyArO3FSOqgJ3LN4gmFYt5ulBAMIl0P7h6WX1l2RuCSyS0Br7oK9lo6tLgyX5WamM1NcWjXbtxLmIhn55Mpa3yVvSzmANBJ22SU1xohbTf3CyJFSjm18eo693VSyYLOnAKuzaZMyKfVitJCoVK4FhVP0Xn1EEA8sixwc61s1F7k5V3KkL"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 13));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 3));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "8hFLB0JJdcu99T9Yk3WoHpxLeiQpQHH6DFzN9uncgk7wrVWnOnADQxgXVAR4enn0Zhx3ftndFmqk1ryrjVDc7Nk5UhVkMVoN32EPiyGjSi76OHOYswt7j7H1VYC8afAPwYz6Mmwo8N8jaOy714zXDfjbewEvLiqB2kcoAwgYYb6ZpTwqcN21A5kwyu5M6jXb4vinLLA4400nqQf5CYOHnvyQwY5dSddt9IJBYbeWc6k3EpvbblrZLsoNFN8cGdqmj"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 18, "autoSave", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 19, "autoSave", true));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                AppMenus appmenus = createAppMenus(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = appmenus.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 2:
                        appmenus.setMenuTreeId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 3:
                        appmenus.setMenuIcon(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 4:
                        appmenus.setMenuAction(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 5:
                        appmenus.setMenuCommands(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 7:
                        break;
                    case 8:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 9:
                        break;
                    case 10:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 11:
                        appmenus.setMenuLabel(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 12:
                        appmenus.setUiType(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 13:
                        appmenus.setRefObjectId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 14:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 15:
                        appmenus.setMenuAccessRights(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 16:
                        appmenus.setAppType(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 17:
                        appmenus.setAppId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 18:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 19:
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
