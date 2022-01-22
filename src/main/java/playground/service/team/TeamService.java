package playground.service.team;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import playground.domain.user.UserRepository;
import playground.domain.team.Team;
import playground.domain.team.TeamRepository;
import playground.domain.user.User;
import playground.service.team.dto.TeamMemberResponse;
import playground.service.team.dto.TeamResponse;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<TeamResponse> findAllTeams() {
        List<Team> teams = teamRepository.findAll();

        return teams.stream()
                .map(TeamResponse::new)
                .collect(Collectors.toList());
    }
}
