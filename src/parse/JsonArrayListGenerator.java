package parse;

import java.util.ArrayList;

import org.json.JSONObject;

import com.google.gson.Gson;

public class JsonArrayListGenerator<E> {
	JSONObject jo;
	public JsonArrayListGenerator(ArrayList<E> input){
		jo = new JSONObject();
		int count=0;
		for(E object:input){
			Gson gson = new Gson();
			jo.put(String.valueOf(count++),gson.toJson(object));
		}
	}
	public JSONObject getObject(){
		return jo;
	}
}
