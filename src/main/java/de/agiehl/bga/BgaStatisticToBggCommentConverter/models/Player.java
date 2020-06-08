package de.agiehl.bga.BgaStatisticToBggCommentConverter.models;

import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Player {

    private String name;

    @Setter
    private String displayName;

    @Getter(AccessLevel.NONE)
    private List<PlayerStatisticValue> values = new ArrayList<>();

    public Player(String name) {
        super();
        this.name = name;
    }

    public Player(String name, String displayName) {
        super();
        this.name = name;
        this.displayName = displayName;
    }

    public void add(String key, String value) {
        values.add(new PlayerStatisticValue(key, value, true));
    }

    public List<PlayerStatisticValue> getValues() {
        return unmodifiableList(values);
    }
    
}