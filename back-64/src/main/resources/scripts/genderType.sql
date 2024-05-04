-- Masculino
INSERT INTO main.gender_type (created_at, updated_at, gender_type_id, code, "name") VALUES (now(), now(), 'af19c1e4-9076-42ee-b682-36a2af8b7dbb', 'M', 'Masculino');

-- Femenino
INSERT INTO main.gender_type (created_at, updated_at, gender_type_id, code, "name") VALUES (now(), now(), '7346bc01-449f-4d79-b1d0-a1c0dbfb06cb', 'F', 'Femenino');

-- Indefinido
INSERT INTO main.gender_type (created_at, updated_at, gender_type_id, code, "name") VALUES (now(), now(), '578a3aca-f995-4ee5-85cd-ce9de25c206b', 'I', 'Indefinido');

-- Prefiero no decir
INSERT INTO main.gender_type (created_at, updated_at, gender_type_id, code, "name") VALUES (now(), now(), '276dd2a2-ebd6-479c-a136-1f312469e06b', 'PND', 'Prefiero no decir');