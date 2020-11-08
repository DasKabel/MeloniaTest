-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 08. Nov 2020 um 15:07
-- Server-Version: 10.4.14-MariaDB
-- PHP-Version: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `melonia`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `keypass`
--

CREATE TABLE `keypass` (
  `user_id` int(6) NOT NULL,
  `keypass` varbinary(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `keypass`
--

INSERT INTO `keypass` (`user_id`, `keypass`) VALUES
(1, 0xb95c8f2418d20ced),
(2, 0x0f45d5134eaf7d18),
(3, 0xee9a161f0a777a50),
(4, 0x3f0e3672fd40cf71),
(5, 0x51dac50c76b470e7),
(6, 0x5eb5a30a75bc35d2);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `membergroup`
--

CREATE TABLE `membergroup` (
  `user_id` int(6) NOT NULL,
  `user_group` varbinary(160) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `membergroup`
--

INSERT INTO `membergroup` (`user_id`, `user_group`) VALUES
(1, 0x61646d696e),
(2, 0x75736572),
(3, 0x75736572),
(4, 0x61646d696e),
(5, 0x75736572),
(6, 0x75736572);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `pass`
--

CREATE TABLE `pass` (
  `user_id` int(6) NOT NULL,
  `pass` varbinary(160) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `pass`
--

INSERT INTO `pass` (`user_id`, `pass`) VALUES
(1, 0x6b66efa3527fc335fa6cd5ba70ed74e83ef3e37d),
(2, 0x3685c5308c33aea55ee8e606fbc07b2b9be5768d),
(3, 0xaaf57b94fdd1a19d58ec087f74fab0053ac88e71),
(4, 0xb20bfee6ae9425c70882b79412bb3c4b6ac66492),
(5, 0x8feb65d3815ee85fa62a95d292d2a8f5eef7ef37),
(6, 0x3db74b3dc99df4214d6c863e868303b6e3b02058);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `time`
--

CREATE TABLE `time` (
  `user_id` int(6) NOT NULL,
  `date` date NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  `working_hours` bigint(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `time`
--

INSERT INTO `time` (`user_id`, `date`, `start_time`, `end_time`, `working_hours`) VALUES
(2, '2020-11-08', '00:00:00', '00:30:00', 0),
(1, '2020-11-08', '00:00:00', '00:30:00', 0),
(5, '2020-11-08', '00:00:00', '00:30:00', 0),
(4, '2020-11-08', '00:00:00', '00:30:00', 0),
(3, '2020-11-08', '00:00:00', '00:30:00', 0),
(6, '2020-11-08', '08:00:00', '17:00:00', 30600000);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `userdata`
--

CREATE TABLE `userdata` (
  `user_id` int(6) NOT NULL,
  `firstName` varchar(30) DEFAULT NULL,
  `lastName` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `userdata`
--

INSERT INTO `userdata` (`user_id`, `firstName`, `lastName`) VALUES
(1, 'admin', 'admin'),
(2, 'Test', 'User'),
(3, 'Test1', 'User1'),
(4, 'Test3', 'Drei'),
(5, 'Test', 'Vier'),
(6, 'Test', 'Account');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `users`
--

CREATE TABLE `users` (
  `ID` int(6) NOT NULL,
  `username` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `users`
--

INSERT INTO `users` (`ID`, `username`) VALUES
(1, 'admin'),
(2, 'Test1'),
(3, 'Test2'),
(4, 'Test3'),
(5, 'Test4'),
(6, 'TestAccount');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `keypass`
--
ALTER TABLE `keypass`
  ADD UNIQUE KEY `user_id` (`user_id`);

--
-- Indizes für die Tabelle `membergroup`
--
ALTER TABLE `membergroup`
  ADD UNIQUE KEY `user_id` (`user_id`);

--
-- Indizes für die Tabelle `pass`
--
ALTER TABLE `pass`
  ADD UNIQUE KEY `user_id` (`user_id`);

--
-- Indizes für die Tabelle `time`
--
ALTER TABLE `time`
  ADD KEY `ft_users` (`user_id`);

--
-- Indizes für die Tabelle `userdata`
--
ALTER TABLE `userdata`
  ADD UNIQUE KEY `user_id` (`user_id`);

--
-- Indizes für die Tabelle `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `users`
--
ALTER TABLE `users`
  MODIFY `ID` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `keypass`
--
ALTER TABLE `keypass`
  ADD CONSTRAINT `fs_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`ID`) ON DELETE CASCADE;

--
-- Constraints der Tabelle `membergroup`
--
ALTER TABLE `membergroup`
  ADD CONSTRAINT `fg_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`ID`) ON DELETE CASCADE;

--
-- Constraints der Tabelle `pass`
--
ALTER TABLE `pass`
  ADD CONSTRAINT `fp_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`ID`) ON DELETE CASCADE;

--
-- Constraints der Tabelle `time`
--
ALTER TABLE `time`
  ADD CONSTRAINT `ft_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`ID`) ON DELETE CASCADE;

--
-- Constraints der Tabelle `userdata`
--
ALTER TABLE `userdata`
  ADD CONSTRAINT `ud_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`ID`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
