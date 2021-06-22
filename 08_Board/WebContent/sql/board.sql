-- board 테이블 생성

create table board(
	board_no number(5) primary key,			-- 게시글 글번호
	board_writer varchar2(20) not null,		-- 게시글 작성자
	board_title varchar2(100) not null, 	-- 게시글 제목
	board_cont varchar2(1000),				-- 게시글 글내용
	board_pwd varchar2(20) not null,		-- 게시글 비밀번호
	board_hit number(5) default 0, 			-- 게시글 조회수
	board_regdate date						-- 게시글 작성일
);


-- board 테이블 시퀀스 생성
create sequence board_seq
start with 1
increment by 1
nocache;


-- board 테이블에 데이터 추가
insert into board values(board_seq.nextval, '홍길동', '제목1', '길동이 글', '1111', default, sysdate);
insert into board values(board_seq.nextval, '이순신', '거분석', '내거', '2222', default, sysdate);
insert into board values(board_seq.nextval, '유관순', '대한독립만세', '만세', '3333', default, sysdate);
insert into board values(board_seq.nextval, '김유신', '유신이글', '유신이꺼', '4444', default, sysdate);
insert into board values(board_seq.nextval, '김연아', '제목5', '내용없음', '5555', default, sysdate);