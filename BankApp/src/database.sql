create table Account (accountNo number primary key ,customerName varchar2(20), balance number);

create table transactions(transactionId number primary key, type varchar2(20), trans_date date,accountNo number references Account(accountNo) );

insert into account values (10001,'Ankita',13000);
insert into account values (12001,'Sujay',23000);
insert into account values (10002,'Shraddha',34000);

create sequence transactionId_seq start with 101;

