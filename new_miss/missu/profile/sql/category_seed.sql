-- ============================================================
-- missu.category 种子数据（39 行）
-- 数据来源：用户提供的 Navicat 截图（clip_20261111_055358_2.png）
-- 提取方式：本地 RapidOCR，id 列做行锚点 + 逐行裁切放大识别 + 逐列复核
--
-- 低置信度/存疑单元格（已按 OCR 结果原样写入，需要可自行核对）：
--   id=12 update_time  截图只显示到 .56，补 .560
--   id=27 name         OCR 显示“餐边”（列宽可能截断），按语义补为“餐边柜”
--   id=30 online       该格 OCR 未取到，按同批数据统一填 1
--   id=35 parent_id=4  家具挂在居家下，OCR 两次一致，照录
--   id=8  img          u8.png（一次读到 u6，两次读到 u8，取 u8）
--   is_root=1 的 24/27/29/33/37 行：原数据如此（看起来是实验时改过的），照录
--
-- 用法：
--   mysql -uroot -p --default-character-set=utf8mb4 missu < category_seed.sql
-- 表非空时可用下面这句替换 INSERT（会覆盖同 id 的行）：
--   把 INSERT INTO 改成 REPLACE INTO 即可
-- ============================================================

SET NAMES utf8mb4;

INSERT INTO `category`
  (`id`, `name`, `description`, `create_time`, `update_time`, `delete_time`,
   `is_root`, `parent_id`, `img`, `index`, `online`, `level`)
