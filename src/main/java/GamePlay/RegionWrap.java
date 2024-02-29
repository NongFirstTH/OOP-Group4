package GamePlay;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegionWrap {
    int row;
    int col;
    int player;
    long deposit;
}
