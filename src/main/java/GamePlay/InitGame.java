package GamePlay;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InitGame {
    private final long n;
    private final long m;
    private final long init_plan_sec;
    private final long init_budget;
    private final long init_center_dep;
    private final long plan_rev_sec;
    private final long rev_cost;
    private final long max_dep;
    private final long interest_pct;
}
