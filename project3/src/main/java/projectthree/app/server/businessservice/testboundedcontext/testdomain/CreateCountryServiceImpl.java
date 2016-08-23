package projectthree.app.server.businessservice.testboundedcontext.testdomain;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import projectthree.app.server.businessservice.testboundedcontext.TestQueryOneBzService;
import projectthree.app.config.annotation.Complexity;
import projectthree.app.config.annotation.SourceCodeAuthorClass;
import java.lang.Override;
import java.util.List;
import projectthree.app.shared.testboundedcontext.QueryOneRM;

@Component
@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "3", comments = "CreateCountryServiceImpl", complexity = Complexity.HIGH)
public class CreateCountryServiceImpl implements CreateCountryService {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private TestQueryOneBzService testQueryOneBzService;

    @Override
    public List<QueryOneRM> createCountryService() throws Exception {
        java.util.List<projectthree.app.shared.testboundedcontext.QueryOneRM> queryOneRMList = testQueryOneBzService.executeQueryQueryOne();
        System.out.println("\t\t "+ queryOneRMList.size());
        System.out.println("\t\t "+ queryOneRMList.size());
        return queryOneRMList;
    }
}
