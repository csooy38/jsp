create table practice(
	practice_no number(5) primary key,			-- 게시글 글번호
	practice_writer varchar2(20) not null,		-- 게시글 작성자
	practice_title varchar2(100) not null, 	-- 게시글 제목
	practice_cont varchar2(1000),				-- 게시글 글내용
	practice_pwd varchar2(20) not null,		-- 게시글 비밀번호
	practice_hit number(5) default 0, 			-- 게시글 조회수
	practice_regdate date						-- 게시글 작성일
);

alter table practice add(practice_head varchar2(100));

create sequence pr_seq
start with 1
increment by 1
nocache;

insert into practice values(pr_seq.nextval, '홍길동', '제목1', '길동이 글', '1111', default, sysdate);
insert into practice values(pr_seq.nextval, '이순신', '거분석', '내거', '2222', default, sysdate);
insert into practice values(pr_seq.nextval, '유관순', '대한독립만세', '만세', '3333', default, sysdate);
insert into practice values(pr_seq.nextval, '김유신', '유신이글', '유신이꺼', '4444', default, sysdate);
insert into practice values(pr_seq.nextval, '김연아', '제목5', '내용없음', '5555', default, sysdate);

create table pr_member(
    pr_no number(5),
    pr_id varchar2(20) primary key,
    pr_pwd varchar2(20) not null,
    pr_name varchar2(20) not null,
    pr_phone varchar2(100),
    pr_addr varchar2(1000)
);

create sequence pr_member_seq
start with 1
increment by 1
nocache;

insert into pr_member values(pr_member_seq.nextval, 'test1', '1111', '테스트1', '02-1111-1111', '1구 1동 1번지');
insert into pr_member values(pr_member_seq.nextval, 'test2', '2222', '테스트2', '02-2222-2222', '2구 2동 2번지');
insert into pr_member values(pr_member_seq.nextval, 'test3', '3333', '테스트3', '02-3333-3333', '3구 3동 3번지');

commit;