INSERT INTO intexsoft.webapp.roles(title) VALUES ('GUEST');
INSERT INTO intexsoft.webapp.roles(title) VALUES ('USER');
INSERT INTO intexsoft.webapp.roles(title) VALUES ('ADMIN');

INSERT INTO intexsoft.webapp.USERS (FULLNAME, LOGIN, PASSWORD, role_id) VALUES ('Nadzeja Shvants', 'nad', '123', 2);
INSERT INTO intexsoft.webapp.USERS (FULLNAME, LOGIN, PASSWORD, role_id) VALUES ('masha shvants', 'ms@2003', '2003', 2);
INSERT INTO intexsoft.webapp.USERS (FULLNAME, LOGIN, PASSWORD, role_id) VALUES ('Алексей Шванц', 'ashvants', '11091991', 2);
INSERT INTO intexsoft.webapp.USERS (FULLNAME, LOGIN, PASSWORD, role_id) VALUES ('test', 'test', '11', 1);
INSERT INTO intexsoft.webapp.USERS (FULLNAME, LOGIN, PASSWORD, role_id) VALUES ('test1', 'test1', 't1', 1);
INSERT INTO intexsoft.webapp.USERS (FULLNAME, LOGIN, PASSWORD, role_id) VALUES ('Aliaksei Shvants', 'admin', 'admin', 3);
INSERT INTO intexsoft.webapp.USERS (FULLNAME, LOGIN, PASSWORD, role_id) VALUES ('Hanna Baushevich', 'ane4ka', '24', 3);
INSERT INTO intexsoft.webapp.USERS (FULLNAME, LOGIN, PASSWORD, role_id) VALUES ('test2', 't2', '22', 1);
INSERT INTO intexsoft.webapp.USERS (FULLNAME, LOGIN, PASSWORD, role_id) VALUES ('test3', 't3', '33', 1);

INSERT INTO intexsoft.webapp.LINKS (DESCRIPTION, SHORT_URL, URL) VALUES ('bla-bla', 'short URL', 'long URL');
INSERT INTO intexsoft.webapp.LINKS (DESCRIPTION, SHORT_URL, URL) VALUES ('bla2-bla2', 'https://su', 'https://long/URL');
INSERT INTO intexsoft.webapp.LINKS (DESCRIPTION, SHORT_URL, URL) VALUES ('blaaablaa', 'https://shv.by/d', 'longurlstring');
INSERT INTO intexsoft.webapp.LINKS (DESCRIPTION, SHORT_URL, URL) VALUES ('asdfgh', 'https://shv.by/e', 'qwerty');
INSERT INTO intexsoft.webapp.LINKS (DESCRIPTION, SHORT_URL, URL) VALUES ('bla', 'https://shv.by/f', 'qaqady');
INSERT INTO intexsoft.webapp.LINKS (DESCRIPTION, SHORT_URL, URL)
VALUES ('fullstack chapters', 'https://shv.by/h', 'https://docs.google.com/document/d/1Upku0IwCsQw2SPowj8PQ5SW3fkrfOayqmIjS1d-Zyr8/edit');
INSERT INTO intexsoft.webapp.LINKS (DESCRIPTION, SHORT_URL, URL)
VALUES ('angular', 'https://shv.by/i', 'http://javasampleapproach.com/spring-framework/spring-boot/use-angular-httpclient-post-put-delete-data-springboot-rest-apis-angular-4');

INSERT INTO intexsoft.webapp.TAGS(TITLE) VALUES ('short');
INSERT INTO intexsoft.webapp.TAGS(TITLE) VALUES ('test');
INSERT INTO intexsoft.webapp.TAGS(TITLE) VALUES ('bla');
INSERT INTO intexsoft.webapp.TAGS(TITLE) VALUES ('su');
INSERT INTO intexsoft.webapp.TAGS(TITLE) VALUES ('bla2');
INSERT INTO intexsoft.webapp.TAGS(TITLE) VALUES ('first');
INSERT INTO intexsoft.webapp.TAGS(TITLE) VALUES ('long');
INSERT INTO intexsoft.webapp.TAGS(TITLE) VALUES ('random');
INSERT INTO intexsoft.webapp.TAGS(TITLE) VALUES ('second');
INSERT INTO intexsoft.webapp.TAGS(TITLE) VALUES ('qwerty');
INSERT INTO intexsoft.webapp.TAGS(TITLE) VALUES ('3');
INSERT INTO intexsoft.webapp.TAGS(TITLE) VALUES ('FS');
INSERT INTO intexsoft.webapp.TAGS(TITLE) VALUES ('intexsoft');
INSERT INTO intexsoft.webapp.TAGS(TITLE) VALUES ('intern');
INSERT INTO intexsoft.webapp.TAGS(TITLE) VALUES ('angular');
INSERT INTO intexsoft.webapp.TAGS(TITLE) VALUES ('spring');
INSERT INTO intexsoft.webapp.TAGS(TITLE) VALUES ('REST API');

