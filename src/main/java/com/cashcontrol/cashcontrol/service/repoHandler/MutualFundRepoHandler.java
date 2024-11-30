package com.cashcontrol.cashcontrol.service.repoHandler;

import com.cashcontrol.cashcontrol.constants.AdminConstants;
import com.cashcontrol.cashcontrol.entity.admin.MutualFund;
import com.cashcontrol.cashcontrol.model.response.SuccessResponse;
import com.cashcontrol.cashcontrol.repository.MutualFundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutualFundRepoHandler {

    @Autowired
    private MutualFundRepository mutualFundRepository;

    public SuccessResponse saveMutualFund(MutualFund mutualFund) {
        mutualFundRepository.save(mutualFund);
        return new SuccessResponse(AdminConstants.MUTUAL_FUND_REGISTRATION_SUCCESS_RESPONSE);
    }
}
