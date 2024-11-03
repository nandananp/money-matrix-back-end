package com.cashcontrol.cashcontrol.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MutualFundRequest {

    @JsonProperty("mutualFundName")
    private String mutualFundName;

    @JsonProperty("description")
    private String description;

    @JsonProperty("minimumAmount")
    private Long minimumAmount;
}
