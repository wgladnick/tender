START TRANSACTION;
DROP TABLE invitation_votes;
DROP TABLE invitee_details;
DROP TABLE invitation;
CREATE TABLE IF NOT EXISTS invitation (
                invite_id SERIAL PRIMARY KEY,
                location VARCHAR,
                radius INTEGER,
                creator_user_id INTEGER,
                deadline TIMESTAMPTZ,
                reservation_date_time VARCHAR
                );
              
CREATE TABLE IF NOT EXISTS invitee_details (
                invite_id INTEGER,
                unique_id VARCHAR NOT NULL,
                user_id INTEGER NOT NULL,
                first_name VARCHAR NOT NULL,
                last_name VARCHAR NOT NULL,
                email VARCHAR NOT NULL,
                has_voted BOOLEAN,
                is_attending VARCHAR DEFAULT 'Pending',
                
                 CONSTRAINT fk_invite FOREIGN KEY(invite_id) REFERENCES invitation (invite_id)
                
                );
                
CREATE TABLE IF NOT EXISTS invitation_votes (
                invite_id INTEGER,
                yelp_id VARCHAR NOT NULL,
                thumbs_up INTEGER,
                thumbs_down INTEGER,
                
                 CONSTRAINT fk_invite FOREIGN KEY(invite_id) REFERENCES invitation (invite_id)
                );
                      
              
COMMIT;   

INSERT INTO invitation (location, radius, creator_user_id, deadline, reservation_date_time) VALUES ('Charlotte', 5000, 1, '2021-03-07 16:00', 'March 14th, 2021 7pm' ) RETURNING invite_id;
GRANT ALL
ON ALL TABLES IN SCHEMA public
TO final_capstone_owner;
GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO final_capstone_owner;
GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO final_capstone_appuser;
GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO final_capstone_appuser;



     