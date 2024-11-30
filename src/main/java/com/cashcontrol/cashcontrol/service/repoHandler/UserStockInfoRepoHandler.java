package com.cashcontrol.cashcontrol.service.repoHandler;

import com.cashcontrol.cashcontrol.repository.UserStockInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserStockInfoRepoHandler {

    @Autowired
    private UserStockInfoRepository userStockInfoRepository;


}
