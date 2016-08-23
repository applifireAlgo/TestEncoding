package projectthree.app.server.businessservice.testboundedcontext.testdomain;
import projectthree.app.config.annotation.Complexity;
import projectthree.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;
import projectthree.app.shared.testboundedcontext.QueryOneRM;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "3", comments = "CreateCountryService", complexity = Complexity.HIGH)
public interface CreateCountryService {

    public List<QueryOneRM> createCountryService() throws Exception;
}
