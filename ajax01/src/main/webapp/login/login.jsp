<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원 가입</h1>
	<form>
		<p>
			아이디 
			<input type="text" name="id" id="id" />
			<input type="button" value="중복확인" id="check"/>
			<span id="result"></span>
		<p>
		
		<p>비밀번호 <input type="password" name="pwd" id="pwd" /></p>
		<p>이름 <input type="text" name="name" id="name" /></p>
		<p>이메일 <input type="text" name="email" id="email" /></p>
		
		<input type="button" value="가입" id="join" />
	</form>
	
	<script>
		const checkEl = document.getElementById('check');
		const joinEl = document.getElementById('join');
		
		checkEl.addEventListener('click', function(){
			console.log("xhr");
			
			let idEl = document.getElementById('id').value;
			let xhr = new XMLHttpRequest(); // 서버에 요청을 보낼 객체
			
			if(idEl == ""){
				document.getElementById('result').innerHTML = "입력된 아이디가 없습니다.";
				return;
			}
			
			xhr.onreadystatechange = function(){
				if(xhr.readyState == 4 && xhr.status == 200){
					let xml = xhr.responseXML;
					
					let result = document.getElementById("result");
					let dup = xml.getElementsByTagName('code')[0].innerHTML;
					
					if(dup == 'duplication'){
						result.innerHTML = "중복된 아이디가 존재합니다.";
						// alert('중복된 아이디가 존재합니다.');
					}else {
						result.innerHTML = "생성 가능한 아이디입니다.";
					}
				}
			}
			xhr.open("get", "${pageContext.request.contextPath}/check_id?id="+idEl, true);
			xhr.send();
		});
		
		
	</script>
</body>
</html>