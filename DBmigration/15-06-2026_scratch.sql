- =========================================================================
-- STEP 1: CLEAN UP EXISTING TEMPORARY SETUP
-- =========================================================================
ALTER TABLE users DROP COLUMN IF EXISTS uuid_id CASCADE;
ALTER TABLE posts DROP COLUMN IF EXISTS uuid_id CASCADE;
ALTER TABLE comments DROP COLUMN IF EXISTS uuid_id CASCADE;
ALTER TABLE albums DROP COLUMN IF EXISTS uuid_id CASCADE;
ALTER TABLE photos DROP COLUMN IF EXISTS uuid_id CASCADE;
ALTER TABLE todos DROP COLUMN IF EXISTS uuid_id CASCADE;

-- =========================================================================
-- STEP 2: ACTIVATE EXTENSION AND ADD FRESH UNIQUE UUID COLUMNS
-- =========================================================================
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Users
ALTER TABLE users ADD COLUMN uuid_id UUID DEFAULT gen_random_uuid();
UPDATE users SET uuid_id = gen_random_uuid() WHERE uuid_id IS NULL;
ALTER TABLE users ALTER COLUMN uuid_id SET NOT NULL;

-- Posts
ALTER TABLE posts ADD COLUMN uuid_id UUID DEFAULT gen_random_uuid();
UPDATE posts SET uuid_id = gen_random_uuid() WHERE uuid_id IS NULL;
ALTER TABLE posts ALTER COLUMN uuid_id SET NOT NULL;

-- Comments
ALTER TABLE comments ADD COLUMN uuid_id UUID DEFAULT gen_random_uuid();
UPDATE comments SET uuid_id = gen_random_uuid() WHERE uuid_id IS NULL;
ALTER TABLE comments ALTER COLUMN uuid_id SET NOT NULL;

-- Albums
ALTER TABLE albums ADD COLUMN uuid_id UUID DEFAULT gen_random_uuid();
UPDATE albums SET uuid_id = gen_random_uuid() WHERE uuid_id IS NULL;
ALTER TABLE albums ALTER COLUMN uuid_id SET NOT NULL;

-- Photos
ALTER TABLE photos ADD COLUMN uuid_id UUID DEFAULT gen_random_uuid();
UPDATE photos SET uuid_id = gen_random_uuid() WHERE uuid_id IS NULL;
ALTER TABLE photos ALTER COLUMN uuid_id SET NOT NULL;

-- Todos
ALTER TABLE todos ADD COLUMN uuid_id UUID DEFAULT gen_random_uuid();
UPDATE todos SET uuid_id = gen_random_uuid() WHERE uuid_id IS NULL;
ALTER TABLE todos ALTER COLUMN uuid_id SET NOT NULL;

-- =========================================================================
-- STEP 3: CONVERT AUDIT DATA STRUCT NAMES (RENAME ID TO EXTERNAL_ID)
-- =========================================================================
-- Drop old primary key constraints to unlock column renaming routines
ALTER TABLE users DROP CONSTRAINT IF EXISTS users_pkey CASCADE;
ALTER TABLE posts DROP CONSTRAINT IF EXISTS posts_pkey CASCADE;
ALTER TABLE comments DROP CONSTRAINT IF EXISTS comments_pkey CASCADE;
ALTER TABLE albums DROP CONSTRAINT IF EXISTS albums_pkey CASCADE;
ALTER TABLE photos DROP CONSTRAINT IF EXISTS photos_pkey CASCADE;
ALTER TABLE todos DROP CONSTRAINT IF EXISTS todos_pkey CASCADE;

-- Execute Column Renaming operations
ALTER TABLE users RENAME COLUMN id TO external_id;
ALTER TABLE posts RENAME COLUMN id TO external_id;
ALTER TABLE comments RENAME COLUMN id TO external_id;
ALTER TABLE albums RENAME COLUMN id TO external_id;
ALTER TABLE photos RENAME COLUMN id TO external_id;
ALTER TABLE todos RENAME COLUMN id TO external_id;

