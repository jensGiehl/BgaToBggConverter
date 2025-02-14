package de.agiehl.bga.BgaStatisticToBggCommentConverter.service.converter;

import de.agiehl.bga.BgaStatisticToBggCommentConverter.models.ConverterJob;
import de.agiehl.bga.BgaStatisticToBggCommentConverter.models.Player;

import java.util.List;

public interface ConvertStyle {

	String convert(ConverterJob job, List<Player> players, String infoText);

}