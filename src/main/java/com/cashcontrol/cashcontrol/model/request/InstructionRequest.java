package com.cashcontrol.cashcontrol.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InstructionRequest {

    private Integer levelNumber;

    private String instruction;
}
