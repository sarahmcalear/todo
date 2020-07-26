CREATE TABLE IF NOT EXISTS todo
(
    id          UUID PRIMARY KEY,
    title       VARCHAR(200) NOT NULL,
    description VARCHAR(200),
    timestamp   TIMESTAMP WITH TIME ZONE NOT NULL
)