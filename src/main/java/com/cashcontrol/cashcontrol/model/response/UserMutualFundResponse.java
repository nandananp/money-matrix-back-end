package com.cashcontrol.cashcontrol.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserMutualFundResponse {

    private String mutualFundId;
    private String mutualFundName;
    private Long totalReturn;
    private Long sipAmount;

}
