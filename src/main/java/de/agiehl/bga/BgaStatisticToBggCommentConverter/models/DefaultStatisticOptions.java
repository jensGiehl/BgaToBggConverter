package de.agiehl.bga.BgaStatisticToBggCommentConverter.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Setter;

@Component
@Setter
public class DefaultStatisticOptions {

    @Value("${statistic.default.enabled:}")
    private List<String> enabled;

    public boolean isActive(String key) {
        if (enabled == null || enabled.isEmpty()) {
            return true;
        }

        return enabled.contains(key);
    }
    
}