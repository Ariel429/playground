package playground;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PeriodUtilTest {

    @Test
    public void 시작일이_도착일_이후일_때_IllegalArgumentException을_발생시킨다() {
        // given
        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> {
            PeriodUtil.validatePeriod(
                    LocalDateTime.of(2022, 1, 1, 0, 0, 0),
                    LocalDateTime.of(2020, 1, 1, 0, 0, 0)
            );
        });
    }

    @Test
    public void 시작일이_과거날짜일때_IllException발생() {
        assertThrows(IllegalArgumentException.class, () -> {
            PeriodUtil.validatePeriod(
                    LocalDateTime.of(2022, 1, 1, 0, 0, 0),
                    LocalDateTime.of(2023, 1, 1, 0, 0, 0)
            );
        });
    }

}