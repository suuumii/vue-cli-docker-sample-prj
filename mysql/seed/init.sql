-- DBの作成
DROP DATABASE IF EXISTS gc2_db;
CREATE DATABASE gc2_db;

-- 利用者テーブル
CREATE TABLE IF NOT EXISTS gc2_db.sc_users (
	id BIGINT NOT NULL,
	name VARCHAR(256) NOT NULL primary key comment 'ユーザ名',
	password VARCHAR(256) NOT NULL comment 'パスワード',
    del_flg BOOLEAN NOT NULL,
    created DATETIME NOT NULL,
    created_user VARCHAR(256) NOT NULL,
    modified DATETIME NOT NULL,
    modified_user VARCHAR(256) NOT NULL,
    INDEX sc_users_IX1(name, password, del_flg)
) comment='ログインユーザー';

INSERT INTO `gc2_db`.`sc_users` (`id`,`name`,`password`,`del_flg`,`created`,`created_user`,`modified`,`modified_user`) VALUES (1, 'root','$2a$10$APUqLp8bWhhD92KQWqXVsOgAdHIhEiODMR5YOs34CCa9qxiYomeYe',0,now(),1,now(),1);
INSERT INTO `gc2_db`.`sc_users` (`id`,`name`,`password`,`del_flg`,`created`,`created_user`,`modified`,`modified_user`) VALUES (2, 'adminuser','$2a$10$3Vb4ceKv5dBnPxFa91C0We/8DT6Dm9PluHQ.yU/t.RlyDCS6/TVny',0,now(),1,now(),1);


-- ゴミ分別種類マスタテーブル
CREATE TABLE IF NOT EXISTS gc2_db.mt_gc (
	gc_id BIGINT NOT NULL primary key comment 'ゴミID',
	gc_name VARCHAR(256) NOT NULL comment 'ゴミ名',
    del_flg BOOLEAN NOT NULL,
    created DATETIME NOT NULL,
    created_user VARCHAR(256) NOT NULL,
    modified DATETIME NOT NULL,
    modified_user VARCHAR(256) NOT NULL,
    INDEX mt_gc_IX1(gc_id, gc_name, del_flg)
) comment='ゴミ分別種類マスタ';

INSERT INTO `gc2_db`.`mt_gc` (`gc_id`, `gc_name`, `del_flg`, `created`, `created_user`, `modified`, `modified_user`) VALUES (1, '可燃', 0, now(), '1', now(), '1');
INSERT INTO `gc2_db`.`mt_gc` (`gc_id`, `gc_name`, `del_flg`, `created`, `created_user`, `modified`, `modified_user`) VALUES (2, '不燃', 0, now(), '1', now(), '1');
INSERT INTO `gc2_db`.`mt_gc` (`gc_id`, `gc_name`, `del_flg`, `created`, `created_user`, `modified`, `modified_user`) VALUES (3, 'プラ', 0, now(), '1', now(), '1');
INSERT INTO `gc2_db`.`mt_gc` (`gc_id`, `gc_name`, `del_flg`, `created`, `created_user`, `modified`, `modified_user`) VALUES (4, '資源', 0, now(), '1', now(), '1');


-- ゴミ分別カレンダー
CREATE TABLE IF NOT EXISTS gc2_db.tr_gc_calendar (
	id BIGINT NOT NULL primary key AUTO_INCREMENT,
    gc_date DATE NOT NULL comment 'ゴミ出し日',
	gc_id VARCHAR(256) NOT NULL comment 'ゴミID',
    del_flg BOOLEAN NOT NULL,
    created DATETIME NOT NULL,
    created_user VARCHAR(256) NOT NULL,
    modified DATETIME NOT NULL,
    modified_user VARCHAR(256) NOT NULL,
    INDEX tr_gc_calendar_IX1(gc_date, gc_id, del_flg)
) comment='ゴミ分別カレンダー';

INSERT INTO `gc2_db`.`tr_gc_calendar` (`id`, `gc_date`, `gc_id`, `del_flg`, `created`, `created_user`, `modified`, `modified_user`) VALUES (1, '2022-09-01', 1, 0, now(), '1', now(), '1');
INSERT INTO `gc2_db`.`tr_gc_calendar` (`id`, `gc_date`, `gc_id`, `del_flg`, `created`, `created_user`, `modified`, `modified_user`) VALUES (2, '2022-09-05', 1, 0, now(), '1', now(), '1');
INSERT INTO `gc2_db`.`tr_gc_calendar` (`id`, `gc_date`, `gc_id`, `del_flg`, `created`, `created_user`, `modified`, `modified_user`) VALUES (3, '2022-09-06', 4, 0, now(), '1', now(), '1');


-- カレンダーデータ取得クエリ　例）
select
	A.id,
    A.gc_date,
    A.gc_id,
    B.gc_name
from tr_gc_calendar as A
left join mt_gc as B
on B.gc_id = A.gc_id
and B.del_flg = 0
;

