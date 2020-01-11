INSERT INTO module_management.module
(id, description, module_id, name)
VALUES('1d277ed8-8f34-48e3-a688-129b1cbfda06', 'PromoCard', 'PromoCard', 'PromoCard');
INSERT INTO module_management.module
(id, description, module_id, name)
VALUES('20fbc824-51b8-4eb8-98a1-2a2138b95495', 'CategoryCard', 'CategoryCard', 'CategoryCard');
INSERT INTO module_management.module
(id, description, module_id, name)
VALUES('26d4f784-a340-4a7f-b960-f3fef37811f6', 'FlashSaleCard', 'FlashSaleCard', 'FlashSaleCard');
INSERT INTO module_management.module
(id, description, module_id, name)
VALUES('31dc6265-ba14-41d1-9f8e-8cddd2e644ae', 'HistoryCard', 'HistoryCard', 'HistoryCard');
INSERT INTO module_management.module
(id, description, module_id, name)
VALUES('3fe149a8-34bb-4b1c-a57f-1f144985f200', 'NewsCard', 'NewsCard', 'NewsCard');

INSERT INTO module_management.user_group
(id, group_id, name)
VALUES('066d8256-5f5f-4956-b277-8fbb26cc9bc7', 'GroupA', 'GroupA');
INSERT INTO module_management.user_group
(id, group_id, name)
VALUES('1f0568d6-ac19-448f-976b-a5b98b31cf7b', 'GroupB', 'GroupB');
INSERT INTO module_management.user_group
(id, group_id, name)
VALUES('d5f3164d-aedd-4d55-b67b-2d7f4c1181ab', 'GroupC', 'GroupC');

INSERT INTO module_management.`user`
(id, full_name, user_id, user_group_fk)
VALUES('85f4eddf-f15f-4b1b-8c09-c05688dbbe58', 'UserB', 'UserB', '1f0568d6-ac19-448f-976b-a5b98b31cf7b');
INSERT INTO module_management.`user`
(id, full_name, user_id, user_group_fk)
VALUES('b3f22784-8b5b-4760-86b7-6bbb566fede5', 'Titus Nainggolan', 'UserA', '066d8256-5f5f-4956-b277-8fbb26cc9bc7');
INSERT INTO module_management.`user`
(id, full_name, user_id, user_group_fk)
VALUES('ea7dfd1c-f577-4134-aef9-027c89b569ac', 'UserC', 'UserC', 'd5f3164d-aedd-4d55-b67b-2d7f4c1181ab');


INSERT INTO module_management.user_group_modules
(id, order_number, module_fk, user_group_fk)
VALUES('115be6e3-f0a8-4eef-a320-3cf55853714c', 4, '3fe149a8-34bb-4b1c-a57f-1f144985f200', 'd5f3164d-aedd-4d55-b67b-2d7f4c1181ab');
INSERT INTO module_management.user_group_modules
(id, order_number, module_fk, user_group_fk)
VALUES('1615e49a-a4ad-4765-ad8c-2d85da0484d8', 3, '20fbc824-51b8-4eb8-98a1-2a2138b95495', 'd5f3164d-aedd-4d55-b67b-2d7f4c1181ab');
INSERT INTO module_management.user_group_modules
(id, order_number, module_fk, user_group_fk)
VALUES('1b12e92b-dc2f-4297-afe5-acd279e85c20', 5, '31dc6265-ba14-41d1-9f8e-8cddd2e644ae', 'd5f3164d-aedd-4d55-b67b-2d7f4c1181ab');
INSERT INTO module_management.user_group_modules
(id, order_number, module_fk, user_group_fk)
VALUES('3e6f639c-532a-4706-a37d-85ea547a6859', 3, '26d4f784-a340-4a7f-b960-f3fef37811f6', '1f0568d6-ac19-448f-976b-a5b98b31cf7b');
INSERT INTO module_management.user_group_modules
(id, order_number, module_fk, user_group_fk)
VALUES('445b5460-c722-4009-9094-c8cc16b56308', 2, '20fbc824-51b8-4eb8-98a1-2a2138b95495', '066d8256-5f5f-4956-b277-8fbb26cc9bc7');
INSERT INTO module_management.user_group_modules
(id, order_number, module_fk, user_group_fk)
VALUES('5382d137-bad7-4254-9e58-a21d8f5443cb', 3, '26d4f784-a340-4a7f-b960-f3fef37811f6', '066d8256-5f5f-4956-b277-8fbb26cc9bc7');
INSERT INTO module_management.user_group_modules
(id, order_number, module_fk, user_group_fk)
VALUES('553603c4-7917-4d95-8e2a-4fbd6ab1913e', 1, '1d277ed8-8f34-48e3-a688-129b1cbfda06', '1f0568d6-ac19-448f-976b-a5b98b31cf7b');
INSERT INTO module_management.user_group_modules
(id, order_number, module_fk, user_group_fk)
VALUES('602bca8a-205c-4e13-b67c-f3afff36332d', 2, '3fe149a8-34bb-4b1c-a57f-1f144985f200', '1f0568d6-ac19-448f-976b-a5b98b31cf7b');
INSERT INTO module_management.user_group_modules
(id, order_number, module_fk, user_group_fk)
VALUES('677c9ce5-89fc-4e0c-81a7-0e9eb71d27e4', 4, '31dc6265-ba14-41d1-9f8e-8cddd2e644ae', '066d8256-5f5f-4956-b277-8fbb26cc9bc7');
INSERT INTO module_management.user_group_modules
(id, order_number, module_fk, user_group_fk)
VALUES('7559bcfd-92ef-4efd-b7c2-cf63e45f1518', 4, '20fbc824-51b8-4eb8-98a1-2a2138b95495', '1f0568d6-ac19-448f-976b-a5b98b31cf7b');
INSERT INTO module_management.user_group_modules
(id, order_number, module_fk, user_group_fk)
VALUES('7ff080b2-eb76-4624-a1e7-78c3c496c0f7', 5, '3fe149a8-34bb-4b1c-a57f-1f144985f200', '066d8256-5f5f-4956-b277-8fbb26cc9bc7');
INSERT INTO module_management.user_group_modules
(id, order_number, module_fk, user_group_fk)
VALUES('84df2268-53b4-450b-8941-180441f9db43', 5, '31dc6265-ba14-41d1-9f8e-8cddd2e644ae', '1f0568d6-ac19-448f-976b-a5b98b31cf7b');
INSERT INTO module_management.user_group_modules
(id, order_number, module_fk, user_group_fk)
VALUES('b798c7cf-d2fe-4628-9133-91981e84c2e2', 1, '1d277ed8-8f34-48e3-a688-129b1cbfda06', '066d8256-5f5f-4956-b277-8fbb26cc9bc7');
INSERT INTO module_management.user_group_modules
(id, order_number, module_fk, user_group_fk)
VALUES('de109004-c8ab-4a57-97e8-9a78d4c4c91b', 2, '26d4f784-a340-4a7f-b960-f3fef37811f6', 'd5f3164d-aedd-4d55-b67b-2d7f4c1181ab');
INSERT INTO module_management.user_group_modules
(id, order_number, module_fk, user_group_fk)
VALUES('f34ef203-7219-483e-9507-4196b40c416d', 1, '1d277ed8-8f34-48e3-a688-129b1cbfda06', 'd5f3164d-aedd-4d55-b67b-2d7f4c1181ab');