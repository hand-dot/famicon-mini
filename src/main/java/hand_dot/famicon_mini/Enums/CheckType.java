package hand_dot.famicon_mini.Enums;

public enum CheckType {
	text("text"), html("html");
	private final String type;

	CheckType(final String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