VALUES
  (1, '鞋', NULL, '2019-07-15 08:51:19.000', '2019-09-21 23:23:28.452', NULL, 1, NULL, 'http://i2.sleeve.7yue.pro/u2.png', 3, 1, NULL),
  (2, '服装', NULL, '2019-07-15 08:51:28.000', '2019-09-21 23:25:37.271', NULL, 1, NULL, 'http://i2.sleeve.7yue.pro/u3.png', 2, 1, NULL),
  (3, '包包', NULL, '2019-07-15 08:51:35.000', '2019-09-21 23:26:14.260', NULL, 1, NULL, 'http://i2.sleeve.7yue.pro/u3.png', 1, 1, NULL),
  (4, '居家', NULL, '2019-07-15 08:51:42.000', '2019-09-21 23:26:17.106', NULL, 1, NULL, 'http://i2.sleeve.7yue.pro/u2.png', 5, 1, NULL),
  (5, '饰品', NULL, '2019-07-15 08:51:49.000', '2019-09-21 23:26:25.245', NULL, 1, NULL, 'http://i2.sleeve.7yue.pro/u2.png', 4, 1, NULL),
  (6, '平底鞋', NULL, '2019-07-15 08:51:55.000', '2019-09-21 23:32:00.822', NULL, 0, 1, 'http://i2.sleeve.7yue.pro/u4.png', NULL, 1, NULL),
  (7, '凉鞋', NULL, '2019-07-15 08:52:47.000', '2019-09-21 23:32:04.346', NULL, 0, 1, 'http://i2.sleeve.7yue.pro/u5.png', NULL, 1, NULL),
  (8, '拖鞋', NULL, '2019-07-15 08:53:04.000', '2019-09-21 23:37:12.047', NULL, 0, 1, 'http://i2.sleeve.7yue.pro/u8.png', NULL, 1, NULL),
  (9, '运动鞋', NULL, '2019-07-15 08:53:23.000', '2019-09-21 23:37:18.707', NULL, 0, 1, 'http://i2.sleeve.7yue.pro/u4.png', NULL, 1, NULL),
  (10, '高跟鞋', NULL, '2019-07-15 08:53:51.000', '2019-09-21 23:37:26.999', NULL, 0, 1, 'http://i2.sleeve.7yue.pro/u5.png', NULL, 1, NULL),
  (11, '衬衫', NULL, '2019-07-15 08:54:41.000', '2019-09-21 23:37:40.172', NULL, 0, 2, 'http://i2.sleeve.7yue.pro/u5.png', NULL, 1, NULL),
  (12, 'T恤', NULL, '2019-07-15 08:55:11.000', '2019-09-21 23:37:47.560', NULL, 0, 2, 'http://i2.sleeve.7yue.pro/u4.png', NULL, 1, NULL),
  (13, '牛仔裤', NULL, '2019-07-15 08:55:58.000', '2019-09-21 23:37:50.095', NULL, 0, 2, 'http://i2.sleeve.7yue.pro/u5.png', NULL, 1, NULL),
  (14, '针织衫', NULL, '2019-07-15 08:56:27.000', '2019-09-21 23:37:55.138', NULL, 0, 2, 'http://i2.sleeve.7yue.pro/u6.png', NULL, 1, NULL),
  (15, '连衣裙', NULL, '2019-07-15 08:56:43.000', '2019-09-21 23:38:08.140', NULL, 0, 2, 'http://i2.sleeve.7yue.pro/u6.png', NULL, 1, NULL),
  (16, '风衣', NULL, '2019-07-15 08:57:38.000', '2019-09-21 23:38:10.184', NULL, 0, 2, 'http://i2.sleeve.7yue.pro/u5.png', NULL, 1, NULL),
  (17, '手包', NULL, '2019-07-15 08:58:12.000', '2019-09-21 23:38:17.871', NULL, 0, 3, 'http://i2.sleeve.7yue.pro/u6.png', NULL, 1, NULL),
  (18, '旅行包', NULL, '2019-07-15 08:58:38.000', '2019-09-21 23:38:24.079', NULL, 0, 3, 'http://i2.sleeve.7yue.pro/u4.png', NULL, 1, NULL),
  (19, '单肩包', NULL, '2019-07-15 08:58:51.000', '2019-09-21 23:38:28.196', NULL, 0, 3, 'http://i2.sleeve.7yue.pro/u5.png', NULL, 1, NULL),
  (20, '收纳', NULL, '2019-07-15 09:00:19.000', '2019-09-21 23:31:27.823', NULL, 0, 4, 'http://i2.sleeve.7yue.pro/u4.png', NULL, 1, NULL),
  (21, '毛巾', NULL, '2019-07-15 09:01:38.000', '2019-09-21 23:31:32.074', NULL, 0, 4, 'http://i2.sleeve.7yue.pro/u5.png', NULL, 1, NULL),
  (22, '四件套', NULL, '2019-07-15 09:04:52.000', '2019-09-21 23:31:35.949', NULL, 0, 4, 'http://i2.sleeve.7yue.pro/u6.png', NULL, 1, NULL),
  (23, '台灯', NULL, '2019-07-15 14:18:40.000', '2019-09-21 23:31:45.456', NULL, 0, 4, 'http://i2.sleeve.7yue.pro/u4.png', NULL, 1, NULL),
  (24, '工艺', NULL, '2019-07-15 14:20:05.000', '2019-09-21 23:26:58.146', NULL, 1, NULL, 'http://i2.sleeve.7yue.pro/u3.png', 1, 1, NULL),
  (25, '玻璃杯', NULL, '2019-07-15 14:23:01.000', '2019-09-21 23:40:47.698', NULL, 0, 27, 'http://i2.sleeve.7yue.pro/u6.png', NULL, 1, NULL),
  (26, '桌布', NULL, '2019-07-15 14:26:25.000', '2019-07-15 14:32:14.000', NULL, 0, 27, NULL, NULL, 1, NULL),
  (27, '餐边柜', NULL, '2019-07-15 14:32:01.000', '2019-09-11 14:35:36.000', NULL, 1, NULL, NULL, 6, 0, NULL),
  (28, '盘和碗', NULL, '2019-07-15 14:40:23.000', '2019-07-15 14:43:47.000', NULL, 0, 27, NULL, NULL, 1, NULL),
  (29, '数码', NULL, '2019-07-17 07:51:07.000', '2019-09-11 14:36:54.000', NULL, 1, NULL, NULL, 7, 0, NULL),
  (30, '笔电', NULL, '2019-07-17 07:51:57.000', '2019-07-17 07:52:25.000', NULL, 0, 29, NULL, NULL, 1, NULL),
  (31, '手机', NULL, '2019-07-17 08:01:59.000', '2019-07-17 08:02:02.000', NULL, 0, 29, NULL, NULL, 1, NULL),
  (32, '文具', NULL, '2019-07-30 01:29:19.000', '2019-09-22 00:14:40.026', NULL, 0, 24, 'http://i2.sleeve.7yue.pro/u6.png', NULL, 1, NULL),
  (33, '女妆', NULL, '2019-08-02 02:54:11.000', '2019-09-11 14:35:27.000', NULL, 1, NULL, NULL, 8, 0, NULL),
  (34, '香水', NULL, '2019-08-02 02:54:30.000', '2019-08-02 02:54:30.000', NULL, 0, 33, NULL, NULL, 1, NULL),
  (35, '家具', NULL, '2019-08-02 19:44:24.000', '2019-09-21 23:40:27.664', NULL, 0, 4, 'http://i2.sleeve.7yue.pro/u6.png', 9, 1, NULL),
  (36, '酷玩', NULL, '2019-08-07 22:49:22.000', '2019-09-21 23:38:50.402', NULL, 0, 5, 'http://i2.sleeve.7yue.pro/u5.png', NULL, 1, NULL),
  (37, '测试数据', NULL, '2019-08-25 19:06:24.000', '2019-09-21 23:39:21.756', NULL, 1, NULL, 'http://i2.sleeve.7yue.pro/u2.png', NULL, 1, NULL),
  (38, '测试数据', NULL, '2019-08-25 19:07:53.000', '2019-09-21 23:38:52.170', NULL, 0, 37, 'http://i2.sleeve.7yue.pro/u5.png', NULL, 1, NULL),
  (39, '皮带', NULL, '2019-09-07 16:43:29.000', '2019-09-21 23:39:47.444', NULL, 0, 5, 'http://i2.sleeve.7yue.pro/u4.png', NULL, 1, NULL);

-- 校验：
-- SELECT id, name, is_root, parent_id, `index`, online FROM category ORDER BY id;
