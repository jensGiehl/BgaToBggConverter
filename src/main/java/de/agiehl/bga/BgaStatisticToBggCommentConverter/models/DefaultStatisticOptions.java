package de.agiehl.bga.BgaStatisticToBggCommentConverter.models;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Setter;

@Component
@Configuration
@PropertySource(ignoreResourceNotFound = true, value = "classpath:defaultStatisticOptions.properties") // YAML dosn't work... i have no idea why
@ConfigurationProperties(prefix = "statistic.default", ignoreUnknownFields = true)
@Setter
public class DefaultStatisticOptions {

    private List<String> enabled;

    public boolean isActive(String key) {
        if (enabled == null || enabled.isEmpty()) {
            return true;
        }

        return enabled.contains(key);
    }
    
}