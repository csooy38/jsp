create table board_comment (
    comment_no number(5),
    comment_board_no number(5),
    comment_writer varchar2(20),
    comment_cont varchar2(1000),
    comment_pwd varchar2(20),
    comment_sysdate date,
    foreign key(comment_board_no) references board(board_no)
);

alter table board add board_c_count number(20) default 0;