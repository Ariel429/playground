package playground;

import java.time.LocalDateTime;

public class PeriodUtil {

    public static void validatePeriod(LocalDateTime departTime, LocalDateTime arrivalTime) {
        if (departTime.isAfter(arrivalTime)) {
            throw new IllegalArgumentException("시작일이 도착일보다 뒤에요");
        }
        if (departTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("과거야!");
        }
    }
}
