package playground.service.document.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import playground.domain.document.ApprovalState;
import playground.domain.document.Category;
import playground.domain.document.Document;
import playground.domain.user.User;

@Getter
@NoArgsConstructor
public class DocumentResponseDto {

    private Long id;
    private String title;
    private Category category;
    private String contents;
    private Long userId;
    private ApprovalState approvalState;
    private String userName;


    public DocumentResponseDto(Document document) {
        this.id = document.getId();
        this.title = document.getTitle();
        this.category = document.getCategory();
        this.contents = document.getContents();
        this.approvalState = document.getApprovalState();

        this.userId = document.getDrafter().getId();
        this.userName = document.getDrafter().getName();
    }

    public String getCategoryText() {
        return category.getText();
    }

    public String getApprovalStateText() {
        return approvalState.getText();
    }

}
