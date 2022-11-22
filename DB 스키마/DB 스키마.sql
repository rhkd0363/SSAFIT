CREATE TABLE `user` (
  `user_name` varchar(45) NOT NULL COMMENT '회원 이름. 회원명',
  `user_id` varchar(45) NOT NULL COMMENT '회원 ID. 회원ID',
  `user_pw` varchar(45) NOT NULL COMMENT '회원 PW. 회원PW',
  `user_email` varchar(45) NOT NULL COMMENT '회원 email. 이메일',
  `user_phone_number` varchar(15) NOT NULL COMMENT '회원 전화번호. 연락처',
  `user_img` varchar(200) DEFAULT NULL COMMENT '사진 URL',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='유저테이블. 유저 테이블';

#==================================================================================================================================

CREATE TABLE `video` (
  `video_id` varchar(200) NOT NULL COMMENT 'ID. 영상 ID',
  `video_title` varchar(200) NOT NULL COMMENT '제목. 영상 제목',
  `channel_title` varchar(200) NOT NULL COMMENT '채널명. 채널명',
  `channel_id` varchar(100) NOT NULL COMMENT '채널ID. 채널 ID',
  `thumbnail_url` varchar(200) NOT NULL COMMENT '썸네일 주소. 썸네일 URL',
  `view_cnt` int DEFAULT '0',
  `body_part` varchar(5) NOT NULL,
  PRIMARY KEY (`video_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='영상 테이블. 비디오 테이블';

#==================================================================================================================================

CREATE TABLE `reply` (
  `reply_id` int NOT NULL AUTO_INCREMENT COMMENT '대댓글 ID',
  `review_id` int NOT NULL COMMENT '부모 댓글 ID',
  `reply_content` text NOT NULL COMMENT '대댓글 내용',
  `reg_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성일시',
  `user_id` varchar(45) NOT NULL COMMENT '회원 ID',
  `user_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`reply_id`,`review_id`),
  KEY `FK_REPLY_review_id_VIDEO_REVIEW_review_id` (`review_id`),
  CONSTRAINT `FK_REPLY_review_id_VIDEO_REVIEW_review_id` FOREIGN KEY (`review_id`) REFERENCES `video_review` (`review_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='대댓글 테이블';

#==================================================================================================================================

CREATE TABLE `user_follow` (
  `user_id` varchar(45) NOT NULL,
  `follow_id` varchar(45) NOT NULL,
  `follow_yn` int NOT NULL COMMENT '팔로우여부(1/0)',
  PRIMARY KEY (`user_id`,`follow_id`),
  KEY `FK_USER_FOLLOW_follow_id_USER_user_id` (`follow_id`),
  CONSTRAINT `FK_USER_FOLLOW_follow_id_USER_user_id` FOREIGN KEY (`follow_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_USER_FOLLOW_user_id_USER_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='유저 팔로우 테이블';

#==================================================================================================================================

CREATE TABLE `video_like` (
  `video_id` varchar(200) NOT NULL COMMENT '비디오ID. 영상ID',
  `user_id` varchar(45) NOT NULL COMMENT '회원 ID. 회원ID',
  `like_yn` int NOT NULL COMMENT '좋아요 여부(1,0). 좋아요여부(1/0)',
  PRIMARY KEY (`video_id`,`user_id`),
  KEY `FK_VIDEO_LIKE_user_id_USER_user_id` (`user_id`),
  CONSTRAINT `FK_VIDEO_LIKE_user_id_USER_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_VIDEO_LIKE_video_id_VIDEO_video_id` FOREIGN KEY (`video_id`) REFERENCES `video` (`video_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='영상 좋아요 관리. 영상 좋아요 테이블';

#==================================================================================================================================

CREATE TABLE `video_review` (
  `video_id` varchar(200) NOT NULL COMMENT '비디오 ID',
  `user_id` varchar(45) NOT NULL COMMENT '회원 ID',
  `review_content` text NOT NULL COMMENT '리뷰 내용',
  `review_id` int NOT NULL AUTO_INCREMENT COMMENT '리뷰 ID',
  `reg_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성일시',
  `user_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`review_id`,`video_id`,`user_id`),
  KEY `FK_VIDEO_REVIEW_video_id_VIDEO_video_id` (`video_id`),
  KEY `FK_VIDEO_REVIEW_user_id_USER_user_id` (`user_id`),
  CONSTRAINT `FK_VIDEO_REVIEW_user_id_USER_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_VIDEO_REVIEW_video_id_VIDEO_video_id` FOREIGN KEY (`video_id`) REFERENCES `video` (`video_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='영상 리뷰 테이블';



#+====================================

CREATE TABLE `board` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '게시글 ID',
  `title` varchar(200) NOT NULL COMMENT '제목',
  `user_id` varchar(45) NOT NULL COMMENT '작성자',
  `content` text NOT NULL COMMENT '내용',
  `reg_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성일자',
  `view_cnt` int DEFAULT '0',
  PRIMARY KEY (`id`,`user_id`),
  KEY `FK_BOARD_user_id_USER_user_id` (`user_id`),
  CONSTRAINT `FK_BOARD_user_id_USER_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='게시글 테이블';


#===================================================
CREATE TABLE `trekking_course` (
  `ESNTL_ID` varchar(100) NOT NULL COMMENT '코스 ID',
  `WLK_COURS_FLAG_NM` varchar(300) COMMENT '산책경로구분명',
  `WLK_COURS_NM` varchar(300) COMMENT '산책경로명',
  `COURS_DC` text COMMENT '경로설명',
  `SIGNGU_NM`varchar(300) COMMENT '시군구명',
  `COURS_LEVEL_NM` varchar(300) COMMENT '경로레벨명',
  `COURS_LT_CN` varchar(600) COMMENT '경로길이내용',
  `COURS_DETAIL_LT_CN` varchar(600) COMMENT '경로상세길이내용',
  `ADIT_DC` text COMMENT '추가설명',
  `COURS_TIME_CN` varchar(600) COMMENT '경로시간내용',
  `OPTN_DC` varchar(600) COMMENT '옵션설명',
  `TOILET_DC` varchar(600) COMMENT '화장실설명',
  `CVNTL_NM` varchar(300) COMMENT '편의시설명',
  `LNM_ADDR` varchar(300) COMMENT '지번주소',
  `COURS_SPOT_LA` varchar(20) COMMENT '경로지점위도',
  `COURS_SPOT_LO` varchar(20) COMMENT '경로지점경도',
  PRIMARY KEY (`ESNTL_ID`)
) ENGINE=InnoDB default character set utf8 collate utf8_general_ci COMMENT='트레킹코스 테이블';
