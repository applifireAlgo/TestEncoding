package projectthree.app.server.businessservice.appbasicsetup.usermanagement;
import projectthree.app.shared.appbasicsetup.usermanagement.User;
import java.util.List;

public interface UserBusinessService {

    void update(User entity) throws Exception;

    void update(List<User> entity) throws Exception;
}
