-- -------------------------
-- MOVIES (6)
-- IDs esperados: 1..6
-- -------------------------
INSERT INTO movies (title, release_year, genre, active) VALUES
                                                            ('Inception',            2010, 'SCI_FI',  true),
                                                            ('The Dark Knight',      2008, 'ACTION',  true),
                                                            ('Interstellar',         2014, 'SCI_FI',  true),
                                                            ('The Prestige',         2006, 'DRAMA',   true),
                                                            ('Dunkirk',              2017, 'WAR',     true),
                                                            ('Batman Begins',        2005, 'ACTION',  false); -- inactiva para filtros

-- -------------------------
-- ACTORS (6)
-- IDs esperados: 1..6
-- -------------------------
INSERT INTO actors (stage_name, full_name, nationality, active) VALUES
                                                                    ('DiCaprio',   'Leonardo DiCaprio', 'American',   true),
                                                                    ('Caine',      'Michael Caine',     'British',    true),
                                                                    ('Nolan',      'Christian Bale',    'British',    true),
                                                                    ('Murphy',     'Cillian Murphy',    'Irish',      true),
                                                                    ('Cotillard',  'Marion Cotillard',  'French',     true),
                                                                    ('Oldman',     'Gary Oldman',       'British',    false); -- inactivo para filtros

-- -------------------------
-- MOVIE_CAST (tabla intermedia con atributos)
-- Diseñado para cubrir todos los casos del plan de pruebas:
--   - Un actor en varias películas     (DiCaprio → Inception + Interstellar)
--   - Una película con varios actores  (Inception → DiCaprio + Caine + Cotillard)
--   - Actor inactivo con rol activo    (Oldman en Batman Begins)
-- -------------------------
INSERT INTO movie_cast (movie_id, actor_id, character_name, screen_minutes, salary_override, active) VALUES
--  Inception (1)
(1, 1, 'Cobb',          110, 5000000.00, true),   -- DiCaprio en Inception
(1, 2, 'Miles',          40, 1000000.00, true),   -- Caine en Inception
(1, 5, 'Mal',            55,        null, true),  -- Cotillard en Inception

--  The Dark Knight (2)
(2, 3, 'Batman',        120, 8000000.00, true),   -- Bale en The Dark Knight
(2, 4, 'Scarecrow',      15,        null, true),  -- Murphy en The Dark Knight
(2, 6, 'Gordon',         60, 2000000.00, true),   -- Oldman en The Dark Knight

--  Interstellar (3)
(3, 1, 'Cooper',        120, 7000000.00, true),   -- DiCaprio en Interstellar (actor en 2 películas)
(3, 2, 'Professor',      30, 1500000.00, true),   -- Caine en Interstellar

--  The Prestige (4)
(4, 3, 'Angier',         95, 4000000.00, true),   -- Bale en The Prestige
(4, 2, 'Cutter',         45,        null, true),  -- Caine en The Prestige (actor en 3 películas)

--  Dunkirk (5)
(5, 4, 'Tommy',         100, 3000000.00, true),   -- Murphy en Dunkirk

--  Batman Begins (6) — película inactiva
(6, 3, 'Bruce Wayne',   110, 5000000.00, true),   -- Bale en Batman Begins
(6, 6, 'Gordon',         50, 1000000.00, false);  -- Oldman en Batman Begins (rol inactivo)