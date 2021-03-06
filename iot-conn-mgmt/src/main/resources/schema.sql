;             
CREATE USER IF NOT EXISTS "SA" SALT '012fd4b4ae5c610f' HASH 'db81d01b68566c1cb3577c137dd8ff98a0ba206044a74d471b06f37f6164f8fc' ADMIN;         
CREATE SEQUENCE "PUBLIC"."HIBERNATE_SEQUENCE" START WITH 31;
CREATE MEMORY TABLE "PUBLIC"."DEVICE_TEMPLATE_TOPIC"(
    "ID" BIGINT NOT NULL,
    "DESCRIPTION" VARCHAR(255),
    "DEVICE_TEMPLATE_ID" VARCHAR(255),
    "DEVICE_TEMPLATE_NAME" VARCHAR(255),
    "INBOUND" BOOLEAN,
    "NAME" VARCHAR(255),
    "OUTBOUND" BOOLEAN,
    "PROTOCOL_NAME" VARCHAR(255)
);          
ALTER TABLE "PUBLIC"."DEVICE_TEMPLATE_TOPIC" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_9" PRIMARY KEY("ID");        
-- 15 +/- SELECT COUNT(*) FROM PUBLIC.DEVICE_TEMPLATE_TOPIC;  
INSERT INTO "PUBLIC"."DEVICE_TEMPLATE_TOPIC" VALUES
(1, 'redefine clicks-and-mortar methodologies', '16ca8851-2d1a-4f8f-a84a-88566dc159dc', 'Product-01', TRUE, '/16ca8851-2d1a-4f8f-a84a-88566dc159dc/${deviceId}/bypass/program', TRUE, 'MQTT'),
(2, 'recontextualize global models', '16ca8851-2d1a-4f8f-a84a-88566dc159dc', 'Product-01', TRUE, '/16ca8851-2d1a-4f8f-a84a-88566dc159dc/${deviceId}/bypass/bus', TRUE, 'MQTT'),
(3, 'empower one-to-one networks', '16ca8851-2d1a-4f8f-a84a-88566dc159dc', 'Product-01', FALSE, '/16ca8851-2d1a-4f8f-a84a-88566dc159dc/${deviceId}/reboot/harddrive', TRUE, 'MQTT'),
(4, 'unleash compelling synergies', '16ca8851-2d1a-4f8f-a84a-88566dc159dc', 'Product-01', TRUE, '/16ca8851-2d1a-4f8f-a84a-88566dc159dc/${deviceId}/backup/bandwidth', TRUE, 'MQTT'),
(5, 'engage transparent bandwidth', '16ca8851-2d1a-4f8f-a84a-88566dc159dc', 'Product-01', TRUE, '/16ca8851-2d1a-4f8f-a84a-88566dc159dc/${deviceId}/copy/interface', TRUE, 'MQTT'),
(6, 'grow 24/7 users', '16ca8851-2d1a-4f8f-a84a-88566dc159dc', 'Product-01', TRUE, '/16ca8851-2d1a-4f8f-a84a-88566dc159dc/${deviceId}/synthesize/interface', TRUE, 'MQTT'),
(7, 'innovate front-end ROI', '16ca8851-2d1a-4f8f-a84a-88566dc159dc', 'Product-01', TRUE, '/16ca8851-2d1a-4f8f-a84a-88566dc159dc/${deviceId}/backup/bandwidth', FALSE, 'MQTT'),
(8, 'enable one-to-one e-business', '16ca8851-2d1a-4f8f-a84a-88566dc159dc', 'Product-01', TRUE, '/16ca8851-2d1a-4f8f-a84a-88566dc159dc/${deviceId}/generate/card', FALSE, 'MQTT'),
(9, 'repurpose customized metrics', '16ca8851-2d1a-4f8f-a84a-88566dc159dc', 'Product-01', TRUE, '/16ca8851-2d1a-4f8f-a84a-88566dc159dc/${deviceId}/hack/driver', FALSE, 'MQTT'),
(10, 'revolutionize viral solutions', '16ca8851-2d1a-4f8f-a84a-88566dc159dc', 'Product-01', FALSE, '/16ca8851-2d1a-4f8f-a84a-88566dc159dc/${deviceId}/generate/transmitter', TRUE, 'MQTT'),
(11, 'benchmark e-business partnerships', '16ca8851-2d1a-4f8f-a84a-88566dc159dc', 'Product-01', FALSE, '/16ca8851-2d1a-4f8f-a84a-88566dc159dc/${deviceId}/quantify/array', TRUE, 'MQTT'),
(12, 'architect cutting-edge action-items', '16ca8851-2d1a-4f8f-a84a-88566dc159dc', 'Product-01', TRUE, '/16ca8851-2d1a-4f8f-a84a-88566dc159dc/${deviceId}/backup/capacitor', TRUE, 'MQTT'),
(13, 'generate granular content', '16ca8851-2d1a-4f8f-a84a-88566dc159dc', 'Product-01', FALSE, '/16ca8851-2d1a-4f8f-a84a-88566dc159dc/${deviceId}/program/harddrive', TRUE, 'MQTT'),
(14, 'productize front-end niches', '16ca8851-2d1a-4f8f-a84a-88566dc159dc', 'Product-01', FALSE, '/16ca8851-2d1a-4f8f-a84a-88566dc159dc/${deviceId}/connect/system', TRUE, 'MQTT'),
(15, 'empower leading-edge e-business', '16ca8851-2d1a-4f8f-a84a-88566dc159dc', 'Product-01', FALSE, '/16ca8851-2d1a-4f8f-a84a-88566dc159dc/${deviceId}/index/firewall', TRUE, 'MQTT');            
CREATE MEMORY TABLE "PUBLIC"."DEVICE_TOPIC"(
    "ID" BIGINT NOT NULL,
    "DESCRIPTION" VARCHAR(255),
    "DEVICE_ID" VARCHAR(255),
    "DEVICE_NAME" VARCHAR(255),
    "DEVICE_TEMPLATE_ID" VARCHAR(255),
    "DEVICE_TEMPLATE_NAME" VARCHAR(255),
    "INBOUND" BOOLEAN,
    "NAME" VARCHAR(255),
    "OUTBOUND" BOOLEAN,
    "PARENT_ID" BIGINT,
    "PROTOCOL_NAME" VARCHAR(255)
);          
ALTER TABLE "PUBLIC"."DEVICE_TOPIC" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_9D" PRIMARY KEY("ID");
-- 15 +/- SELECT COUNT(*) FROM PUBLIC.DEVICE_TOPIC;           
INSERT INTO "PUBLIC"."DEVICE_TOPIC" VALUES
(16, 'redefine clicks-and-mortar methodologies', '33fa8425-0d6a-42b1-931c-30197bbde6ae', 'Device-01', '16ca8851-2d1a-4f8f-a84a-88566dc159dc', 'Product-01', TRUE, '/16ca8851-2d1a-4f8f-a84a-88566dc159dc/33fa8425-0d6a-42b1-931c-30197bbde6ae/bypass/program', TRUE, 1, 'MQTT'),
(17, 'recontextualize global models', '33fa8425-0d6a-42b1-931c-30197bbde6ae', 'Device-01', '16ca8851-2d1a-4f8f-a84a-88566dc159dc', 'Product-01', TRUE, '/16ca8851-2d1a-4f8f-a84a-88566dc159dc/33fa8425-0d6a-42b1-931c-30197bbde6ae/bypass/bus', TRUE, 2, 'MQTT'),
(18, 'empower one-to-one networks', '33fa8425-0d6a-42b1-931c-30197bbde6ae', 'Device-01', '16ca8851-2d1a-4f8f-a84a-88566dc159dc', 'Product-01', FALSE, '/16ca8851-2d1a-4f8f-a84a-88566dc159dc/33fa8425-0d6a-42b1-931c-30197bbde6ae/reboot/harddrive', TRUE, 3, 'MQTT'),
(19, 'unleash compelling synergies', '33fa8425-0d6a-42b1-931c-30197bbde6ae', 'Device-01', '16ca8851-2d1a-4f8f-a84a-88566dc159dc', 'Product-01', TRUE, '/16ca8851-2d1a-4f8f-a84a-88566dc159dc/33fa8425-0d6a-42b1-931c-30197bbde6ae/backup/bandwidth', TRUE, 4, 'MQTT'),
(20, 'engage transparent bandwidth', '33fa8425-0d6a-42b1-931c-30197bbde6ae', 'Device-01', '16ca8851-2d1a-4f8f-a84a-88566dc159dc', 'Product-01', TRUE, '/16ca8851-2d1a-4f8f-a84a-88566dc159dc/33fa8425-0d6a-42b1-931c-30197bbde6ae/copy/interface', TRUE, 5, 'MQTT'),
(21, 'grow 24/7 users', '33fa8425-0d6a-42b1-931c-30197bbde6ae', 'Device-01', '16ca8851-2d1a-4f8f-a84a-88566dc159dc', 'Product-01', TRUE, '/16ca8851-2d1a-4f8f-a84a-88566dc159dc/33fa8425-0d6a-42b1-931c-30197bbde6ae/synthesize/interface', TRUE, 6, 'MQTT'),
(22, 'innovate front-end ROI', '33fa8425-0d6a-42b1-931c-30197bbde6ae', 'Device-01', '16ca8851-2d1a-4f8f-a84a-88566dc159dc', 'Product-01', TRUE, '/16ca8851-2d1a-4f8f-a84a-88566dc159dc/33fa8425-0d6a-42b1-931c-30197bbde6ae/backup/bandwidth', FALSE, 7, 'MQTT'),
(23, 'enable one-to-one e-business', '33fa8425-0d6a-42b1-931c-30197bbde6ae', 'Device-01', '16ca8851-2d1a-4f8f-a84a-88566dc159dc', 'Product-01', TRUE, '/16ca8851-2d1a-4f8f-a84a-88566dc159dc/33fa8425-0d6a-42b1-931c-30197bbde6ae/generate/card', FALSE, 8, 'MQTT'),
(24, 'repurpose customized metrics', '33fa8425-0d6a-42b1-931c-30197bbde6ae', 'Device-01', '16ca8851-2d1a-4f8f-a84a-88566dc159dc', 'Product-01', TRUE, '/16ca8851-2d1a-4f8f-a84a-88566dc159dc/33fa8425-0d6a-42b1-931c-30197bbde6ae/hack/driver', FALSE, 9, 'MQTT'),
(25, 'revolutionize viral solutions', '33fa8425-0d6a-42b1-931c-30197bbde6ae', 'Device-01', '16ca8851-2d1a-4f8f-a84a-88566dc159dc', 'Product-01', FALSE, '/16ca8851-2d1a-4f8f-a84a-88566dc159dc/33fa8425-0d6a-42b1-931c-30197bbde6ae/generate/transmitter', TRUE, 10, 'MQTT'),
(26, 'benchmark e-business partnerships', '33fa8425-0d6a-42b1-931c-30197bbde6ae', 'Device-01', '16ca8851-2d1a-4f8f-a84a-88566dc159dc', 'Product-01', FALSE, '/16ca8851-2d1a-4f8f-a84a-88566dc159dc/33fa8425-0d6a-42b1-931c-30197bbde6ae/quantify/array', TRUE, 11, 'MQTT'),
(27, 'architect cutting-edge action-items', '33fa8425-0d6a-42b1-931c-30197bbde6ae', 'Device-01', '16ca8851-2d1a-4f8f-a84a-88566dc159dc', 'Product-01', TRUE, '/16ca8851-2d1a-4f8f-a84a-88566dc159dc/33fa8425-0d6a-42b1-931c-30197bbde6ae/backup/capacitor', TRUE, 12, 'MQTT'),
(28, 'generate granular content', '33fa8425-0d6a-42b1-931c-30197bbde6ae', 'Device-01', '16ca8851-2d1a-4f8f-a84a-88566dc159dc', 'Product-01', FALSE, '/16ca8851-2d1a-4f8f-a84a-88566dc159dc/33fa8425-0d6a-42b1-931c-30197bbde6ae/program/harddrive', TRUE, 13, 'MQTT'),
(29, 'productize front-end niches', '33fa8425-0d6a-42b1-931c-30197bbde6ae', 'Device-01', '16ca8851-2d1a-4f8f-a84a-88566dc159dc', 'Product-01', FALSE, '/16ca8851-2d1a-4f8f-a84a-88566dc159dc/33fa8425-0d6a-42b1-931c-30197bbde6ae/connect/system', TRUE, 14, 'MQTT'),
(30, 'empower leading-edge e-business', '33fa8425-0d6a-42b1-931c-30197bbde6ae', 'Device-01', '16ca8851-2d1a-4f8f-a84a-88566dc159dc', 'Product-01', FALSE, '/16ca8851-2d1a-4f8f-a84a-88566dc159dc/33fa8425-0d6a-42b1-931c-30197bbde6ae/index/firewall', TRUE, 15, 'MQTT');       
CREATE MEMORY TABLE "PUBLIC"."MESSAGE"(
    "ID" VARCHAR(255) NOT NULL,
    "CONTENT" VARCHAR(255),
    "DIRECTION" VARCHAR(255),
    "STATUS" VARCHAR(255),
    "TIMESTAMP" BIGINT,
    "TOPIC_ID" BIGINT
);          
ALTER TABLE "PUBLIC"."MESSAGE" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_6" PRIMARY KEY("ID");      
-- 73 +/- SELECT COUNT(*) FROM PUBLIC.MESSAGE;
INSERT INTO "PUBLIC"."MESSAGE" VALUES
('721dcdbc-e4c8-4cce-b70d-397d706dc9a2', 'METAR: UAAA 271730Z 03003MPS 9999 FEW050 30/10 Q1007 R88/CLRD65 NOSIG', 'OUTBOUND', 'SENDING', 1592292317430, 16),
('99064647-8e7f-4580-bef0-336cd8f26317', 'METAR: VABB 302100Z 02007KT 2100 -RA SCT010 SCT015 FEW030CB OVC080 26/25 Q1002 TEMPO 1500 +RA', 'OUTBOUND', 'SENDING', 1592249546218, 16),
('5b0623dc-5de6-4038-92f8-89daa9c6cdda', 'METAR: DAAG 221300Z 02012KT 360V070 6000 FEW026 SCT200 34/24 Q1015', 'OUTBOUND', 'FAILED', 1592239189394, 16),
('4190d0d8-1464-4bb5-8bad-ddd4e839ae19', 'METAR: SBSP 161800Z 18008KT 4000 -RA BKN007 SCT015 OVC100 13/11 Q1019', 'INBOUND', 'RECEIVED', 1592239451698, 16),
('f52f7de1-0513-41dc-86f6-3c442538bd4a', 'METAR: ZGKL 280600Z 32002MPS 290V350 9999 SCT043 OVC066 26/25 Q1002 RERA NOSIG', 'INBOUND', 'RECEIVED', 1592254035040, 16),
('19f9cca9-286e-4c86-b616-370463be39f7', 'METAR: PTSA 120350Z 25009KT 10SM SCT022 SCT130 BKN300 31/25 A2976 RMK LAST', 'OUTBOUND', 'SENDING', 1592254175624, 16),
('e480ca12-1708-48d7-a26f-884133257ba3', 'METAR: GOOY 251100Z 24008KT 9999 BKN011 27/22 Q1014', 'OUTBOUND', 'FAILED', 1592302896850, 16),
('fa7984a5-235f-4d1e-8f3c-a54402acf6d1', 'METAR: BIIS 101100Z 05007KT 9999 FEW025 07/M01 Q1018', 'OUTBOUND', 'SENDING', 1592296452053, 17),
('4b21a392-283f-4e9a-aaf5-51ec3eb0e9b8', 'METAR: UAAA 271730Z 03003MPS 9999 FEW050 30/10 Q1007 R88/CLRD65 NOSIG', 'INBOUND', 'RECEIVED', 1592274923501, 17),
('ea2a94c8-40e1-4815-a490-f919e71ceb45', 'METAR: DIAP 181230Z 22009KT 8000 SCT012 BKN040 27/24 Q1014 TEMPO BKN012', 'INBOUND', 'RECEIVED', 1592315891546, 17),
('375a52fb-891b-43ba-918f-f8e4108dfd2f', 'METAR: SGAS 031230Z 19007KT 130V220 CAVOK 11/03 Q1028', 'INBOUND', 'RECEIVED', 1592250843603, 17),
('63297e0e-3637-44b4-91f2-d813e61b055e', 'METAR: SGAS 031230Z 19007KT 130V220 CAVOK 11/03 Q1028', 'OUTBOUND', 'FAILED', 1592254983390, 17),
('33770ce5-874d-4a56-8fde-1fa1ee001d75', 'METAR: MMTJ 051141Z 00000KT 5SM OVC013 16/14 A3000 RMK SLP161 54000 918 8/5// HZY', 'INBOUND', 'RECEIVED', 1592322680428, 17),
('614dbb1f-52dc-41bd-9288-c728071c2408', 'METAR: UAAA 271730Z 03003MPS 9999 FEW050 30/10 Q1007 R88/CLRD65 NOSIG', 'OUTBOUND', 'FAILED', 1592320429334, 17),
('9d28ca1e-dd3c-4060-8e21-58c2f70ee4f5', 'METAR: NTAA 140330Z 11004KT 9999 FEW023 SCT300 22/17 Q1014 NOSIG', 'OUTBOUND', 'FAILED', 1592251016712, 17),
('6be8088d-4987-4f7f-b28a-b4e7964d82c6', 'METAR: ULAA 011400Z 17004MPS 120V220 9999 SCT030 18/06 Q1002 R08/190068 NOSIG RMK QFE750/1001', 'INBOUND', 'RECEIVED', 1592282537810, 18),
('c10468a2-1454-4cbd-b470-f05316af6545', 'METAR: SCIP 011500Z 33009KT 9999 SCT020 20/17 Q1016 NOSIG', 'OUTBOUND', 'FAILED', 1592287362943, 18),
('49d81a41-5369-4cfe-88e4-1f4c8fff0398', 'METAR: OIII 300500Z 18006KT CAVOK 38/02 Q1006 A2973 NOSIG', 'INBOUND', 'RECEIVED', 1592273459323, 18),
('cf4449c3-1d79-4f15-b024-e65b513fff99', 'METAR: DNIB 210500Z 26004KT 9999 SCT009 24/23 Q1013', 'OUTBOUND', 'SENDING', 1592246341348, 18),
('4eb660b1-44c1-4230-8637-495c7bfa6519', 'METAR: ORBI 231400Z 30016KT CAVOK 45/08 Q0997 NOSIG', 'OUTBOUND', 'SENDING', 1592266929694, 18),
('b1171506-d7e2-4a14-8f54-79a8393281e9', 'METAR: AGGH 102330Z 11002KT 9999 FEW014 23/23 Q1011', 'OUTBOUND', 'SENDING', 1592286376174, 18),
('02acbc3f-b93b-45b9-916f-171122183786', 'METAR: ZYHB 061600Z 20002MPS 4000 BR SCT033 23/20 Q0999 BECMG TL1420 23008MPS -TSRA', 'INBOUND', 'RECEIVED', 1592298886795, 18),
('2996d112-6b79-4616-83d6-7100f838787c', 'METAR: URWW 230800Z 02007MPS 9999 BKN050CB 21/08 Q1010 R06/010070 TEMPO -TSRA BKN030CB', 'INBOUND', 'RECEIVED', 1592310272699, 18),
('1ded1f5b-692e-4c36-8421-06d3cc6c23a9', 'METAR: SVMI 151204Z 14003KT 9999 SCT016 26/23 Q1013', 'OUTBOUND', 'SENT', 1592278876672, 18),
('dbba1e65-f278-49a0-a58e-88e6bd97a232', 'METAR: BIIS 101100Z 05007KT 9999 FEW025 07/M01 Q1018', 'OUTBOUND', 'SENT', 1592292420310, 20),
('ae82ed8f-efb3-40c8-a44b-107712fcd367', 'METAR: HECA 261900Z 34008KT CAVOK 33/17 Q1007 NOSIG', 'OUTBOUND', 'SENDING', 1592279919497, 20),
('76e5e276-4c3c-4f85-8822-6b6c165a10b6', 'METAR: SKBO 021200Z 05007KT 9999 FEW020 SCT200 11/08 A3037 NOSIG', 'INBOUND', 'RECEIVED', 1592316374464, 20);   
INSERT INTO "PUBLIC"."MESSAGE" VALUES
('9b49eeaf-e555-486e-9b30-25a824ae0633', 'METAR: SCRM 171300Z 34008KT 0100 FZFG OVC003 M03/M03 Q1006', 'INBOUND', 'RECEIVED', 1592240110496, 21),
('7bfa57ad-99bb-4180-a09f-9036ab091c97', 'METAR: BIIS 101100Z 05007KT 9999 FEW025 07/M01 Q1018', 'OUTBOUND', 'SENT', 1592248740463, 21),
('ea97d9c9-62d0-47e5-93e6-2d40d3a6ddab', 'METAR: UAAA 271730Z 03003MPS 9999 FEW050 30/10 Q1007 R88/CLRD65 NOSIG', 'OUTBOUND', 'SENT', 1592298469035, 22),
('16e97501-056c-45fd-a935-00bc8689e3f7', 'METAR: FACT 190900Z 01008KT 340V050 CAVOK 17/08 Q1024 NOSIG', 'OUTBOUND', 'FAILED', 1592296603271, 22),
('1339f4fa-889b-4105-a287-892620ca188b', 'METAR: UAAA 271730Z 03003MPS 9999 FEW050 30/10 Q1007 R88/CLRD65 NOSIG', 'INBOUND', 'RECEIVED', 1592295973593, 22),
('6bbd6a22-6394-4dd5-97a0-f75122c3e640', 'METAR: ZSPD 071300Z 13004MPS CAVOK 23/22 Q1001 NOSIG', 'INBOUND', 'RECEIVED', 1592288795652, 22),
('d4986585-90ac-4aa6-bc9a-770860d9e684', 'METAR: FACT 190900Z 01008KT 340V050 CAVOK 17/08 Q1024 NOSIG', 'OUTBOUND', 'FAILED', 1592296248838, 22),
('482736f1-4a2c-4222-be48-d64fb4aef06f', 'METAR: HECA 261900Z 34008KT CAVOK 33/17 Q1007 NOSIG', 'OUTBOUND', 'SENT', 1592272951152, 22),
('ae5c2115-e2ae-49fc-b6cf-4b336215a114', 'METAR: WSSS 092200Z 15008KT 130V190 9999 FEW020 BKN300 29/24 Q1009 NOSIG', 'INBOUND', 'RECEIVED', 1592315071350, 22),
('117f8683-124d-45c9-adc6-41a936a5ccbc', 'METAR: UIAA 082000Z AUTO 32002MPS 9999 NCD 16/10 Q1009 RMK QFE698/0931', 'OUTBOUND', 'SENT', 1592270650210, 22),
('0943a4cc-dbe0-4de5-b313-c34e8bc78add', 'METAR: HECA 261900Z 34008KT CAVOK 33/17 Q1007 NOSIG', 'INBOUND', 'RECEIVED', 1592315648663, 23),
('9c725abf-0b42-4260-a96a-53a43cdf4717', 'METAR: NCRG 130400Z AUTO 10011G23KT 070V160 9999 -RA FEW012/// SCT021/// BKN043/// 20/17 Q1020', 'INBOUND', 'RECEIVED', 1592269407460, 23),
('29342519-f8ce-4e0a-94e9-126d5fc109af', 'METAR: MMTJ 051141Z 00000KT 5SM OVC013 16/14 A3000 RMK SLP161 54000 918 8/5// HZY', 'INBOUND', 'RECEIVED', 1592276931320, 23),
('2e5a4caf-6379-4be6-8a3e-e498608dc37e', 'METAR: PTSA 120350Z 25009KT 10SM SCT022 SCT130 BKN300 31/25 A2976 RMK LAST', 'OUTBOUND', 'FAILED', 1592265554774, 23),
('b4ddb2e4-81e1-444d-bbc5-4771fe95e9b0', 'METAR: YSSY 110700Z 14009KT 9999 VCSH FEW018 SCT022 BKN034 14/13 Q1035 NOSIG', 'INBOUND', 'RECEIVED', 1592240539451, 23),
('90370ded-032f-4769-bf87-8bea9a5c2cf2', 'METAR: YSSY 110700Z 14009KT 9999 VCSH FEW018 SCT022 BKN034 14/13 Q1035 NOSIG', 'INBOUND', 'RECEIVED', 1592313143366, 23),
('e6213928-694d-41a5-a9b8-0d329644567b', 'METAR: ZYHB 061600Z 20002MPS 4000 BR SCT033 23/20 Q0999 BECMG TL1420 23008MPS -TSRA', 'OUTBOUND', 'SENDING', 1592239677364, 23),
('68602d6a-362f-453f-ba7f-b4ebf4c9d8c1', 'METAR: UIAA 082000Z AUTO 32002MPS 9999 NCD 16/10 Q1009 RMK QFE698/0931', 'OUTBOUND', 'SENT', 1592306308051, 23),
('95e14db0-4ffb-4fda-b97a-3bdda8fcd5d1', 'METAR: SVMI 151204Z 14003KT 9999 SCT016 26/23 Q1013', 'OUTBOUND', 'SENDING', 1592318035692, 24),
('a37b7d7e-9269-4b43-a33a-bb34be505205', 'METAR: VVNB 310100Z 13008KT 9999 FEW030 32/25 Q1000 NOSIG', 'INBOUND', 'RECEIVED', 1592289431735, 25),
('df7b73a8-37e3-4c63-8db8-8d4193f04e1a', 'METAR: KSFO 011356Z 25007KT 10SM FEW008 SCT013 SCT200 13/11 A3006 RMK AO2 SLP180 T01330106', 'OUTBOUND', 'FAILED', 1592289058107, 25),
('d9d07119-e0da-4c44-9a51-04c0bf3f498b', 'METAR: URWW 230800Z 02007MPS 9999 BKN050CB 21/08 Q1010 R06/010070 TEMPO -TSRA BKN030CB', 'INBOUND', 'RECEIVED', 1592247928596, 25),
('c476f786-2052-495a-9647-fd8f1dc51f2d', 'METAR: MMTJ 051141Z 00000KT 5SM OVC013 16/14 A3000 RMK SLP161 54000 918 8/5// HZY', 'INBOUND', 'RECEIVED', 1592309543487, 25),
('c144d063-a03b-4e81-8d04-3b17d4d99ad7', 'METAR: NCRG 130400Z AUTO 10011G23KT 070V160 9999 -RA FEW012/// SCT021/// BKN043/// 20/17 Q1020', 'OUTBOUND', 'SENT', 1592318134179, 25),
('49916dff-9fec-4e7a-9e77-d595b3e61542', 'METAR: VABB 302100Z 02007KT 2100 -RA SCT010 SCT015 FEW030CB OVC080 26/25 Q1002 TEMPO 1500 +RA', 'OUTBOUND', 'SENDING', 1592282582498, 25),
('cd4e5e46-5709-4ee3-8db6-3c3777bc340b', 'METAR: ULAA 011400Z 17004MPS 120V220 9999 SCT030 18/06 Q1002 R08/190068 NOSIG RMK QFE750/1001', 'OUTBOUND', 'SENT', 1592296151248, 26);   
INSERT INTO "PUBLIC"."MESSAGE" VALUES
('3795de14-c7ab-428c-a5fb-ac8f108c1163', 'METAR: FACT 190900Z 01008KT 340V050 CAVOK 17/08 Q1024 NOSIG', 'OUTBOUND', 'SENDING', 1592310076443, 26),
('859f62e9-5f11-47b5-94c7-1e7f76452804', 'METAR: VNKT 291250Z 36004KT 7000 FEW010 SCT030 BKN100 25/22 Q1003 NOSIG', 'INBOUND', 'RECEIVED', 1592242846152, 26),
('714634ff-7cc4-4bd6-9a45-adf2401cff92', 'METAR: ZSPD 311400Z 12002MPS 080V190 9999 -SHRA NSC 23/22 Q1008 NOSIG', 'OUTBOUND', 'FAILED', 1592319155632, 26),
('d781d9c1-807d-4e58-9009-95528d3137d5', 'METAR: UAAA 271730Z 03003MPS 9999 FEW050 30/10 Q1007 R88/CLRD65 NOSIG', 'OUTBOUND', 'SENT', 1592267815895, 26),
('14ba5e69-ae38-47f4-8177-e7d08e5e3e96', 'METAR: MMTJ 051141Z 00000KT 5SM OVC013 16/14 A3000 RMK SLP161 54000 918 8/5// HZY', 'OUTBOUND', 'FAILED', 1592297994679, 26),
('a9f71092-2a3f-4748-9575-d9a0e8c8014a', 'METAR: DNIB 210500Z 26004KT 9999 SCT009 24/23 Q1013', 'INBOUND', 'RECEIVED', 1592245783630, 27),
('0b3da6f7-c73f-445f-86d0-0ecd3e4447df', 'METAR: WSSS 092200Z 15008KT 130V190 9999 FEW020 BKN300 29/24 Q1009 NOSIG', 'INBOUND', 'RECEIVED', 1592291586675, 27),
('b53bc477-9a87-43c8-bcfd-3b8d4a26f09a', 'METAR: SGAS 031230Z 19007KT 130V220 CAVOK 11/03 Q1028', 'INBOUND', 'RECEIVED', 1592295006684, 27),
('81517fd8-f510-4930-acc0-e59da337baa2', 'METAR: VNKT 291250Z 36004KT 7000 FEW010 SCT030 BKN100 25/22 Q1003 NOSIG', 'OUTBOUND', 'SENT', 1592258538972, 27),
('2f855405-f6a8-4f36-80f9-bff03737c33b', 'METAR: MMCU 041040Z 00000KT 10SM SCT030 BKN080 BKN200 20/15 A3016 RMK 8/572 RTS', 'OUTBOUND', 'FAILED', 1592254335555, 27),
('fd55170f-9f50-49cf-92d6-a5bb2d81c4d5', 'METAR: SCRM 171300Z 34008KT 0100 FZFG OVC003 M03/M03 Q1006', 'INBOUND', 'RECEIVED', 1592313150750, 28),
('1a615343-b4eb-4f7e-9600-9c22d0bb4834', 'METAR: ZGKL 280600Z 32002MPS 290V350 9999 SCT043 OVC066 26/25 Q1002 RERA NOSIG', 'INBOUND', 'RECEIVED', 1592290707854, 28),
('80e7f867-5b6b-46dc-a5b8-e5242aa62131', 'METAR: FACT 190900Z 01008KT 340V050 CAVOK 17/08 Q1024 NOSIG', 'INBOUND', 'RECEIVED', 1592304132973, 28),
('f00f5d81-6983-490c-aa8d-5e8b68cf95bd', 'METAR: SGAS 031230Z 19007KT 130V220 CAVOK 11/03 Q1028', 'INBOUND', 'RECEIVED', 1592284520002, 29),
('cbae9dbd-3962-4bd3-bb00-598f0510f9f0', 'METAR: DIAP 181230Z 22009KT 8000 SCT012 BKN040 27/24 Q1014 TEMPO BKN012', 'INBOUND', 'RECEIVED', 1592317169763, 29),
('2213025e-c0e9-42b2-879b-0632c21acd44', 'METAR: VVNB 310100Z 13008KT 9999 FEW030 32/25 Q1000 NOSIG', 'INBOUND', 'RECEIVED', 1592273857183, 29),
('e07126ed-88ee-47f0-9411-6c9bc966e9a4', 'METAR: DIAP 181230Z 22009KT 8000 SCT012 BKN040 27/24 Q1014 TEMPO BKN012', 'INBOUND', 'RECEIVED', 1592286529243, 29),
('344bd990-5276-47f1-b6d4-4d11241a4bf2', 'METAR: ZSPD 071300Z 13004MPS CAVOK 23/22 Q1001 NOSIG', 'INBOUND', 'RECEIVED', 1592270063673, 29),
('17a7d49b-c7df-4bbe-b798-db4665b49058', 'METAR: NCRG 130400Z AUTO 10011G23KT 070V160 9999 -RA FEW012/// SCT021/// BKN043/// 20/17 Q1020', 'INBOUND', 'RECEIVED', 1592250086517, 29),
('334b5bf8-ec39-47de-9f34-cb68239e99a2', 'METAR: GOOY 251100Z 24008KT 9999 BKN011 27/22 Q1014', 'OUTBOUND', 'SENDING', 1592259265718, 29);       
CREATE MEMORY TABLE "PUBLIC"."PROTOCOL"(
    "NAME" VARCHAR(255) NOT NULL,
    "IMPLEMENT" VARCHAR(255),
    "JAR_FILE" VARCHAR(255)
);   
ALTER TABLE "PUBLIC"."PROTOCOL" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_F" PRIMARY KEY("NAME");   
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.PROTOCOL;
INSERT INTO "PUBLIC"."PROTOCOL" VALUES
('MQTT', 'edu.nju.se.yrd.iotconnmgmt.protocolImpl.ProtocolMQTTImpl', '');             
ALTER TABLE "PUBLIC"."DEVICE_TOPIC" ADD CONSTRAINT "PUBLIC"."FKQIEJCE9FVFKIQSTN3745LXAH6" FOREIGN KEY("PARENT_ID") REFERENCES "PUBLIC"."DEVICE_TEMPLATE_TOPIC"("ID") NOCHECK; 
ALTER TABLE "PUBLIC"."DEVICE_TOPIC" ADD CONSTRAINT "PUBLIC"."FKNRXO3DI70NMPXH59DVM1CQLMI" FOREIGN KEY("PROTOCOL_NAME") REFERENCES "PUBLIC"."PROTOCOL"("NAME") NOCHECK;        
ALTER TABLE "PUBLIC"."DEVICE_TEMPLATE_TOPIC" ADD CONSTRAINT "PUBLIC"."FKQEBS8SV9RKPKKYQ43U4W8IFGC" FOREIGN KEY("PROTOCOL_NAME") REFERENCES "PUBLIC"."PROTOCOL"("NAME") NOCHECK;               
ALTER TABLE "PUBLIC"."MESSAGE" ADD CONSTRAINT "PUBLIC"."FKFGRCRRU8O33JSVE0D0K2HG8RU" FOREIGN KEY("TOPIC_ID") REFERENCES "PUBLIC"."DEVICE_TOPIC"("ID") NOCHECK;
