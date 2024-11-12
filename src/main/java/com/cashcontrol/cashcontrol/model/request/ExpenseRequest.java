package com.cashcontrol.cashcontrol.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseRequest {

    private String expenseName;
    private String description;
    private Long fullAmount;
    private Long emi;
}
