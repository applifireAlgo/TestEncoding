package projectthree.app.server.businessservice.testboundedcontext.testdomain;
import projectthree.app.config.annotation.Complexity;
import projectthree.app.config.annotation.SourceCodeAuthorClass;
import projectthree.app.shared.testboundedcontext.testdomain.Issue;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "3", comments = "CreateSampleSevice", complexity = Complexity.HIGH)
public interface CreateSampleSevice {

    public void createSampleSevice(Issue entity) throws Exception;
}
