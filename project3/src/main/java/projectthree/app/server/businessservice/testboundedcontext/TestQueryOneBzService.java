package projectthree.app.server.businessservice.testboundedcontext;
import projectthree.app.config.annotation.Complexity;
import projectthree.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;
import projectthree.app.shared.testboundedcontext.QueryOneRM;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "1", comments = "TestQueryOneBzService", complexity = Complexity.HIGH)
public interface TestQueryOneBzService {

    public List<QueryOneRM> executeQueryQueryOne() throws Exception;
}
