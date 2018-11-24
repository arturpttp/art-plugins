package net.dev.art.core.utils;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class Extra {

	public static boolean isList(Class<?> claz) {
		return List.class.isAssignableFrom(claz);
	}
	
	
	public static boolean isMap(Class<?> claz) {
		return Map.class.isAssignableFrom(claz);
	}
	
	public static String getQuestionMarks(int size) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < size; i++) {
			if (i != 0)
				builder.append(",");
			builder.append("?");
		}
		return builder.toString();
	}
	
	public static String fromJavaToSQL(Object value) {
		if (value == null) {
			return "NULL";
		}
		Class<? extends Object> type = value.getClass();
		if (type == java.util.Date.class) {
			value = new Date(((java.util.Date) value).getTime());
		} else if (value instanceof Calendar) {
			value = new Timestamp(((Calendar) value).getTimeInMillis());
		}

		return value.toString();
	}
	
	public static void setSQLValue(PreparedStatement state, int param, Object value) {
		try {
			state.setString(param, fromJavaToSQL(value));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
