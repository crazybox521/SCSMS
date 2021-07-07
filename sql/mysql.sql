/*
创建一个数据库
名字叫做chenyu_bsdb
本数据库表使用innodb引擎
*/


DROP DATABASE IF EXISTS chenyu_bsdb;
create database if not exists chenyu_bsdb character set UTF8;

use chenyu_bsdb;

/**
 * 
 * 角色表
 * role:id,role,notes
 * **/

create table role(
	id int primary key auto_increment,
	name varchar(30) not null,
	notes varchar(300)
);
insert into role(id,name,notes) values(1,'administrator','administrator');
insert into role(id,name,notes) values(2,'teacher','teacher');
insert into role(id,name,notes) values(3,'student','student');


/**
 * 
 *用户表
 * user:id,username,password,roleid
 * **/
create table user(
	id int primary key auto_increment,
	username varchar(30) not null,
	password varchar(70) not null,
	roleid int,
	time datetime

);
insert into user(id,username,password,roleid) values(1,'admin','201731064415',1);

/**
 * 
 *学生表
 * student:id,name,phone,email,notes
 * **/
create table student(
	id int primary key auto_increment,
	name varchar(30),
	sex varchar(10),
	phone varchar(70),
	email varchar(80),
	notes varchar(300),
	userid int not null
	
);

/**
 * 
 *student_grade
 * student_grade:id,studentid,gradeid
 * **/
create table student_grade(
	id int primary key auto_increment,
	studentid int,
	gradeid int
);

/**
 * 
 *
teacher
 * 
teacher:id,name,phone,email,notes
 * **/

create table teacher(
	id int primary key auto_increment,
	name varchar(30),
	sex varchar(10),
	phone varchar(70),
	email varchar(80),
	notes varchar(300),
	userid int not null,
	lessonid int
	
);




/*
 * 班级表grade:id,gradeName,gradeRoomid,lessonid,teacherid,notes,starttime,endtime,state
 * */
create table grade(
	id int primary key auto_increment,
	gradeName varchar(30),
	roomid int,
	lessonid int,
	teacherid int,
	notes varchar(100),
	starttime date,
	endtime date,
	state varchar(30)
);

/*
 * room:id,roomName,location
 * */
create table room(
	id int primary key auto_increment,
	roomname varchar(30),
	location varchar(100)
);



/*
 * lesson:id,lessonName,notes,price,hours
 * */

create table lesson(
	id int primary key auto_increment,
	lessonname varchar(50),
	notes varchar(100),
	price int,
	hours int
);



/*
 * mark:id,examid,studentid,fenshu,notes,state
 * */

create table mark(
	id int primary key auto_increment,
	fenshu varchar(40),
	gradeid int,
	studentid int,
	notes varchar(100),
	state varchar(40)
);


/*
 * payment:id,studentid,gradeid,number,payid,alipay,state
 * 
 */

create table payment(
	id int primary key auto_increment,
	studentid int,
	gradeid int,
	number int,
	state varchar(20),
	payid varchar(60),
	alipay varchar(60)
);


/*
 * notice:id,notes,gradeid,time
coment:id,level,notes,studentname
 * 
 */


create table notice(
	id int primary key auto_increment,
	notes varchar(100),
	gradeid int,
	time datetime
);

create table coment(
	id int primary key auto_increment,
	level int,
	notes varchar(100),
	studentname varchar(50),
	lessonid int,
	time datetime
);


ALTER TABLE user TYPE= INNODB;
ALTER TABLE role TYPE= INNODB;
ALTER TABLE student TYPE= INNODB;
ALTER TABLE teacher TYPE= INNODB;
ALTER TABLE mark TYPE= INNODB;
ALTER TABLE lesson TYPE= INNODB;
ALTER TABLE grade TYPE= INNODB;
ALTER TABLE coment TYPE= INNODB;
ALTER TABLE notice TYPE= INNODB;
ALTER TABLE payment TYPE= INNODB;
ALTER TABLE room TYPE= INNODB;
ALTER TABLE student_grade TYPE= INNODB;

insert into lesson(id,lessonname,notes,price,hours)values(1,'java后端','此为系统默认数据，可删除',10000,1000);
insert into room(roomname,location)values('培训中心101','西南石油大学思学楼，此为默认数据，可删除');
insert into lesson(id,lessonname,notes,price,hours)values(2,'web前端','此为系统默认数据，可删除',10000,1000);
insert into room(roomname,location)values('培训中心102','西南石油大学思学楼，此为默认数据，可删除');
insert into user(id,username,password,roleid) values(2,'t1','123456',2);
insert into teacher(userid)values(2);
update teacher set name = '教师一测试',sex = '男',phone = '12345678901',email = '1@163.com',notes = '系统默认教师模板，可删除',lessonid = 1 where userid = 2; 
insert into user(id,username,password,roleid) values(3,'s1','123456',3);
insert into student(userid)values(3);
update student set name = '学员一测试',sex = '女',phone = '12345678901',email = '1@163.com',notes = '系统默认学员模板，可删除' where userid = 3; 




ALTER TABLE student ADD CONSTRAINT fk_userid1
FOREIGN KEY(userid) REFERENCES user(id)
ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE teacher ADD CONSTRAINT fk_userid2
FOREIGN KEY(userid) REFERENCES user (id)
ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE notice ADD CONSTRAINT fk_gradeid1
FOREIGN KEY(gradeid) REFERENCES grade(id)
ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE mark ADD CONSTRAINT fk_studentid1
FOREIGN KEY(studentid) REFERENCES student(id)
ON DELETE CASCADE ON UPDATE CASCADE;
alter table user AUTO_INCREMENT 10000;


