package playground.domain.document;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import playground.domain.user.User;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {

//    SELECT * from document join User join DocumentApproval where document.id = 1")
    @Query("SELECT d FROM Document d join d.drafter " +
            "join d.documentApprovals join d.drafter.team where d.id = :id")
    Document findByDocumentIdAll(Long id);
    List<Document> findByDrafterAndApprovalState(User drafter, ApprovalState approvalState);
}

