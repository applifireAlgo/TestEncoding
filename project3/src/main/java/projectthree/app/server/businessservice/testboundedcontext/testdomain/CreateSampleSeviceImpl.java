package projectthree.app.server.businessservice.testboundedcontext.testdomain;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import projectthree.app.server.repository.testboundedcontext.testdomain.BugRepository;
import projectthree.app.shared.testboundedcontext.testdomain.Bug;
import projectthree.app.config.annotation.Complexity;
import projectthree.app.config.annotation.SourceCodeAuthorClass;
import java.lang.Override;
import projectthree.app.shared.testboundedcontext.testdomain.Issue;

@Component
@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "3", comments = "CreateSampleSeviceImpl", complexity = Complexity.HIGH)
public class CreateSampleSeviceImpl implements CreateSampleSevice {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private BugRepository<Bug> bugRepository;

    @Override
    public void createSampleSevice(Issue entity) throws Exception {
        if (entity.getIssueId() != null) {
            projectthree.app.shared.testboundedcontext.acl.Samplacl samplacl = new projectthree.app.shared.testboundedcontext.acl.Samplacl(entity);
            projectthree.app.shared.testboundedcontext.testdomain.Bug bug = bugRepository.save(samplacl.testacl());
        }
    }
}
