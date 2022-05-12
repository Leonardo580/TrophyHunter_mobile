-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 12, 2022 at 10:30 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `games`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `ID_cart` int(11) NOT NULL,
  `ID_USER` int(11) DEFAULT NULL,
  `SUM` float(8,2) DEFAULT NULL,
  `DISCOUNT` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `ID_CATEGORY` int(11) NOT NULL,
  `CATEGORY` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`ID_CATEGORY`, `CATEGORY`) VALUES
(1, 'MMO'),
(4, 'Survival'),
(7, 'Adventure'),
(8, 'Adventure'),
(26, 'unreal'),
(27, 'Action'),
(28, 'JRPG'),
(29, 'Racing'),
(30, 'rihbj');

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `ID_COMMENT` int(11) NOT NULL,
  `ID_NEWS` int(11) DEFAULT NULL,
  `COMMENT` varchar(512) DEFAULT NULL,
  `LIKES` int(11) DEFAULT NULL,
  `DISLIKES` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`ID_COMMENT`, `ID_NEWS`, `COMMENT`, `LIKES`, `DISLIKES`) VALUES
(11, 47, 'test comment', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `competitions`
--

CREATE TABLE `competitions` (
  `ID_COMPETION` int(11) NOT NULL,
  `DATEOFCOMP` date DEFAULT NULL,
  `GAME_NAME` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `competitions`
--

INSERT INTO `competitions` (`ID_COMPETION`, `DATEOFCOMP`, `GAME_NAME`) VALUES
(2, '2023-03-13', 'LOL'),
(5, '2022-06-15', 'PUBG'),
(7, '2022-05-15', 'HELLO'),
(8, '2022-06-05', 'test'),
(10, '2023-06-13', 'PUBG');

-- --------------------------------------------------------

--
-- Table structure for table `doctrine_migration_versions`
--

CREATE TABLE `doctrine_migration_versions` (
  `version` varchar(191) COLLATE utf8_unicode_ci NOT NULL,
  `executed_at` datetime DEFAULT NULL,
  `execution_time` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `games`
--

CREATE TABLE `games` (
  `ID_GAME` int(11) NOT NULL,
  `ID_CATEGORY` int(11) DEFAULT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `DESCRIPTION` text DEFAULT NULL,
  `RATE` decimal(8,4) DEFAULT NULL,
  `img` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `games`
--

INSERT INTO `games` (`ID_GAME`, `ID_CATEGORY`, `NAME`, `DESCRIPTION`, `RATE`, `img`) VALUES
(102, 7, 'elden-ring', '\n\nL\'Ordre doré a été brisé.\n\nLevez-vous, les Souillés, et laissez-vous guider par la grâce pour manipuler le pouvoir de l\'Anneau élden et devenir des seigneurs éldens dans les Terres entre.\n\nDans les Terres entre régies par la reine Marika l\'Éternelle, l\'Anneau élden, la source de l\'Arbre érdique, a été brisé en morceaux.\n\nLes enfants de Marika, tous des demi-dieux, ont revendiqué les éclats de l\'Anneau élden connus sous le nom de Grandes Runes, et la folie de leur nouveau pouvoir a déclenché une guerre : Le Déchirement. Une guerre qui a marqué l\'abandon par la Grande Volonté.\n\nEt maintenant, la grâce de la guidance sera apportée aux Souillés qui ont été rejetés par la grâce dorée et bannis des Terres entre. Vous, morts qui vivez encore, votre grâce longtemps perdue, suivez le chemin vers les Terres entre au-delà de la mer brumeuse pour vous présenter devant l\'Anneau élden.', '96.0000', 'BackAssets\\images\\GameImgs\\elden-ring.jpg'),
(108, 8, 'ttt', 'fghgfh fgfgh', '78.0000', 'BackAssets\\images\\GameImgs\\wp7489322.jpg'),
(110, 7, 'gris', '<p>Gris is a hopeful young girl lost in her own world, dealing with a painful experience in her life. Her journey through sorrow is manifested in her dress, which grants new abilities to better navigate her faded reality. As the story unfolds, Gris will grow emotionally and see her world in a different way, revealing new paths to explore using her new abilities.<br />\nGRIS is a serene and evocative experience, free of danger, frustration or death. Players will explore a meticulously designed world brought to life with delicate art, detailed animation, and an elegant original score. Through the game light puzzles, platforming sequences, and optional skill-based challenges will reveal themselves as more of Gris’s world becomes accessible.</p>', '83.0000', 'BackAssets\\images\\GameImgs\\gris.jpg'),
(118, 1, 'unreal', 'who are you it\'s weird i fellingfd', '79.0000', 'BackAssets\\images\\GameImgs\\wp7489322.jpg'),
(119, 1, 'unreal', '\n\nQui êtes-vous? C\'est étrange, je me sens bizarre.', '79.0000', 'BackAssets\\images\\GameImgs\\unreal.jpg'),
(120, 1, 'gris', '<p>Gris is a hopeful young girl lost in her own world, dealing with a painful experience in her life. Her journey through sorrow is manifested in her dress, which grants new abilities to better navigate her faded reality. As the story unfolds, Gris will grow emotionally and see her world in a different way, revealing new paths to explore using her new abilities.<br />\nGRIS is a serene and evocative experience, free of danger, frustration or death. Players will explore a meticulously designed world brought to life with delicate art, detailed animation, and an elegant original score. Through the game light puzzles, platforming sequences, and optional skill-based challenges will reveal themselves as more of Gris’s world becomes accessible.</p>', '83.0000', 'BackAssets\\images\\GameImgs\\gris.jpg'),
(121, 1, 'unreal', 'who are you it\'s weird i fellingfd fdgdfg', '79.0000', 'BackAssets\\images\\GameImgs\\wp7489322.jpg'),
(122, 1, 'test moblie ', 'mobile', '78.6000', 'BackAssets\\images\\GameImgs\\wp7489322.jpg'),
(124, 1, 'fffff', 'hfgh hf', '25.0000', 'null'),
(125, 1, 'ggggg', 'ffff', '36.0000', 'null'),
(126, 1, 'gfghj', 'hjkjhk', '47.0000', 'null'),
(127, 1, 'fff', 'fgg', '47.0000', 'null'),
(128, 1, 'hhh', 'fcff', '47.3000', 'null'),
(129, 1, 'gris', 'fjvjfd df jdfjkdffd df kdkf', '78.0000', 'BackAssets\\images\\GameImgs\\624f4e7dd645b308327572.PNG'),
(132, 1, 'hh', 'hhh', '14.0000', 'BackAssets\\images\\GameImgs\\hh.jpg'),
(133, 1, 'hh', 'hh', '14.0000', 'BackAssets\\images\\GameImgs\\hh.jpg'),
(134, 1, 'hh', 'hhh', '14.5000', 'BackAssets\\images\\GameImgs\\hh.jpg'),
(135, 1, 'gg', 'dd', '45.0000', 'BackAssets\\images\\GameImgs\\gg.jpg'),
(136, 1, 'fff', 'ffff', '45.0000', 'BackAssets\\images\\GameImgs\\fff.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `groups`
--

CREATE TABLE `groups` (
  `ID_GROUP` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `groups`
--

INSERT INTO `groups` (`ID_GROUP`, `name`) VALUES
(4, 'Anas ben brahim');

-- --------------------------------------------------------

--
-- Table structure for table `news`
--

CREATE TABLE `news` (
  `ID_NEWS` int(11) NOT NULL,
  `HEADLINE` varchar(100) DEFAULT NULL,
  `CONTENT` text DEFAULT NULL,
  `IMG` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `news`
--

INSERT INTO `news` (`ID_NEWS`, `HEADLINE`, `CONTENT`, `IMG`) VALUES
(42, 'Leak Shows Terrifying New Dead By Daylight Killer, The Dredge', 'Dead by Daylight is close to celebrating its sixth anniversary, and a recent leak shows what the game\'s next horrifying monster could be.', 'https://static0.gamerantimages.com/wordpress/wp-content/uploads/2022/05/dead-by-daylight-2.jpg'),
(43, 'Artist Reimagines Portal in Unreal Engine 5', 'With the recent release of Unreal Engine 5, many are seeing what some older games look like with a fresh coat of paint, including the original Portal.', 'https://static0.gamerantimages.com/wordpress/wp-content/uploads/2022/05/portal-unreal-engine-5.jpg'),
(44, 'China Now Bans Minors From Watching Streams After 10 PM', 'China continues its clamp down on video games and streaming.', 'https://static1.thegamerimages.com/wordpress/wp-content/uploads/2021/10/china-game-ban-(1).jpg'),
(45, 'Pokemon Fan Makes Cyndaquil Skateboard', 'A Pokemon fan decides to take their favorite pocket monster out of the games and into real life by putting some awesome art on their skateboard.', 'https://static0.gamerantimages.com/wordpress/wp-content/uploads/2022/05/cyndaquil-skateboard.jpg'),
(46, 'Metal Gear Solid 5’s infamous nuclear disarmament mission is ‘impossible’, investigation concludes', 'Players have been trying to rid MGS5 of nukes for over half a decade', 'https://www.videogameschronicle.com/files/2021/08/metal-gear-solid-v-phantom-pain.jpg'),
(47, 'Badland Party launches on Apple Arcade with online and local multiplayer modes', 'In case you missed it, HypeHype Inc. has officially launched Badland Party on Apple Arcade, letting players dive back into the BADLAND franchise with online multiplayer on mobile. The side-scrolling action adventure features atmospheric levels that boast new worlds and fun boss fights for players to challenge alone or with friends.\n\nIn Badland Party, players can expect to save the Clones by solving multiplayer puzzles or by soaring through the iconic world of the popular IP across 40 levels in the co-op adventure mode. The game supports local two-to-four-player multiplayer with controller support as well as two-to-four-player online multiplayer, and if you\'re playing solo, you can embark on your adventure with an AI companion and switch characters as you wish. ', 'https://media.pocketgamer.com/artwork/na-34178-1652085522/badland-party-apple-arcade-launch-cover.jpg'),
(48, 'Xbox’s online DRM under fire as some users left unable to play games for 4th day', 'Microsoft\'s gaming servers have been experiencing outages on-and-off since Friday', 'https://www.videogameschronicle.com/files/2022/05/FSHKNjGXwAcO1ZT.jpg'),
(49, 'Coin Master free spins - updated daily links (May 2022)', 'May 9, 2022 - Updated links, added new ones\n\nWith a player base of millions, this is a game that doesn\'t shy away from giving free rewards! With each passing day, players can claim a bunch of Coin Master free spins and coins from the game\'s Facebook, and let\'s be honest - who doesn\'t want some? Every single day there is a new freebie that is definitely going to help you advance and get more rewards. ', 'https://media.pocketgamer.com/artwork/na-31930-1611427130/coin%20master%20ios%20android%20free%20spins.jpg'),
(50, 'Rune Factory 5 aims to strike ‘an amazing balance between the farming simulator and RPG aspects of t', 'Originating as a spin-off of the Story of Seasons - formally called Harvest Moon - franchise, the Rune Factory series marries traditional farming simulator mechanics with dungeon crawling RPG gameplay. One day you may be carefully cultivating your growing form, only to spend the next cutting down monsters with your sword and possibly taming a few to work in your fields. Fans of the series have waited nine years, only broken by a part of Rune Factory 4 in 2019, for Rune Factory 5, which was released in Japan during May 2021 and in March of this year outside of Japan.', 'https://assets.reedpopcdn.com/rune-factory-5-header.jpg/BROK/resize/1920x1920%3E/format/jpg/quality/80/rune-factory-5-header.jpg'),
(51, 'Method Man releases new song for Evil Dead: The Game', 'Wu Tang Clan rapper and legendary lyricist, Method Man, is a pretty hardcore fan of The Evil Dead. The musician has always been a fan of Sam Raimi, often making reference to Raimi\'s work in his songs. Now, after the team behind Evil Dead: The Game reached out to Method Man and producer, Statik Selektah, the pair have collaborated an an original new song fit for bashing brains out with boomsticks. Following the release of Evil Dead: The Game\'s official theme earlier last week, which brings on board original composer Joseph LoDuca, \'Come Get Some\' is the latest track we\'ve seen from the game. Read more', 'https://asset.vg247.com/evil-dead-the-game-ash-williams.jpg/BROK/resize/1920x1920%3E/format/jpg/quality/80/evil-dead-the-game-ash-williams.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `ID_PRODUCT` int(11) NOT NULL,
  `ID_CATEGORY` int(11) DEFAULT NULL,
  `PROD_NAME` varchar(100) DEFAULT NULL,
  `PRICE` float(8,2) DEFAULT NULL,
  `DISCOUNT` tinyint(4) DEFAULT NULL,
  `Quantity` int(11) NOT NULL,
  `image` varchar(1000) NOT NULL,
  `description` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`ID_PRODUCT`, `ID_CATEGORY`, `PROD_NAME`, `PRICE`, `DISCOUNT`, `Quantity`, `image`, `description`) VALUES
(3, 1, 'opentggggyyy', 48.00, 78, 10, '6278d3bce3afd329174072.jpg', 'fdj fdfghhgh hgjhgjggj'),
(5, 27, 'Crysis 3', 75.00, 10, 12, '6278d262601f9096952394.jpg', 'fdj fdfgfdDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD'),
(6, 4, 'donationgame', 15.00, 16, 4, '6278e4fc0584e907693521.PNG', 'fffffdfgfdgdfgdfgfdllllllllllllllllllllllllllllllllllllllillllllllllllllli'),
(7, 4, 'uyyyyyyyyy', 15.00, 2, 7, '6278d3a69f5dc147708239.PNG', 'hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhlopp'),
(8, 1, 'Crysis 3', 20.00, 10, 0, '6278e5452d7cf816844652.PNG', 'dfgkkkkkkkkkkkkkkkkkk');

-- --------------------------------------------------------

--
-- Table structure for table `reset_password_request`
--

CREATE TABLE `reset_password_request` (
  `id` int(11) NOT NULL,
  `ID_USER` int(11) NOT NULL,
  `selector` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `hashed_token` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `requested_at` datetime NOT NULL COMMENT '(DC2Type:datetime_immutable)',
  `expires_at` datetime NOT NULL COMMENT '(DC2Type:datetime_immutable)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `teams`
--

CREATE TABLE `teams` (
  `ID_TEAM` int(11) NOT NULL,
  `ID_COMPETION` int(11) DEFAULT NULL,
  `TEAM_NAME` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `teams`
--

INSERT INTO `teams` (`ID_TEAM`, `ID_COMPETION`, `TEAM_NAME`) VALUES
(13, 2, 'Esprit5'),
(14, 5, 'Pionners'),
(15, 5, 'Esprit'),
(19, 2, 'fff'),
(20, 7, 'test'),
(21, 5, 'testesprit'),
(22, 2, 'Esprit'),
(23, 7, 'test');

-- --------------------------------------------------------

--
-- Table structure for table `trophies`
--

CREATE TABLE `trophies` (
  `ID_TROPHY` int(11) NOT NULL,
  `ID_GAME` int(11) DEFAULT NULL,
  `TITLE` varchar(30) DEFAULT NULL,
  `DESCRIPTION` varchar(1000) DEFAULT NULL,
  `PLATFORM` varchar(20) DEFAULT NULL,
  `DIFFICULITY` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `trophies`
--

INSERT INTO `trophies` (`ID_TROPHY`, `ID_GAME`, `TITLE`, `DESCRIPTION`, `PLATFORM`, `DIFFICULITY`) VALUES
(52, 102, 'gris', 'hhh', 'PS4', 'Medium'),
(56, 102, 'guuuu', 'uuu', 'PS5', 'Very Easy'),
(60, 102, 'xxcxcx', 'xcvxcxcv', 'XBox Series X|S', 'Easy'),
(61, 102, 'eeeee', 'ddddddddddddd', 'XBox Series X|S', 'Hard');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `ID_USER` int(11) NOT NULL,
  `EMAIL` varchar(180) COLLATE utf8mb4_unicode_ci NOT NULL,
  `roles` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '(DC2Type:json)',
  `PASSWORD` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `FULL_NAME` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ISACTIVE` int(11) DEFAULT NULL,
  `privilege_` int(11) DEFAULT NULL,
  `img` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_verified` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`ID_USER`, `EMAIL`, `roles`, `PASSWORD`, `FULL_NAME`, `ISACTIVE`, `privilege_`, `img`, `is_verified`) VALUES
(42, '123@esprit.com', '[\"ROLE_ADMIN\"]', '$argon2id$v=19$m=65536,t=4,p=1$eU9xZWMuNjkuTFhiMC9TNA$7tkEjbHg8hVrDWKUZ3g83L4KHeBhoez/7MgW/RnBWtA', 'anas', NULL, NULL, '646a12f95eabc728d95a6e19f1f56693.png', 0),
(43, 'hhhhh@gmail.com', '[]', '$argon2id$v=19$m=65536,t=4,p=1$VnR6WnlrT1o3NFFDa05oZQ$mSTLz+4uy/2V1ompLtWQ4iF2NVyjCxc4fJhA5jPRzr0', 'ahmed', 0, NULL, '8309e47089153102e460ef1073035892.png', 0),
(44, 'nermine.benamara@esprit.tn', '[]', '$argon2id$v=19$m=65536,t=4,p=1$L0N2Z2x3ZDFuOUdGS3FrVQ$cgfIN9v4KOQjN0cuE46kREiW68A6APjr62GqUWx8ozE', 'nermin', NULL, NULL, NULL, 0),
(45, 'ner@gmail.com', '[]', '$argon2id$v=19$m=65536,t=4,p=1$eHRlMUJnZFNMUEQweU9Fdg$cLqMpCbjbXHvOYu5ktSF1xCM6Pih/TXf5rrUaY6Qs4w', 'nermine12222', 1, NULL, '418d3f951435dcc70f8b0c17b73bf56e.jpeg', 0),
(46, 'nermine@gmail.com', '[]', '$argon2id$v=19$m=65536,t=4,p=1$ZmhodkpDaFhCanZjSzI5Yg$f6bU53rmlHyf9HbVfQR96As2qiZnGtCyrIS67s5vr0Q', 'nermine', NULL, NULL, NULL, 1),
(49, 'jason@gmail.com', '[]', '123456', 'jarib2', NULL, NULL, NULL, 0),
(50, 'anas.benbrahim@esprit.tn', '[]', '$argon2id$v=19$m=65536,t=4,p=1$ejNyNi50aGFHbUxWSkJhZQ$4D7B7KW3b8Je2GpvZA6fxSp0Cnv0mIWz31SDpzfPlJQ', 'Anasbenbrahim', NULL, NULL, '88bc199066090209808f818f5ea78f14.png', 0),
(51, 'anas@gmail.com', '[]', '$argon2id$v=19$m=65536,t=4,p=1$allacDV6ZTJpRUEwNXZCWQ$uUSHvCYAHpQ4AsMKJTZWKD3Azf1u3ykreYtXGjgFFH4', 'Anas ben brahim', NULL, NULL, '004b71fdce7c94a9d99fedeaff0ebf9e.png', 1),
(52, 'ghada.benkhalifa@esprit.tn', '[]', '$argon2id$v=19$m=65536,t=4,p=1$M3R0UVpTeXdiUFMuQ1J2aw$EBGS3SBReHSb8MUtH8r4msewZuUawqiYI7ENLiQmQKA', 'ghada', NULL, NULL, '234322a274b890bf75b8d3d9119e26b9.png', 0);

-- --------------------------------------------------------

--
-- Table structure for table `user_groups`
--

CREATE TABLE `user_groups` (
  `ID_USERS_GPS` int(11) NOT NULL,
  `status` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ID_USER` int(11) DEFAULT NULL,
  `ID_GROUP` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`ID_cart`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`ID_CATEGORY`);

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`ID_COMMENT`),
  ADD KEY `FK_COMMENTS_REFERENCE_NEWS` (`ID_NEWS`);

--
-- Indexes for table `competitions`
--
ALTER TABLE `competitions`
  ADD PRIMARY KEY (`ID_COMPETION`);

--
-- Indexes for table `doctrine_migration_versions`
--
ALTER TABLE `doctrine_migration_versions`
  ADD PRIMARY KEY (`version`);

--
-- Indexes for table `games`
--
ALTER TABLE `games`
  ADD PRIMARY KEY (`ID_GAME`),
  ADD KEY `FK_GAMES_REFERENCE_CATEGORY` (`ID_CATEGORY`);

--
-- Indexes for table `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`ID_GROUP`);

--
-- Indexes for table `news`
--
ALTER TABLE `news`
  ADD PRIMARY KEY (`ID_NEWS`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`ID_PRODUCT`),
  ADD KEY `FK_PRODUCT_REFERENCE_CATEGORY` (`ID_CATEGORY`);

--
-- Indexes for table `reset_password_request`
--
ALTER TABLE `reset_password_request`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_7CE748AA76ED395` (`ID_USER`);

--
-- Indexes for table `teams`
--
ALTER TABLE `teams`
  ADD PRIMARY KEY (`ID_TEAM`),
  ADD KEY `FK_TEAMS_REFERENCE_COMPETIT` (`ID_COMPETION`);

--
-- Indexes for table `trophies`
--
ALTER TABLE `trophies`
  ADD PRIMARY KEY (`ID_TROPHY`),
  ADD KEY `FK_TROPHIES_REFERENCE_GAMES` (`ID_GAME`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`ID_USER`),
  ADD UNIQUE KEY `UNIQ_1483A5E910C6BEC4` (`EMAIL`);

--
-- Indexes for table `user_groups`
--
ALTER TABLE `user_groups`
  ADD PRIMARY KEY (`ID_USERS_GPS`),
  ADD KEY `IDX_953F224DF8371B55` (`ID_USER`),
  ADD KEY `IDX_953F224D42DA9C8F` (`ID_GROUP`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `ID_CATEGORY` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `ID_COMMENT` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `competitions`
--
ALTER TABLE `competitions`
  MODIFY `ID_COMPETION` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `games`
--
ALTER TABLE `games`
  MODIFY `ID_GAME` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=137;

--
-- AUTO_INCREMENT for table `groups`
--
ALTER TABLE `groups`
  MODIFY `ID_GROUP` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `news`
--
ALTER TABLE `news`
  MODIFY `ID_NEWS` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `ID_PRODUCT` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `reset_password_request`
--
ALTER TABLE `reset_password_request`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT for table `teams`
--
ALTER TABLE `teams`
  MODIFY `ID_TEAM` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `trophies`
--
ALTER TABLE `trophies`
  MODIFY `ID_TROPHY` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `ID_USER` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT for table `user_groups`
--
ALTER TABLE `user_groups`
  MODIFY `ID_USERS_GPS` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `FK_COMMENTS_REFERENCE_NEWS` FOREIGN KEY (`ID_NEWS`) REFERENCES `news` (`ID_NEWS`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `games`
--
ALTER TABLE `games`
  ADD CONSTRAINT `FK_GAMES_REFERENCE_CATEGORY` FOREIGN KEY (`ID_CATEGORY`) REFERENCES `category` (`ID_CATEGORY`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK_PRODUCT_REFERENCE_CATEGORY` FOREIGN KEY (`ID_CATEGORY`) REFERENCES `category` (`ID_CATEGORY`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `teams`
--
ALTER TABLE `teams`
  ADD CONSTRAINT `FK_TEAMS_REFERENCE_COMPETIT` FOREIGN KEY (`ID_COMPETION`) REFERENCES `competitions` (`ID_COMPETION`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `trophies`
--
ALTER TABLE `trophies`
  ADD CONSTRAINT `FK_TROPHIES_REFERENCE_GAMES` FOREIGN KEY (`ID_GAME`) REFERENCES `games` (`ID_GAME`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user_groups`
--
ALTER TABLE `user_groups`
  ADD CONSTRAINT `FK_953F224D42DA9C8F` FOREIGN KEY (`ID_GROUP`) REFERENCES `groups` (`ID_GROUP`),
  ADD CONSTRAINT `FK_953F224DF8371B55` FOREIGN KEY (`ID_USER`) REFERENCES `users` (`ID_USER`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
