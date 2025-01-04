package com.cashcontrol.cashcontrol.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventResponse {

    private String eventId;
    private String eventName;
    private String eventType; //mutualFund/stock/creditCard
    private Boolean eventMandatory;
    private String eventDescription;
    private Long eventMinimumAmount;
    private Long eventMaximumAmount;
    private Long eventFixedAmount;
    private Long eventCurrentPrice;

}
