package com.cashcontrol.cashcontrol.service.repoHandler;

import com.cashcontrol.cashcontrol.constants.AdminConstants;
import com.cashcontrol.cashcontrol.entity.admin.Stock;
import com.cashcontrol.cashcontrol.model.response.SuccessResponse;
import com.cashcontrol.cashcontrol.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockRepoHandler {

    @Autowired
    private StockRepository stockRepository;

    public SuccessResponse saveStock(Stock stock) {
        stockRepository.save(stock);
        return new SuccessResponse(AdminConstants.STOCK_REGISTER_SUCCESS_RESPONSE);
    }
}
