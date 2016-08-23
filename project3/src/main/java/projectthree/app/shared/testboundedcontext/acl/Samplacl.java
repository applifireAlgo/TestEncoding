package projectthree.app.shared.testboundedcontext.acl;
import projectthree.app.config.annotation.Complexity;
import projectthree.app.config.annotation.SourceCodeAuthorClass;
import projectthree.app.shared.testboundedcontext.testdomain.Issue;
import projectthree.app.shared.testboundedcontext.testdomain.Bug;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "1", comments = "Samplacl", complexity = Complexity.MEDIUM)
public class Samplacl {

    public Samplacl(Issue _issue) {
        this.issueInput = _issue;
        this.doMapping();
    }

    private Issue issueInput;

    private Bug bugOutput;

    public Bug testacl() {
        return bugOutput;
    }

    public void doMapping() {
        bugOutput = new Bug();
        bugOutput.setBugName(issueInput.getIssueName());
        bugOutput.setBugComponent(issueInput.getComponent());
        bugOutput.setBugPriority(issueInput.getIssueId());
    }
}
