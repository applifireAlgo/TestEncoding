package projectthree.app.server.repository.appbasicsetup.usermanagement;
import org.springframework.stereotype.Repository;
import projectthree.app.server.repository.core.SearchInterfaceImpl;
import projectthree.app.shared.appbasicsetup.usermanagement.PasswordPolicy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import projectthree.app.config.annotation.Complexity;
import projectthree.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import javax.persistence.EntityManager;
import com.spartan.server.password.interfaces.PasswordPolicyInterface;
import java.lang.Override;
import java.util.List;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;

@Repository
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "3", comments = "Repository for PasswordPolicy Master table Entity", complexity = Complexity.LOW)
public class PasswordPolicyRepositoryImpl extends SearchInterfaceImpl implements PasswordPolicyRepository<PasswordPolicy> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    /**
     * Method for fetching list of entities
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<PasswordPolicyInterface> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        java.util.List<com.spartan.server.password.interfaces.PasswordPolicyInterface> query = emanager.createNamedQuery("PasswordPolicy.findAll").getResultList();
        Log.out.println("ABSUM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "PasswordPolicyRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Saves the new  <PasswordPolicy> object.
     * @return PasswordPolicy
     * @Params Object of PasswordPolicy
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public PasswordPolicy save(PasswordPolicy entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("ABSUM321990200", runtimeLogInfoHelper.getRequestHeaderBean(), "PasswordPolicyRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <PasswordPolicy> object.
     * @return java.util.List<PasswordPolicy>
     * @Params list of PasswordPolicy
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<PasswordPolicy> save(List<PasswordPolicy> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            PasswordPolicy obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("ABSUM321990200", runtimeLogInfoHelper.getRequestHeaderBean(), "PasswordPolicyRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <PasswordPolicy> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        PasswordPolicy object = emanager.find(projectthree.app.shared.appbasicsetup.usermanagement.PasswordPolicy.class, id);
        emanager.remove(object);
        Log.out.println("ABSUM328990200", runtimeLogInfoHelper.getRequestHeaderBean(), "PasswordPolicyRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Updates the <PasswordPolicy> object.
     * @Params Object of PasswordPolicy
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(PasswordPolicy entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("ABSUM322990200", runtimeLogInfoHelper.getRequestHeaderBean(), "PasswordPolicyRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <PasswordPolicy> object.
     * @Params list of PasswordPolicy
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<PasswordPolicy> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            PasswordPolicy obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("ABSUM322990200", runtimeLogInfoHelper.getRequestHeaderBean(), "PasswordPolicyRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Returns list of objects.
     * @Params findername,Map of fields,Map of fieldMetadata
     * @throws java.lang.Exception
     */
    @Transactional
    public List<Object> search(String finderName, Map<String, Object> fields, Map<String, String> fieldMetaData) throws Exception {
        EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery(finderName);
        java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
        Map<String, String> metaData = new java.util.HashMap<String, String>();
        metaData = fieldMetaData;
        String inputStr = "01-01-1850 00:00:00 UTC";
        java.util.Date date = setFormattedDate(inputStr);
        java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
        for (Map.Entry<String, String> entry : metaData.entrySet()) {
            boolean matched = false;
            for (Map.Entry<String, Object> entry1 : fields.entrySet()) {
                if (entry.getKey() == entry1.getKey()) {
                    if (entry.getValue().equalsIgnoreCase("integer") || entry.getValue().equalsIgnoreCase("double") || entry.getValue().equalsIgnoreCase("float") || entry.getValue().equalsIgnoreCase("long")) {
                        map.put("min" + entry1.getKey(), entry1.getValue());
                        map.put("max" + entry1.getKey(), entry1.getValue());
                    } else if (entry.getValue().equalsIgnoreCase("String")) {
                        map.put(entry1.getKey(), "%" + entry1.getValue() + "%");
                    } else if (entry.getValue().equalsIgnoreCase("Date") || entry.getValue().equalsIgnoreCase("DateTime")) {
                        java.util.Date dateValue = setFormattedDate(entry1.getValue().toString());
                        map.put(entry1.getKey(), dateValue);
                    } else if (entry.getValue().equalsIgnoreCase("TimeStamp")) {
                        java.util.Date dateValue = setFormattedDate(entry1.getValue().toString());
                        map.put(entry1.getKey(), new java.sql.Timestamp(dateValue.getTime()));
                    } else {
                        map.put(entry1.getKey(), entry1.getValue());
                    }
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                if (entry.getValue().equalsIgnoreCase("String")) {
                    map.put(entry.getKey(), "%");
                } else if (entry.getValue().equalsIgnoreCase("integer")) {
                    map.put("min" + entry.getKey(), Integer.MIN_VALUE);
                    map.put("max" + entry.getKey(), Integer.MAX_VALUE);
                } else if (entry.getValue().equalsIgnoreCase("double")) {
                    map.put("min" + entry.getKey(), java.lang.Double.MIN_VALUE);
                    map.put("max" + entry.getKey(), java.lang.Double.MAX_VALUE);
                } else if (entry.getValue().equalsIgnoreCase("long")) {
                    map.put("min" + entry.getKey(), java.lang.Long.MIN_VALUE);
                    map.put("max" + entry.getKey(), java.lang.Long.MAX_VALUE);
                } else if (entry.getValue().equalsIgnoreCase("float")) {
                    map.put("min" + entry.getKey(), java.lang.Float.MIN_VALUE);
                    map.put("max" + entry.getKey(), java.lang.Float.MAX_VALUE);
                } else if (entry.getValue().equalsIgnoreCase("Date") || entry.getValue().equalsIgnoreCase("DATETIME")) {
                    map.put(entry.getKey(), date);
                } else if (entry.getValue().equalsIgnoreCase("TINYINT")) {
                    map.put(entry.getKey(), 1);
                } else if (entry.getValue().equalsIgnoreCase("timestamp")) {
                    map.put(entry.getKey(), timestamp);
                } else if (entry.getValue().equalsIgnoreCase("integer_userAccesCode")) {
                    map.put(entry.getKey(), runtimeLogInfoHelper.getUserAccessCode());
                }
            }
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        List<Object> list = query.getResultList();
        Log.out.println("ABSUM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "PasswordPolicyRepositoryImpl", "search", "Total Records Fetched = " + list.size());
        return list;
    }

    /**
     * Return PasswordPolicy object by filtering on refernce key <policyId>
     * @return PasswordPolicy
     * @Params policyId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public PasswordPolicy findById(String policyId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("PasswordPolicy.findById");
        query.setParameter("policyId", policyId);
        PasswordPolicy listOfPasswordPolicy = (PasswordPolicy) query.getSingleResult();
        Log.out.println("ABSUM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "PasswordPolicyRepositoryImpl", "findById", "Total Records Fetched = " + listOfPasswordPolicy);
        return listOfPasswordPolicy;
    }
}