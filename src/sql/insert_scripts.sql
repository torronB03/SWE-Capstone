--TEST USER MUST COMPILE FIRST
INSERT INTO swegrg25."ENDUSER" (first_name, last_name, username, email_linked, password, address, city, state, zip,user_data_index) OVERRIDING SYSTEM VALUE VALUES ('Test', 'User', 'testUser92', 'testuser92@gmail.com', 'testuser', '123 test avenue', 'user', 'Idaho', '1235', 0);

INSERT INTO swegrg25."Organization" (org_name, user_data_index, address, contact_info)
VALUES
('Salvation Army', 0, '4526 S. Claiborne Avenue, New Orleans, LA 70125', '15048994569.0'),
('Hotel Hope', 0, '3923 Martin Luther King Jr Blvd, New Orleans, LA 70125', '15048217773.0'),
('New Orleans''s Women & Children Shelter', 0, '2625 Iberville St, New Orleans, LA 70119', '15045229340.0'),
('New Orleans Mission', 0, '1130 O.C. Haley Blvd, New Orleans, LA 70113', '15045232116.0'),
('Ozanam Inn', 0, '2239 Poydras St, New Orleans, LA 70119', '15045231184.0'),
('Low Barrier Shelter', 0, '1530 Gravier St, New Orleans, LA 70112', '15045171815.0'),
('New Orleans Family Justice Center', 0, '701 Loyola Ave #201, New Orleans, LA 70113', '15045924005.0'),
('Covenant House', 0, '611 N Rampart St, New Orleans, LA 70112', '15045841111.0'),
('City of New Orleans Shelter and Engagement Center', 0, '1530 Gravier St, New Orleans, LA 70112', '15045171815.0'),
('Concerned Citizens-A Better', 0, '1409 Nunez St #1417, New Orleans, LA 70114', '15043663726.0'),
('Volunteers of America Southeast Louisiana', 0, '1801 Canal St, New Orleans, LA 70112', '15047081700.0'),
('Harry Tompson Center', 0, '1803 Gravier St, New Orleans, LA 70112', '15042735547.0'),
('Hagar''s House', 0, '3401 Canal St, New Orleans, LA 70119', '15042105064.0'),
('Family Violence Program of Saint Bernard', 0, '3010 Jean Lafitte Pkwy, Chalmette, LA 70043', '15042773177.0'),
('Magnolia Villa', 0, '1801 Magnolia St, New Orleans, LA 70113', NULL),
('VA CRRC', 0, '1530 Gravier St, New Orleans, LA 70112', '15044123700.0'),
('Unity Welcome Home', 0, '2407 Baronne St, New Orleans, LA 70113', '15048994589.0'),
('Friendship House', 0, '813 Elysian Fields Ave, New Orleans, LA 70117', '15049494469.0'),
('Travelers Aid Society of Greater New Orleans', 0, '2131 St. Charles Ave, New Orleans, LA 70130', '15048783156.0'),
('Salvation Army Clothing Store', 0, '100 Jefferson Hwy, New Orleans, LA 70121', '5048357130'),
('Goodwill', 0, '3400 Tulane Ave, New Orleans, LA 70119', '5044562622'),
('Super Thrift Store', 0, '4243 Earhart Blvd, New Orleans, LA 70125', '5048212479'),
('Red, White, and Blue', 0, '5728 Jefferson Hwy, New Orleans, LA 70123', '5047338066'),
('Out of the Closet Thrift Store', 0, '2900 Magazine St, New Orleans, LA 70115', '5048212400'),
('Ozanam Inn - Food Distribution Center', 0, '2239 Poydras St, New Orleans, LA 70119', '15045231184'),
('Broadmoor Community Church', 0, '2021 S Dupre St, New Orleans, LA 7012', '15048227229'),
('Nola Community Fridges', 0, '1823 Washington Ave, New Orleans, LA 70113', 'https://www.nolacommunityfridges.org/contact-us'),
('Edible Schoolyard New Orleans', 0, '2319 Valence St, New Orleans, LA 70115', '15042679038'),
('New Orleans Mission Food Bank', 0, '1130 O.C. Haley Blvd, New Orleans, LA 70113', '15045232116'),
('Iggy''s Cupboard (Loyola Students Only)', 0, '6363 St Charles Ave, New Orleans, LA 70118', 'imbrashe@loyno.edu'),
('Salvation Army Food Pantry', 0, '4500 S Claiborne Ave, New Orleans, LA 70125', '15048994569'),
('Oschner Urgent Care & Occupational Health - Uptown', 0, '4605 Magazine St, New Orleans, LA 70115', '15043095015'),
('MinuteClinic at CVS', 0, '4401 S Claiborne Ave, New Orleans, LA 70125', '15043095015'),
('LCMC Health Urgent Care - Uptown', 0, '5800 Magazine St A, New Orleans, LA 70115', '15049003160'),
('In & Out Urgent Care - New Orleans', 0, '6225 S Claiborne Ave, New Orleans, LA 70125', '15048648080'),
('Manning Family Children''s LCMC Health', 0, '200 Henry Clay Ave, New Orleans, LA 70118', '15048999511'),
('Loyola Student Health Services', 0, '6363 St Charles Ave, New Orleans, LA 70118', '15048653326'),
('Dryades YMCA', 0, '2220 Oretha C Haley Blvd, New Orleans, LA 70113', NULL),
('Stallings St. Claude Recreation Center', 0, '4300 St Claude Ave, New Orleans, LA 70117', NULL),
('Cut Off Recreation Center', 0, '6600 Belgrade St, New Orleans, LA 70131', NULL),
('Joe W. Brown Recreation Center', 0, '5601 Read Blvd, New Orleans, LA 70127', NULL),
('Gernon Brown Recreation Center', 0, '1001 Harrison Ave, New Orleans, LA 70124', NULL),
('Milne Recreation Center', 0, '5420 Franklin Ave, New Orleans, LA 70122', NULL),
('Treme Recreation Community Center', 0, '900 N Villere St, New Orleans, LA 70116', NULL);





