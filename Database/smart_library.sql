-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 05, 2018 at 11:37 AM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `samighumman_smart_library`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `admin_name` varchar(16) NOT NULL,
  `admin_password` varchar(32) NOT NULL,
  `admin_id` int(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `book_rfid` bigint(20) NOT NULL,
  `book_name` varchar(128) NOT NULL,
  `book_author` varchar(32) NOT NULL,
  `book_edition` varchar(12) NOT NULL,
  `book_shelf_no` varchar(12) NOT NULL,
  `book_rack_no` varchar(12) NOT NULL,
  `book_quantity` varchar(16) NOT NULL,
  `book_status` varchar(12) NOT NULL,
  `book_category` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `issue_books`
--

CREATE TABLE `issue_books` (
  `issue_id` int(11) NOT NULL,
  `std_fk` varchar(16) NOT NULL,
  `book_fk` bigint(20) NOT NULL,
  `book_issue_date` varchar(32) NOT NULL,
  `book_expiry_date` varchar(32) NOT NULL,
  `book_return_date` varchar(32) NOT NULL,
  `fine` varchar(16) NOT NULL,
  `status` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `std_roll` varchar(16) NOT NULL,
  `std_pass` varchar(15) NOT NULL,
  `std_name` varchar(32) NOT NULL,
  `std_email` varchar(46) NOT NULL,
  `std_cell` varchar(14) NOT NULL,
  `std_batch` varchar(32) NOT NULL,
  `std_dept` varchar(32) NOT NULL,
  `std_status` varchar(12) NOT NULL,
  `std_rf` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`std_roll`, `std_pass`, `std_name`, `std_email`, `std_cell`, `std_batch`, `std_dept`, `std_status`, `std_rf`) VALUES
('14061598-064', '14061598-064', 'Samiullah', 'samighumman@yahoo.com', '03344415567', '14', 'SE', 'active', 1111111111),
('14061598-074', '14061598-074', 'Murtaza', 'murtazamustafa@yahoo.com', '03041351353', '14', 'SE', 'active', 2222222222),
('14061598-076', '14061598-076', 'Ahmed', 'ahmediftikhar@yahoo.com', '03134687812', '14', 'SE', 'active', 3333333333),
('14061598-084', '14061598-084', 'Shahzaib', 'shahzaibsohail@yahoo.com', '03441234412', '14', 'SE', 'active', 4444444444);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`admin_id`);

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`book_rfid`);

--
-- Indexes for table `issue_books`
--
ALTER TABLE `issue_books`
  ADD PRIMARY KEY (`issue_id`),
  ADD KEY `std_fk` (`std_fk`),
  ADD KEY `book_fk` (`book_fk`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`std_roll`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `admin_id` int(16) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `issue_books`
--
ALTER TABLE `issue_books`
  MODIFY `issue_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `issue_books`
--
ALTER TABLE `issue_books`
  ADD CONSTRAINT `book_issue` FOREIGN KEY (`book_fk`) REFERENCES `books` (`book_rfid`),
  ADD CONSTRAINT `student_issue_book` FOREIGN KEY (`std_fk`) REFERENCES `student` (`std_roll`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
