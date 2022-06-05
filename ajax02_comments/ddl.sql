CREATE TABLE board (
	mnum INT(5) primary key auto_increment,
    writer varchar(30),
	title varchar(50),
	content varchar(100)
);

CREATE TABLE comments (
	num INT(5) primary key auto_increment, -- 댓글번호
	mnum INT(5) references movie(mnum), -- 글번호
	id varchar(20), -- 작성자
	comments varchar(100) -- 내용
);

INSERT INTO board(writer, title, content) VALUES('이길동', '닥터스트레인지', '무서운영화');
INSERT INTO board(writer, title, content) VALUES('박길동', '토르', '재밌는영화');
commit;

INSERT INTO comments(mnum, id, comments) VALUES(1, '일동이', '재밌어요!');
INSERT INTO comments(mnum, id, comments) VALUES(1, '이동이', '무서워요!');
INSERT INTO comments(mnum, id, comments) VALUES(2, '삼동이', '신나요!!');
commit;