INSERT INTO swegrg25."Shelters" (
    org_name,
    space_available,
    maximum_capacity,
    hours_of_operation,
    address,
    contact_info,
    coordinate_location,
    location_notes,
    permanent,
    user_data_index
)
VALUES
('Salvation Army', NULL, NULL, '{"Wednesday": "8:30 AM - 4:30 PM", "Thursday": "8:30 AM - 4:30 PM", "Friday (Good Friday)": "8:30 AM - 4:30 PM", "Saturday": "Closed", "Sunday (Easter)": "Closed", "Monday": "8:30 AM - 4:30 PM", "Tuesday": "8:30 AM - 4:30 PM"}', '4526 S. Claiborne Avenue, New Orleans, LA 70125', '15048994569.0', '29.93971533928408, -90.10548508823189', NULL, 'place', 0),
('Hotel Hope', NULL, NULL, NULL, '3923 Martin Luther King Jr Blvd, New Orleans, LA 70125', '15048217773.0', '29.95158037748582, -90.09727304775161', NULL, 'place', 0),
('New Orleans''s Women & Children Shelter', NULL, NULL, NULL, '2625 Iberville St, New Orleans, LA 70119', '15045229340.0', '29.966007802793303, -90.0854435618273', NULL, 'place', 0),
('New Orleans Mission', NULL, NULL, '{"24/7": "24/7"}', '1130 O.C. Haley Blvd, New Orleans, LA 70113', '15045232116.0', '29.94344274647142, -90.07716901951204', NULL, 'place', 0),
('Ozanam Inn', NULL, 156, '{"Everyday": "9:00 AM - 4:00 PM"}', '2239 Poydras St, New Orleans, LA 70119', '15045231184.0', '29.95768851964588, -90.09003716183148', NULL, 'place', 0),
('Low Barrier Shelter', NULL, NULL, NULL, '1530 Gravier St, New Orleans, LA 70112', '15045171815.0', '29.95452548226056, -90.07870286288089', NULL, 'place', 0),
('New Orleans Family Justice Center', NULL, NULL, '{"Wednesday": "8:30 AM - 4:00 PM", "Thursday": "8:30 AM - 4:00 PM", "Friday (Good Friday)": "8:30 AM - 4:00 PM", "Saturday": "Closed", "Sunday (Easter)": "Closed", "Monday": "8:30 AM - 4:00 PM", "Tuesday": "8:30 AM - 4:00 PM"}', '701 Loyola Ave #201, New Orleans, LA 70113', '15045924005.0', '29.94821554206436, -90.07792515200782', NULL, 'place', 0),
('Covenant House', NULL, NULL, '{"24/7": "24/7"}', '611 N Rampart St, New Orleans, LA 70112', '15045841111.0', '29.960224301228187, -90.06908003485113', NULL, 'place', 0),
('City of New Orleans Shelter and Engagement Center', NULL, NULL, NULL, '1530 Gravier St, New Orleans, LA 70112', '15045171815.0', '29.95446,-90.1022745', NULL, 'place', 0),
('Concerned Citizens-A Better', NULL, NULL, '{"24/7": "24/7"}', '1409 Nunez St #1417, New Orleans, LA 70114', '15043663726.0', '29.95446,-90.1022745', NULL, 'place', 0),
('Volunteers of America Southeast Louisiana', NULL, NULL, NULL, '1801 Canal St, New Orleans, LA 70112', '15047081700.0', '29.9516412,-90.1352289', NULL, 'place', 0),
('Harry Tompson Center', NULL, NULL, NULL, '1803 Gravier St, New Orleans, LA 70112', '15042735547.0', '29.9516412,-90.1352289', NULL, 'place', 0),
('Hagar''s House', NULL, NULL, NULL, '3401 Canal St, New Orleans, LA 70119', '15042105064.0', '29.9516412,-90.1352289', NULL, 'place', 0),
('Family Violence Program of Saint Bernard', NULL, NULL, NULL, '3010 Jean Lafitte Pkwy, Chalmette, LA 70043', '15042773177.0', '29.9537031,-90.0207835', NULL, 'place', 0),
('Magnolia Villa', NULL, NULL, NULL, '1801 Magnolia St, New Orleans, LA 70113', NULL, '29.9441456,-90.1248542', NULL, 'place', 0),
('VA CRRC', NULL, NULL, NULL, '1530 Gravier St, New Orleans, LA 70112', '15044123700.0', '29.9441456,-90.1248542', NULL, 'place', 0),
('Unity Welcome Home', NULL, NULL, NULL, '2407 Baronne St, New Orleans, LA 70113', '15048994589.0', '29.9441456,-90.1248542', NULL, 'place', 0),
('Friendship House', NULL, NULL, NULL, '813 Elysian Fields Ave, New Orleans, LA 70117', '15049494469.0', '29.9659327,-90.0944285', NULL, 'place', 0),
('Travelers Aid Society of Greater New Orleans', NULL, NULL, '{"Wednesday": "7:30 AM - 3:00 PM", "Thursday": "7:30 AM - 3:00 PM", "Friday": "7:30 AM - 3:00 PM", "Saturday": "Closed", "Sunday": "Closed", "Monday": "7:30 AM - 3:00 PM", "Tuesday": "7:30 AM - 3:00 PM"}', '2131 St. Charles Ave, New Orleans, LA 70130', '15048783156.0', '29.9393747,-90.0731439', NULL, 'place', 0);




