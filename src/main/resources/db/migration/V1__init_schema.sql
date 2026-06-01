CREATE TABLE authors (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    birth_date DATE
);

CREATE TABLE books (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    publishing_year INTEGER,
    author_id BIGINT NOT NULL,

    CONSTRAINT fk_book_author
        FOREIGN KEY (author_id)
        REFERENCES authors(id)
        ON DELETE RESTRICT
);

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    role VARCHAR(20) NOT NULL,

    CONSTRAINT chk_user_role
        CHECK (role IN ('USER', 'ADMIN'))
);

CREATE TABLE comments (
    id BIGSERIAL PRIMARY KEY,
    text_content TEXT NOT NULL,
    post_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    user_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    parent_comment_id BIGINT,

    CONSTRAINT fk_comment_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_comment_book
        FOREIGN KEY (book_id)
        REFERENCES books(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_comment_parent
        FOREIGN KEY (parent_comment_id)
        REFERENCES comments(id)
        ON DELETE CASCADE
);

CREATE TABLE favorite_books (
    user_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,

    PRIMARY KEY (user_id, book_id),

    CONSTRAINT fk_favorite_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_favorite_book
        FOREIGN KEY (book_id)
        REFERENCES books(id)
        ON DELETE CASCADE
);

CREATE INDEX idx_books_author
    ON books(author_id);

CREATE INDEX idx_comments_book
    ON comments(book_id);

CREATE INDEX idx_comments_user
    ON comments(user_id);

CREATE INDEX idx_comments_parent
    ON comments(parent_comment_id);

CREATE INDEX idx_favorite_books_user
    ON favorite_books(user_id);

CREATE INDEX idx_favorite_books_book
    ON favorite_books(book_id);