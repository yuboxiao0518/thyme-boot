
/*修改用户表字段nickname为nick_name*/
ALTER TABLE sys_user change  nickname nick_name varchar(50);

COMMIT;