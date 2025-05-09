package de.agiehl.bga.BgaStatisticToBggCommentConverter.service;

import de.agiehl.bga.BgaStatisticToBggCommentConverter.models.ConverterJob;
import de.agiehl.bga.BgaStatisticToBggCommentConverter.models.Headline;
import de.agiehl.bga.BgaStatisticToBggCommentConverter.models.Player;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class ParseBgaStringService {

	private Environment env;

	private MessageSource msg;

	private static final String lineSeparatorRegex = "\\r?\\n";

	private static final String rowSeparatorRegex = "\\t";

	public ConverterJob convert(@NonNull String raw) {
		if (!raw.startsWith("\t")) {
			raw = "\t" + raw;
		}

		String[] rows = raw.split(lineSeparatorRegex);

		List<Player> players = stream(rows[0].split(rowSeparatorRegex)).skip(1).map(String::trim)
				.map(Player::new).collect(toList());
		List<Headline> allHeadlines = new ArrayList<>();

		for (int r = 1; r < rows.length; r++) {
			String[] col = rows[r].split(rowSeparatorRegex);
			String headline = col[0].trim();

			boolean isFilled = false;
			for (int c = 1; c < col.length; c++) {
				String val = col[c];
				players.get(c - 1).add(headline, val.trim());
				if (!IsEmpty.isEmpty(val)) {
					isFilled = true;
				}
			}

			allHeadlines.add(new Headline(headline, isFilled));
		}

		// Setting display name
		players.forEach(this::setDisplayName);

		return new ConverterJob(players, allHeadlines);
	}

	private void setDisplayName(Player player) {
		player.setDisplayName(env.getProperty("playername.mapping." + player.getName(), bgaUsernameString(player)));
	}

	private String bgaUsernameString(Player player) {
		return msg.getMessage("player.user", new String[] { player.getName() }, LocaleContextHolder.getLocale());
	}

}