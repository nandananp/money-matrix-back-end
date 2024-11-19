package com.cashcontrol.cashcontrol.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LevelInstructionResponse {

    private Integer level;

    private String instruction;
}
