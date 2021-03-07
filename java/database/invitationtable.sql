START TRANSACTION;

CREATE TABLE IF NOT EXISTS invitation (
                invite_id SERIAL PRIMARY KEY,
                location VARCHAR NOT NULL,
                radius VARCHAR NOT NULL,
                creator_user_id VARCHAR NOT NULL,
                deadline TIMESTAMPTZ NOT NULL,
                reservation_date_time VARCHAR NOT NULL
                );
              
CREATE TABLE IF NOT EXISTS invitee_details (
                invite_id INTEGER,
                unique_id VARCHAR NOT NULL,
                user_id VARCHAR NOT NULL,
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


     