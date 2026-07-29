CREATE TABLE users (
id SERIAL PRIMARY KEY,
login VARCHAR(50) UNIQUE NOT NULL,
password VARCHAR(255) NOT NULL
);

CREATE TABLE teams (
id SERIAL PRIMARY KEY,
name VARCHAR(100) UNIQUE NOT NULL,
country VARCHAR(100) NOT NULL
);

CREATE TABLE players (
id SERIAL PRIMARY KEY,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
position VARCHAR(30),
date_of_birth DATE NOT NULL,
team_id INT NOT NULL,
CONSTRAINT fk_team
FOREIGN KEY (team_id)
REFERENCES teams(id)
ON DELETE CASCADE
);

INSERT INTO users (login, password) VALUES
('admin', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92'),
('user1', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92');

INSERT INTO teams (name, country) VALUES
('Real Madrid', 'Spain'),
('AEK Athens', 'Greece');

INSERT INTO players (first_name, last_name, position, team_id) VALUES
('Kylian', 'Mbappe', 'Forward', 1),
('Thibaut ', 'Courtois', 'Goalkeeper', 1),
('Harold', 'Moukoudi', 'Defender', 2),
('Petros', 'Mantalos', 'Midfielder', 2);