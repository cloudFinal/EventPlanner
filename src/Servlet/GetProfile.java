package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import parse.JsonProcess;
import parse.Parse;
import beans.Profile;

import com.google.gson.Gson;

/**
 * Servlet implementation class GetProfile
 */
public class GetProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetProfile() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userId = "zhangluoma";
		Profile profile = Center.db.getProfile(userId);
		boolean result=false;
		String obj=null;
		if(profile!=null){
			result=true;
			Gson gson = new Gson();
			obj = gson.toJson(profile);
		}
		JSONObject output = new JSONObject();
		output.put("result", result);
		output.put("object", obj);
		JsonProcess.sendJson(response, output);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Get Profile");
		String text = Parse.getPostData(request);
		System.out.println(text);
		JSONObject input = JsonProcess.getJason(text);
		String userId = input.getString("userid");
		Profile profile = Center.db.getProfile(userId);
		boolean result=false;
		String obj=null;
		if(profile!=null){
			result=true;
			Gson gson = new Gson();
			obj = gson.toJson(profile);
		}
		JSONObject output = new JSONObject();
		output.put("result", result);
		output.put("object", obj);
		JsonProcess.sendJson(response, output);
	}

}
