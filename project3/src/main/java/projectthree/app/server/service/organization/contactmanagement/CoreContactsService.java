package projectthree.app.server.service.organization.contactmanagement;
import projectthree.app.config.annotation.Complexity;
import projectthree.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import java.util.List;
import projectthree.app.shared.organization.contactmanagement.CoreContacts;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "3", comments = "Service for CoreContacts Transaction table", complexity = Complexity.MEDIUM)
public abstract class CoreContactsService {

    abstract HttpEntity<ResponseBean> findAll() throws Exception;

    abstract HttpEntity<ResponseBean> save(CoreContacts entity) throws Exception;

    abstract HttpEntity<ResponseBean> save(List<CoreContacts> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> delete(String id) throws Exception;

    abstract HttpEntity<ResponseBean> update(CoreContacts entity) throws Exception;

    abstract HttpEntity<ResponseBean> update(List<CoreContacts> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> findByTitleId(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findByNativeLanguageCode(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findByGenderId(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findByTimeZoneId(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception;
}
