package GamePlay;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TerritoryWrap {
    public final RegionWrap[] regions;
}
