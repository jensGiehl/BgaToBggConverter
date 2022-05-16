package de.agiehl.bga.BgaStatisticToBggCommentConverter.service;

import de.agiehl.bga.BgaStatisticToBggCommentConverter.models.ConverterJob;
import de.agiehl.bga.BgaStatisticToBggCommentConverter.models.Player;
import de.agiehl.bga.BgaStatisticToBggCommentConverter.models.PlayerStatisticValue;
import de.agiehl.bga.BgaStatisticToBggCommentConverter.service.converter.ConvertStyleTypes;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

import static de.agiehl.bga.BgaStatisticToBggCommentConverter.service.IsEmpty.isNotEmpty;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class ConverterService {

	private MessageSource msg;

	public String convert(ConverterJob job, ConvertStyleTypes style, List<String> choosenStats,
			List<String> choosenPlayer, Locale locale) {

		List<Player> players = job.getPlayer().stream().filter(player -> choosenPlayer.contains(player.getName()))
				.collect(toList());

		for (Player player : players) {
			for (PlayerStatisticValue value : player.getValues()) {
                value.setActive(choosenStats.contains(value.getKey()) && isNotEmpty(value.getValue()));
			}
		}

		String infoText = msg.getMessage("playedOn.text", null, locale);

		return style.getConverter().convert(job, players, infoText);
	}

}