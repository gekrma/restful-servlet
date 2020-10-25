package fastfoodFranchises.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import fastfoodFranchises.dto.Franchises;
import fastfoodFranchises.dto.ResObject;
import fastfoodFranchises.model.FranchisesDAO;

@WebServlet("/franchises/*")
public class FranchisesServlet extends HttpServlet {
	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();;
	private static FranchisesDAO franchisesDAO = FranchisesDAO.getInstance(); 
	
	private void sendAsJson( HttpServletResponse response, String resObject ) throws IOException {
		response.setContentType( "application/json" );
		response.setCharacterEncoding( "UTF-8" );
		PrintWriter out = response.getWriter();
		out.print( resObject );
		out.close();
	}
	
	private HashMap<String, String> paramMap( HttpServletRequest request ) {
		HashMap<String, String> hashMap = new HashMap<>();
		
		hashMap.put( "brand", request.getParameter( "brand" ) );
		hashMap.put( "company", request.getParameter( "company" ) );
		hashMap.put( "franchisesNum", request.getParameter( "franchisesNum" ) );
		hashMap.put( "avgSales", request.getParameter( "avgSales" ) );
		hashMap.put( "areaAvgSales", request.getParameter( "areaAvgSales" ) );
		
		return hashMap;
	}
	
	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		request.setCharacterEncoding( "UTF-8" );
		String pathInfo = request.getPathInfo();
		
		if ( pathInfo == null ) {
			getAll( request, response );
		} else {
			String[] splitted = pathInfo.split( "/" );
			
			if ( splitted.length > 2 ) {
				ResObject resObject = new ResObject( response.SC_NOT_FOUND, "경로가 잘못되었습니다." );
				sendAsJson( response, gson.toJson( resObject ) );
			} else {
				getOne( request, response );
			}
		}
	}

	protected void getAll( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		String param = request.getParameter( "order" );
		String order = ( ( param == null || param.equals( "null" ) ) ? "franchises_num" : param );
		int code = response.SC_OK;
		String serialized = null;
		ArrayList<Franchises> list = null;
		JsonArray jsonArray = null;
		Object data = null;
		
		try {
			list = franchisesDAO.getAllFranchises( order );
		} catch ( SQLException error ) {
			error.printStackTrace();
			code = response.SC_BAD_REQUEST;
			data = "입력한 값이 오라클 문법에 맞지 않습니다.";
		} catch ( Exception error ) {
			error.printStackTrace();
			code = response.SC_INTERNAL_SERVER_ERROR;
			data = "내부 서버에 이상이 발생하였습니다.";
		}

		if ( list != null ) {
			serialized = gson.toJson( list );
			jsonArray = gson.fromJson( serialized, JsonElement.class ).getAsJsonArray();
			data = jsonArray;
		}

	    	String resObject = gson.toJson( new ResObject( code, data ) );
	    	sendAsJson( response, resObject );
	}
	
	protected void getOne( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		String brand = request.getPathInfo().split( "/" )[1];
		String serialized = null;
		Franchises franchises = null;
		JsonObject jsonObject = null;
		Object data = null;
		int code;
		
		try {
			franchises = franchisesDAO.getFranchises( brand );
		} catch ( SQLException error ) {
			error.printStackTrace();
			code = response.SC_BAD_REQUEST;
			data = "입력한 값이 오라클 문법에 맞지 않습니다.";
		} catch ( Exception error ) {
			error.printStackTrace();
			code = response.SC_INTERNAL_SERVER_ERROR;
			data = "내부 서버에 이상이 발생하였습니다.";
		}

		if ( franchises != null ) {
			serialized = gson.toJson( franchises );
			jsonObject = gson.fromJson( serialized, JsonElement.class ).getAsJsonObject();
			code = response.SC_OK;
			data = jsonObject;
		} else {
			code = response.SC_NO_CONTENT;
			data = "브랜드명에 해당하는 데이터가 없습니다.";
		}

	    	String resObject = gson.toJson( new ResObject( code, data ) );
	    	sendAsJson( response, resObject );
	}
	
	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		request.setCharacterEncoding( "UTF-8" );
		String method = request.getParameter( "_method" );

		if ( method.equals( "put" ) ) {
			doPut( request, response );
			return;
		}
		
		if ( method.equals( "delete" ) ) {
			doDelete( request, response );
			return;
		}			
		
		HashMap<String, String> hashMap = paramMap( request );
		Object data = "성공적으로 추가되었습니다.";
		boolean insertResult = false;
		int code = response.SC_CREATED;

		try {
			insertResult = franchisesDAO.insertFranchises( 
							new Franchises( 
								hashMap.get( "brand" ), 
								hashMap.get( "company" ), 
								Integer.parseInt( hashMap.get( "franchisesNum" ) ), 
								Integer.parseInt( hashMap.get( "avgSales") ), 
								Integer.parseInt( hashMap.get( "areaAvgSales" ) )
								) 
							);
		} catch ( SQLException error ) {
			error.printStackTrace();
			code = response.SC_BAD_REQUEST;
			data = "입력한 값이 오라클 문법에 맞지 않습니다.";
		} catch ( Exception error ) {
			error.printStackTrace();
			code = response.SC_INTERNAL_SERVER_ERROR;
			data = "내부 서버에 이상이 발생하였습니다.";
		}

	    	String resObject = gson.toJson( new ResObject( code, data ) );
	    	sendAsJson( response, resObject );
	}

	protected void doPut( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		HashMap<String, String> hashMap = paramMap( request );
		Object data = "성공적으로 수정되었습니다.";
		boolean insertResult = false;
		int code = response.SC_NO_CONTENT;

		try {
			insertResult = franchisesDAO.updateFranchises( 
							new Franchises( 
								hashMap.get( "brand" ), 
								hashMap.get( "company" ), 
								Integer.parseInt( hashMap.get( "franchisesNum" ) ), 
								Integer.parseInt( hashMap.get( "avgSales") ), 
								Integer.parseInt( hashMap.get( "areaAvgSales" ) )
								) 
							);
		} catch ( SQLException error ) {
			error.printStackTrace();
			code = response.SC_BAD_REQUEST;
			data = "입력한 값이 오라클 문법에 맞지 않습니다.";
		} catch ( Exception error ) {
			error.printStackTrace();
			code = response.SC_INTERNAL_SERVER_ERROR;
			data = "내부 서버에 이상이 발생하였습니다.";
		}
		
	    	String resObject = gson.toJson( new ResObject( code, "" ) );
	    	sendAsJson( response, resObject );
	}

	protected void doDelete( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		String brand = request.getPathInfo().split( "/" )[1];
		Object data = "성공적으로 삭제되었습니다.";
		boolean deleteResult = false;
		int code = response.SC_NO_CONTENT;

		try {
			deleteResult = franchisesDAO.deleteFranchises( brand );
		} catch ( SQLException error ) {
			error.printStackTrace();
			code = response.SC_BAD_REQUEST;
			data = "입력한 값이 오라클 문법에 맞지 않습니다.";
		} catch ( Exception error ) {
			error.printStackTrace();
			code = response.SC_INTERNAL_SERVER_ERROR;
			data = "내부 서버에 이상이 발생하였습니다.";
		}
		
	    	String resObject = gson.toJson( new ResObject( code, data ) );
	    	sendAsJson( response, resObject );
	}

}
