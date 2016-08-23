package projectthree.app.shared.testboundedcontext.testdomain;
import com.athena.server.pluggable.interfaces.EntityValidatorInterface;
import java.io.Serializable;
import projectthree.app.config.annotation.Complexity;
import projectthree.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Transient;
import java.lang.Override;
import javax.xml.bind.annotation.XmlRootElement;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "2", comments = "Customer", complexity = Complexity.MEDIUM)
@XmlRootElement
public class Customer implements EntityValidatorInterface, Serializable {

    private static final long serialVersionUID = 1471941347438L;

    @Transient
    @JsonIgnore
    private EntityValidatorHelper<Object> dtoValidator;

    private String customerId;

    private String customerName;

    private String custAddress;

    @Transient
    @JsonIgnore
    private boolean isDtoValidated = false;

    @JsonIgnore
    @Override
    public boolean isEntityValidated() {
        return isDtoValidated;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String _customerId) {
        this.customerId = _customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String _customerName) {
        this.customerName = _customerName;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String _custAddress) {
        this.custAddress = _custAddress;
    }

    @Override
    public void setEntityValidator(EntityValidatorHelper<Object> _validateFactory) {
        this.dtoValidator = _validateFactory;
    }

    @JsonIgnore
    @Override
    public boolean isValid() throws SecurityException {
        boolean isValid = false;
        if (this.dtoValidator != null) {
            isValid = this.dtoValidator.validateEntity(this);
            this.isDtoValidated = true;
        } else {
            throw new SecurityException();
        }
        return isValid;
    }
}
