START TRANSACTION;

CREATE TABLE IF NOT EXISTS favorites (
    user_id INTEGER NOT NULL,
    business_id VARCHAR NOT NULL
);
    
COMMIT;