package de.agiehl.bga.BgaStatisticToBggCommentConverter.service.converter;

import java.util.List;

import de.agiehl.bga.BgaStatisticToBggCommentConverter.models.ConverterJob;
import de.agiehl.bga.BgaStatisticToBggCommentConverter.models.Player;

public interface ConvertStyle {

	String convert(ConverterJob job, List<Player> players, String infoText);

}