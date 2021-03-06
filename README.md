1. 주제 : 패스트푸드 프랜차이즈 현황 (https://franchise.ftc.go.kr/user/extra/main/70/firHope/listBrand/jsp/LayOutPage.do)
		- 각 프랜차이즈의 브랜드명과 회사명, 가맹점수, 평균매출액, 면적당 평균매출액 등 현황 조회하고 그외 기본적인 생성과 변경 및 삭제
2. 목표
	- Backend(backend_servlet)
		- (1). doGet, doPost, doPut, doDelete 등 HttpServletRequest 활용
		- (2). Rest API 가이드라인 준수
		- (3). PrintWirter 로 Json 정상 출력
	
	- Frontend(frontend_jsp)
		- (1). Encoding 으로써 정상적으로 uri 를 지정해서 Rest API 를 호출하기
		- (2). Rest API 호출로부터 JSON 과 DTO 를 이용하여 정상적으로 값 받아오기
		- (3). EL, JSTL 등 JSP 태그 활용
		- (4). w3schools 로부터 html/css 양식 갖다쓰기
		
3. 패턴
	- Frontend
		- (1). MVC
		- (2). Singleton
		- (3). Command
		
4. 기능
	- 1. Rest API 구축
	- 2. 전체 프랜차이즈를 정렬하여 조회
	- 3. 그외 기본적인 프랜차이즈 추가 및 변경, 삭제 (*프랜차이즈 변경 및 삭제는 전체 출력에서 브랜드를 클릭하면 들어가집니다.)

5. 구현된 것
	- Backend
		- (1). doGet/doPost/doPut/doDelete 등 HttpServletRequest 활용
		- (2). Rest API 가이드라인 준수하여 구축 ( CRUD 완료 )
		- (3). DTO 와 Json 을 이용하여 PrintWriter 로 보기 좋게 출력
	
	- Frontend
		- (1). Encoding 과 HttpURLConnection 으로 문자열을 파라미터로 보내서 Rest API 호출
		- (2). Rest API 로부터 Json 과 DTO 를 이용하여 결과값을 사용가능한 객체로 받아오기 ( CRUD 완료 )
		- (3). EL, JSTL 등 JSP 태그 활용
		- (4). w3schools 로부터 html/css 양식 갖다쓰기

6. 추후 해야할 것(todo)
	- Backend
		- (1). Rest API 가이드라인 정독
		- (2). 자바 Rest API 코드 보기
	
	- Frontend
		- (1). 동작에 따른 url 설계 고민
		- (2). HttpURLConnection 이 아닌 더 좋은 http 호출 라이브러리 확인해보기 (QueryString 과 Encoding 에 효율적인)


**프로그램 동작은 백엔드와 프론트엔드 프로그램 둘다 서버 실행하고, 프론트엔드의 메인페이지(main.jsp) 로 동작해보시면 되겠습니다.
	
*backend_servlet 규격서

*기본 URL = "http://127.0.0.1:8081/backend_servlet/franchises
	( backend_servlet : 프로젝트명, franchises : 서블릿명 )
	( 프로젝트명은 제외할 수 있으나 별도로 설정해야 하고 또, 공유를 위한 프로젝트이기 때문에 보류하였음 )

*DTO : 1. new Franchises(<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	String brand 	// 브랜드(PK)<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	String company	// 상호<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	int franchisesNum	// 가맹점수<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	int avgSales	// 평균매출액<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	int areaAvgSales	// 면적당 평균매출액<br />
		);

1. GET
	- (1) 기본 URL : 전체 프랜차이즈를 가맹점수 내림차순으로 출력
			
	예시) GET - http://127.0.0.1:8081/backend_servlet/franchises<br />
	결과)<br />
	{<br />
	  "code": 200,<br />
	  "data": [<br />
	    {<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	      "brand": "롯데리아",<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	      "company": "롯데지알에스(주)",<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	      "franchisesNum": 1207,<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	      "avgSales": 647888,<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	      "areaAvgSales": 12543<br />
	    },<br />
	    {<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	      "brand": "맘스터치",<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	      "company": "해마로푸드서비스(주)",<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	      "franchisesNum": 1167,<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	      "avgSales": 425896,<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	      "areaAvgSales": 17326<br />
	    },<br />
	    {<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	      "brand": "뉴욕핫도그앤커피",<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	      "company": "(주)뉴욕핫도그앤커피",<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	      "franchisesNum": 120,<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	      "avgSales": 48858,<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	      "areaAvgSales": 4696<br />
	    },<br />
	    {<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	      "brand": "버거킹(Burger King)",<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	      "company": "(주)비케이알",<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	      "franchisesNum": 99,<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	      "avgSales": 913851,<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   	      "areaAvgSales": 12792<br />
      	    },<br />
   		...<br />
  	  ]<br />
    	}<br />
    
    - (2) 기본 URL + ?order={order} : 전체 프랜차이즈를 franchisesNum 혹은 avgSales, areaAvgSales 내림차순으로 출력<br />
          예시) GET - http://127.0.0.1:8081/backend_servlet/franchises?order=avgSales<br />
          결과) 위와 동일
          
    - (3) 기본 URL + /{brand} : 전체 프랜차이즈에서 브랜드로 조회<br />
          예시) GET - http://127.0.0.1:8081/backend_servlet/franchises/롯데리아<br />
          결과)<br />
     	{<br />
		"code": 200,<br />
		"data": {<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			"brand": "롯데리아",<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			"company": "롯데지알에스(주)",<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			"franchisesNum": 1207,<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			"avgSales": 647888,<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			"areaAvgSales": 12543<br />
		}<br />
	 }<br />
	 
