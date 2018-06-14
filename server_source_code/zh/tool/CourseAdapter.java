package zh.tool;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by kindl on 10/24/2016.
 * A Wrapper class that convert database ResultSet to JSONArray
 */
public class CourseAdapter {
    public static JSONArray courseConverter(ResultSet rs){
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject;
        try{
            ResultSetMetaData meta=rs.getMetaData();// Class for doing more operation to ResultSet
            while (rs.next()){
                jsonObject=new JSONObject();
                for (int i=1;i<=meta.getColumnCount();i++){
                    jsonObject.put(meta.getColumnName(i),rs.getString(i));//producing name-value pairs
                }
                jsonArray.put(jsonObject);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return jsonArray;
    }
}
