package playground.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import playground.domain.document.Document;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String name;

    @OneToMany(mappedBy = "drafter", cascade = CascadeType.ALL)
    private List<Document> documents = new ArrayList<>();

    @Builder
    private User(Long id, String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }

}
