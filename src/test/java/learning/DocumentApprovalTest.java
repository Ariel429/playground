package learning;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class DocumentApprovalTest {

    @Test
    @DisplayName("문서 결재 정보를 생성한다.")
    void create() {
        //given
        User approver = new User(1L, "결재자");
        int approvalOrder = 1;

        //when
        DocumentApproval documentApproval = createDocumentApproval(approver, ApprovalState.DRAFTING, 1, null);

        //then
        assertThat(documentApproval)
                .extracting("approver.id", "approvalState", "approvalOrder", "approvalComment")
                .containsExactly(approver.getId(), ApprovalState.DRAFTING, approvalOrder, null);
    }

    @ParameterizedTest
    @CsvSource(value = {"APPROVED, true", "DRAFTING, false", "CANCELED, false"})
    @DisplayName("결재 승인 상태인지 확인한다.")
    void isApproved(ApprovalState approvalState, boolean expected) {
        //given
        User approver = new User(1L, "결재자");
        DocumentApproval documentApproval = createDocumentApproval(approver, approvalState, 1, null);

        //when
        boolean isApproved = documentApproval.isApproved();

        //then
        assertThat(isApproved).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"APPROVED, false", "DRAFTING, true", "CANCELED, true"})
    @DisplayName("결재가 승인 상태가 아닌지 확인한다.")
    void isNotApproved(ApprovalState approvalState, boolean expected) {
        //given
        User approver = new User(1L, "결재자");
        DocumentApproval documentApproval = createDocumentApproval(approver, approvalState, 1, null);

        //when
        boolean isNotApproved = documentApproval.isNotApproved();

        //then
        assertThat(isNotApproved).isEqualTo(expected);
    }

    private DocumentApproval createDocumentApproval(final User approver, final ApprovalState approvalState,
                                                    final Integer approvalOrder, final String approvalComment) {
        return DocumentApproval.builder()
                .approver(approver)
                .approvalState(approvalState)
                .approvalOrder(approvalOrder)
                .approvalComment(approvalComment)
                .build();
    }
}
