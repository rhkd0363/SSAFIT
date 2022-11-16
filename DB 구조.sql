CREATE DATABASE SSAFIT_DB;

USE SSAFIT_DB;

#---------------------------------------------------------------------------
CREATE TABLE VIDEO
(
    `video_id`       VARCHAR(200)    NOT NULL    COMMENT 'ID. 영상 ID', 
    `video_title`    VARCHAR(200)    NOT NULL    COMMENT '제목. 영상 제목', 
    `channel_title`  VARCHAR(200)    NOT NULL    COMMENT '채널명. 채널명', 
    `channel_id`     VARCHAR(100)    NOT NULL    COMMENT '채널ID. 채널 ID', 
    `thumbnail_url`  VARCHAR(200)    NOT NULL    COMMENT '썸네일 주소. 썸네일 URL', 
     PRIMARY KEY (video_id)
);
ALTER TABLE VIDEO COMMENT '영상 테이블. 비디오 테이블';

ALTER TABLE VIDEO ADD COLUMN view_cnt int default 0;

alter table video add column body_part varchar(5) not null;

#---------------------------------------------------------------------------

CREATE TABLE USER
(
    `user_name`          VARCHAR(45)     NOT NULL    COMMENT '회원 이름. 회원명', 
    `user_id`            VARCHAR(45)     NOT NULL    COMMENT '회원 ID. 회원ID', 
    `user_pw`            VARCHAR(45)     NOT NULL    COMMENT '회원 PW. 회원PW', 
    `user_email`         VARCHAR(45)     NOT NULL    COMMENT '회원 email. 이메일', 
    `user_phone_number`  VARCHAR(15)     NOT NULL    COMMENT '회원 전화번호. 연락처', 
    `user_img`           VARCHAR(200)    NULL        COMMENT '사진 URL', 
     PRIMARY KEY (user_id)
);

ALTER TABLE USER COMMENT '유저테이블. 유저 테이블';

#---------------------------------------------------------------------------

CREATE TABLE VIDEO_LIKE
(
    `video_id`  VARCHAR(200)    NOT NULL    COMMENT '비디오ID. 영상ID', 
    `user_id`   VARCHAR(45)     NOT NULL    COMMENT '회원 ID. 회원ID', 
    `like_yn`   INT             NOT NULL    COMMENT '좋아요 여부(1,0). 좋아요여부(1/0)', 
     PRIMARY KEY (video_id, user_id)
);

ALTER TABLE VIDEO_LIKE COMMENT '영상 좋아요 관리. 영상 좋아요 테이블';
ALTER TABLE VIDEO_LIKE
    ADD CONSTRAINT FK_VIDEO_LIKE_video_id_VIDEO_video_id FOREIGN KEY (video_id)
        REFERENCES VIDEO (video_id) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE VIDEO_LIKE
    ADD CONSTRAINT FK_VIDEO_LIKE_user_id_USER_user_id FOREIGN KEY (user_id)
        REFERENCES USER (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT;
        
        

#---------------------------------------------------------------------------

CREATE TABLE USER_FOLLOW
(
    `user_id`    VARCHAR(45)    NOT NULL, 
    `follow_id`  VARCHAR(45)    NOT NULL, 
    `follow_yn`  INT            NOT NULL    COMMENT '팔로우여부(1/0)', 
     PRIMARY KEY (user_id, follow_id)
);

ALTER TABLE USER_FOLLOW COMMENT '유저 팔로우 테이블';

ALTER TABLE USER_FOLLOW
    ADD CONSTRAINT FK_USER_FOLLOW_user_id_USER_user_id FOREIGN KEY (user_id)
        REFERENCES USER (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE USER_FOLLOW
    ADD CONSTRAINT FK_USER_FOLLOW_follow_id_USER_user_id FOREIGN KEY (follow_id)
        REFERENCES USER (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT;
        
#---------------------------------------------------------------------------

CREATE TABLE VIDEO_REVIEW
(
    `video_id`        VARCHAR(200)    NOT NULL    COMMENT '비디오 ID', 
    `user_id`         VARCHAR(45)     NOT NULL    COMMENT '회원 ID', 
    `review_content`  TEXT            NOT NULL    COMMENT '리뷰 내용', 
    `review_id`       INT             NOT NULL    AUTO_INCREMENT COMMENT '리뷰 ID', 
    `reg_date`        DATETIME        NOT NULL    DEFAULT now() COMMENT '작성일시', 
     PRIMARY KEY (review_id, video_id, user_id)
);

ALTER TABLE VIDEO_REVIEW COMMENT '영상 리뷰 테이블';

ALTER TABLE VIDEO_REVIEW
    ADD CONSTRAINT FK_VIDEO_REVIEW_video_id_VIDEO_video_id FOREIGN KEY (video_id)
        REFERENCES VIDEO (video_id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE VIDEO_REVIEW
    ADD CONSTRAINT FK_VIDEO_REVIEW_user_id_USER_user_id FOREIGN KEY (user_id)
        REFERENCES USER (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT;

#---------------------------------------------------------------------------

CREATE TABLE REPLY
(
    `reply_id`       INT            NOT NULL    AUTO_INCREMENT COMMENT '대댓글 ID', 
    `review_id`      INT            NOT NULL    COMMENT '부모 댓글 ID', 
    `reply_content`  TEXT           NOT NULL    COMMENT '대댓글 내용', 
    `reg_date`       DATETIME       NOT NULL    DEFAULT now() COMMENT '작성일시', 
    `user_id`        VARCHAR(45)    NOT NULL    COMMENT '회원 ID', 
     PRIMARY KEY (reply_id, review_id)
);

ALTER TABLE REPLY COMMENT '대댓글 테이블';

ALTER TABLE REPLY
    ADD CONSTRAINT FK_REPLY_review_id_VIDEO_REVIEW_review_id FOREIGN KEY (review_id)
        REFERENCES VIDEO_REVIEW (review_id) ON DELETE RESTRICT ON UPDATE RESTRICT;


#---------------------------------------------------------------------------video_like

insert into VIDEO (video_id, video_title, channel_title, channel_id, thumbnail_url, view_cnt, body_part )
		VALUES ('1','1','1','1','1','1','1');

select * from video limit 10000