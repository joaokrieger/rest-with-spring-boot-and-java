INSERT INTO person (id, address, first_name, gender, last_name) VALUES
    ((SELECT NEXTVAL('person_id_seq')), 'Hollywood, Los Angeles', 'Tom', 'Male', 'Hanks'),
    ((SELECT NEXTVAL('person_id_seq')), 'Abbey Road, London', 'John', 'Male', 'Lennon'),
    ((SELECT NEXTVAL('person_id_seq')), 'Beverly Hills, California', 'Marilyn', 'Female', 'Monroe'),
    ((SELECT NEXTVAL('person_id_seq')), 'Paris, France', 'Napoleon', 'Male', 'Bonaparte'),
    ((SELECT NEXTVAL('person_id_seq')), 'Mount Vernon, Virginia', 'George', 'Male', 'Washington'),
    ((SELECT NEXTVAL('person_id_seq')), 'Moscow, Russia', 'Vladimir', 'Male', 'Putin'),
    ((SELECT NEXTVAL('person_id_seq')), 'Cape Town, South Africa', 'Nelson', 'Male', 'Mandela'),
    ((SELECT NEXTVAL('person_id_seq')), 'Rome, Italy', 'Leonardo', 'Male', 'Da Vinci'),
    ((SELECT NEXTVAL('person_id_seq')), 'Birmingham, England', 'Ozzy', 'Male', 'Osbourne'),
    ((SELECT NEXTVAL('person_id_seq')), 'Madrid, Spain', 'Penélope', 'Female', 'Cruz');
