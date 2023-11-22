-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: localhost
-- Üretim Zamanı: 22 Kas 2023, 16:28:00
-- Sunucu sürümü: 8.0.31
-- PHP Sürümü: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `travel_agency_r01`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `hotel`
--

CREATE TABLE `hotel` (
  `id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `star` enum('*','**','***','****','*****') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `property` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `hotel`
--

INSERT INTO `hotel` (`id`, `name`, `star`, `property`, `address`, `phone`, `email`) VALUES
(11, '1', '**', '1', '1', '1', '1'),
(12, 'Name', '*', '2', '2', '2', '2'),
(13, 'yeni', '*****', 'otopark\nwifi', 'İstanbul', '0000', 'email');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `reservation`
--

CREATE TABLE `reservation` (
  `id` int NOT NULL,
  `room_id` int NOT NULL,
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `price` int NOT NULL,
  `start_date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `finish_date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `adult_visitor` int NOT NULL,
  `child_visitor` int DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `identity_number` int NOT NULL,
  `age` int NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `room`
--

CREATE TABLE `room` (
  `id` int NOT NULL,
  `room_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `stock` int NOT NULL,
  `season_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `room_price_per_adult` int DEFAULT NULL,
  `room_price_per_child` int DEFAULT NULL,
  `hotel_id` int NOT NULL,
  `capacity` int NOT NULL,
  `room_properties` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `area` int NOT NULL,
  `season_id` int NOT NULL,
  `type_hotel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `room`
--

INSERT INTO `room` (`id`, `room_type`, `stock`, `season_name`, `room_price_per_adult`, `room_price_per_child`, `hotel_id`, `capacity`, `room_properties`, `area`, `season_id`, `type_hotel`) VALUES
(9, 'suit room', 10, 'Summer', 0, 0, 12, 6, 'minibar', 30, 0, '');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `roomprice`
--

CREATE TABLE `roomprice` (
  `id` int NOT NULL,
  `room_id` int NOT NULL,
  `hotel_id` int NOT NULL,
  `room_price_per_adult` int NOT NULL,
  `room_price_per_child` int NOT NULL,
  `seasonName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `hotelType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `roomprice`
--

INSERT INTO `roomprice` (`id`, `room_id`, `hotel_id`, `room_price_per_adult`, `room_price_per_child`, `seasonName`, `hotelType`) VALUES
(11, 9, 12, 1000, 500, 'Summer', 'All-Inclusive'),
(12, 10, 12, 800, 400, 'Summer', 'All-Inclusive'),
(13, 8, 11, 700, 400, 'Winter', 'Ultra All-Inclusive');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `season`
--

CREATE TABLE `season` (
  `id` int NOT NULL,
  `season_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `season_start` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `season_end` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `hotel_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `season`
--

INSERT INTO `season` (`id`, `season_name`, `season_start`, `season_end`, `hotel_id`) VALUES
(66, 'Winter', '01/11/2023', '30/04/2024', 11),
(70, 'Summer', '01/05/2024', '30/10/2024', 12),
(71, 'Winter', '01/11/2023', '30/04/2024', 12),
(74, 'Summer', '01/05/2024', '30/10/2024', 13);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `type_hotel`
--

CREATE TABLE `type_hotel` (
  `id` int NOT NULL,
  `type` enum('Ultra All-Inclusive','All-Inclusive','Bed and Breakfast','Full Board','Half Board','Room Only','Ultra - Alcohol Excluded') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `hotel_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `type_hotel`
--

INSERT INTO `type_hotel` (`id`, `type`, `hotel_id`) VALUES
(125, 'All-Inclusive', 12),
(154, 'Ultra All-Inclusive', 11),
(155, 'All-Inclusive', 11),
(157, 'Ultra All-Inclusive', 13),
(158, 'All-Inclusive', 13),
(159, 'Bed and Breakfast', 13),
(160, 'Full Board', 13),
(161, 'Half Board', 13),
(162, 'Room Only', 13),
(163, 'Ultra - Alcohol Excluded', 13);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `user`
--

CREATE TABLE `user` (
  `id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `uname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `pass` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` enum('admin','employee') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `user`
--

INSERT INTO `user` (`id`, `name`, `uname`, `pass`, `type`) VALUES
(1, 'x', 'x', 'x', 'admin'),
(2, 'z', 'z', 'z', 'employee'),
(3, 'a', 'a', 'a', 'admin'),
(4, 'e', 'e', 'e', 'employee'),
(6, 'c', 'c', 'c', 'employee');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `roomprice`
--
ALTER TABLE `roomprice`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `season`
--
ALTER TABLE `season`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `type_hotel`
--
ALTER TABLE `type_hotel`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `hotel`
--
ALTER TABLE `hotel`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Tablo için AUTO_INCREMENT değeri `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- Tablo için AUTO_INCREMENT değeri `room`
--
ALTER TABLE `room`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Tablo için AUTO_INCREMENT değeri `roomprice`
--
ALTER TABLE `roomprice`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Tablo için AUTO_INCREMENT değeri `season`
--
ALTER TABLE `season`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=75;

--
-- Tablo için AUTO_INCREMENT değeri `type_hotel`
--
ALTER TABLE `type_hotel`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=164;

--
-- Tablo için AUTO_INCREMENT değeri `user`
--
ALTER TABLE `user`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