INSERT INTO swegrg25."Clothing_Stores"
(org_name, price_range, hours_of_operation, address, contact_info, coordinate_location, location_notes, permanent, user_data_index)
VALUES
(
  'Salvation Army Clothing Store',
  'Low-Cost',
  '{"Monday-Saturday": "9AM-5PM", "Sunday": "Closed"}',
  '100 Jefferson Hwy, New Orleans, LA 70121',
  '5048357130',
  '29.956445,-90.147832',
  'Located near Ochsner Baptist; has dedicated parking lot',
  'place',
  0
),
(
  'Goodwill',
  'Low-Cost',
  '{"Monday-Friday": "9AM-8PM", "Sunday": "9AM-6PM"}',
  '3400 Tulane Ave, New Orleans, LA 70119',
  '5044562622',
  '29.970682,-90.102183',
  'Free parking available in front; large donation center onsite',
  'place',
  0
),
(
  'Super Thrift Store',
  'Low-Cost',
  '{"Monday-Friday": "9AM-5PM", "Sunday": "Closed"}',
  '4243 Earhart Blvd, New Orleans, LA 70125',
  '5048212479',
  '29.956445,-90.147832',
  'Free parking available in front; large donation center on-site.',
  'place',
  0
),
(
  'Red, White, and Blue',
  'Low-Cost',
  '{"Monday-Sunday": "9AM-9PM"}',
  '5728 Jefferson Hwy, New Orleans, LA 70123',
  '5047338066',
  '29.9512377,-90.07130031',
  'Located near the Elmwood mall',
  'place',
  0
),
(
  'Out of the Closet Thrift Store',
  'Mixed',
  '{"Monday-Sunday": "10AM-7PM"}',
  '2900 Magazine St, New Orleans, LA 70115',
  '5048212400',
  '29.922742,-90.083875',
  'Proceeds benefit AIDS Healthcare Foundation; HIV testing offered on-site.',
  'place',
  0
);




