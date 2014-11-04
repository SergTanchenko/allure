package my.company.lib;

/**
 * @author sergiitanchenko
 */
public enum Browsers {
	FIREFOX("firefox"),
	CHROME("chrome"),
	IE("ie"),
	UNKNOWN("unknown");

	private static String BROWSER_TYPE_PROP_NAME = "it.webdriver.browser.type";

	String browser;

	private Browsers(String browser) {
		this.browser = browser;
	}

	public static Browsers parseValue(String browser) {
		for (Browsers b : values()) {
			if (b.browser.equalsIgnoreCase(browser)) {
				return b;
			}
		}
		return UNKNOWN;
	}

	public static Browsers getCurrentOr(Browsers defaultVal) {
		Browsers currVal = Browsers.parseValue(System.getProperty(BROWSER_TYPE_PROP_NAME));
		if (UNKNOWN.equals(currVal)) {
			return defaultVal;
		}

		return currVal;
	}
}
