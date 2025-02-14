package de.agiehl.bga.BgaStatisticToBggCommentConverter.service.converter;

import de.agiehl.bga.BgaStatisticToBggCommentConverter.models.ConverterJob;
import de.agiehl.bga.BgaStatisticToBggCommentConverter.models.Player;
import de.agiehl.bga.BgaStatisticToBggCommentConverter.models.PlayerStatisticValue;

import java.util.List;

import static java.util.stream.Collectors.joining;

public class PlainTextStyle implements ConvertStyle {

	private String playerToString(Player player) {
		String playerValues = player.getValues().stream().filter(PlayerStatisticValue::isActive)
				.map(this::statisticToString).collect(joining("\n"));
		return "*** " + player.getDisplayName() + " ***\n" + playerValues;
	}

	private String statisticToString(PlayerStatisticValue pair) {
		return pair.getKey() + ": " + pair.getValue();
	}

	@Override
	public String convert(ConverterJob job, List<Player> players, String infoText) {
		String statText = players.stream().map(this::playerToString).collect(joining("\n\n"));

		return infoText + "\n\n" + statText;
	}

}