package de.agiehl.bga.BgaStatisticToBggCommentConverter.service.converter;

import java.util.List;

import de.agiehl.bga.BgaStatisticToBggCommentConverter.models.Player;

public interface ConvertStyle {

    String convert(List<Player> allPlayers);
    
}