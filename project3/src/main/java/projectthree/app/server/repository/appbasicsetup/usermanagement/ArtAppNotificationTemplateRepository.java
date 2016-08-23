package projectthree.app.server.repository.appbasicsetup.usermanagement;
import projectthree.app.shared.appbasicsetup.usermanagement.ArtAppNotificationTemplate;

public interface ArtAppNotificationTemplateRepository {

	ArtAppNotificationTemplate findById(String templateId) throws Exception;
}
