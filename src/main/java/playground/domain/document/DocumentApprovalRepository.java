package playground.domain.document;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import playground.domain.user.User;
import playground.service.team.dto.TeamMemberResponse;

public interface DocumentApprovalRepository extends JpaRepository<DocumentApproval, Long> {
//    @Query("select distinct u from User u join fetch u.approver")
//    TeamMemberResponse findByUserIdWithTeamUsingJoin(User user);
}