2. POST 
	- (1) 기본 URL + ?_method=post&brand={brand}&company={company}&franchisesNum={franchisesNum}&avgSales={avgSales}&areaAvgSales={areaAvgSales} : 프랜차이즈 추가<br />
	 예시) POST - http://127.0.0.1:8081/backend_servlet/franchises?_method=post&brand=집게리아&company=스폰지밥&franchisesNum=10&avgSales=300000&areaAvgSales=11111<br />
	 결과)<br />	
     	    {<br />
		"code": 201,<br />
		"data": "성공적으로 추가되었습니다."<br /> 
	     }<br />
3. PUT
	- (1) 기본 URL + /{brand} + ?_method=put&{위의 POST와 동일} : 프랜차이즈 수정<br />
	 예시) POST - http://127.0.0.1:8081/backend_servlet/franchises/집게리아?_method=put&brand=스폰지리아&company=스폰지밥&franchisesNum=20&avgSales=400000&areaAvgSales=22222<br />
	 결과)<br />	
     	    {<br />
		"code": 204,<br />
		"data": "성공적으로 수정되었습니다."<br /> 
	     }<br />
	
4. DELETE
	- (1) 기본 URL + /{brand} + ?_method=delete<br />
	 예시) POST - http://127.0.0.1:8081/backend_servlet/franchises/롯데리아?_method=delete<br />
	 결과)<br />	
     	    {<br />
		"code": 204,<br />
		"data": "성공적으로 삭제되었습니다."<br /> 
	     }<br />
	 
5. code
	- (1) 200 : HttpServletResponse.SC_OK, 성공적으로 조회하여 데이터를 받아왔을 때. 주로 GET
	- (2) 201 : HttpServletResponse.SC_CREATED, 성공적으로 생성하였을 때. POST
	- (3) 204 : HttpServletResponse.SC_NO_CONTENTS, 처리는 되었으나 받아오는 데이터가 없을 때. GET/PUT/DELETE
	- (4) 400 : HttpServletResponse.SC_BAD_REQUEST, sql 문법이 잘못되었을 때.
	- (5) 404 : HttpServletResponse.SC_NOT_FOUND, 경로가 잘못되었을 때.
	- (6) 500 : HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 내부 서버 에러가 있을 때.
	 