package de.agiehl.bga.BgaStatisticToBggCommentConverter.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerStatisticValue {

    private String key;
    private String value;
    private boolean active;
    
}