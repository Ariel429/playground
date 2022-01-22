package playground.service.document.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import playground.domain.document.ApprovalState;
import playground.domain.document.Category;
import playground.domain.document.Document;
import playground.domain.user.User;
import playground.service.team.dto.TeamMemberResponse;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class DocumentResponseDto {

    private Long id;
    private String title;
    private Category category;
    private String contents;
    private ApprovalState approvalState;
    private TeamMemberResponse drafter;
    private List<DocumentApprovalResponse> approvers;

    public DocumentResponseDto(Document document) {
        this.id = document.getId();
        this.title = document.getTitle();
        this.category = document.getCategory();
        this.contents = document.getContents();
        this.approvalState = document.getApprovalState();

        this.drafter = new TeamMemberResponse(document.getDrafter());
        this.approvers = document.getDocumentApprovals().stream()
                .map(DocumentApprovalResponse::new)
                .sorted(Comparator.comparing(DocumentApprovalResponse::getApprovalOrder))
                .collect(Collectors.toList());
    }

    public String getCategoryText() {
        return category.getText();
    }

    public String getApprovalStateText() {
        return approvalState.getText();
    }

}
