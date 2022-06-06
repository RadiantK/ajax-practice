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
	<div id="page"></div>
	
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
					
					// let pageTag = xml.getElementsByTagName('page')[0].innerHTML;
					let list = xml.getElementsByTagName('list');
					let commentsEl = document.getElementById('commentsList');
					
					let childs = commentsEl.childNodes;
					console.log(childs);
					for(let i = childs.length - 1; i >= 0; i--){
						let child = childs.item(i);
						commentsEl.removeChild(child);
					}
					
					for(let i = 0; i < list.length; i++){
						let num = list[i].getElementsByTagName('num')[0].innerHTML;
						let bnum = list[i].getElementsByTagName('bnum')[0].innerHTML;
						// 작성자 아이디
						let id = list[i].getElementsByTagName('id')[0].innerHTML;
						// 댓글 내용
						let comments = list[i].getElementsByTagName('comments')[0].innerHTML;
						// 현재 페이지
						let pageTag = xml.getElementsByTagName('page')[0].innerHTML;
						
						let divEl = document.createElement('div');
						divEl.setAttribute('class', 'commentsList');
						divEl.innerHTML = "번호 : " + num + "<br/>" +
							"아이디 : " + id + "<br/>" + 
							"내용 : " + comments + "<br/>" + 
							"<a href='javascript:deleteComments("+ num +","+ pageTag +")'>삭제</a>";
						
						commentsEl.appendChild(divEl);
					}
					
					let pageEl = document.getElementById('page');
					let pageChilds = pageEl.childNodes;
					for(let i = pageChilds.length-1; i >= 0; i--){
						let child = pageChilds.item(i);
						pageEl.removeChild(i);
					}
					
					let startPage = xml.getElementsByTagName('startPage')[0].innerHTML;
					let endPage = xml.getElementsByTagName('endPage')[0].innerHTML;
					
					for(let i = startPage; i <= endPage; i++){
						let span = document.createElement('span')
						span.innerHTML = "<a href='javascript:commentsList("+ i +")'>"+ i +"</a> ";
						
						pageEl.appendChild(span);
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