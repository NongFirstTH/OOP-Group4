package com.websocket.demo.GamePlay.Wrapper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonCreator
    public InitGame(@JsonProperty("n") long n,
                    @JsonProperty("m") long m,
                    @JsonProperty("init_plan_sec") long init_plan_sec,
                    @JsonProperty("init_budget") long init_budget,
                    @JsonProperty("init_center_dep") long init_center_dep,
                    @JsonProperty("plan_rev_sec") long plan_rev_sec,
                    @JsonProperty("rev_cost") long rev_cost,
                    @JsonProperty("max_dep") long max_dep,
                    @JsonProperty("interest_pct") long interest_pct) {
        this.n = n;
        this.m = m;
        this.init_plan_sec = init_plan_sec;
        this.init_budget = init_budget;
        this.init_center_dep = init_center_dep;
        this.plan_rev_sec = plan_rev_sec;
        this.rev_cost = rev_cost;
        this.max_dep = max_dep;
        this.interest_pct = interest_pct;
    }
}
