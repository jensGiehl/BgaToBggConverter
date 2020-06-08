package de.agiehl.bga.BgaStatisticToBggCommentConverter.service.converter;

import static java.util.stream.Collectors.joining;

import java.util.List;

import de.agiehl.bga.BgaStatisticToBggCommentConverter.models.Player;
import de.agiehl.bga.BgaStatisticToBggCommentConverter.models.PlayerStatisticValue;

public class DefaultStyle implements ConvertStyle {

    @Override
    public String convert(List<Player> allPlayers) {
        return allPlayers.stream().map(this::playerToString).collect(joining("\n\n"));
    }

    private String playerToString(Player player) {
        String playerValues = player.getValues().stream().filter(PlayerStatisticValue::isActive).map(this::statisticToString).collect(joining("\n"));
        return "*** " + player.getDisplayName().trim() + " ***\n" + playerValues;
    }

    private String statisticToString(PlayerStatisticValue pair) {
        return pair.getKey().trim() + ": " + pair.getValue().trim();
    }

    
}