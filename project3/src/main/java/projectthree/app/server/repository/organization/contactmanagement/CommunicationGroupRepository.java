package projectthree.app.server.repository.organization.contactmanagement;
import projectthree.app.server.repository.core.SearchInterface;
import projectthree.app.config.annotation.Complexity;
import projectthree.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "2", comments = "Repository for CommunicationGroup Master table Entity", complexity = Complexity.LOW)
public interface CommunicationGroupRepository<T> extends SearchInterface {

    List<T> findAll() throws Exception;

    T save(T entity) throws Exception;

    List<T> save(List<T> entity) throws Exception;

    void delete(String id) throws Exception;

    void update(T entity) throws Exception;

    void update(List<T> entity) throws Exception;

    T findById(String commGroupId) throws Exception;
}
