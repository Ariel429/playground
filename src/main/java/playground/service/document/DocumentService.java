package playground.service.document;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import playground.dao.document.dto.DocumentTitleResponse;
import playground.domain.document.Document;
import playground.domain.document.DocumentApproval;
import playground.domain.document.DocumentRepository;
import playground.domain.user.User;
import playground.domain.user.UserRepository;
import playground.service.document.dto.DocumentResponseDto;
import playground.web.document.dto.DocumentCreateRequest;
import playground.web.document.dto.DocumentOutboxRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static playground.domain.document.ApprovalState.DRAFTING;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final UserRepository userRepository;

    public List<DocumentTitleResponse> findOutboxDocuments(DocumentOutboxRequest request) {
        User drafter = userRepository.findById(request.getDrafterId())
                .orElseThrow(() -> new RuntimeException());
        List<Document> outboxDocuments = documentRepository.findByDrafterAndApprovalState(drafter, DRAFTING);
        return convertTitleDtoFrom(outboxDocuments);
    }

    public DocumentResponseDto findDocument(Long documentId) {
        Document document = documentRepository.findByDocumentIdAll(documentId);
        if (document == null) {
            throw new RuntimeException();
        }
        return new DocumentResponseDto(document);
    }

    @Transactional
    public void create(DocumentCreateRequest request) {
        User drafter = userRepository.findById(request.getDrafterId())
                .orElseThrow(() -> new RuntimeException());

        Document document = request.toEntity(drafter);

        List<Long> approverIds = request.getApproverIds();
        List<DocumentApproval> documentApprovals = new ArrayList<>();

        for (int index = 0; index < approverIds.size(); index++) {
            User approver = userRepository.findById(approverIds.get(index))
                    .orElseThrow(() -> new RuntimeException());

            DocumentApproval documentApproval = DocumentApproval.builder()
                    .document(document)
                    .approvalState(DRAFTING)
                    .approver(approver)
                    .approvalOrder(index + 1)
                    .build();

            documentApprovals.add(documentApproval);
        }
        document.setDocumentApprovals(documentApprovals);
        documentRepository.save(document);
    }

    private List<DocumentTitleResponse> convertTitleDtoFrom(List<Document> documents) {
        return documents.stream()
                .map(DocumentTitleResponse::new)
                .collect(Collectors.toList());
    }

}
