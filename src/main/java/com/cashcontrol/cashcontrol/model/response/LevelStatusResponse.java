package com.cashcontrol.cashcontrol.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LevelStatusResponse {

    private Long level;
    private boolean levelFlag;
}
