package com.tv_talk;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONConnect {
    private String JSONArrayName = "List";
    private String JSONObjType = "Type";
    private String JSONObjMess = "Message";
    private String JSONObjFlag = "Flag";

    public String Parssing(JSONObject object){
        String str = null;
        try{
            JSONObject obj = object.getJSONObject("responseData");
            str = new String(obj.toString());
        }
        catch(JSONException je) {
            str = null;
        }
        return str;
    }
    public JSONObject convertJSONObj(String type, Object[] value){
        JSONObject obj = new JSONObject();
        if(type.compareTo(JSONObjMess) == 0) {
            try{
                obj.putOpt(type, value[0]);
            }
            catch (JSONException je){
                obj = null;
            }
        }
        return obj;
    }
}
