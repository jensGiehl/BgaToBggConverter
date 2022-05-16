package de.agiehl.bga.BgaStatisticToBggCommentConverter.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ConverterJob {
	private List<Player> player;

	private List<Headline> headlines;
}
