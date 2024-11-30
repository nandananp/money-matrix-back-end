package com.cashcontrol.cashcontrol.service.repoHandler;

import com.cashcontrol.cashcontrol.repository.UserMutualFundInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMutualFundInfoHandler {

    @Autowired
    private UserMutualFundInfoRepository userMutualFundInfoRepository;
}
