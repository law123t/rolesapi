SET FOREIGN_KEY_CHECKS = 0;
DELETE FROM test_basketball.center;
DELETE FROM test_basketball.point_guard;
DELETE FROM test_basketball.power_forward;
DELETE FROM test_basketball.small_forward;
DELETE FROM test_basketball.shooting_guard;
DELETE FROM test_basketball.user_team;
DELETE FROM test_basketball.user_roles;
DELETE FROM test_basketball.users;

USE test_basketball;

LOCK TABLES `shooting_guard` WRITE;
ALTER TABLE `shooting_guard` DISABLE KEYS;
INSERT INTO `shooting_guard` VALUES(1,'James','Harden',2356,907,659,121,38,464),(2,'DeMar','DeRozan',2020,290,386,78,13,180);
ALTER TABLE `shooting_guard` ENABLE KEYS;
UNLOCK TABLES;

LOCK TABLES `point_guard` WRITE;
ALTER TABLE `point_guard` DISABLE KEYS;
INSERT INTO `point_guard` VALUES (1,'Russel','Westbrook',2558,840,864,132,31,438),(2,'Stephen','Curry',1999,524,353,142,17,239);
ALTER TABLE `point_guard` ENABLE KEYS;
UNLOCK TABLES;

LOCK TABLES `small_forward` WRITE;
ALTER TABLE `small_forward` DISABLE KEYS;
INSERT INTO `small_forward` VALUES (1,'Lebron','James',1954,646,639,92,44,303),(2,'Kawhi','Leonard',1376,260,430,133,55,154);
ALTER TABLE `small_forward` ENABLE KEYS;
UNLOCK TABLES;

LOCK TABLES `power_forward` WRITE;
ALTER TABLE `power_forward` DISABLE KEYS;
INSERT INTO `power_forward` VALUES (1,'Anthony','Davis',2099,157,884,94,167,181);
ALTER TABLE `power_forward` ENABLE KEYS;
UNLOCK TABLES;

LOCK TABLES `center` WRITE;
ALTER TABLE `center` DISABLE KEYS;
INSERT INTO `center` VALUES (1,'Karl','Anthony Towns',805,220,1007,56,103,212);
ALTER TABLE `center` ENABLE KEYS;
UNLOCK TABLES;

LOCK TABLES `user_roles` WRITE;
ALTER TABLE `user_roles` DISABLE KEYS;
INSERT INTO `user_roles` VALUES (1,'administrator', 1, 'admin'),(2,'administrator', 2, 'admin2'),(3,'user', 3, 'User');
ALTER TABLE `user_roles` ENABLE KEYS;
UNLOCK TABLES;

LOCK TABLES `user_team` WRITE;
ALTER TABLE `user_team` DISABLE KEYS;
INSERT INTO `user_team` VALUES (1,'Team One',1,1,1,1,1,1);
ALTER TABLE `user_team` ENABLE KEYS;
UNLOCK TABLES;

LOCK TABLES `users` WRITE;
ALTER TABLE `users` DISABLE KEYS;
INSERT INTO `users` VALUES (1,'admin','admin','John','Smith','smith@john.com'), (2,'admin2','admin2','Tim','Jim','Jim@Tim.com'),(3,'User','User','Beth','Seth','seth@beth.com');
ALTER TABLE `users` ENABLE KEYS;
UNLOCK TABLES;
SET FOREIGN_KEY_CHECKS = 1;