INSERT INTO swegrg25."Food_Banks" (
    org_name, accommodation_limit, hours_of_operation, coordinate_location,
    address, contact_info, location_notes, permanent, user_data_index
)
VALUES
(
    'Ozanam Inn - Food Distribution Center',
    156,
    '"Everyday 9AM-4PM"',
    '29.95768851964588, -90.09003716183148',
    '2239 Poydras St, New Orleans, LA 70119',
    '15045231184',
    NULL,
    'place',
    0
),
(
    'Broadmoor Community Church',
    NULL,
    '{"Weekdays": "9 AM–5 PM", "Saturday": "Closed", "Sunday": "11 AM–12:30 AM"}',
    '29.94944663329988, -90.10415727389268',
    '2021 S Dupre St, New Orleans, LA 7012',
    '15048227229',
    NULL,
    'place',
    0
),
(
    'Nola Community Fridges',
    NULL,
    '{"Everyday": "24/7"}',
    '29.93340175458241, -90.08766407174201',
    '1823 Washington Ave, New Orleans, LA 70113',
    'https://www.nolacommunityfridges.org/contact-us',
    NULL,
    'place',
    0
),
(
    'Edible Schoolyard New Orleans',
    NULL,
    '{"Weekdays": "8 AM–5 AM", "Weekends": "Closed"}',
    '29.96706255937846, -90.08458855019092',
    '2319 Valence St, New Orleans, LA 70115',
    '15042679038',
    NULL,
    'place',
    0
),
(
    'New Orleans Mission Food Bank',
    NULL,
    '{"Everyday": "24/7"}',
    '29.94344274647142, -90.07716901951204',
    '1130 O.C. Haley Blvd, New Orleans, LA 70113',
    '15045232116',
    NULL,
    'place',
    0
),
(
    'Iggy''s Cupboard (Loyola Students Only)',
    NULL,
    '{"Weekdays": "9AM - 6PM", "Weekends": "9AM - 3PM"}',
    '29.93614582613223, -90.1204983825435',
    '6363 St Charles Ave, New Orleans, LA 70118',
    'imbrashe@loyno.edu',
    NULL,
    'place',
    0
),
(
    'Salvation Army Food Pantry',
    NULL,
    '{"Thursday": "1–4 PM"}',
    '29.93977903869515, -90.10493648851964',
    '4500 S Claiborne Ave, New Orleans, LA 70125',
    '15048994569',
    NULL,
    'place',
    0
);




