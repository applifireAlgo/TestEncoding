package projectthree.app.server.repository.appbasicsetup.usermanagement;
import projectthree.app.shared.appbasicsetup.usermanagement.ArtDocumentTemplate;

public interface ArtDocumentTemplateRepository {
	ArtDocumentTemplate findById(final String docTempId) throws Exception;
}
