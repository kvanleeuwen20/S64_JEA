INSERT INTO App_User(ID, BIO, EMAIL, LOCATION, NAME, PASSWORD, PROFILEPICTUREPATH, ROLE, USERNAME, WEBSITE) VALUES (0, 'My life is not interesting enough for a bio.... :(', 'pim@janissen.nl', 'BELFELD', 'Pim Janissen', 'password', '/imgs/0_0002', 'ADMINISTRATOR', 'PimJan', 'www.ihasabucket.com');
INSERT INTO App_User(ID, BIO, EMAIL, LOCATION, NAME, PASSWORD, PROFILEPICTUREPATH, ROLE, USERNAME, WEBSITE) VALUES (1, '', 'jhlm.janssen@student.fontys.nl', 'NUENEN', 'Jeroen', 'Kwetter', '/imgs/1_0000', 'USER', 'Jehama', 'www.beurs.nl/koersen/aex/p1');
INSERT INTO App_User(ID, BIO, EMAIL, LOCATION, NAME, PASSWORD, PROFILEPICTUREPATH, ROLE, USERNAME, WEBSITE) VALUES (2, 'Lees mijn boek over hoe ik vaak ruzie met mensen heb waardoor ik nog meer ruzie heb gekregen waardoor ik nu in principe wel een deel 2 kan schrijven.', 'Gordon@hotmail.com', 'AMSTERDAM', 'Gordon', 'Joling', '/imgs/2_0039', 'USER', 'TheRealG', 'www.gordon.nl');

INSERT INTO App_User_App_user(following_ID, followers_ID) VALUES(1, 0);
INSERT INTO App_User_App_user(following_ID, followers_ID) VALUES(1, 2);
INSERT INTO App_User_App_user(following_ID, followers_ID) VALUES(2, 0);
INSERT INTO App_User_App_user(following_ID, followers_ID) VALUES(2, 1);

COMMIT;