INSERT INTO swegrg25."Med_Centers"
(org_name, average_wait_time, address, contact_info, services_provided, coordinate_location, location_notes, permanent, user_data_index)
VALUES
(
  'Oschner Urgent Care & Occupational Health - Uptown',
  45,
  '4605 Magazine St, New Orleans, LA 70115',
  '15043095015',
  '["Non-Emergency Urgent Care", "Minor Illness/Injury", "Physical Exam", "Drug and Illness Screening", "COVID Testing"]',
  '29.920736568485324,-90.10409178515874',
  '["Urgent Care"]',
  'place',
  0
),
(
  'MinuteClinic at CVS',
  22,
  '4401 S Claiborne Ave, New Orleans, LA 70125',
  '15043095015',
  '["Common skin conditions", "Common illnesses", "Minor injuries", "TB testing", "Flu shots", "Sports physicals"]',
  '29.94058794325153,-90.10418952520627',
  '["Walk-in clinic"]',
  'place',
  0
),
(
  'LCMC Health Urgent Care - Uptown',
  30,
  '5800 Magazine St A, New Orleans, LA 70115',
  '15049003160',
  '["Non-Emergency Urgent Care", "Minor Illness/Injury", "X-Ray", "Infections & Conditions", "Physical Exams", "DOT Testing"]',
  '29.94058794325153,-90.10418952520627',
  '["Urgent Care"]',
  'place',
  0
),
(
  'In & Out Urgent Care - New Orleans',
  25,
  '6225 S Claiborne Ave, New Orleans, LA 70125',
  '15048648080',
  '["Immediate Minor Medical Care", "Diagnostic Services", "Routine Physical Exams", "Vaccinations", "Occupational Health"]',
  '29.946359404871306,-90.11328857289743',
  '["Urgent Care"]',
  'place',
  0
),
(
  'Manning Family Children''s LCMC Health',
  28,
  '200 Henry Clay Ave, New Orleans, LA 70118',
  '15048999511',
  '["Pediatric Primary Care", "Behavioral Health"]',
  '29.91692490672255,-90.12785224481671',
  '["Emergency Care", "Outpatient Care"]',
  'place',
  0
),
(
  'Loyola Student Health Services',
  22,
  '6363 St Charles Ave, New Orleans, LA 70118',
  '15048653326',
  '["Primary care", "Urgent Care", "Vaccinations", "STD testing", "Well Woman Exams", "Gynecological services"]',
  '29.93614582613223,-90.1204983825435',
  '["Primary Care (Accessed only by Loyola Students)"]',
  'place',
  0
);




INSERT INTO swegrg25."Community_Centers"
(org_name, hours_of_operation, coordinate_location, address, contact_info, offered_services, location_notes, permanent, user_data_index)
VALUES
(
    'Dryades YMCA',
    '{"M-F": "7:00 AM - 7:00 PM", "Sat": "8:00 AM - 12:00 PM", "Sun": "Closed"}',
    '29.93648143271945, -90.0839767495443',
    '2220 Oretha C Haley Blvd, New Orleans, LA 70113',
    NULL,
    NULL,
    NULL,
    'place',
    0
),
(
    'Stallings St. Claude Recreation Center',
    '{"M-F": "8:00 AM - 5:00 PM"}',
    '29.97172741784939, -90.03893894217933',
    '4300 St Claude Ave, New Orleans, LA 70117',
    NULL,
    NULL,
    NULL,
    'place',
    0
),
(
    'Cut Off Recreation Center',
    '{"M-F": "8:00 AM - 5:00 PM"}',
    '29.901140122183055, -90.02644225784324',
    '6600 Belgrade St, New Orleans, LA 70131',
    NULL,
    NULL,
    NULL,
    'place',
    0
),
(
    'Joe W. Brown Recreation Center',
    '{"M-F": "8:00 AM - 5:00 PM"}',
    '30.022480156527206, -89.96888842952255',
    '5601 Read Blvd, New Orleans, LA 70127',
    NULL,
    NULL,
    NULL,
    'place',
    0
),
(
    'Gernon Brown Recreation Center',
    '{"M-F": "8:00 AM - 5:00 PM"}',
    '29.96032340380276, -90.11633915879578',
    '1001 Harrison Ave, New Orleans, LA 70124',
    NULL,
    NULL,
    NULL,
    'place',
    0
),
(
    'Milne Recreation Center',
    '{"M-F": "8:00 AM - 5:00 PM"}',
    '30.012121059272163, -90.0587350449685',
    '5420 Franklin Ave, New Orleans, LA 70122',
    NULL,
    NULL,
    NULL,
    'place',
    0
),
(
    'Treme Recreation Community Center',
    '{"M-F": "8:00 AM - 5:00 PM"}',
    '29.965663435669373, -90.07192283079169',
    '900 N Villere St, New Orleans, LA 70116',
    NULL,
    NULL,
    NULL,
    'place',
    0
);




--TEST DATA

INSERT INTO swegrg25."Community_Events" (name, description, event_date, user_data_index, address, contact_info, category, attendee_count, coordinate_location, location_notes, permanent)
VALUES ('test1', 'test data ', '2025-06-02 18:57:01.000000', 0, '123', '544', 'food', 500, '92', '01', 'event');

