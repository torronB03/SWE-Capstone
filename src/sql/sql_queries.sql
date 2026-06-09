-- 1. Retrieve the information on whether it's an event or a permanent place
-- Assumes there is a 'permanent' boolean column in a shared table or view
SELECT id, name, permanent
FROM resources
WHERE permanent IS NOT NULL;

-- 2. Retrieve specific data for each filter option
-- Example assumes filters like category, zip code, and price_range are available
SELECT name, category, zip_code, price_range
FROM resources
WHERE
  (category = ? OR ? IS NULL) AND
  (zip_code = ? OR ? IS NULL) AND
  (price_range = ? OR ? IS NULL);

-- 3. Retrieve each event (all columns) based on its ID or name
-- Useful for Learn More or event detail pages
SELECT *
FROM events
WHERE id = ? OR name ILIKE ?;

-- 4. Insert the current logged-in user when an event is created
-- Assumes user_id and event_id are passed from the app, with foreign key constraints
INSERT INTO user_created_events (user_id, event_id, created_at)
VALUES (?, ?, NOW());

-- RETRIEVE CURRENT LOGGED IN USER THAT'S SIGNED UP FOR AN EVENT
SELECT user_id FROM user_created_events
WHERE user_id = ? AND event_id = ?;


-- INSERT LOGGED IN USER WHEN THEY VOLUNTEER FOR EVENT
INSERT INTO volunteers (vo_id, user_id) VALUES (?, ?);

-- RETRIEVE LOGGED IN USER WHEN THEY VOLUNTEER FOR EVENT
SELECT user_id FROM volunteers
WHERE user_id = ? AND vo_id = ?;

-- INSERT INTO USER TABLE WHEN AN ACCOUNT IS CREATED
-- Creating a batch from LOG_User_Info_Queue to manage possible load
WITH batch AS (
    SELECT user_id,
            first_name,
             last_name,
             username,
             email_linked,
             password,
             address,
             city,
             state,
             zip
    FROM LOG_User_Info_Queue
    ORDER BY timelogged
    LIMIT 5 --Limit of 5 right now
    FOR UPDATE SKIP LOCKED -- if another process is operating on a row, the query will skip it instead of waiting
)

-- Inserting from batch
INSERT INTO ENDUSER (first_name, last_name, username, email_linked, password, address, city, state, zip)
SELECT first_name, last_name, username, email_linked, password, address, city, state, zip
FROM batch;
-- Deleting from queue
DELETE FROM LOG_User_Info_Queue
WHERE user_id IN (SELECT user_id FROM batch);

-- RETRIEVING USER INFO WHEN LOGGING IN
SELECT user_id, account_name, hashed_password FROM LOG_User_Login WHERE account_name = ?

