package de.agiehl.bga.BgaStatisticToBggCommentConverter.service.converter;

public enum ConvertStyleTypes {

	PLAIN_TEXT(new PlainTextStyle()), BGG_STYLE(new BggStyle());

	private final ConvertStyle style;

	private ConvertStyleTypes(ConvertStyle style) {
		this.style = style;
	}

	public ConvertStyle getConverter() {
		return style;
	}

}