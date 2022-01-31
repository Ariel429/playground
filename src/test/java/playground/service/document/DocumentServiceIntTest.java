package playground.service.document;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import playground.domain.document.ApprovalState;
import playground.domain.document.Category;
import playground.domain.document.Document;
import playground.domain.document.DocumentRepository;
import playground.domain.team.Team;
import playground.domain.user.User;
import playground.domain.user.UserRepository;
import playground.service.document.dto.DocumentResponseDto;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DocumentServiceIntTest {

    @BeforeEach
    void setUp() {
        documentRepository.deleteAll();
    }

    @Autowired
    private DocumentService documentService;

    @Autowired
    private DocumentRepository documentRepository;

    @Test
    public void document_조회_test() {
        // given
        User drafter = User.builder().id(1L)
            .email("email")
            .password("---")
            .name("ai")
            .team(new Team("---"))
            .build();

        documentRepository.save(new Document(1L, "doc", Category.OPERATING_EXPENSES, "--", ApprovalState.APPROVED, drafter));

        // when
        DocumentResponseDto dto = documentService.findDocument(1L);
        // then
        assertEquals(dto.getTitle(), "doc");
    }


}