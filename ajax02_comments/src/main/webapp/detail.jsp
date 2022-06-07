<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#wrap {
		width: 400px;
		height: 200px;
		background-color : #ddd;
	}
	.commentsList {
		width: 400px;
		height: 100px;
		margin-top: 10px;
		border: 2px solid #ddd;
	}
	#addComment {
		margin-top : 10px;
	}
	#pageControll {
		display: flex;
	}
	#pageControll #prev span,
	#pageControll #next span{
		cursor: pointer;
		color: blue;
	}
	#pageControll #prev {
		margin-right : 5px;
	}
	#pageControll #next {
		margin-left : 5px;
	}
	a {
		text-decoration: none;
	}
</style>
</head>
<body>
	<div id="wrap">
		<h1>제목 : ${b.title}</h1>
		<p>작성자 : ${b.writer}</p>
		<p>내용 : ${b.content}</p>
	</div>
	<div id="commentsList">
	</div>
	<div id="pageControll">
		<div id="prev"></div>
		<div id="page"></div>
		<div id="next"></div>
	</div>
	
	<div id="addComment">
		아이디<br/>
		<input type="text" id="id" /><br/>
		영화평<br/>
		<textarea rows="4" cols="50" id="comments"></textarea><br/>
		<input type="button" value="등록" id="addBtn" />
	</div>
	
	<script>
		const addBtnEl = document.getElementById('addBtn');
	
		function commentsList(page){
			xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function(){
				if(xhr.readyState == 4 && xhr.status == 200){
					let xml = xhr.responseXML;
					
					let list = xml.getElementsByTagName('list');
					let commentsEl = document.getElementById('commentsList');
					// 현재 페이지
					let pageTag = xml.getElementsByTagName('page')[0].innerHTML;
					
					/*
						목록을 다시 불렀을 때 이전 요소를 지우도록 함
					*/
					let childs = commentsEl.childNodes;
					console.log(childs);
					for(let i = childs.length - 1; i >= 0; i--){
						let child = childs.item(i);
						commentsEl.removeChild(child);
					}
					
					/*
						댓글 출력
					*/
					for(let i = 0; i < list.length; i++){
						let num = list[i].getElementsByTagName('num')[0].innerHTML;
						let bnum = list[i].getElementsByTagName('bnum')[0].innerHTML;
						// 작성자 아이디
						let id = list[i].getElementsByTagName('id')[0].innerHTML;
						// 댓글 내용
						let comments = list[i].getElementsByTagName('comments')[0].innerHTML;
						
						let divEl = document.createElement('div');
						divEl.setAttribute('class', 'commentsList');
						divEl.innerHTML = "번호 : " + num + "<br/>" +
							"아이디 : " + id + "<br/>" + 
							"내용 : " + comments + "<br/>" + 
							"<a href='javascript:deleteComments("+ num +","+ pageTag +")'>삭제</a>";
						
						commentsEl.appendChild(divEl);
					}
					
					let startPage = xml.getElementsByTagName('startPage')[0].innerHTML;
					let endPage = xml.getElementsByTagName('endPage')[0].innerHTML;
					let pageCount = xml.getElementsByTagName('pageCount')[0].innerHTML;
					
					let pageEl = document.getElementById('page'); // 페이지 목록을 담을 변수
					let prevEl = document.getElementById('prev'); // 이전페이지를 담을 변수
					let nextEl = document.getElementById('next'); // 다음페이지를 담을 변수
					
					let prevChilds = prevEl.childNodes;
					let nextChilds = nextEl.childNodes;
					
					for(let i = prevChilds.length -1; i >= 0; i--){
						prevEl.removeChild(prevChilds.item(i));
						nextEl.removeChild(nextChilds.item(i));
					}
					
					/*
						이전페이지 처리
					*/
					let prevTemp = "";
					if(parseInt(pageTag) <= 1){
						prevTemp = document.createElement('span');
						prevTemp.addEventListener('click', function(){
							alert('이전 페이지가 없습니다.');
						})
						prevTemp.innerHTML = "이전";
					} else {
						prevTemp = document.createElement('a');
						let tempTag = parseInt(pageTag)-1;
						prevTemp.href="javascript:commentsList("+ tempTag +")";
						prevTemp.innerHTML = "이전";
					}
					prevEl.appendChild(prevTemp);
					
					/*
						다음페이지 처리
					*/
					let nextTemp = ""
					if(parseInt(pageTag) >= pageCount){
						nextTemp = document.createElement('span');
						nextTemp.addEventListener('click', function(){
							alert('다음 페이지가 없습니다.');
						})
						nextTemp.innerHTML = "다음";
					} else {
						nextTemp = document.createElement('a');
						let tempTag = parseInt(pageTag)+1;
						nextTemp.href="javascript:commentsList("+ tempTag +")";
						nextTemp.innerHTML = "다음";
					}
					nextEl.appendChild(nextTemp);
					
					/*
						목록을 다시 불렀을 때 이전 페이지번호 요소를 지우도록 함
					*/
					let pageChilds = pageEl.childNodes;
					for(let i = pageChilds.length-1; i >= 0; i--){
						let child = pageChilds.item(i);
						pageEl.removeChild(child);
					}
					
					/*
						페이지 출력
					*/
					for(let i = startPage; i <= endPage; i++){
						let aTag = document.createElement('a');
						aTag.href = "javascript:commentsList("+ i +")";
						if(pageTag == i){
							aTag.innerHTML = "<span style='color:red;'>"+ i +" </span>"
						}else {
							aTag.innerHTML = "<span style='color:#000;'>"+ i +" </span>"
						}
						
						pageEl.appendChild(aTag);
					}
				}
			}
			xhr.open("get", "${pageContext.request.contextPath}/comments/list?bnum=${b.bnum}&p="+page, true);
			xhr.send();
		}
		
		function deleteComments(num, pageTag) {
			let xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function(){
				if(xhr.readyState == 4 && xhr.status == 200){
					let xml = xhr.responseXML;
					let code = xml.getElementsByTagName('code')[0].innerHTML;
					
					if(code == 'success'){
						commentsList(pageTag);
					}else {
						alert('실패입니다.');
					}
				}
			}
			xhr.open('get', '${pageContext.request.contextPath}/comments/delete?num='+num, true);
			xhr.send();
		}
		
		addBtnEl.addEventListener('click', function(){
			let id = document.getElementById('id').value;
			let comments = document.getElementById('comments').value;
			
			let xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function(){
				if(xhr.readyState == 4 && xhr.status == 200){
					let xml = xhr.responseXML;
					
					let code = xml.getElementsByTagName('code')[0].innerHTML;
					if(code == 'success'){
						document.getElementById('id').value = "";
						document.getElementById('comments').value = "";
						commentsList(1);
					}else {
						alert('등록중 오류가 발생했습니다.');
					}
				}
			}
			xhr.open('post', '${pageContext.request.contextPath}/comments/insert', true);
			xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			let param = "bnum=${b.bnum}&id="+ id +"&comments=" + comments;
			xhr.send(param);
		});
		
		window.onload = function(){
			commentsList(1);
		}
	</script>
</body>
</html>