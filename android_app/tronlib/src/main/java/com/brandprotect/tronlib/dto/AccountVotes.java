package com.brandprotect.tronlib.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountVotes {

    @JsonProperty("total")
    private int total;

    @JsonProperty("data")
    List<AccountVote> data;

    private long totalVotes;
}
