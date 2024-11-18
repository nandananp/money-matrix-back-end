package com.cashcontrol.cashcontrol.service;

import com.cashcontrol.cashcontrol.entity.LevelInfo;
import com.cashcontrol.cashcontrol.model.response.LevelInstructionResponse;
import com.cashcontrol.cashcontrol.service.repoHandler.LevelInfoRepoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LevelInfoService {


    @Autowired
    private LevelInfoRepoHandler levelInfoRepoHandler;

    public List<LevelInstructionResponse> getMeFullInstruction() {

        List<LevelInfo> levelInfos = levelInfoRepoHandler.findAllInstruction();

        return levelInfos.stream().map(levelInfo -> {
            LevelInstructionResponse instructionResponse = new LevelInstructionResponse();
            instructionResponse.setLevel(levelInfo.getLevelNumber());
            instructionResponse.setInstruction(levelInfo.getDescription());
            return instructionResponse;
        }).collect(Collectors.toList());
    }
}
