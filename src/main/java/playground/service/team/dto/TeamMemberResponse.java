package playground.service.team.dto;

import lombok.Getter;
import playground.domain.user.JobPosition;
import playground.domain.user.User;

@Getter
public class TeamMemberResponse {

    private final Long id;
    private final JobPosition jobPosition;
    private final String teamName;
    private final String name;
    private final String jobPositionText;

    public TeamMemberResponse(User user) {
        this.id = user.getId();
        this.jobPosition = user.getJobPosition();
        this.teamName = user.getTeam().getName();
        this.name = user.getName();
        this.jobPositionText = user.getJobPosition().getJobPositionText();
    }
}
