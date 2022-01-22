package playground.domain.document;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import playground.domain.user.User;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class DocumentApproval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Document document;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User approver;

    @Enumerated(EnumType.STRING)
    @Column(name = "approval_state")
    private ApprovalState approvalState;

    @Column(name = "approval_order")
    private Integer approvalOrder;

    @Column(name = "approval_comment")
    private String approvalComment;

    @Builder
    private DocumentApproval(Document document, User approver, ApprovalState approvalState, Integer approvalOrder, String approvalComment) {
        this.document = document;
        this.approver = approver;
        this.approvalState = approvalState;
        this.approvalOrder = approvalOrder;
        this.approvalComment = approvalComment;
    }

}
