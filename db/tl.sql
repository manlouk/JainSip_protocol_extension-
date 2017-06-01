-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Φιλοξενητής: 127.0.0.1
-- Χρόνος δημιουργίας: 19 Μαρ 2015 στις 18:11:20
-- Έκδοση διακομιστή: 5.5.32
-- Έκδοση PHP: 5.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Βάση: `tl`
--
CREATE DATABASE IF NOT EXISTS `tl` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `tl`;

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `billing`
--

CREATE TABLE IF NOT EXISTS `billing` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `start_time` bigint(20) DEFAULT NULL,
  `duration` bigint(20) DEFAULT NULL,
  `cost` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=18 ;

--
-- Άδειασμα δεδομένων του πίνακα `billing`
--

INSERT INTO `billing` (`id`, `username`, `start_time`, `duration`, `cost`) VALUES
(1, 'gata', 1426005066444, -1, NULL),
(2, 'zois', 1426006752828, -1, NULL),
(3, 'manolis', 1426006862071, -1, NULL),
(4, 'manolis', 1426007562830, -1, NULL),
(5, 'test4', 1426100430075, 10, 100),
(6, 'm2', 1426171922314, -1, NULL),
(7, 'm1', 1426172064576, -1, NULL),
(8, 'zois', 1426172195477, -1, NULL),
(9, 'bil1', 1426174151455, 9, 90),
(10, 'bil6', 1426176997296, 9, 90),
(11, 'bil5', 1426177036880, 12, 12),
(12, 'bil6', 1426177094010, 8, 80),
(13, 'bild', 1426177968808, 11, 11),
(14, 'bilt', 1426177993870, 10, 100),
(15, 'bilt', 1426178566961, 7, 70),
(16, 'bilt', 1426184038360, 28, 280),
(17, 'gates', 1426777687448, 8, 80);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `blocking`
--

CREATE TABLE IF NOT EXISTS `blocking` (
  `blockedFrom` varchar(30) NOT NULL,
  `blocked` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Άδειασμα δεδομένων του πίνακα `blocking`
--

INSERT INTO `blocking` (`blockedFrom`, `blocked`) VALUES
('zois', 'zois'),
('mitsos', 'zois'),
('gg', 'gates');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `forwarding`
--

CREATE TABLE IF NOT EXISTS `forwarding` (
  `forwardFrom` varchar(30) NOT NULL,
  `forwardTo` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Άδειασμα δεδομένων του πίνακα `forwarding`
--

INSERT INTO `forwarding` (`forwardFrom`, `forwardTo`) VALUES
('manolis', 'gata'),
('gata', 'zois'),
('zois', 'zois'),
('bilt', 'bild'),
('bild', 'bild2'),
('gates', 'gates1'),
('gates1', 'gg');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `plans`
--

CREATE TABLE IF NOT EXISTS `plans` (
  `id` int(11) DEFAULT NULL,
  `charge` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Άδειασμα δεδομένων του πίνακα `plans`
--

INSERT INTO `plans` (`id`, `charge`) VALUES
(0, 10),
(1, 5),
(0, 0);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(256) DEFAULT NULL,
  `password` varchar(256) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `plan` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=30 ;

--
-- Άδειασμα δεδομένων του πίνακα `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `email`, `plan`) VALUES
(27, 'gates', '', '', 0),
(28, 'gg', '', '', 0),
(29, 'gates1', '', '', 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
