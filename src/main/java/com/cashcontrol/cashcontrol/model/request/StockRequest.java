package com.cashcontrol.cashcontrol.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StockRequest {

    private String stockName;
    private String description;
    private Long minPrice;
    private Long maxPrice;
    private Long currentPrice;

}