INSERT INTO intexsoft.webapp.LINKS_TAGS (LINK_ID, TAG_ID) VALUES (1,1);
INSERT INTO intexsoft.webapp.LINKS_TAGS (LINK_ID, TAG_ID) VALUES (1,2);
INSERT INTO intexsoft.webapp.LINKS_TAGS (LINK_ID, TAG_ID) VALUES (1,3);
INSERT INTO intexsoft.webapp.LINKS_TAGS (LINK_ID, TAG_ID) VALUES (2,3);
INSERT INTO intexsoft.webapp.LINKS_TAGS (LINK_ID, TAG_ID) VALUES (2,4);
INSERT INTO intexsoft.webapp.LINKS_TAGS (LINK_ID, TAG_ID) VALUES (2,5);
INSERT INTO intexsoft.webapp.LINKS_TAGS (LINK_ID, TAG_ID) VALUES (3,3);
INSERT INTO intexsoft.webapp.LINKS_TAGS (LINK_ID, TAG_ID) VALUES (3,6);
INSERT INTO intexsoft.webapp.LINKS_TAGS (LINK_ID, TAG_ID) VALUES (3,7);
INSERT INTO intexsoft.webapp.LINKS_TAGS (LINK_ID, TAG_ID) VALUES (4,8);
INSERT INTO intexsoft.webapp.LINKS_TAGS (LINK_ID, TAG_ID) VALUES (4,9);
INSERT INTO intexsoft.webapp.LINKS_TAGS (LINK_ID, TAG_ID) VALUES (4,10);
INSERT INTO intexsoft.webapp.LINKS_TAGS (LINK_ID, TAG_ID) VALUES (5,3);
INSERT INTO intexsoft.webapp.LINKS_TAGS (LINK_ID, TAG_ID) VALUES (5,8);
INSERT INTO intexsoft.webapp.LINKS_TAGS (LINK_ID, TAG_ID) VALUES (5,11);
INSERT INTO intexsoft.webapp.LINKS_TAGS (LINK_ID, TAG_ID) VALUES (6,12);
INSERT INTO intexsoft.webapp.LINKS_TAGS (LINK_ID, TAG_ID) VALUES (6,13);
INSERT INTO intexsoft.webapp.LINKS_TAGS (LINK_ID, TAG_ID) VALUES (6,14);
INSERT INTO intexsoft.webapp.LINKS_TAGS (LINK_ID, TAG_ID) VALUES (7,15);
INSERT INTO intexsoft.webapp.LINKS_TAGS (LINK_ID, TAG_ID) VALUES (7,16);
INSERT INTO intexsoft.webapp.LINKS_TAGS (LINK_ID, TAG_ID) VALUES (7,17);

INSERT INTO intexsoft.webapp.USERS_LINKS(USER_ID, LINK_ID) VALUES (1,1);
INSERT INTO intexsoft.webapp.USERS_LINKS(USER_ID, LINK_ID) VALUES (2,2);
INSERT INTO intexsoft.webapp.USERS_LINKS(USER_ID, LINK_ID) VALUES (2,3);
INSERT INTO intexsoft.webapp.USERS_LINKS(USER_ID, LINK_ID) VALUES (6,4);
INSERT INTO intexsoft.webapp.USERS_LINKS(USER_ID, LINK_ID) VALUES (6,5);
INSERT INTO intexsoft.webapp.USERS_LINKS(USER_ID, LINK_ID) VALUES (7,6);
INSERT INTO intexsoft.webapp.USERS_LINKS(USER_ID, LINK_ID) VALUES (7,7);