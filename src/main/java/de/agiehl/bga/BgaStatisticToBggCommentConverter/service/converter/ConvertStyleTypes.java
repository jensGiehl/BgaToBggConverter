package de.agiehl.bga.BgaStatisticToBggCommentConverter.service.converter;

public enum ConvertStyleTypes {

    DEFAULT(new DefaultStyle());

    private final ConvertStyle style;

    private ConvertStyleTypes(ConvertStyle style) {
        this.style = style;
    }

    public ConvertStyle getConverter() {
        return style;
    }
    
}