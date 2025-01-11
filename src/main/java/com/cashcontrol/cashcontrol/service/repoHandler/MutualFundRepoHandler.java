package com.cashcontrol.cashcontrol.service.repoHandler;

import com.cashcontrol.cashcontrol.constants.AdminConstants;
import com.cashcontrol.cashcontrol.entity.admin.MutualFund;
import com.cashcontrol.cashcontrol.model.response.SuccessResponse;
import com.cashcontrol.cashcontrol.repository.MutualFundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MutualFundRepoHandler {

    @Autowired
    private MutualFundRepository mutualFundRepository;

    public SuccessResponse saveMutualFund(MutualFund mutualFund) {
        mutualFundRepository.save(mutualFund);
        return new SuccessResponse(AdminConstants.MUTUAL_FUND_REGISTRATION_SUCCESS_RESPONSE);
    }

    public List<MutualFund> findAllMutualFunds() {
        return mutualFundRepository.findAll();
    }

    public MutualFund findByMutualFundId(String eventId) {
        return mutualFundRepository.findById(UUID.fromString(eventId));
    }
}
