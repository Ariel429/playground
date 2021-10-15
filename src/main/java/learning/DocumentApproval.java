package learning;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DocumentApproval {

    private User approver;
    private ApprovalState approvalState;
    private Integer approvalOrder;
    private String approvalComment;

    @Builder
    private DocumentApproval(final User approver, final ApprovalState approvalState,
                             final Integer approvalOrder, final String approvalComment) {
        this.approver = approver;
        this.approvalState = approvalState;
        this.approvalOrder = approvalOrder;
        this.approvalComment = approvalComment;
    }

    public static DocumentApproval of(final User approver, final int order) {
        return new DocumentApproval(approver, ApprovalState.DRAFTING, order, null);
    }

    public boolean isApproved() {
        return approvalState.isApproved();
    }

    public boolean isNotApproved() {
        return !isApproved();
    }

    public boolean hasNotSameApprover(final User approver) {
        return !this.approver.isSame(approver);
    }

    public void changeStateToApproved(final String comment) {
        approvalState = ApprovalState.APPROVED;
        approvalComment = comment;
    }

    public boolean isSameOrder(final int approvalOrder) {
        return this.approvalOrder == approvalOrder;
    }
}
