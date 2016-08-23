package projectthree.app.server.service.appbasicsetup.usermanagement;
import projectthree.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projectthree.app.server.repository.appbasicsetup.usermanagement.LoginRepository;
import projectthree.app.shared.appbasicsetup.usermanagement.Login;
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
import projectthree.app.shared.appbasicsetup.usermanagement.User;
import projectthree.app.server.repository.appbasicsetup.usermanagement.UserRepository;
import projectthree.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import projectthree.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import projectthree.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import projectthree.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import projectthree.app.shared.appbasicsetup.usermanagement.PassRecovery;
import projectthree.app.shared.appbasicsetup.usermanagement.Question;
import projectthree.app.server.repository.appbasicsetup.usermanagement.QuestionRepository;
import projectthree.app.shared.appbasicsetup.usermanagement.UserData;
import projectthree.app.shared.organization.contactmanagement.CoreContacts;
import projectthree.app.server.repository.organization.contactmanagement.CoreContactsRepository;
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
public class LoginTestCase extends EntityTestCriteria {

    /**
     * LoginRepository Variable
     */
    @Autowired
    private LoginRepository<Login> loginRepository;

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

    private Login createLogin(Boolean isSave) throws Exception {
        User user = new User();
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelName("P6qmQUVwqJplwgc82XGMNRmQJKm9DG6HxfQIORnBABQyYuwe96");
        useraccesslevel.setLevelDescription("eiiH6uPj9zljRa5ASwpESPkgeOY26ZtsAG9b44ij9TPmcbOlsf");
        useraccesslevel.setLevelHelp("yc8svriqi38aw1ZfCib7KVGol5H6IIhNha8FGeXGTQAmWx1xkz");
        useraccesslevel.setLevelIcon("bpqxdRe0I5X5akM3O26QEAn4xBP8PBamHGKaVUxQlH5CphKHHF");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainHelp("GdfpdfBFwyqy9QitrY3JrljHvi7LwFKM86TIALy2jgy4W3wEPU");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainName("inAR0RPZhwlZCSC5LNLvUFazOfXynrcAL33BOvNKqDYmMiOnSx");
        useraccessdomain.setDomainIcon("DbnT0Hiq7SU3jTYnPxHyYT9PJcmqCzVfkxben7levI3h6K0ByQ");
        useraccessdomain.setDomainDescription("eG4eH2Jyvzd7jpGXCsTTdJ3nE1RliAPURuGesR2wNtswSbWnls");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setIsLocked(1);
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1471935875546l));
        user.setAllowMultipleLogin(1);
        user.setChangePasswordNextLogin(1);
        user.setGenTempOneTimePassword(1);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1471935875546l));
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setIsDeleted(1);
        user.setPasswordAlgo("YCmG7NVDONbJDAFZKzTYvLYukJraBUIRrIj8EbUJfbqUHCDQYM");
        user.setMultiFactorAuthEnabled(1);
        user.setSessionTimeout(2580);
        user.setUserAccessCode(30840);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        Question question = new Question();
        question.setQuestionIcon("jvXRYlpUvR2c1PVSCXiFHYf3XcbbmH4wnq4vj5blx2oVruuCWp");
        question.setLevelid(6);
        question.setQuestionDetails("R8WRhbBGPg");
        question.setQuestion("FyYDobIQYU2WAqTq18uYf1lP40lkHgbkwlDjn0D9DaHZeR4bL6");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        passrecovery.setAnswer("F8e0XfbXbh8WecmckSEunvKUn1gZQhSDVsEKI8n5XQTjQjDwbG");
        passrecovery.setUser(user);
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setLast5Passwords("W3YXhOXJDIzAQnS4Baj5LBlkIMx4LFEVuyGOjOj1oIeZkCr4yb");
        userdata.setPassword("G7h06iejsadkn144JPQTTOq1yuW3VSil0bRB9yaBoSnXUTerTR");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1471935875700l));
        userdata.setLast5Passwords("mw5zsQ36hBeksEgHZ3PHzFf8G8aa7S6oVlMAQSjiEU8Oxp2pN8");
        userdata.setPassword("KFgXOffuDEZ2s7ZOKEDcvHjWXf397G9VK7N5AH8xU03flgwd1l");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1471935875716l));
        userdata.setUser(user);
        userdata.setOneTimePassword("Iwrwq3xXVKA5bI5ln8g9Vcbla7ty0tuX");
        userdata.setOneTimePasswordExpiry(5);
        user.setUserData(userdata);
        CoreContacts corecontacts = new CoreContacts();
        Title title = new Title();
        title.setTitles("R9CyUuObxWnrwyA8WsvmmPUJM2g24RMOYmtTYJiuzP73He2flg");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Language language = new Language();
        language.setLanguageType("DseiNh0mwrCdd2DAyfyy0DHh0jEVOZ9H");
        language.setLanguageDescription("o5JoZb4bS5127opK2jGbwc15rz3v4iGSPzE0Q7NYsQMe1axuJT");
        language.setAlpha4parentid(1);
        language.setAlpha3("2cG");
        language.setAlpha4("NnCE");
        language.setLanguageIcon("LNVzmRFE3uH5wPd5H7kah8rRfE5r4doMF6xSDOMbbg533cPj6q");
        language.setAlpha2("LN");
        language.setLanguage("oI5LJvs9yE5byLcsOgtnkSp7Ds12IANWyQUltSMlNkPhmolJ31");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setGmtLabel("d3RxeE7GVtJc3TQ0YimUIAsOhHgY00ahNs4oaQsqbaY2ednCX2");
        timezone.setTimeZoneLabel("n2RmtqSTxb2u4tZm7Y4r7ikfN8qa3lnticZIbS8TTFB0T89tmM");
        timezone.setUtcdifference(5);
        timezone.setCountry("K0CsSVCgBGjj3PBZKZffbmnrYy9OdJLbmnKYEkNFODBff8VCFS");
        timezone.setCities("YlQq4Zl5VkILO9p0XHUqMseTw0njp4VXpwiZSUh2UKrMzVqXkZ");
        Gender gender = new Gender();
        gender.setGender("Yqb5PBqslbbxzejThQEn6axNpX5dFX2IoLAPUj5VLDX2JSjowZ");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeLastName("bmSFWDh2AvevrPtx6sFXx034ilBqAN7XHRxELPKxoGFidxwqai");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1471935875857l));
        corecontacts.setFirstName("9kndQbbSKsgYKniELFCuiOYgLFz4Dc7YdJ2nM0CPrfyQpMvBa1");
        corecontacts.setNativeMiddleName("qc4CRzDMUqhMM4J9pvcbbxoz8xxXkeiikGlwQvUbuje8svM2ai");
        corecontacts.setNativeTitle("vwliVO7ph14gwEJqzce8eedddfHpBiLuL3DteV0WwlAcaBcwpO");
        corecontacts.setEmailId("FeV4BsywEL8yHdTLeax6TeQwXaqArm0nDic4L0NFkjFJkzlYwL");
        corecontacts.setNativeFirstName("w4240ypIV4OqYWMUrq6b1kYa5tmIXRGXucRedgNNVdJboE9omY");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setMiddleName("8PXpTMspcU5S4NVyhMbgHnhx6PlrXxn8LCSG8OqPSCTeiB7vVr");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setAge(82);
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1471935875942l));
        corecontacts.setLastName("vhsx1P6yh0ne1WndGL1eXUiGrCqSc85yH2vYIx4MIyOH5ZDTRO");
        corecontacts.setPhoneNumber("ia1GcjckkpeN8ORKRTch");
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("AQnAeyu5AYGLM5dvcz27fSTDP9grYEfBQegrtJS83H60ooQhYT");
        communicationtype.setCommTypeDescription("htOqDhRcOlNYKRT72yEEQvzdGQti4tdAjjYSZd4m8qjbKsnC2U");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("zBGYXfhabQBLW873v8a7ownfRh3tsHfZgcuqZAjZtTIXR4jZBT");
        communicationgroup.setCommGroupDescription("MR2dd7eRQJFzCG1nvYjl5LUuT55qY1O26bJ12GCGOngZEHkldo");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        communicationtype.setCommTypeName("FBksWQ2WHB5jb9YaiEUxboE2IzWwyU89d14jNH8EvSm5WYas9x");
        communicationtype.setCommTypeDescription("46gNiSQOJ3EYVZFEThcS99UZ2jkcciz1jwaCoTUG8OzpGb6AC2");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommData("ehLiWrAaTc");
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddressLabel("UxUKgMrxgzb");
        address.setZipcode("VocIrQ");
        address.setAddress3("iLw52huwyhFCnH0cr5N4j7NYLDj2z2l25gbgVP0OlTfnn6OIKz");
        City city = new City();
        city.setCityCodeChar2("ueT96nq1y6RLvymZfK2aKqzyWURmvlqd");
        Country country = new Country();
        country.setCountryCode1("9DQ");
        country.setCapitalLongitude(2);
        country.setCapitalLatitude(6);
        country.setIsoNumeric(488);
        country.setCurrencyCode("OvE");
        country.setCountryFlag("pedZRtvw38uYT8e6zKgJVcVk5vse1POVDRnpe5SZIFTkfwMDqT");
        country.setCapital("jjzQmZ0hA90cngLDj2NaseceiMZ4Xs4B");
        country.setCountryName("CBEbSU23qEsJxSsYWEegxs1IuhrnuowFOgpyOorb1heAdccPCW");
        country.setCurrencySymbol("jdAF0GZFsS98t5RzkfAOS0ymlTjjRfBM");
        country.setCurrencyName("GGlQhwwqL88GDcBu7RWPqPgn7CJk5guWkTL487KR25HJGb5vbD");
        country.setCountryCode2("Alf");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar3("CFSR8NDPhS1LGGgfOVnsCi52z7ZfBxpm");
        state.setStateName("Bk0uNHVnJH1wCG1MKS2HTdbl77CCMCfpPzEH75ZccBkuiiUeIZ");
        state.setStateCodeChar2("uY2fKd20wUYozed2QBn5okhEePexm5Tn");
        state.setStateDescription("LeJty13vwA77o7R3iZdE8eqtRMYptiWJ01Drf8ZjWFnD8t03US");
        state.setStateCode(2);
        state.setStateFlag("L2OMn71GkwH5p8eRbtHqXCeVZ4LsHD0lvJIozguAmjIP5Cq7yl");
        state.setStateCapitalLongitude(7);
        state.setStateCapitalLatitude(3);
        state.setStateCapital("zU0AhPW4QO846hXktlo5ujDfbwL5lbV1hNrgu3kUhZeWqdaYAF");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityCodeChar2("IF6WwRkfh0gA6QgIVgrQqI2PU5zohcCu");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("6Zmm4trIFr6fUMeZ48Fd7aaepqqYyFxrHEoBytNoBWGRUGz0Zg");
        city.setCityLongitude(11);
        city.setCityName("a7GrfjaRbNMF2lHVveu7TKngpb3VTvl2v2xMRjiO3MJ9Xug3tb");
        city.setCityDescription("ONEf5w1vH9zDOLgCHollemCXNhBk0kswcSbwtFapMSO0HQKRTo");
        city.setCityLatitude(10);
        city.setCityCode(1);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("1U7WJG5kQCX9VdFTr5PKeJCU8ddk2SgJA4pCHUNiL6yLytmyxm");
        addresstype.setAddressTypeDesc("cVqfhrLDhxjtNxBlFZ0LckTHXT9bnQYMoQT0hOSUVLGeJ58k8d");
        addresstype.setAddressType("Poe71n2PinRCgdvSuOm3yJqEtnHmj5DUkr6oDr61P7bbPRhp9J");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        address.setAddressLabel("kVzXMYRs0af");
        address.setZipcode("jt2BzZ");
        address.setAddress3("WyRSrsnmhY1E6ioJPPokFb93fcrXlqs9P0ekoy9tc2njhMS2L1");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("jS6IL6jMk9tQKuvvfZvs0YyEPhMOuRGXFZa2MYBLjCKTO2ino5");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLongitude("D8mId7YKqvt9zY4U69oSFPvq1rfPUXndV6j7HJjEOYz7fmpF5A");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey());
        address.setLatitude("fZoCdkDHawE880iDVcXXBREK54oXrj2shMhHBxmMLwKqnA0uN3");
        address.setAddress1("Z3X3vRfMb9aS8aQX2Lx1JLkJd562fKFppeONdZC9iH71brDmJY");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        Login login = new Login();
        user.setUserId(null);
        login.setUser(user);
        login.setServerAuthImage("v8Xi6SzlAvKVznWelZ5JJYMt23KVKX7i");
        login.setIsAuthenticated(true);
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setLoginId("SYDrteqpZXepGBMHRVoIhxDwi2gbzMP3xfZQNYsTxREhqeIW5P");
        login.setFailedLoginAttempts(11);
        login.setServerAuthText("B0BPgivQDOzoIYBN");
        login.setEntityValidator(entityValidator);
        return login;
    }

    @Test
    public void test1Save() {
        try {
            Login login = createLogin(true);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            login.isValid();
            loginRepository.save(login);
            map.put("LoginPrimaryKey", login._getPrimarykey());
            map.put("UserPrimaryKey", login.getUser()._getPrimarykey());
            map.put("CoreContactsPrimaryKey", login.getCoreContacts()._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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
            Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setServerAuthImage("YUWMYysRs28p9RF8vQphHT4fhCotObam");
            login.setVersionId(1);
            login.setLoginId("KuWP64aTUk5YFp7E1Khb0MxSLXA6Sg4BAjpyFNg4fHnwTCzmsV");
            login.setFailedLoginAttempts(3);
            login.setServerAuthText("eTc2xjtg8QSP5WJV");
            login.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            loginRepository.update(login);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testNQFindUnMappedUser() {
        try {
            loginRepository.FindUnMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNQFindMappedUser() {
        try {
            loginRepository.FindMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.delete((java.lang.String) map.get("LoginPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateLogin(EntityTestCriteria contraints, Login login) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            login.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            login.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            login.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            loginRepository.save(login);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "loginId", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "yQLimtkv36hBLKsWIs55Ju898uPFtR0rEYtpGIIhSTAkC9YHQMLdcxHpDGSHfhymql6t7FUNirTjDtHzFbpDq8HrOAFmPGXqCO2CbwEiEJzPPPsyXm9wDtfDxtyerhuHHEOMC91Q5RL7ckm7gUYklUQKzTwaQKyGYvJGL82cTYd1USnqAcYtrTXJt61MWdgabab1G54hD"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "hcr9Ni01vXsK6Ss0RLYSKbnOL2xtNqvot"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "g6X9Q6UeJBLmGLIe4"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 16));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "isAuthenticated", true));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Login login = createLogin(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = login.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(login, null);
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 2:
                        login.setLoginId(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 3:
                        login.setServerAuthImage(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 4:
                        login.setServerAuthText(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 5:
                        login.setFailedLoginAttempts(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 6:
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
