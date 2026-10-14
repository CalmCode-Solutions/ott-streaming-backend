-- 1. Users Table
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Subscription Plans Table
CREATE TABLE subscription_plans (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    resolution VARCHAR(50) NOT NULL,
    max_screens INT DEFAULT 1,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 3. Subscriptions Table
CREATE TABLE subscriptions (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    plan_id BIGINT REFERENCES subscription_plans(id) ON DELETE SET NULL,
    status VARCHAR(50) DEFAULT 'ACTIVE', -- ACTIVE, EXPIRED, CANCELLED
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL
);

-- 4. Videos Table (Movies, Series, Documentaries)
CREATE TABLE videos (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    type VARCHAR(50) NOT NULL, -- MOVIE, SERIES, DOCUMENTARY
    genre VARCHAR(100),
    poster_url VARCHAR(500),
    video_url VARCHAR(500),
    release_year INT,
    duration_minutes INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 5. Live Channels Table
CREATE TABLE channels (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(100),
    stream_url VARCHAR(500) NOT NULL,
    logo_url VARCHAR(500),
    is_active BOOLEAN DEFAULT TRUE
);

-- 6. User Favorites (My List) Table - Many-to-Many
CREATE TABLE user_favorites (
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    video_id BIGINT NOT NULL REFERENCES videos(id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, video_id)
);

-- 7. Watch History & Progress Table
CREATE TABLE watch_history (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    video_id BIGINT NOT NULL REFERENCES videos(id) ON DELETE CASCADE,
    progress_seconds INT DEFAULT 0,
    duration_seconds INT DEFAULT 0,
    last_watched_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ==========================================
-- Insert Default Data (Seed Data)
-- ==========================================

-- Insert Basic Subscription Plans
INSERT INTO subscription_plans (name, price, resolution, max_screens) VALUES
('Basic', 9.99, '720p', 1),
('Standard', 14.99, '1080p', 2),
('Premium', 19.99, '4K+HDR', 4);

-- Insert Default Admin User
-- (Password is 'Admin@12345' hashed with BCrypt)
INSERT INTO users (name, email, password, role) VALUES
('Admin', 'admin@ott.com', '$2a$10$x.F.0.2Wq/y1W9Zt/C3o6.s6tU.eXk4Z2H/o7uVqK9h3U1f1qM8C6', 'ADMIN');