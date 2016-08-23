package projectthree.app.server.service.testboundedcontext.testdomain;
import projectthree.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projectthree.app.server.repository.testboundedcontext.testdomain.EmployeeRepository;
import projectthree.app.shared.testboundedcontext.testdomain.Employee;
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
public class EmployeeTestCase extends EntityTestCriteria {

    /**
     * EmployeeRepository Variable
     */
    @Autowired
    private EmployeeRepository<Employee> employeeRepository;

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

    private Employee createEmployee(Boolean isSave) throws Exception {
        Employee employee = new Employee();
        employee.setEmpName("dSxG6lNosHuU7t4jglVjOq0jqZQCa4QQPAChzIo7HemKpLuup8");
        employee.setEmpSal(-1600.0d);
        employee.setEntityValidator(entityValidator);
        return employee;
    }

    @Test
    public void test1Save() {
        try {
            Employee employee = createEmployee(true);
            employee.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            employee.isValid();
            employeeRepository.save(employee);
            map.put("EmployeePrimaryKey", employee._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("EmployeePrimaryKey"));
            Employee employee = employeeRepository.findById((java.lang.String) map.get("EmployeePrimaryKey"));
            employee.setEmpName("SRVLGeHgpXZqgvS7YZLRbUZnVqBqovGDKNYrWqq8BGIbswmR0z");
            employee.setEmpSal(-7600.0d);
            employee.setVersionId(1);
            employee.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            employeeRepository.update(employee);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("EmployeePrimaryKey"));
            employeeRepository.findById((java.lang.String) map.get("EmployeePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("EmployeePrimaryKey"));
            employeeRepository.delete((java.lang.String) map.get("EmployeePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateEmployee(EntityTestCriteria contraints, Employee employee) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            employee.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            employee.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            employee.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            employeeRepository.save(employee);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "empName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "empName", "joOyVpRLacP0SQEAOR1cCFPWfH4M6g1xxUvTBxE1HiFmrnjLK8IUe7kuffuB233JoXW974jUhQW4qHgOkK1YOfGkcBasnQyWuiECZXxLNZPaH9DCw1MWOAOR71GBnspb2ykyLQa528HVQJcbnnfLwKYVOLX0xBbXzKyGtuUjbzk4bR20YN13m5jTqN3LufMP84Wyrsmm8cgkzD4i25jUoT4WeLyMUvJZ6nXzxCx8aAldGOOhFynFenbKnD1qfB7ey"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "empSal", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "empSal", 9.414129707394597E18d));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Employee employee = createEmployee(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = employee.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(employee, null);
                        validateEmployee(contraints, employee);
                        failureCount++;
                        break;
                    case 2:
                        employee.setEmpName(contraints.getNegativeValue().toString());
                        validateEmployee(contraints, employee);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(employee, null);
                        validateEmployee(contraints, employee);
                        failureCount++;
                        break;
                    case 4:
                        employee.setEmpSal(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateEmployee(contraints, employee);
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
