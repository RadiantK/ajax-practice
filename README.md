# AJAX

ajax(asynchronous JavaScript And Xml)란 자바스크립트 라이브러리중 하나로 자바스크립트를 이용하여 비동기 통신을 하는 방식이다.  
보통 서버와 통신을할 때 form 태그를 사용해서 submit으로 서버와 통신하면 웹 페이지 전체가 리로드 되는데, ajax를 사용하면 화면에서 필요한 부분의 데이터만 갱신할 수 있다.  
  
ajax는 XMLHttpRequest객체를 통해서 웹 서버와 통신하며 웹 서버의 응답결과는 xml또는 텍스트(html, JSON)로 받는다.

## XMLHttpRequest
자바스크립트를 사용해서 비동기로 HTTP요청을 전송하기 위한 객체  

**주요 메소드**

-   onreadystatechange : readyState가 변할 때마다 발생하는 이벤트 핸들러, callback 함수로 요청이 처리되었을 때 실행할 함수를 호출한다.
-   readyState : Http요청의 현재 상태를 나타내는 메소드
    -   readyState == 0 : XMLHttpRequest객체 생성
    -   readyState == 1 : open메소드 호출(send메소드 호출 전)
    -   readtState == 2 : send메소드 호출(요청을 서버가 받은 상태)
    -   readtState == 3 : 서버에서 요청을 처리중인 상태
    -   readtState == 4 : 요청 처리가 완료되고 서버에서 응답할 준비가 완료된 상태
-   status : 상태 값이 200이면 서버가 요청을 정상적으로 처리했다는 의미이다.
-   open : 서버요청에 대한 정보를 초기화한다. open(요청방식, url, 비동기 여부)
-   send : 서버에 요청을 전송한다. 요청이 post방식이면 메소드의 인자로 파라미터를 전달한다.
-   responseText : 응답결과를 text로 얻어온다.
-   responseXML : 응답결과를 XML형식으로 DOM으로 얻어온다.

응답받은 결과를 span태그를 통해 텍스트로 처리할 수 도있고 alert를 통해 경고창을 표시할 수도 있다.  
XML형식의 결과를 얻어올 때 DOM객체의 getElementsByTagName메소드를 통해 태그값을 배열로 얻어올 수 있다.


## 프로젝트
- ajax01_idcheck : 아이디 중복확인
- ajax02_comments : 게시물 댓글 추가, 삭제 및 댓글 

## 개발환경
- EclipseIDE
- JAVA 11(JDK 11)
- tomcat(9.0.56)
- MySQL(8.0.27)
