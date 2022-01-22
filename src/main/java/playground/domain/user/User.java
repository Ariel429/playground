package playground.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import playground.domain.document.Document;
import playground.domain.team.Team;

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

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey)
    private Team team;

    @Enumerated(EnumType.STRING)
    private JobPosition jobPosition;

    @OneToMany(mappedBy = "drafter", cascade = CascadeType.ALL)
    private final List<Document> documents = new ArrayList<>();

    @Builder
    private User(Long id, String email, String password, String name, Team team, JobPosition jobPosition) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.team = team;
        this.jobPosition = jobPosition;
    }

}
