-- customer 테이블 생성

create table customer(
	no number(5) unique,			-- 고객 번호
	id varchar2(20) primary key,	-- 고객 아이디
	name varchar2(20) not null,		-- 고객 이름
	age number(3),					-- 고객 나이
	phone varchar2(20),				-- 고객 연락처
	addr varchar2(200)				-- 고객 주소
);

-- 고객 정보 저장
insert into customer values(1, 'hong', '홍길동', 27, '010-1111-1111', '서울시 마포구');
insert into customer values(2, 'id2', '테스트', 21, '010-2222-2222', '제주도 서귀포시');
insert into customer values(3, 'id3', '고구마', 80, '010-3333-3333', '서울시 강서구');
insert into customer values(4, 'id4', '왕감자', 35, '010-4444-4444', '서울시 강동구');
insert into customer values(5, 'star', '김오이', 56, '010-5555-5555', '용인시 처인구');