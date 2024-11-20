package com.cashcontrol.cashcontrol.service.repoHandler;

import com.cashcontrol.cashcontrol.entity.LevelInfo;
import com.cashcontrol.cashcontrol.repository.LevelInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelInfoRepoHandler {

    @Autowired
    private LevelInfoRepository levelInfoRepository;

    public List<LevelInfo> findAllInstruction() {
        return levelInfoRepository.findAll();
    }

    public void saveInstruction(LevelInfo levelInfo) {
        levelInfoRepository.save(levelInfo);
    }

    public List<LevelInfo> findAllInstructionOneLevel(String id) {
        return levelInfoRepository.findAllByLevelNumber(Integer.getInteger(id));
    }
}
