package parse;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class JsonProcess {
	public static boolean sendJson(HttpServletResponse response,JSONObject jsonObject){
		response.setContentType("application/json");  
		PrintWriter out=null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			return false;
		}
		out.print(jsonObject);
		out.flush();
		return true;
	}
	public static JSONObject getJason(String receivedData){
		JSONObject jo=null;
		if(receivedData.startsWith("{")){
			jo = new JSONObject(receivedData);
		}
		return jo;
	}
}
