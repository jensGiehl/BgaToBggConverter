package de.agiehl.bga.BgaStatisticToBggCommentConverter.service;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import de.agiehl.bga.BgaStatisticToBggCommentConverter.models.ConverterJob;
import de.agiehl.bga.BgaStatisticToBggCommentConverter.models.Player;
import de.agiehl.bga.BgaStatisticToBggCommentConverter.models.PlayerStatisticValue;
import de.agiehl.bga.BgaStatisticToBggCommentConverter.service.converter.ConvertStyleTypes;

@Service
public class ConverterService {

    @Autowired
    private MessageSource msg;

    public String convert(ConverterJob job, ConvertStyleTypes style, List<String> choosenStats,
            List<String> choosenPlayer, Locale locale) {

        List<Player> players = job.getPlayer().stream().filter(player -> choosenPlayer.contains(player.getName())).collect(toList());

        for (Player player : players) {
            for (PlayerStatisticValue value : player.getValues()) {
                if (choosenStats.contains(value.getKey())) {
                    value.setActive(true);
                } else {
                    value.setActive(false);
                }
            }
        }

        return msg.getMessage("playedOn.text", null, locale) + "\n\n" +  style.getConverter().convert(players);
    }
    
}