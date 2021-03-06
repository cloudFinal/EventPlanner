package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;

import parse.JsonArrayListGenerator;
import parse.JsonProcess;
import parse.Parse;
import beans.Activity;
import beans.Event;
import beans.Location;
import beans.Preference;

/**
 * Servlet implementation class LookUpEvent
 */
public class LookUpEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LookUpEvent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Preference preference = new Preference();
		preference.setAll("zhangluoma", "hello", 1, 1000, 2, 20, "w", "swimming",1,10);
		ArrayList<Event> result = Center.db.getEventAtPrefer(preference);
		for(Event e:result){
			System.out.println(e.getHeldIn());
			System.out.println(e.getActivityName());
		}
		JsonProcess.sendJson(response,new JsonArrayListGenerator<Event>(result).getObject());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String receivedData = Parse.getPostData(request);
		JSONObject jo = JsonProcess.getJason(receivedData);
		String obj = jo.getString("1");
		Gson gson = new Gson();
		Preference preference = gson.fromJson(obj, Preference.class);
		ArrayList<Event> result = Center.db.getEventAtPrefer(preference);
		JsonProcess.sendJson(response,new JsonArrayListGenerator<Event>(result).getObject());
	}
}
