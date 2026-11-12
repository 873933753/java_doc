-- ============================================================
-- missu.spec_key 种子数据（8 行，id 1-8）
-- 数据来源：用户提供的 MySQL Workbench 截图（clip_20261113_011002_1.png）
-- 提取方式：本地 RapidOCR + 像素级列边界/单元格校验
--   1) 按列 x 边界（id/name/unit/standard/create_time/update_time/delete_time/description）
--      与 8 条文字带 y 区间逐格裁切放大识别，不依赖整行 OCR 的拼接结果；
--   2) standard 列为右对齐黑色数字，与 create_time 左对齐文本粘连（"12026-07-15 ..."），
--      已按列边界拆分，取值 1/0；
--   3) "standard" 数字是否为 1：id=1、id=4 为 1，其余为 0。
--
-- 存疑单元格（已按截图原样写入）：
--   id=8 unit         截图中该格空白（不是灰色的 "(NULL)"），按空字符串 '' 写入
--   id=7 description  同上，空字符串 ''
--   id=8 description  同上，空字符串 ''
--   若你的库里这三处应为 NULL，把对应 '' 改成 NULL 即可。
--
-- 表结构对照 missyou-v1.sql：name varchar(100) NOT NULL、unit varchar(30) NULL、
--   standard tinyint(3) unsigned NOT NULL DEFAULT 0、时间列为 datetime（秒精度）。
--
-- 用法：
--   mysql -uroot -p --default-character-set=utf8mb4 missu < spec_key_seed.sql
-- 表非空/需覆盖同 id 行时，把 INSERT INTO 改成 REPLACE INTO 即可。
-- ============================================================

SET NAMES utf8mb4;

INSERT INTO `spec_key`
  (`id`, `name`, `unit`, `standard`, `create_time`, `update_time`, `delete_time`, `description`)
VALUES
  (1, '颜色', NULL, 1, '2026-07-15 15:33:18', '2026-07-15 15:33:18', NULL, NULL),
  (2, '尺寸', '英寸', 0, '2026-07-15 15:48:52', '2026-07-16 13:02:44', NULL, NULL),
  (3, '图案', NULL, 0, '2026-07-17 08:21:42', '2026-07-17 08:21:42', NULL, NULL),
  (4, '尺码', NULL, 1, '2026-07-17 08:24:40', '2026-07-17 08:24:47', NULL, NULL),
  (5, '颜色与规格', NULL, 0, '2026-07-30 06:39:27', '2026-07-30 06:39:27', NULL, NULL),
  (6, '数量', '个', 0, '2026-09-10 02:13:11', '2026-09-10 02:13:11', NULL, NULL),
  (7, '双色沙发尺寸（非标）', '米', 0, '2026-09-14 02:32:05', '2026-09-14 02:32:05', NULL, ''),
  (8, '台灯高低', '', 0, '2026-09-14 03:28:00', '2026-09-14 03:28:00', NULL, '');