-- =========================================================================
-- STEP 4: MIGRATE RELATIONAL FIELD DATA MAPS TO UUID VECTORS
-- =========================================================================
-- 1. Migrate posts (User relationship)
ALTER TABLE posts ADD COLUMN user_uuid UUID;
UPDATE posts p SET user_uuid = u.uuid_id FROM users u WHERE p.user_id = u.external_id;
ALTER TABLE posts ALTER COLUMN user_uuid SET NOT NULL;
ALTER TABLE posts DROP COLUMN user_id;
ALTER TABLE posts RENAME COLUMN user_uuid TO user_id;

-- 2. Migrate comments (Post relationship)
ALTER TABLE comments ADD COLUMN post_uuid UUID;
UPDATE comments c SET post_uuid = p.uuid_id FROM posts p WHERE c.post_id = p.external_id;
ALTER TABLE comments ALTER COLUMN post_uuid SET NOT NULL;
ALTER TABLE comments DROP COLUMN post_id;
ALTER TABLE comments RENAME COLUMN post_uuid TO post_id;

-- 3. Migrate albums (User relationship)
ALTER TABLE albums ADD COLUMN user_uuid UUID;
UPDATE albums a SET user_uuid = u.uuid_id FROM users u WHERE a.user_id = u.external_id;
ALTER TABLE albums ALTER COLUMN user_uuid SET NOT NULL;
ALTER TABLE albums DROP COLUMN user_id;
ALTER TABLE albums RENAME COLUMN user_uuid TO user_id;

-- 4. Migrate photos (Album relationship)
ALTER TABLE photos ADD COLUMN album_uuid UUID;
UPDATE photos ph SET album_uuid = a.uuid_id FROM albums a WHERE ph.album_id = a.external_id;
ALTER TABLE photos ALTER COLUMN album_uuid SET NOT NULL;
ALTER TABLE photos DROP COLUMN album_id;
ALTER TABLE photos RENAME COLUMN album_uuid TO album_id;

-- 5. Migrate todos (User relationship)
ALTER TABLE todos ADD COLUMN user_uuid UUID;
UPDATE todos t SET user_uuid = u.uuid_id FROM users u WHERE t.user_id = u.external_id;
ALTER TABLE todos ALTER COLUMN user_uuid SET NOT NULL;
ALTER TABLE todos DROP COLUMN user_id;
ALTER TABLE todos RENAME COLUMN user_uuid TO user_id;

-- =========================================================================
-- STEP 5: INITIALIZE NEW PRIMARY KEY CONSTRAINTS 
-- =========================================================================
ALTER TABLE users ADD CONSTRAINT users_pkey PRIMARY KEY (uuid_id);
ALTER TABLE posts ADD CONSTRAINT posts_pkey PRIMARY KEY (uuid_id);
ALTER TABLE comments ADD CONSTRAINT comments_pkey PRIMARY KEY (uuid_id);
ALTER TABLE albums ADD CONSTRAINT albums_pkey PRIMARY KEY (uuid_id);
ALTER TABLE photos ADD CONSTRAINT photos_pkey PRIMARY KEY (uuid_id);
ALTER TABLE todos ADD CONSTRAINT todos_pkey PRIMARY KEY (uuid_id);

-- =========================================================================
-- STEP 6: ENFORCE DATABASE FOREIGN KEY CONSTRAINTS
-- =========================================================================
ALTER TABLE posts ADD CONSTRAINT fk_posts_user FOREIGN KEY (user_id) REFERENCES users(uuid_id);
ALTER TABLE comments ADD CONSTRAINT fk_comments_post FOREIGN KEY (post_id) REFERENCES posts(uuid_id);
ALTER TABLE albums ADD CONSTRAINT fk_albums_user FOREIGN KEY (user_id) REFERENCES users(uuid_id);
ALTER TABLE photos ADD CONSTRAINT fk_photos_album FOREIGN KEY (album_id) REFERENCES albums(uuid_id);
ALTER TABLE todos ADD CONSTRAINT fk_todos_user FOREIGN KEY (user_id) REFERENCES users(uuid_id);