package com.brandprotect.tronlib.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by user on 2018. 5. 28..
 */

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TopAddressAccount {

    @JsonProperty("address")
    private String address;

    @JsonProperty("name")
    private String name;

    @JsonProperty("balance")
    private long balance;

    @JsonProperty("power")
    private long power;

    @JsonProperty("dateCreated")
    private long dateCreated;

    @JsonProperty("dateUpdated")
    private long dateUpdated;
}
