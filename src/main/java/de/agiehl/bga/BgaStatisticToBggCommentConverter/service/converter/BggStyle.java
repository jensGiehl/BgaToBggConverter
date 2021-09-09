package de.agiehl.bga.BgaStatisticToBggCommentConverter.service.converter;

import static java.util.stream.Collectors.joining;

import java.util.List;

import de.agiehl.bga.BgaStatisticToBggCommentConverter.models.ConverterJob;
import de.agiehl.bga.BgaStatisticToBggCommentConverter.models.Player;
import de.agiehl.bga.BgaStatisticToBggCommentConverter.models.PlayerStatisticValue;

public class BggStyle implements ConvertStyle {

	@Override
	public String convert(ConverterJob job, List<Player> players, String infoText) {
		String bggFormatedInfoText = italicize(infoText);

		String statText = players.stream().map(this::playerToString).collect(joining("\n\n"));

		return bggFormatedInfoText + "\n\n" + statText;
	}

	private String italicize(String txt) {
		return "[i]" + txt + "[/i]";
	}

	private String green(String txt) {
		return "g{" + txt + "}g";
	}

	private String bold(String txt) {
		return "[b]" + txt + "[/b]";
	}

	private String underline(String txt) {
		return "[u]" + txt + "[/u]";
	}

	private String playerToString(Player player) {
		String playerValues = player.getValues().stream().filter(PlayerStatisticValue::isActive)
				.map(this::statisticToString).collect(joining("\n"));
		return bold(underline(green(player.getDisplayName()))) + "\n" + playerValues;
	}

	private String statisticToString(PlayerStatisticValue pair) {
		return pair.getKey() + ": " + bold(pair.getValue());
	}

}
