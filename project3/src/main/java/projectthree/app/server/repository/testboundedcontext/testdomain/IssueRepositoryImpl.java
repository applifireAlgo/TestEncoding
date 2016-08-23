package projectthree.app.server.repository.testboundedcontext.testdomain;
import org.springframework.stereotype.Repository;
import projectthree.app.server.repository.core.SearchInterfaceImpl;
import projectthree.app.shared.testboundedcontext.testdomain.Issue;
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
import java.lang.Override;
import java.util.List;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "1", comments = "Repository for Issue Master table Entity", complexity = Complexity.LOW)
public class IssueRepositoryImpl extends SearchInterfaceImpl implements IssueRepository<Issue> {

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
    public List<Issue> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<Issue> query = emanager.createNamedQuery("Issue.findAll").getResultList();
        Log.out.println("GHYGG324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "IssueRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Saves the new  <Issue> object.
     * @return Issue
     * @Params Object of Issue
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public Issue save(Issue entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("GHYGG322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "IssueRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <Issue> object.
     * @return java.util.List<Issue>
     * @Params list of Issue
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Issue> save(List<Issue> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            Issue obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("GHYGG322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "IssueRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <Issue> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Issue object = emanager.find(projectthree.app.shared.testboundedcontext.testdomain.Issue.class, id);
        emanager.remove(object);
        Log.out.println("GHYGG328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "IssueRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Updates the <Issue> object.
     * @Params Object of Issue
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(Issue entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("GHYGG321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "IssueRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <Issue> object.
     * @Params list of Issue
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<Issue> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            Issue obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("GHYGG321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "IssueRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return Issue object by filtering on refernce key <issueId>
     * @return Issue
     * @Params issueId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public Issue findById(String issueId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Issue.findById");
        query.setParameter("issueId", issueId);
        Issue listOfIssue = (Issue) query.getSingleResult();
        Log.out.println("GHYGG324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "IssueRepositoryImpl", "findById", "Total Records Fetched = " + listOfIssue);
        return listOfIssue;
    }
}