INSERT INTO swegrg25."Community_Events" (name, description, event_date, user_data_index, address, contact_info, category, attendee_count, coordinate_location, location_notes, permanent)
VALUES ('test5', 'test data ', '2025-06-03 18:57:01.000000', 0, '123', '544', 'food', 500, '92', '01', 'event');

INSERT INTO swegrg25."Community_Events" (name, description, event_date, user_data_index, address, contact_info, category, attendee_count, coordinate_location, location_notes, permanent)
VALUES ('test7', 'test data ', '2025-06-04 18:57:01.000000', 0, '123', '544', 'food', 500, '92', '01', 'event');

INSERT INTO swegrg25."Community_Events" (name, description, event_date, user_data_index, address, contact_info, category, attendee_count, coordinate_location, location_notes, permanent)
VALUES ('test2', 'test data ', '2025-06-06 18:57:01.000000', 0, '123', '544', 'food', 500, '92', '01', 'event');

INSERT INTO swegrg25."Community_Events" (name, description, event_date, user_data_index, address, contact_info, category, attendee_count, coordinate_location, location_notes, permanent)
VALUES ('test3', 'test data ', '2025-06-24 18:57:01.000000', 0, '123', '544', 'food', 500, '92', '01', 'event');

INSERT INTO swegrg25."Community_Events" (name, description, event_date, user_data_index, address, contact_info, category, attendee_count, coordinate_location, location_notes, permanent)
VALUES ('test6', 'test data ', '2025-06-10 18:57:01.000000', 0, '123', '544', 'food', 500, '92', '01', 'event');


INSERT INTO swegrg25."Organization" (org_name, address, contact_info, user_data_index) VALUES ('test1', '123', '1', 0);
INSERT INTO swegrg25."Organization" (org_name, address, contact_info, user_data_index) VALUES ('test2', '345', '2', 0);
INSERT INTO swegrg25."Organization" (org_name, address, contact_info, user_data_index) VALUES ('test3', '567', '3', 0);
INSERT INTO swegrg25."Organization" (org_name, address, contact_info, user_data_index) VALUES ('test4', '899', '4', 0);
INSERT INTO swegrg25."Organization" (org_name, address, contact_info, user_data_index) VALUES ('test5', '564', '5', 0);


INSERT INTO swegrg25."Volunteer_Opportunities" (org_name, org_data_index, vo_description, event_date, ongoing, requirements, coordinate_location, address, contact_info, location_notes, user_data_index, permanent)
VALUES ('test1', 44, 'trash pickup', '2025-05-08 19:27:01.000000', true, 'null', '45', '123', null, 'no parking', 0, 'volunteer');
INSERT INTO swegrg25."Volunteer_Opportunities" (org_name, org_data_index, vo_description, event_date, ongoing, requirements, coordinate_location, address, contact_info, location_notes, user_data_index, permanent)
VALUES ('test2', 45, 'community event', '2025-05-07 19:27:06.000000', false, 'null', '25', '445', null, 'yes parking', 0, 'volunteer');
INSERT INTO swegrg25."Volunteer_Opportunities" (org_name, org_data_index, vo_description, event_date, ongoing, requirements, coordinate_location, address, contact_info, location_notes, user_data_index, permanent)
VALUES ('test3', 46, 'vendor', '2025-05-09 19:27:09.000000', true, 'null', '23', '225', null, 'must RSVP', 0, 'volunteer');
INSERT INTO swegrg25."Volunteer_Opportunities" (org_name, org_data_index, vo_description, event_date, ongoing, requirements, coordinate_location, address, contact_info, location_notes, user_data_index, permanent)
VALUES ('test4', 47, 'arena volunteer', '2025-05-10 19:27:11.000000', true, 'null', '12', '44', null, 'Fill out Survey', 0, 'volunteer');
INSERT INTO swegrg25."Volunteer_Opportunities" (org_name, org_data_index, vo_description, event_date, ongoing, requirements, coordinate_location, address, contact_info, location_notes, user_data_index, permanent)
VALUES ('test5', 48, 'STEM Camp', '2025-05-12 19:27:14.000000', false, 'null', '78', '88', null, 'Experience Required', 0, 'volunteer');

