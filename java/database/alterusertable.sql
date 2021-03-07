START TRANSACTION;

ALTER TABLE users

ADD COLUMN first_name VARCHAR,
ADD COLUMN last_name VARCHAR,
ADD COLUMN email VARCHAR;

UPDATE users
SET first_name = 'user', last_name = 'name', email = 'user@user.com'
WHERE user_id = 1;

UPDATE users
SET first_name = 'admin', last_name = 'istrator', email = 'admin@user.com'
WHERE user_id = 2;

ALTER TABLE users

ALTER COLUMN first_name SET NOT NULL,
ALTER COLUMN last_name SET NOT NULL,
ALTER COLUMN email SET NOT NULL;

SELECT * FROM users;

COMMIT;