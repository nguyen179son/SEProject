package Helper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Utility for converting ResultSets into some Output formats
 */
public class Convertor {

    /**
     * Convert a result set into a JSON Array
     * @param resultSet
     * @return a JSONArray
     * @throws Exception
     */
    public static JSONArray convertToJSON(ResultSet resultSet) throws Exception {
        JSONArray jsonArray = new JSONArray();
        while (resultSet.next()) {
            int total_rows = resultSet.getMetaData().getColumnCount();
            for (int i = 0; i < total_rows; i++) {
                JSONObject obj = new JSONObject();
                obj.put(resultSet.getMetaData().getColumnLabel(i + 1)
                        .toLowerCase(), resultSet.getObject(i + 1));
                jsonArray.put(obj);
            }
        }
        return jsonArray;
    }

    public static int converBooleanIntoInt(boolean bool){
        if (bool) return 1;
        else return 0;
    }

    public static int convertBooleanStringIntoInt(String bool){
        if (bool.equals("false")) return 0;
        else if (bool.equals("true")) return 1;
        else {
            throw new IllegalArgumentException("wrong value is passed to the method. Value is "+bool);
        }
    }

    public static double getDoubleOutOfString(String value, String format, Locale locale){
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(locale);
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat f = new DecimalFormat(format,otherSymbols);
        String formattedValue = f.format(Double.parseDouble(value));
        double number = Double.parseDouble(formattedValue);
        return  Math.round(number * 100.0) / 100.0;
    }

}
