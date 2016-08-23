package projectthree.app.server.businessservice.testboundedcontext;
import projectthree.app.config.annotation.Complexity;
import projectthree.app.config.annotation.SourceCodeAuthorClass;
import org.springframework.stereotype.Component;
import com.athena.server.dataengine.bizService.QueryExecuterService;
import org.springframework.beans.factory.annotation.Autowired;
import java.lang.Override;
import java.util.List;
import projectthree.app.shared.testboundedcontext.QueryOneRM;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "1", comments = "TestQueryOneBzServiceImpl", complexity = Complexity.HIGH)
@Component
public class TestQueryOneBzServiceImpl implements TestQueryOneBzService {

    @Autowired
    private QueryExecuterService queryExecuterService;

    @Override
    public List<QueryOneRM> executeQueryQueryOne() throws Exception {
        java.util.List<projectthree.app.shared.testboundedcontext.QueryOneRM> listDtoInterface = new java.util.ArrayList<projectthree.app.shared.testboundedcontext.QueryOneRM>();
        try {
            atg.taglib.json.util.JSONObject queryParams = new atg.taglib.json.util.JSONObject();
            queryParams.put("queryId", "3613F8BA-4876-4CA4-9810-968DE16898AF");
            atg.taglib.json.util.JSONArray jsonArray = new atg.taglib.json.util.JSONArray();
            queryParams.put("queryCriteria", jsonArray);
            listDtoInterface = queryExecuterService.getAllQueryData("projectthree.app.shared.testboundedcontext.QueryOneRM", queryParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDtoInterface;
    }
}
