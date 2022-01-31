package playground.service.document;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import playground.domain.document.Document;
import playground.domain.document.DocumentRepository;
import playground.domain.user.JobPosition;
import playground.domain.user.User;
import playground.domain.user.UserRepository;
import playground.web.document.dto.DocumentOutboxRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static playground.domain.document.ApprovalState.DRAFTING;

@ExtendWith(MockitoExtension.class)
public class DocumentServiceTest {

    @InjectMocks
    private DocumentService documentService;

    @Mock
    private DocumentRepository documentRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    public void outbox_list_조회() {
        // given
        User drafter = User.builder()
                .id(1L)
                .email("e@email.com")
                .name("je")
                .jobPosition(JobPosition.PART_MANAGER)
                .build();
        given(userRepository.findById(1L)).willReturn(Optional.of(drafter));
        DocumentOutboxRequest request = new DocumentOutboxRequest();
        request.setDrafterId(1L);

        List<Document> outboxDocs = new ArrayList<>();
        outboxDocs.add(Document.builder()
                .title("title1")
                .drafter(drafter)
                .contents("---")
                .build()
        );

        given(documentRepository.findByDrafterAndApprovalState(drafter, DRAFTING))
                .willReturn(outboxDocs);

        // when
        documentService.findOutboxDocuments(request);

        // then
        assertEquals(outboxDocs.size(), 1);
        assertEquals(outboxDocs.get(0).getDrafter().getEmail(), "e@email.com");

    }

    @Test
    public void outbox_list_조회_drafter_user를_못찾으면_RuntimeException던진다() {
        given(userRepository.findById(1L)).willReturn(Optional.empty());
        DocumentOutboxRequest request = new DocumentOutboxRequest();
        request.setDrafterId(1L);

        assertThrows(RuntimeException.class, () -> {
            documentService.findOutboxDocuments(request);
        });
    }

}
