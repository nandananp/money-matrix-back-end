package com.cashcontrol.cashcontrol.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventRequest {

    private String eventId;
    private String eventType;
    //ACCEPT/REJECT/SELL/BUY
    private String eventDecision;
    private Long eventCount;


}
