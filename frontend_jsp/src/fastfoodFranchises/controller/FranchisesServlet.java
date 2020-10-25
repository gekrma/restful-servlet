package fastfoodFranchises.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import fastfoodFranchises.dto.Franchises;
import fastfoodFranchises.dto.ResObjDefault;
import fastfoodFranchises.dto.ResObjGetAll;

@WebServlet("/franchises.do")
public class FranchisesServlet extends HttpServlet {
	private String origin = "http://127.0.0.1:8081";
	private String projectName = "backend_servlet";
	private String servletName = "franchises";
	private String defaultUrl = ( origin + "/" + projectName + "/" + servletName );
    private Gson gson = new Gson();
	
	protected void service( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		request.setCharacterEncoding( "UTF-8" );
		String param = request.getParameter( "command" );
		String command = ( param == null ? "" : param );

		switch ( command ) {
			case "franchisesList":
				franchisesList( request, response );
				break;
			case "franchisesView":
				franchisesView( request, response );
				break;
			case "franchisesInsert":
			case "franchisesUpdate":
				franchisesExecute( request, response );
				break;
			case "franchisesDelete":
				franchisesDelete( request, response );
				break;
			default:
				franchisesList( request, response );
		}
	}

	private HashMap<String, String> paramMap( HttpServletRequest request ) {
		HashMap<String, String> hashMap = new HashMap<>();

		hashMap.put( "_method", request.getParameter( "_method" ) );
		hashMap.put( "brand", request.getParameter( "brand" ) );
		hashMap.put( "company", request.getParameter( "company" ) );
		hashMap.put( "franchisesNum", request.getParameter( "franchisesNum" ) );
		hashMap.put( "avgSales", request.getParameter( "avgSales" ) );
		hashMap.put( "areaAvgSales", request.getParameter( "areaAvgSales" ) );
		
		return hashMap;
	}
	
	private boolean checkBlank( HashMap<String, String> map ) {
		String value = null;
		
		for ( Entry<String, String> entry : map.entrySet() ) {
		    value = entry.getValue();
		    
		    if ( ( value == null ) || ( value.trim().length() == 0 ) ) {
		    	return true;
		    }
		}
		
		return false;
	}
	
	private String queryString( HashMap<String, String> map ) throws UnsupportedEncodingException {
		StringBuilder query = new StringBuilder();
		String key = null;
		String value = null;
		String encode = null;

		for ( Entry<String, String> entry : map.entrySet() ) {
			key = entry.getKey();
			value = entry.getValue();
			
			query.append( key + "=" + URLEncoder.encode( value, "UTF-8" ) + "&" );
		}

		return query.deleteCharAt( query.length() - 1 ).toString();
	}
	
	protected void franchisesList( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		String order = request.getParameter( "order" );
		URL url = new URL( defaultUrl + "?order=" + order );
        		HttpURLConnection connection = ( HttpURLConnection ) url.openConnection();
		
        		BufferedReader input = new BufferedReader( new InputStreamReader( connection.getInputStream() ) );
        		StringBuilder builder = new StringBuilder();
        		String line;
        
        		while ( ( line = input.readLine() ) != null ) {
            			builder.append( line );
        		}

        		input.close();

	    	ResObjGetAll resObject = gson.fromJson( builder.toString(), ResObjGetAll.class );
	    	String path = null;

	    	if ( resObject.getCode() != 200 ) {
	    		ResObjDefault resObj = gson.fromJson( builder.toString(), ResObjDefault.class );
			request.setAttribute( "error", resObj.getData() );
	    		path = "view/errorView.jsp";
	    	} else {
			request.setAttribute( "list", resObject.getData() );
			path = "view/franchisesListView.jsp";
	    	}
	    
		request.getRequestDispatcher( path ).forward( request, response );
	}
	
	private void franchisesView( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		StringBuilder serialized = new StringBuilder( "{" ).append( request.getParameter( "data" ) ).append( "}" );
		Franchises franchises = gson.fromJson( serialized.toString(), Franchises.class );
		request.setAttribute( "data", franchises );
		request.getRequestDispatcher( "view/franchisesView.jsp" ).forward( request, response );
	}
	
	private void franchisesExecute( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		HashMap<String, String> hashMap = paramMap( request );
		boolean hasBlank = checkBlank( hashMap );
		
		if ( hasBlank ) {
			response.sendRedirect( "view/franchisesInsertView.jsp" );
			return;
		}

		String method = hashMap.get( "_method" );
		String url = defaultUrl;
		int code;

		if ( method.equals( "put" ) ) {
			url += ( "/" + URLEncoder.encode( hashMap.get( "brand" ), "UTF-8" ) );
			code = response.SC_NO_CONTENT;
		} else {
			code = response.SC_CREATED;
		}

		URL reqUrl = new URL( url + "?" + queryString( hashMap ) );
        		HttpURLConnection connection = ( HttpURLConnection ) reqUrl.openConnection();
        		connection.setRequestMethod( "POST" );

        		BufferedReader input = new BufferedReader( new InputStreamReader( connection.getInputStream() ) );
        		StringBuilder builder = new StringBuilder();
        		String line;
        
        		while ( ( line = input.readLine() ) != null ) {
            			builder.append( line );
        		}

        		input.close();
	    	ResObjDefault resObject = gson.fromJson( builder.toString(), ResObjDefault.class );

	    	if ( resObject.getCode() != code ) {
			request.setAttribute( "error", resObject.getData() );
			request.getRequestDispatcher( "view/errorView.jsp" ).forward( request, response );
	    	} else {
			response.sendRedirect( "franchises.do" );
	    	}
	}
	

	private void franchisesDelete( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException  {
		String method = request.getParameter( "_method" );
		String brand = request.getParameter( "brand" );
		String url = (  defaultUrl + "/" + URLEncoder.encode( brand, "UTF-8" ) );
		URL reqUrl = new URL( url + "?" + "_method=" + URLEncoder.encode( method, "UTF-8" ) );
        		HttpURLConnection connection = ( HttpURLConnection ) reqUrl.openConnection();
        		connection.setRequestMethod( "POST" );
        
        		BufferedReader input = new BufferedReader( new InputStreamReader( connection.getInputStream() ) );
        		StringBuilder builder = new StringBuilder();
        		String line;
        
        		while ( ( line = input.readLine() ) != null ) {
            			builder.append( line );
        		}
        
        		input.close();
	    	ResObjDefault resObject = gson.fromJson( builder.toString(), ResObjDefault.class );
	    
	    	if ( resObject.getCode() != response.SC_NO_CONTENT ) {
			request.setAttribute( "error", resObject.getData() );
			request.getRequestDispatcher( "view/errorView.jsp" ).forward( request, response );
	    	} else {
			response.sendRedirect( "franchises.do" );
	    	}
	}

}
