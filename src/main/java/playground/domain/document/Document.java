package playground.domain.document;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import playground.domain.user.User;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private String contents;

    @Enumerated(EnumType.STRING)
    @Column(name = "approval_state")
    private ApprovalState approvalState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drafter_id")
    private User drafter;

    @Builder
    private Document(Long id, String title, Category category, String contents, ApprovalState approvalState, User drafter) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.contents = contents;
        this.approvalState = approvalState;
        this.drafter = drafter;
    }

}
