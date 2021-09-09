package de.agiehl.bga.BgaStatisticToBggCommentConverter.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConverterJob {
	private List<Player> player;

	private List<Headline> headlines;
}
