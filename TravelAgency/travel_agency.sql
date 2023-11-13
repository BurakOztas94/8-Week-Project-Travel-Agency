-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 13, 2023 at 04:13 PM
-- Server version: 8.0.31
-- PHP Version: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `travel_agency`
--

-- --------------------------------------------------------

--
-- Table structure for table `hotel`
--

CREATE TABLE `hotel` (
  `id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `star` enum('*','**','***','****','*****') COLLATE utf8mb4_general_ci NOT NULL,
  `property` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `address` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hotel`
--

INSERT INTO `hotel` (`id`, `name`, `star`, `property`, `address`, `phone`, `email`) VALUES
(1, 'Hilton Bursa Convention Center & Spa', '*****', 'Free Car Park,Free Wi-Fi,Hotel Concierge,7/24 Room Service', ' Istanbul Caddesi No 347. Bursa, 16210, Türkiye', '(0224) 500 05 05', 'bursa.sales@hilton.com'),
(2, 'a', '*****', 'a\r\na\r\na\r\na', 'a\r\na\r\na\r\na', '123123', '2132132@321321.com');

-- --------------------------------------------------------

--
-- Table structure for table `season`
--

CREATE TABLE `season` (
  `id` int NOT NULL,
  `season_start` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `season_end` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `hotel_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `season`
--

INSERT INTO `season` (`id`, `season_start`, `season_end`, `hotel_id`) VALUES
(1, '01/10/203', '30/05/2024', 1);

-- --------------------------------------------------------

--
-- Table structure for table `type_hotel`
--

CREATE TABLE `type_hotel` (
  `id` int NOT NULL,
  `type` enum('Ultra All-Inclusive','All-Inclusive','Bed and Breakfast','Full Board','Half Board','Room Only','Ultra All Inclusive (Alcohol Excluded)') COLLATE utf8mb4_general_ci NOT NULL,
  `hotel_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `type_hotel`
--

INSERT INTO `type_hotel` (`id`, `type`, `hotel_id`) VALUES
(1, 'Ultra All-Inclusive', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `uname` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `pass` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `type` enum('admin','employee') COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `uname`, `pass`, `type`) VALUES
(1, 'x', 'x', 'x', 'admin'),
(2, 'z', 'z', 'z', 'employee'),
(3, 'a', 'a', 'a', 'admin'),
(4, 'e', 'e', 'e', 'employee'),
(5, 'z', 'z', 'z', 'admin'),
(6, 'c', 'c', 'c', 'employee'),
(7, 'be', 'be', 'be', 'admin'),
(8, 'aasda', 'asdasd', 'asdasd', 'employee');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `season`
--
ALTER TABLE `season`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `type_hotel`
--
ALTER TABLE `type_hotel`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `hotel`
--
ALTER TABLE `hotel`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `season`
--
ALTER TABLE `season`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `type_hotel`
--
ALTER TABLE `type_hotel`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
