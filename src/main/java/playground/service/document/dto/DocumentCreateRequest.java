package playground.service.document.dto;

import playground.domain.document.ApprovalState;
import playground.domain.document.Category;
import playground.domain.document.Document;
import playground.domain.user.User;

import java.util.List;

import static playground.domain.document.ApprovalState.*;

public class DocumentCreateRequest {
    private String title;
    private Category category;
    private String contents;

    private Long drafterId;
    private List<Long> approverIds;

    public Document toEntity(User drafter) {
        return Document.builder()
                .title(title)
                .category(category)
                .contents(contents)
                .drafter(drafter)
                .approvalState(DRAFTING)
                .build();
    }
}
