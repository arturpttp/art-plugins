package net.dev.art.essentials.apis;

import java.util.regex.*;
import java.util.*;
import java.io.*;

@SuppressWarnings("all")
public class StringAPI {

	private static final Pattern INVALIDFILECHARS;
	private static final Pattern STRICTINVALIDCHARS;
	private static final Pattern INVALIDCHARS;

	public static String sanitizeFileName(final String name) {
		return StringAPI.INVALIDFILECHARS.matcher(name.toLowerCase(Locale.ENGLISH)).replaceAll("_");
	}

	public static String safeString(final String string) {
		return StringAPI.STRICTINVALIDCHARS.matcher(string.toLowerCase(Locale.ENGLISH)).replaceAll("_");
	}

	public static String sanitizeString(final String string) {
		return StringAPI.INVALIDCHARS.matcher(string).replaceAll("");
	}

	public static String joinList(final Object... list) {
		return joinList(", ", list);
	}

	public static String joinList(final String seperator, final Object... list) {
		final StringBuilder buf = new StringBuilder();
		for (final Object each : list) {
			if (buf.length() > 0) {
				buf.append(seperator);
			}
			if (each instanceof Collection) {
				buf.append(joinList(seperator, ((Collection) each).toArray()));
			} else {
				try {
					buf.append(each.toString());
				} catch (Exception e) {
					buf.append(each.toString());
				}
			}
		}
		return buf.toString();
	}

	public static String joinListSkip(final String seperator, final String skip, final Object... list) {
		final StringBuilder buf = new StringBuilder();
		for (final Object each : list) {
			if (!each.toString().equalsIgnoreCase(skip)) {
				if (buf.length() > 0) {
					buf.append(seperator);
				}
				if (each instanceof Collection) {
					buf.append(joinListSkip(seperator, skip, ((Collection) each).toArray()));
				} else {
					try {
						buf.append(each.toString());
					} catch (Exception e) {
						buf.append(each.toString());
					}
				}
			}
		}
		return buf.toString();
	}

	static {
		INVALIDFILECHARS = Pattern.compile("[^a-z0-9-]");
		STRICTINVALIDCHARS = Pattern.compile("[^a-z0-9]");
		INVALIDCHARS = Pattern.compile("[^\t\n\r -~\u0085 -\ud7ff\ue000-\ufffc]");
	}

}
