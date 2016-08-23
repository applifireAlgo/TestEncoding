package projectthree.app.shared.testboundedcontext;
import com.athena.server.dataengine.bizService.DTOMapperInterface;
import projectthree.app.config.annotation.Complexity;
import projectthree.app.config.annotation.SourceCodeAuthorClass;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "1", comments = "TestQueryOne", complexity = Complexity.MEDIUM)
public class QueryOneRM implements DTOMapperInterface {

    private String countryId;

    private String countryName;

    private String countryFlag;

    private String capital;

    private String currencyName;

    public QueryOneRM(Object[] aryObject) {
        if (aryObject != null) {
            countryId = (aryObject[0] == null ? null : new java.lang.String(aryObject[0].toString()));
            countryName = (aryObject[1] == null ? null : new java.lang.String(aryObject[1].toString()));
            countryFlag = (aryObject[2] == null ? null : new java.lang.String(aryObject[2].toString()));
            capital = (aryObject[3] == null ? null : new java.lang.String(aryObject[3].toString()));
            currencyName = (aryObject[4] == null ? null : new java.lang.String(aryObject[4].toString()));
        } else {
            countryId = null;
            countryName = null;
            countryFlag = null;
            capital = null;
            currencyName = null;
        }
    }

    public String getCountryId() {
        return countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryFlag() {
        return countryFlag;
    }

    public String getCapital() {
        return capital;
    }

    public String getCurrencyName() {
        return currencyName;
    }
}
