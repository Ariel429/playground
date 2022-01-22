package playground.service.document.dto;

import lombok.Getter;
import playground.domain.document.ApprovalState;
import playground.domain.document.DocumentApproval;
import playground.service.team.dto.TeamMemberResponse;

@Getter
public class DocumentApprovalResponse {

    private final String approverTeamName;
    private final String approverName;
    private final ApprovalState approvalState;
    private final Integer approvalOrder;
    private final String approvalComment;

    public DocumentApprovalResponse(DocumentApproval documentApproval) {
        this.approverTeamName = documentApproval.getApprover().getTeam().getName();
        this.approverName = documentApproval.getApprover().getName();
        this.approvalState = documentApproval.getApprovalState();
        this.approvalOrder = documentApproval.getApprovalOrder();
        this.approvalComment = documentApproval.getApprovalComment();
    }

    public String getApprovalStateText() {
        return approvalState.getText();
    }
}
