# Oracle Model 2 Board program using DBCP

For more information about DBCP, See http://www.java-school.net/jdbc/Connection-Pool#DBCP

### Have to do
Create a table and sequence in the SCOTT account as follows and insert a test record.

	create table hierarchy_article (
	    articleno number,
	    title varchar2(200) NOT NULL,
	    content varchar2(4000),
	    regdate date,
	    parent number,
	    constraint PK_HIERARCHY_ARTICLE PRIMARY KEY(articleno)
	);
	
	create sequence seq_hierarchy_article
	increment by 1
	start with 1;
	
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000001','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000002','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000003','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000004','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000005','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000006','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000007','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000008','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000009','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000010','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000011','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000012','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000013','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000014','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000015','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000016','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000017','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000018','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000019','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000020','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000021','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000022','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000023','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000024','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000025','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000026','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000027','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000028','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000029','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000030','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000031','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000032','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000033','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000034','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000035','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000036','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000037','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000038','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000039','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000040','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000041','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000042','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000043','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000044','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000045','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000046','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000047','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000048','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000049','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000050','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000051','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000052','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000053','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000054','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000055','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000056','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000057','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000058','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000059','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000060','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000061','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000062','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000063','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000064','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000065','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000066','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000067','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000068','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000069','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000070','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000071','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000072','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000073','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000074','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000075','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000076','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000077','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000078','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000079','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000080','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000081','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000082','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000083','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000084','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000085','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000086','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000087','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000088','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000089','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000090','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000091','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000092','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000093','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000094','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000095','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000096','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000097','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000098','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000099','',sysdate, 0);
	insert into hierarchy_article values (seq_hierarchy_article.nextval, '000100','',sysdate, 0);
	commit;


Install the Oracle JDBC Driver in the local repository.
See http://www.java-school.net/spring/di#Oralce-JDBC-Driver-Dependency


### How to run
Go to root directory and run mvn jetty:run and visit http://localhost:8080.