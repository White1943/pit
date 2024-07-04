/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : pethome

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 02/12/2023 11:34:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for adoption
-- ----------------------------
DROP TABLE IF EXISTS `adoption`;
CREATE TABLE `adoption`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `pet_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `review_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of adoption
-- ----------------------------
INSERT INTO `adoption` VALUES ('1', 'b779645d-12fa-44a7-be47-c4e05639e309', '2', '2023-11-25 15:21:37', '1');
INSERT INTO `adoption` VALUES ('2', '3b10af5d-5c84-4150-b0ea-62f33823b89a', '3', '2023-11-13 15:21:37', '0');
INSERT INTO `adoption` VALUES ('3', 'b779645d-12fa-44a7-be47-c4e05639e309', '1', '2023-11-23 15:21:37', '0');
INSERT INTO `adoption` VALUES ('4', 'b779645d-12fa-44a7-be47-c4e05639e309', '5', '2023-10-23 15:21:37', '0');
INSERT INTO `adoption` VALUES ('523e5bb4-636d-43a0-9c65-07af705e902c', 'e5c99d8f-7b58-4754-a432-aecc986d8a4a', '3', '2023-12-01 13:09:20', '0');

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `pet_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `status` int(255) NULL DEFAULT NULL,
  `health` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of feedback
-- ----------------------------
INSERT INTO `feedback` VALUES ('26b012e7-0238-4aeb-8146-a79558de444e', 'd8373c5b-8258-4204-b53e-9c7db9039260', '2', '2023-11-23 15:16:24', '测试接口数据', 0, '健康');
INSERT INTO `feedback` VALUES ('7745b153-fdca-4ab7-9ee9-005a833bc57d', 'e5c99d8f-7b58-4754-a432-aecc986d8a4a', '2', '2023-12-01 20:19:37', '测试', 0, '健康');
INSERT INTO `feedback` VALUES ('831e2eba-9228-4215-a4fe-1703724fe71b', 'e5c99d8f-7b58-4754-a432-aecc986d8a4a', '3', '2023-12-01 20:21:43', '测试', 0, '健康');
INSERT INTO `feedback` VALUES ('9d539cb5-01f9-4bab-87ec-6d2e1c6e4bfa', 'e5c99d8f-7b58-4754-a432-aecc986d8a4a', '2', '2023-12-01 20:19:58', '测试', 0, '健康');
INSERT INTO `feedback` VALUES ('c47be6eb-2c55-4a9f-93ff-71b33e7932c1', 'e5c99d8f-7b58-4754-a432-aecc986d8a4a', '6', '2023-12-02 10:09:27', '测试', 0, '');
INSERT INTO `feedback` VALUES ('d4cf59f9-e6a3-4bca-98e7-e688e2693a71', 'e5c99d8f-7b58-4754-a432-aecc986d8a4a', '3', '2023-12-02 10:09:12', '测试', 0, '');

-- ----------------------------
-- Table structure for pet
-- ----------------------------
DROP TABLE IF EXISTS `pet`;
CREATE TABLE `pet`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `pet_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `breed` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `color` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `health_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `is_adopted` int(11) NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pet
-- ----------------------------
INSERT INTO `pet` VALUES ('1', '拉小拉', '狗', 3, '黑色', '健康', 0, '雌性', '拉布拉多', '城区市中心21号', 'one-1.jpg', '123456', '1', '1');
INSERT INTO `pet` VALUES ('2', '蔡蔡', '狗', 2, '白色', '健康', 0, '雄性', '中华田园犬', '城区市中心21号', 'two-1.jpg', '54645', '1', '1');
INSERT INTO `pet` VALUES ('3', '橘子', '猫', 4, '棕色', '健康', 0, '雌性', '橘猫', '城区市中心21号', 'three-1.jpg', '645', '1', '1');
INSERT INTO `pet` VALUES ('4', '鹏鹏', '猫', 1, '灰色', '健康', 0, '雄性', '橘猫', '城区市中心21号', 'four-1.png', '654', '1', '1');
INSERT INTO `pet` VALUES ('5', '龙龙', '猫', 2, '黄色', '健康', 0, '雌性', '橘猫', '城区市中心21号', 'five-1.jpg', '5465464', '1', '1');
INSERT INTO `pet` VALUES ('6', '毛毛', '猫', 2, '三花色', '健康', 0, '雌性', '橘猫', '城区市中心21号', 'six-1.jpg', '6546', '1', '1');
INSERT INTO `pet` VALUES ('7', '小小', '狗', 2, '白色', '健康', 0, '雄性', '未知', '城区市中心21号', 'seven.jpg', '456546', '1', '0');
INSERT INTO `pet` VALUES ('8', '猫猫', '猫', 1, '黑色', '健康', 0, '雌性', '狸花猫', '城区市中心21号', 'eight-1.png', '54354354', '0', '1');
INSERT INTO `pet` VALUES ('9', '团团', '猫', 2, '橘色', '健康', 0, '雌性', '未知', '城区市中心21号', 'naice-1.jpg', '54343', '0', '1');

-- ----------------------------
-- Table structure for title
-- ----------------------------
DROP TABLE IF EXISTS `title`;
CREATE TABLE `title`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `text` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of title
-- ----------------------------
INSERT INTO `title` VALUES ('1', '搬家注意事项', '搬家对于任何人来说，都是十分忙碌而且压力山大的。从一个熟悉的环境搬到另外一个陌生的环境，任何生物都需要时间来适应和调整。尤其对于猫咪来说，陌生的环境会让猫咪十分害怕，甚至出现严重的应激反应，例如不吃不喝或是突然出现攻击性。如果你最近正好要搬家，为了家里毛孩子的健康，请做好以下几点： 搬家前的准备 搬家前家里肯定乱的一塌糊涂，主人也会忙得脚打后脑勺。如果不想惊吓到猫咪，在打包的时候尽量把猫咪放置在一个安静的房间。在搬家当天，也要小心不要让猫咪跑丢。在准备去新家之前，把猫咪放在航空箱里，里面可以再放点零食。 有些猫咪可以很好的适应航空箱，有些则会嚎叫着要出来。如果是后者的情况，在搬家前几天就要让猫咪适应航空箱，只有在猫咪觉得航空箱是一个安全的藏身之处时，才会老老实实的在里面呆着。 到达新家之后 到达新家之后，不要立马打开航空箱，把猫咪置身于一个广阔的完全陌生的环境。如果这时猫咪再找不到藏身之处，那可真是要吓尿了。先把猫咪放在在一个单独的房间，房间里最好放置有猫咪以前用过的物品，例如之前的猫厕所，猫窝和猫咪玩具等等。这些旧物品上面带有猫咪的味道，只有猫咪在闻到熟悉的味道时，才不会那么害怕。 房间里再放上猫食盆和水盆，当猫咪不再觉得害怕的时候再放出来，让它自己熟悉其他房间。 熟悉新家 到达新家后把猫咪放在了一个单独的小房间。尽管如此，家里胆小的猫咪还是藏在猫砂盆里不肯出来，直到一个星期后才渐渐的不觉得害怕了。对于猫咪这种胆小的生物，一定要给它们足够的时间来熟悉新环境。同时，主人要给予足够的关注和互动，在猫咪感到不安的时候及时安抚，这样猫咪会更快的适应新环境。 注：搬新家对于猫咪来说，是一个不小的挑战。如果猫咪有任何不良反应，例如持续几天不吃不喝，那么需要带去宠物医院寻求专业的帮助。');
INSERT INTO `title` VALUES ('2', '养狗的好处和坏处', '一、好处\r\n\r\n\r\n\r\n1、舒缓工作压力\r\n\r\n都市人的生活繁忙急速，许多的工作令人感到压力很大，也变得精神紧张。但如果回家时，有狗狗摇头摆尾出来迎接你，再加几个可爱卖萌的动作，便已能令你的心情瞬间变好，所有的工作压力及烦恼都立刻减轻，抛诸脑后。\r\n\r\n\r\n\r\n2、遛狗让你养成适度运动的好习惯\r\n\r\n尽管有时候，特别是在寒冷的季节，遛狗似乎是特别让人讨厌的事。但是遛狗可以帮你做更多的运动。养狗的人比那些没养狗的人更趋向于做适度的体育活动。养狗的人每周平均走300分钟，而不养狗的人平均每周只走168分钟。适当运动，自己的身体免疫力也会有所提高，平时带狗狗运动的时候可以适当地去做一些训练，表现好就奖励一些狗狗专用的小零食，同样也可以为狗狗适当补钙哦。\r\n\r\n\r\n\r\n3、加强人际关系\r\n\r\n「狗狗是一个绝佳的破冰话题。」犬只行为学专家表示。事实上，当你养了狗接着，便会发觉与志同道合的朋友，有说不完的话题。因为养了狗狗，你要遛狗到公园，便有更多的机会认识其他狗主，并互相交流养狗心得。而狗狗之间的互动，也为话题增加，容易打破人与人之间的隔膜。\r\n\r\n\r\n\r\n4、关键时刻救你一命\r\n\r\n我们都知道，狗狗是非常通人性的，很多例子证明，狗狗为了救主人，甚至牺牲了自己的生命。如果你饲养一只狗狗，在你遇到危险的时候，狗狗会毫不犹豫去救你，在关键时刻，你还能捡回自己的性命！\r\n\r\n\r\n\r\n\r\n\r\n二、坏处\r\n\r\n如果说养狗有什么坏处，狗狗有什么缺点的话，大概就是狗狗的寿命太短了吧。狗狗只有十几年的时间，能够陪伴我们的时间是非常短暂的。它们陪我们走了十几年的时间，突然就离开我们，很多人都表示接受不了，甚至有的人再也不敢养狗了。这大概就是养狗的坏处了吧！所以我们日常要多点关注狗狗健康的问题，喂食一些营养健康的狗粮，确保狗狗有健康的饮食，好好去护理，这样狗狗的各方面做好了，寿命自然也会长一点，能够陪伴我们更久一点！\r\n\r\n\r\n\r\n注：\r\n\r\n狗狗饮食注意事项：\r\n\r\n平日狗狗的饮食一定要确保健康性，不要喂食人食或者比较低廉的狗粮，狗狗长期食用的话就容易发生各种疾病，最常见的就是泪痕，毛发等方面，甚至狗狗的身体器官承受不住出现肾腺方面有问题等，所以还是要选择一些清淡健康的狗粮，推荐低盐配方的“馋不腻天然狗粮”，富含蛋白质，营养充足，深海鱼油，还能美毛祛泪痕，日常适当搭配新鲜健康的蔬菜，水果和肉类，这样狗狗的饮食就非常健康了。\r\n\r\n狗狗零食推荐：\r\n\r\n狗狗平时除了吃狗粮以外，也可以适当喂食一些小零食补补钙，推荐馋不腻的鸡肉干，钙含量高，能够增强骨骼，但是要适量哦。\r\n\r\n');
INSERT INTO `title` VALUES ('3', '养猫常识', '1. 猫不用遛。遛狗是为了它的健康，顺便解决下大小便。猫不用遛，让它自己呆着就舒服了。\r\n\r\n\r\n\r\n2. 大小便自己解决。准备猫厕所，猫砂。猫会自己在里面解决大小便，并埋好。你要定时去清理。猫砂猫粮和猫厕所是猫必用的。猫窝不必买，它们会自己找到喜欢的趴的地方。猫项圈（防跳蚤圈，铃铛）不必买。项圈戴久了会皱了脖子的毛，防蚤圈没效果，铃铛会吵。别拴养，笼养。不买猫服。猫不喜欢束缚。\r\n\r\n\r\n\r\n3. 小猫不要喂牛奶。部分猫天生乳糖不耐，喂了牛奶会拉稀。\r\n\r\n\r\n\r\n4. 猫咪主食一定是合适的干粮，在所有食物中不要低于50%。\r\n\r\n\r\n\r\n5. 它们每天几乎 16~20 小时在睡觉。有 2 次（每次 20 分钟）活跃时间。注意家里的易碎品。餐具，杯子，花瓶等等，它们会不经意弄掉地上。\r\n\r\n\r\n\r\n6. 内外驱虫。每年吃两次，不要说不外出，不吃不干净食物的话。\r\n\r\n\r\n\r\n7. 每隔一两个礼拜观察猫中耳。如果里面黑了脏了，滴几滴耳油，然后轻揉耳背，再用棉签掏。\r\n\r\n\r\n\r\n8. 生病要及时带去看医生。猫很皮实，不爱生病。但是一旦生病，就很容易挂。比如食欲差，拉稀。尽快送医。\r\n\r\n\r\n\r\n9. 猫吐一般没事。它们可能是在吐毛球，或者吃多了，吃急了。\r\n\r\n\r\n\r\n10. 住楼 ≥3 层养猫的话，要根据猫的淘气程度考虑是否封阳台。根据阳台大小和封阳台选用的材料， 500-1000RMB 左右（包人工）。有一种裹塑钢丝，有隐形效果，可以考虑使用。\r\n\r\n\r\n\r\n11. 猫爱咬线。电脑电源线，手机数据线，耳机线等。咬的时候要警告制止，次数多了猫就知道线不可以咬。同时注意收好线。\r\n\r\n\r\n\r\n12. 猫咪洗澡之后一定要彻底吹干。贴身的绒毛极不容易吹干，频率最多一个月一次。\r\n\r\n\r\n\r\n13. 猫喜欢磨爪子。家里要给他们准备好猫抓板，粗粗的猫抓板磨爽了，自然就不会光顾你的皮沙发了。\r\n\r\n\r\n\r\n14. 短时间外出无需寄养到宠物店。猫咪害怕陌生环境，到了陌生环境有应激反应，短期外出最好不用寄养到别处，一周以内的外出可以准备足够的食物和水以及猫砂盆即可。当然能安排朋友每天过来添水舔食更佳。\r\n\r\n\r\n\r\n15. 6-8个月即可绝育。不然猫和人都不得安生。公猫绝育只是拿掉蛋蛋，母猫麻烦些，去掉卵巢或者子宫。绝育手术不仅剥夺了猫咪的正常生理需求和生活中的许多乐趣。发情是猫咪的正常生理本能，是健康的天性乐观。常有主人因猫咪发情感觉烦恼。猫静猫粮充分考虑猫咪在发情期间的各种生理状况，确保整体营养平衡，在特殊时期缓解猫咪的异常状态。帮助猫咪平静度过这段特殊时期，为家庭创造安宁生活。\r\n\r\n\r\n\r\n16. 猫玩具可以不用买。它们自得其乐，找到根羽毛都可以自 high 半天。\r\n\r\n\r\n\r\n17. 买个毛梳。猫喜欢梳毛，特别是春夏换季的时候，它们掉毛厉害。把陈毛梳掉，它们很开心，而且减少舔到胃里的毛。家里也会少些猫毛飘荡。\r\n\r\n\r\n\r\n18. 清理猫毛：吸尘器（适用大平面，地板，床），湿抹布（小平面，曲面，如沙发背，桌面），封箱胶带（适合清理衣物上的毛）。\r\n\r\n\r\n\r\n19. 喂食生肉的话，要先冷冻48小时恢复到常温再喂食。\r\n\r\n\r\n\r\n20. 抚摸猫自己够不到的地方，比如额头，肩胛。不要拽猫尾巴。猫尾巴是猫重要的平衡器。用力拽会影响它们的平衡感。不要修剪猫胡子。猫胡子是猫的量尺，只要是胡须的宽度，猫身体就可通过。\r\n\r\n\r\n\r\n21. 各种棉线、细线不要让猫逮到。它们一般先是玩，然后就一截截吞到肚子里。会影响消化造成呕吐。但是不必惊慌，大部分情况 2 天左右会排出。别喂猫面条样长条形食物。\r\n\r\n\r\n\r\n22. 跟猫玩的时候，它们可能会因为太高兴没有掌握好尺度误伤人。它们不是故意的，不要生气打骂。用指甲刀给猫剪指甲，把尖端剪去 3mm 左右，降低被误伤的几率。如果是总爱爬阳台的淘气猫慎重。爪子不尖会极大增加它们坠落几率。\r\n\r\n\r\n\r\n23. 跟猫玩的时候当它咬住你的手指，哪怕没有咬疼你也假装叫一声。这样它就会知道伤到你了，会立即松口。这种训练本是在一窝小猫的时候它们互相玩闹就学会的，就是掌握分寸，有的猫因为从小就是独自一喵，没有得到这种经验，有必要教它。\r\n\r\n\r\n\r\n24. 被猫挠伤。它们太 high 了，分寸掌握不好会挠伤人。先用肥皂水冲洗15分钟，然后抹碘伏。\r\n\r\n\r\n\r\n25. 猫咬伤与狂犬病感染风险。（狗同）\r\n\r\n如果被猫狗或雪貂咬伤的时候，它们看起来健康，养主需把它们隔离 10 天并观察，无须狂犬病疫苗。至今没有美国人在动物隔离期（ 10 天）内感染狂犬病病例。如果咬人的动物在咬人时已出现病态或者在 10 日隔离期内出现病态，交由兽医评估狂犬病迹象，并咨询使用狂犬病疫苗的医疗建议。\r\n\r\n\r\n\r\n26. 猫探知外界最常用的是鼻子（婴儿是嘴唇），其次抓，再次嘴牙。\r\n\r\n\r\n\r\n27. 猫的肉垫是唯一的汗腺。夏天摸摸肉垫，就会像人的手心一样冒汗。\r\n\r\n\r\n\r\n28. 猫色弱，对颜色区分能力弱（狗也是）。\r\n\r\n\r\n\r\n29. 距离猫25CM左右的距离以内它看不清，靠嗅觉和胡须定位。\r\n\r\n\r\n\r\n30. 闹猫时嗷嗷叫的猫都是母的。\r\n\r\n\r\n\r\n31. 猫跟人一样，每只猫的性格都不同，越老越难取悦。\r\n\r\n\r\n\r\n32. 猫跟人亲了，会叫你起床，对着它喵，它们会回喵。会养家，干掉一切入侵爬虫，心情好了拍苍蝇蚊子。猫爪两只，挥来万贯家财。猫用脸颊噌你，是把你当同类一样看待。猫在你面前翻肚皮，是有安全感和信赖你的表现。手指背轻噌喵的脸颊（顺着胡子方向噌过去），它会知道你喜欢它。对着它慢慢眨眼，等于对它说你爱它。\r\n\r\n\r\n\r\n33. 猫能感知人的情绪。当你难过落泪时，它们会过来 check 你（狗也能）。当你生气暴躁时，它们就安静呆在那。\r\n\r\n\r\n\r\n34. 有学习能力，会模仿别的猫的行为，也会看懂人的简单行为。比如看见人开门，它们想出去的时候就会用爪子扒门把手。\r\n\r\n\r\n\r\n35. 猫属性。\r\n\r\n向日葵属性：喜欢晒太阳，喜暖厌冷。\r\n\r\n蒲公英属性：掉毛。\r\n\r\n边界属性：喜欢趴在边界里，比如一张纸，纸箱。\r\n\r\n制高点属性：它们喜欢征服占领家里最高的位置。\r\n\r\n鸟属性：它们没有翅膀，但是会飞。\r\n\r\n强烈好奇心，强迫症，多动（一岁以下小猫尤甚），洁癖。\r\n\r\n艺人属性：嗑药。薄荷，木天蓼，风油精，漂白剂的味道都会让大部分猫销魂不已。\r\n\r\n\r\n\r\n36. 猫的开关。脖子后面位置是喵的开关。抓住这的皮它们就不动不挣扎了。（在喵极度害怕的时候不要这样抓）\r\n\r\n\r\n\r\n37. 熟悉了它们的脾气属性就能更好相处，也可以跟它们战斗了。比如它们讨厌橙子皮的味道，在书架上放两块，它们就躲开不会爬上去了。它们一时太 high 了在房间里飞的时候，把手淋湿，甩两下，然后沿着背抚摸几下。它们就得停下来找地方舔毛（谁叫你洁癖谁叫你强迫症），可以让它们安静下来，同时还能清理掉身上的浮毛。\r\n\r\n\r\n\r\n38. 精灵跟人类有个约定，无论什么情况下，在它们面前撒盐，它们会停止一切事，去把盐一粒一粒数清楚。猫跟人类也有个约定，大多数时候，你把手指竖着放在它们面前，它们会凑上鼻子闻（中指不行）。\r\n\r\n\r\n\r\n39. 做好有天它们会离开你的心理准备。所有的陪伴都是都是暂时，或长或短，人和人，和物，和动物。时间到了，默念珍重。其实，它们已经成为你的一部分继续存在。\r\n\r\n\r\n\r\n40. 预防治疗黑下巴/猫痤疮（ feline acne ）。猫的体质和进食习惯等原因，会造成黑下巴。严重的下巴会掉毛肿烂。用温和肥皂清洗，或者纱布浸泡茶水擦拭患处，然后涂红霉素眼膏消炎。食碗水碗换成金属/陶瓷/玻璃的，不要用塑料材质。后两者易碎。');
INSERT INTO `title` VALUES ('4', '养狗小常识', '1、除小狗外,成年狗在家喂养最好定时定量,上班族最好安排在晚上喂,因为晚上时间充裕.\r\n\r\n2、早上出门前给小狗换上干净的水,可给两块零食做安慰剂。\r\n\r\n3、 成年狗一天量一次喂既可,但不要太多,一定要给水,养成它晚饭后出去大小便,而白天安静不调皮, 这样吃,喝,拉有规律,好养,又不给人添麻烦,不增加负担。\r\n\r\n4、切忌不要喂食鸡骨,鸭骨头,很危险,狗是喜欢囫囵吞食东西,鸡鸭骨是长骨不易咬碎,  会扎穿肠道便血而死.这种情况很常见,特别要注意。\r\n\r\n5、出门一定要栓狗绳,其好处如下:\r\n\r\n牵狗绳的第一大好处：增加狗狗的服从性；\r\n\r\n牵狗绳的第二大好处：防止狗狗走失；\r\n\r\n牵狗绳的第三大好处：防止狗狗出车祸；\r\n\r\n牵狗绳的第四大好处：防止狗狗误食有毒或腐烂食物；\r\n\r\n牵狗绳的第五大好处：防止狗狗打架受伤；特别是眼睛最易被抓伤；\r\n\r\n牵狗绳的第六大好处：防止狗狗乱拉88、乱NN,引起邻居厌烦和投诉；\r\n\r\n牵狗绳的第七大好处：防止乱交配，减少意外怀孕和串串狗出生；\r\n\r\n牵狗绳的第八大好处：防止狗狗咬到或惊吓到别人特别是老人和小孩而招致麻烦.\r\n\r\n');
INSERT INTO `title` VALUES ('5', '家居常识', '请将你家的阳台封网，以免猫猫玩时不慎摔到楼下。\r\n\r\n上下班进出特别注意关好门窗，一旦猫猫跑出，将无法抓住，使猫猫变成流浪猫。\r\n\r\n注意家中危险品的摆放，以防猫猫攀爬。\r\n\r\n做饭时请关门，不要让猫猫进入厨房，以免烫伤、烧伤。');
INSERT INTO `title` VALUES ('6', '狗语词典大全', '黯然失色地低下头：我有些难受。\r\n\r\n快速地摇动尾巴：我很高兴！\r\n\r\n紧紧地夹起尾巴：我好害怕。\r\n\r\n故意避开陌生同类的视线：我可不想惹麻烦。\r\n\r\n肚皮向上躺着：你是我的上司，你看我多弱小呀！\r\n\r\n全身僵直、背毛竖立并低声咆哮：识相点！别把我逼急了！\r\n\r\n打哈欠：我好紧张。\r\n\r\n纵起鼻子：我有点生气了。\r\n\r\n纵起鼻子、露出牙齿，同时发出威胁的咆哮：我已经忍到极限了，别找不痛快。\r\n\r\n前身伏下，翘起后身：我们来做游戏吧！');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `phone_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `other_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'test', '21232f297a57a5a743894a0e4a801fc3', '5464@qq.com', '4864', '3');
INSERT INTO `user` VALUES ('123', '21312', 'e99a18c428cb38d5f260853678922e03', '21321@qq.com', '213', '0');
INSERT INTO `user` VALUES ('2131', '2213', '1321', '2131@qq.com', '321', '0');
INSERT INTO `user` VALUES ('3b10af5d-5c84-4150-b0ea-62f33823b89a', '123', '979d472a84804b9f647bc185a877a8b5', '123@qq.com', '123', '0');
INSERT INTO `user` VALUES ('4203903d-09a6-495d-bdb3-2a6ce6c82f2c', '12313', 'a869ccbcbd9568808b8497e28275c7c8', '12321@qq.com', '213213', '0');
INSERT INTO `user` VALUES ('9d1dcffd-e79f-4b92-b960-97f7c005ccfb', '测试2', '21232f297a57a5a743894a0e4a801fc3', '55894984@163.com', '88948819', '0');
INSERT INTO `user` VALUES ('ab638764-905c-426b-b733-2aea7415dd8a', '123', '979d472a84804b9f647bc185a877a8b5', '213@qq.com', '21321', '0');
INSERT INTO `user` VALUES ('afd2a1c4-581b-429f-b8c4-e6b37e539ed6', '阿斯顿2131', 'e99a18c428cb38d5f260853678922e03', '21312@qq.com', '31232131', '0');
INSERT INTO `user` VALUES ('b779645d-12fa-44a7-be47-c4e05639e309', '测试2', 'e99a18c428cb38d5f260853678922e03', '9855@qq.com', '888', '0');
INSERT INTO `user` VALUES ('bb914661-8c14-4aa3-a7d9-9ad3ab1cc7e1', 'eqweq', 'e99a18c428cb38d5f260853678922e03', 'qweq@qq.com', 'qwewq', '0');
INSERT INTO `user` VALUES ('cb07dc1d-3401-49c4-9d28-604013c46eb8', '测试gdl', 'c4cefc53ca414d25294fd23b8fccd356', '1151222@qq.com', '414514', '0');
INSERT INTO `user` VALUES ('d8373c5b-8258-4204-b53e-9c7db9039260', 'admin', '21232f297a57a5a743894a0e4a801fc3', '18056475895@qq.com', '187596420328', '1');
INSERT INTO `user` VALUES ('e5c99d8f-7b58-4754-a432-aecc986d8a4a', 'user21', '21232f297a57a5a743894a0e4a801fc3', '1151@163.com', '414514', '0');

SET FOREIGN_KEY_CHECKS = 1;
