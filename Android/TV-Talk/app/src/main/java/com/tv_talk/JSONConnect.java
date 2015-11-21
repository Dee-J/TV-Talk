package com.tv_talk;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONConnect {
    private String JSONArrayName = "List";
    private String JSONObjType = "Type";
    private String JSONObjMess = "Message";
    private String JSONObjFlag = "Flag";

    private String textjson = "{List:[{\"Type\": \"MessagePush\", \"Message\": \"test\"},"+
    "{\"Type\": \"MessagePush\", \"Message\": \"test1\"},"+
            "{\"Type\": \"MessagePush\", \"Message\": \"test2\"},"+
            "{\"Type\": \"MessagePush\", \"Message\": \"test3\"},"+
            "{\"Type\": \"MessagePush\", \"Message\": \"test4\"},"+
            "{\"Type\": \"MessagePush\", \"Message\": \"test5\"},"+
            "{\"Type\": \"MessagePush\", \"Message\": \"test6\"},"+
            "{\"Type\": \"MessagePush\", \"Message\": \"test7\"},"+
            "{\"Type\": \"MessagePush\", \"Message\": \"test8\"},"+
            "{\"Type\": \"MessagePush\", \"Message\": \"test9\"},"+
            "{\"Type\": \"MessagePush\", \"Message\": \"test10\"},"+
            "{\"Type\": \"MessagePush\", \"Message\": \"test11\"},"+
            "{\"Type\": \"MessagePush\", \"Message\": \"test12\"},"+
            "{\"Type\": \"MessagePush\", \"Message\": \"test13\"},"+
            "{\"Type\": \"MessagePush\", \"Message\": \"test14\"},"+
    "{\"Type\": \"MessagePush\", \"Message\": \"test15\"}]}";

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
    public String gettextJSON(){ return this.textjson; }
    public JSONArray textJSONPassing(){
        JSONArray arr;
        try {
            JSONObject obj123 = new JSONObject(this.textjson);
            arr = new JSONArray(obj123.getString("List"));
            return arr;
        }
        catch (JSONException je) {
            arr = null;
        }

        return arr;
    }
}
