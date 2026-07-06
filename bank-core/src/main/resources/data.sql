-- Role
INSERT INTO bank_role (role_id, role_name)
VALUES (1, 'ADMIN');

INSERT INTO bank_role (role_id, role_name)
VALUES (2, 'USER');

-- User
INSERT INTO bank_user (user_id, username, password, email, role_id)
VALUES (1, 'admin', 'admin123', 'admin@bank.com', 1);

INSERT INTO bank_user (user_id, username, password, email, role_id)
VALUES (2, 'user', 'user123', 'user@bank.com', 2);

-- Menu
INSERT INTO bank_menu (menu_id, menu_name, menu_url, menu_type, parent_id)
VALUES (1, 'Dashboard', '/dashboard', 'MENU', 0);

INSERT INTO bank_menu (menu_id, menu_name, menu_url, menu_type, parent_id)
VALUES (2, 'User Management', '/users', 'MENU', 1);

INSERT INTO bank_menu (menu_id, menu_name, menu_url, menu_type, parent_id)
VALUES (3, 'Role Management', '/roles', 'MENU', 1);

INSERT INTO bank_menu (menu_id, menu_name, menu_url, menu_type, parent_id)
VALUES (21, 'Bank Users List', '/bankUser', 'SUBMENU', 2);

INSERT INTO bank_menu (menu_id, menu_name, menu_url, menu_type, parent_id)
VALUES (22, 'Add New User', '/bankUser/add', 'SUBMENU', 2);


-- 3. Insert Submenus under 'System Settings' (parent_id = 3)
INSERT INTO bank_menu (menu_id, menu_name, menu_url, menu_type, parent_id)
VALUES (31, 'Audit Logs', '/settings/logs', 'SUBMENU', 3);

-- Granting 1 access to all menus (Parents and Submenus)
INSERT INTO bank_role_menu (role_id, menu_id) VALUES (1, 1);  -- Dashboard
INSERT INTO bank_role_menu (role_id, menu_id) VALUES (1, 2);  -- User Management (Parent)
INSERT INTO bank_role_menu (role_id, menu_id) VALUES (1, 21); -- Bank Users List
INSERT INTO bank_role_menu (role_id, menu_id) VALUES (1, 22); -- Add New User
INSERT INTO bank_role_menu (role_id, menu_id) VALUES (1, 3);  -- System Settings (Parent)
INSERT INTO bank_role_menu (role_id, menu_id) VALUES (1, 31); -- Audit Logs

-- Example: Granting a restricted 2 access to only Dashboard and View List
INSERT INTO bank_role_menu (role_id, menu_id) VALUES (2, 1);  -- Dashboard
INSERT INTO bank_role_menu (role_id, menu_id) VALUES (2, 2);  -- User Management (Parent)
INSERT INTO bank_role_menu (role_id, menu_id) VALUES (2, 21); -- Bank Users List only

-- Role Menu
--INSERT INTO bank_role_menu (role_id, menu_id, description)
--VALUES (1, 1, 'Dashboard');
--
--INSERT INTO bank_role_menu (role_id, menu_id, description)
--VALUES (1, 2, 'User Management');
--
--INSERT INTO bank_role_menu (role_id, menu_id, description)
--VALUES (1, 3, 'Role Management');
--
--INSERT INTO bank_role_menu (role_id, menu_id, description)
--VALUES (2, 1, 'Dashboard');