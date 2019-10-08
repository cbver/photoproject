DROP TABLE IF EXISTS photo;

CREATE TABLE photo (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  photo_name VARCHAR(50) NOT NULL,
  photo_user VARCHAR(50) NOT NULL,
  rating BIGINT,
  photo_descriptions VARCHAR(500) ,
  content_type VARCHAR(50),
  content IMAGE
);
