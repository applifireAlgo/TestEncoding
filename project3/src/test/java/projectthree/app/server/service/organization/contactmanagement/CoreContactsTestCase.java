package projectthree.app.server.service.organization.contactmanagement;
import projectthree.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projectthree.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import projectthree.app.shared.organization.contactmanagement.CoreContacts;
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
import projectthree.app.shared.organization.contactmanagement.Title;
import projectthree.app.server.repository.organization.contactmanagement.TitleRepository;
import projectthree.app.shared.organization.locationmanagement.Language;
import projectthree.app.server.repository.organization.locationmanagement.LanguageRepository;
import projectthree.app.shared.organization.locationmanagement.Timezone;
import projectthree.app.server.repository.organization.locationmanagement.TimezoneRepository;
import projectthree.app.shared.organization.contactmanagement.Gender;
import projectthree.app.server.repository.organization.contactmanagement.GenderRepository;
import projectthree.app.shared.organization.contactmanagement.CommunicationData;
import projectthree.app.shared.organization.contactmanagement.CommunicationType;
import projectthree.app.server.repository.organization.contactmanagement.CommunicationTypeRepository;
import projectthree.app.shared.organization.contactmanagement.CommunicationGroup;
import projectthree.app.server.repository.organization.contactmanagement.CommunicationGroupRepository;
import projectthree.app.shared.organization.locationmanagement.Address;
import projectthree.app.server.repository.organization.locationmanagement.AddressRepository;
import projectthree.app.shared.organization.locationmanagement.City;
import projectthree.app.server.repository.organization.locationmanagement.CityRepository;
import projectthree.app.shared.organization.locationmanagement.Country;
import projectthree.app.server.repository.organization.locationmanagement.CountryRepository;
import projectthree.app.shared.organization.locationmanagement.State;
import projectthree.app.server.repository.organization.locationmanagement.StateRepository;
import projectthree.app.shared.organization.locationmanagement.AddressType;
import projectthree.app.server.repository.organization.locationmanagement.AddressTypeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { projectthree.app.config.JPAConfig.class, projectthree.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CoreContactsTestCase extends EntityTestCriteria {

    /**
     * CoreContactsRepository Variable
     */
    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    private CoreContacts createCoreContacts(Boolean isSave) throws Exception {
        Title title = new Title();
        title.setTitles("fflc9iGEmvozuGNl8SIaSd5iU7uOdjD0KVAhraDBnXXWcRxE9n");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Language language = new Language();
        language.setLanguageType("3jX2XVlCtezOFOPiBbSqJpCE99QRNQ2B");
        language.setLanguageDescription("FU8AtQvUglTNzJAUK9SGRpE2pFabLo6oJNHrFEVxgqEUbizFcG");
        language.setAlpha4parentid(9);
        language.setAlpha3("J3l");
        language.setAlpha4("kPoO");
        language.setLanguageIcon("P3QVubima81odeY7X0l6Vgpjw3r9ut4E1dgKk4xaDzO9ft45Wm");
        language.setAlpha2("KN");
        language.setLanguage("NmfoEkuSbxZOkLPbDccwtVmzylgj82xpVcsEATCQj8z8UTarqr");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setGmtLabel("tghKnbiIxZzK94FT91T8vkyEKhzvixS4lUnMZedJuPgz77Fifh");
        timezone.setTimeZoneLabel("TqBiGqiyc0GOrfInFCYt1TGXlhKTQlbjsFV2KbQemQRqFbNRee");
        timezone.setUtcdifference(6);
        timezone.setCountry("ViCQuBSazWF2W9Fd5mnWc2BBf6jL63oKeQJKZQ1J6xK4cBKbA1");
        timezone.setCities("JgSK5CEbVgtEJwsYgmgge6TiqGmjmo2H6jPpCqqGM5dMKx5G4u");
        Gender gender = new Gender();
        gender.setGender("RMz7yagWv2XlgaumJSDxSZL30hfZr4YnWfdCIHxZ7frq2uhIPY");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeLastName("hD7MfwpcnLVEaIfT8tohBa2qRBkCXdq1jmZewRL1BWJaS39TVN");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1471935864853l));
        corecontacts.setFirstName("JRjbLEdO9KS0dhdSPAeB1Otp9DdUIbbL6qJkYtUvRUswI1WCeK");
        corecontacts.setNativeMiddleName("wDIBqV0ew9u2RakHKLAgHm2PPcLirKkLCHqSdv3afrCEe12zY1");
        corecontacts.setNativeTitle("HE3BXovsUIUU4e9BRfl9yySwAQgOyN0ehRoqO8GWMqut9uaBR6");
        corecontacts.setEmailId("pkaVH6Ymr9xzDq0mgMHJ4rc4QsBCy1nSeyconYMDgvQsW3zQLX");
        corecontacts.setNativeFirstName("f4NfGbLiTnM1pNQZkkmlzp1VZGHM6JaKJEO0I1llIU8pPyiglA");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setMiddleName("R1qHObLnOz274pqXWRbcIiX0BHw8mzw4zLgjvdoU9zYrcil3Qw");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setAge(9);
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1471935864995l));
        corecontacts.setLastName("MbJHJU0IiQpD7mcrrwTCDa159PTPqqvt1pHqRqpar2pt4skPjO");
        corecontacts.setPhoneNumber("yyPvBuRDYrUqkV2xzpMd");
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("OHeEqWu4JVTH6Z7Yi7tEMyusZTOMMvIedQzp3QobxB3hrY7xkf");
        communicationtype.setCommTypeDescription("lY3hj8fcNXQQ2eqZakqO62W2sTNOG3d5x1NFaUDk7Oao3TSxfS");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("tROs8EPLfLpZ7H2GYbfuzyVWoCeQx0u14zOfSh97hgwa7pkd9t");
        communicationgroup.setCommGroupDescription("669XivMQjtSERZLtv9DdjT36Gl050nodqGyyAFZCKVv1DfLa5J");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        communicationtype.setCommTypeName("kq9XQkKoL2HgaFVS58oz6Z2pbDqstq6WuO3HMjWTOYjkk5LAkn");
        communicationtype.setCommTypeDescription("WZPDE93psjhgAsg4F2XdkDGN0JhNmJ8TXATSBAsFURxs4xiDzg");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommData("YD4nEL1lqP");
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddressLabel("NHX76L7kFQU");
        address.setZipcode("6744yO");
        address.setAddress3("JYKdMnfnVKuqSWIwvzrdNWIauxD3AgsuZqrTg5mopztLSs2Znv");
        City city = new City();
        city.setCityCodeChar2("UmG3k0DXlYwikqEWUWX09IaFpSvMELiS");
        Country country = new Country();
        country.setCountryCode1("bQD");
        country.setCapitalLongitude(1);
        country.setCapitalLatitude(7);
        country.setIsoNumeric(321);
        country.setCurrencyCode("DLc");
        country.setCountryFlag("jK3uLNdjw6kXJtB8dpjD6JdWKSqW1g1QhLuopd8VdhnCqUt2Kf");
        country.setCapital("1Ne9wZooHM5Kcp6Pm0bUf47VMtt3Nc4d");
        country.setCountryName("ffVLIGygNlFkjHd7EcHSL5qzuKXSKO5m4gEHh5Cl2uktmJYZQs");
        country.setCurrencySymbol("umeFesCsk3FBPNHUSCva8JZH9NirYM9g");
        country.setCurrencyName("muzBXbv0voC0iBWg4ZYiyDj7DU8SVXQn2ootJ9XXVZfGMrD2bz");
        country.setCountryCode2("x7q");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar3("JHmdtTNl18JV3BauJt1bxz2rF920TXy9");
        state.setStateName("r7XqIzZVL5222oNTkKXuehcpR46BKUCqDwMGjlTYypXGQhj3Id");
        state.setStateCodeChar2("epTmot9JjBbI6pl1Hbk772TvwABJ22Ha");
        state.setStateDescription("5U8mFPFtn1wBqufeYXFC62KAgsoDMaFdyGc9gflwxii7ZMGpa2");
        state.setStateCode(2);
        state.setStateFlag("eTFVpSFOgzIQJSE2myrqJV5C3d9JMEj8qzHNuIwm27L0v1SSR6");
        state.setStateCapitalLongitude(2);
        state.setStateCapitalLatitude(9);
        state.setStateCapital("64RK6hZdZLXDHPlP5cVLc8O8hbYsq17uK02ZOgaZ2vQyVy5UBX");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityCodeChar2("hyhFZX4x4HIqBEMixmn3Hat6LCKGYcCS");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("SsLphB6zLHV8ftQM6sbrWOtSFR9sHFjeHdyFc2zoXwsPOzFOXl");
        city.setCityLongitude(7);
        city.setCityName("Dh48L20xui5YUEF8lhNaoNmypVTuezjGsGlSgbvidwmCFOkzmO");
        city.setCityDescription("ky4ygAPqtrrayKKobW1RY8AgYGYiH2sVHx0kmK1l4KEwLse0Tf");
        city.setCityLatitude(10);
        city.setCityCode(3);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("9MitPeQFz9qJkDHLzt88fnkWGCv9llTEvFUfYcPzifVXSG4V2G");
        addresstype.setAddressTypeDesc("JxrQdIpHHARMVj7m5K1hG1FgGmKb7HPlEU3qWTAptAgZDseL3p");
        addresstype.setAddressType("GPx6mP9onzV9N2xAD2mCcz31mvux932TaNPftHAX4miqyOS2jk");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        address.setAddressLabel("jRR2lt01ai5");
        address.setZipcode("Y7apbI");
        address.setAddress3("Vcs3bZg1pCJzHIlmhcqXNL3PqPKQX5yXACiVhMdKAco08n3LuI");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("f3v0PIZGu9VXH05iMYbqeKyy5hikbZYF12Ur6Z35RUKyJBhoFX");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLongitude("Zt7QR8OFIeSV4oN8UspZMgukYcqxO5qaBgjAfNOPvv4Tz4TB8N");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey());
        address.setLatitude("Xgj5BNmaj3qepZSvx2ML9dDpb8wSOQtbE7HYJrqRLmA99a0ce9");
        address.setAddress1("xWsXeE5R9ltsOl5j9QQJAclNQKqRwGJBEvCkxAFKpYPRoKjq8d");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        corecontacts.setEntityValidator(entityValidator);
        return corecontacts;
    }

    @Test
    public void test1Save() {
        try {
            CoreContacts corecontacts = createCoreContacts(true);
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            corecontacts.isValid();
            corecontactsRepository.save(corecontacts);
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private TitleRepository<Title> titleRepository;

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setNativeLastName("y1jwQZW2duiZUvTlJUAVSCNO8mN8byNsnEQvK0PTALihE3ul9t");
            corecontacts.setDateofbirth(new java.sql.Timestamp(1471935865329l));
            corecontacts.setFirstName("U0ZOIGQRMXvtnJBf8kUbqosdBmOvUtsD9kvoAAQkhPE0Quo62r");
            corecontacts.setNativeMiddleName("T1YGzNnA9YUaSV8DIquOH5g47H9IQ17Oza8WCFEjqtKSXydwnh");
            corecontacts.setNativeTitle("SR5lWs7jzgHVk99w07qVNtFIPQw8mSfGH5VF5zdVS3wBKo4MCi");
            corecontacts.setEmailId("Xz8axk7E7ek32COZKzVXgHKQF9O9ZiJ8FwOAzOdLeU9fTbS1WR");
            corecontacts.setNativeFirstName("vU1eoWk5MsL182tZN6AcSZxOHam5AWG1942Q4EYQyNdocrRE5a");
            corecontacts.setVersionId(1);
            corecontacts.setMiddleName("9A1r8b0cD3ceQ0R421usSoQ22zJyXNB1Ifadg9sSHbEm5RJA27");
            corecontacts.setAge(118);
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1471935865479l));
            corecontacts.setLastName("uwUFwRUbEGVH0LaQwMrFoxlqbpc2CvGmudhoGIMiK1tUzPqTU7");
            corecontacts.setPhoneNumber("LD3lDLgWcpH69OsFZBa8");
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBytitleId() {
        try {
            java.util.List<CoreContacts> listoftitleId = corecontactsRepository.findByTitleId((java.lang.String) map.get("TitlePrimaryKey"));
            if (listoftitleId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBynativeLanguageCode() {
        try {
            java.util.List<CoreContacts> listofnativeLanguageCode = corecontactsRepository.findByNativeLanguageCode((java.lang.String) map.get("LanguagePrimaryKey"));
            if (listofnativeLanguageCode.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBygenderId() {
        try {
            java.util.List<CoreContacts> listofgenderId = corecontactsRepository.findByGenderId((java.lang.String) map.get("GenderPrimaryKey"));
            if (listofgenderId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.delete((java.lang.String) map.get("CoreContactsPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateCoreContacts(EntityTestCriteria contraints, CoreContacts corecontacts) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            corecontactsRepository.save(corecontacts);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "firstName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "ntFyZqBtDvCxwdZH5S8MFDLlhsouTAHXABY1FPFSIWb3cukNtYy0wXcLpVMLll1ekyYlQ3Whzr8GABmSI98RD1cdjMc62uymbCWq6gUc97J7ae1E3slKaJt1B96WtJ5HYcCDPQVXHy584z4WwY6S3H65GmKYwpayONz3U2gWg7FhSP3EjxBDUgqpc8U3HSbGGVcjBrDlfUzRUwSEcb64cjefHrfmDax39pvGjYnoNVMwZ3GCOZAFnqjl6EAQWU0if"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "ocrQEMtdl7iBaRUaqdNP7GVUiPivki6tqwFL8OgJrP8uDw8O29KMp6wFDtrsi0CW9Db1ssXmjYGyzxeui2jkDyPDgoEvKkj1ohe6U6xz0xTbRksfg2CofLa9FbyMsg95esF3RQVGvDEjtk6lJ16KVfwd7H80ghsFd6vhuUDGC6ieJuiTeFZq7GKvYffgGpihI5JuDUAJclQvCvKyPQYQgVkAzKywG0i87BGAgxWjLtZC8EUm1K4cNsv45WLycKvxb"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "uoKMY399IePXureSJEcmERIonfCy0vYlL40XNt6UtolggrX4NlZHjmUjWRug0znZEgHfMl8odEGGSrSC94PD9SPwIobdK7bkNWDT7VydmBmD8qMtDsQS3JEdywFZlowajZUIo29Qde1hzekGXTgOu5v1raOobYf7nNogYmssP4ZrEYS7KYF9TyLrTaKtf0n4Tie7nBEkqmCP9d76pRkBrCtg3xZZF4mSMFWwLzaWDkE3bFn6WBBqxzo8Pq0cXkDtf"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "FhrLcOoNvwulSEIIqCAYbfTuoByjOGUHhY1Z1RfbjCRScSBGj5RoCvwxOwenIrOdg"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "I2AosV1P0goVqjHqafRGdEebRMwSV7l51WU8I2dPatBDPA0KSU8ttyzukKWYfMzdgYDLMgjQwHnK2fvtjCuIgMCDODsG7fwG7lxZP2EvDgNSME0QjsPJZcTznGYnVY9jm7kLl0CtbjPhqGscmGUNXhBuR7SjBTzgxI9nnqJHeFvKn2ygogO9es5F6SDt9E7uOLR0yegkkHKrx8D7K9OfLPEpmujt5RPtSC8BYDKrTqkxbtYYi6AKuMtwTnsVe5Nqn"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "hkY7sCbA99aqVzcdLGPnEER6KIa2HmxVrxJs2znpfDvbPRuguc8cofOpVGsYH8lc9jKpj1Ij15Gv8V7Mp7PEuENgWwmRZNOOB7tnVPBR2G9WORxGz1MlTtHryURs1xLkRx6xa61s69MBzMWOpeD22GTkIVt6NZLUY2PbMCvU2cs5gpNv7t2d5da26f0uvExkQYmMB9bU4Lx1kKwzjkzOmXBj68quyroAhnvKM8KAOC2WhBlmC3DY0E3fRlBX3M5Kg"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "uE7VbvXpSt5ud63R5LHO07MjaTciqHyAHtT9kXwGuZ4t6GXOAr8OM4IpTmf1uDIZ0Rud9ebnhUklcJsbMaXTxlRttKwakc7TVazFTbjqVP4XZZhoCLI2OMINeryN9gL7cjYpJSBC0Wa9TRuWMyPlzmBIIgxIiE0M2QYMwYnfsN4qF0AMOwJqzNuSncm8eudcYcy7O4Lt3C9kH3tuUh68ByoqQZgR59KJ4t9tKf2ZWY5FDtymHbvQphG5hsVqVv9Ci"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 176));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "VKiRqpAMlzQKFGLZWxkgJG5cPuG258J8MfgGLoeFiU2nM3xC0BvpcnciYsVC4diAqL8rW6zV2w2aAlSr7PbFHB6ffIsWnKqjR39miRa87o6sDpTqv82MK0Slbp2VuXrxTwDrJ0PoEmCaC9n7UcCGOFEfmmvBVFBgQXQaxXCiauv91Na3itaqo6L93OvrhMY9MCcYvHCALkiREbkOZxTs7gYBQ5DpBgu25y3WhFCSlI6tWN86BukeWUW4Ly7eUDjYL"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "MNsQbo9Irl49rlUGB8ekz"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                CoreContacts corecontacts = createCoreContacts(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = corecontacts.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 2:
                        corecontacts.setFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 3:
                        corecontacts.setMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 5:
                        corecontacts.setLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 6:
                        corecontacts.setNativeTitle(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 7:
                        corecontacts.setNativeFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 8:
                        corecontacts.setNativeMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 9:
                        corecontacts.setNativeLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 10:
                        corecontacts.setAge(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 11:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 12:
                        corecontacts.setEmailId(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 13:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 14:
                        corecontacts.setPhoneNumber(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
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
