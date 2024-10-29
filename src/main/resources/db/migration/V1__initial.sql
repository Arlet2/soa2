CREATE TYPE Weapon as ENUM(
    'HEAVY_BOLTGUN',
    'GRENADE_LAUNCHER',
    'MULTI_MELTA'
);

CREATE TABLE IF NOT EXISTS chapters (
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    marines_count BIGINT
);

CREATE TABLE IF NOT EXISTS space_marines (
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    x REAL NOT NULL,
    y DOUBLE PRECISION,
    health INTEGER NOT NULL,
    heart_count INTEGER NOT NULL,
    achievements TEXT,
    weapon_type Weapon,
    chapter_id BIGINT REFERENCES chapters
);