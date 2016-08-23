package projectthree.app.server.businessservice.organization.contactmanagement;
import projectthree.app.shared.organization.contactmanagement.CoreContacts;
import java.util.List;

public interface CoreContactsBusinessService {

    void update(CoreContacts entity) throws Exception;

    void update(List<CoreContacts> entity) throws Exception;
}
