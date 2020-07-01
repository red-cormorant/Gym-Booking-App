-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 27, 2020 at 06:44 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `planificator`
--

-- --------------------------------------------------------

--
-- Table structure for table `angajat`
--

CREATE TABLE `angajat` (
  `idAngajat` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `numeAngajat` varchar(100) NOT NULL,
  `prenumeAngajat` varchar(100) NOT NULL,
  `telefonAngajat` varchar(100) NOT NULL,
  `emailAngajat` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `angajat`
--

INSERT INTO `angajat` (`idAngajat`, `idUser`, `numeAngajat`, `prenumeAngajat`, `telefonAngajat`, `emailAngajat`) VALUES
(1, 4, 'Marius', 'Cristian', '0726 888 888', 'marius@gym.com'),
(2, 12, 'Ionescu', 'Andreea', '0758 444 365', 'andreea@gym.com');

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `idClient` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `numeClient` varchar(100) NOT NULL,
  `prenumeClient` varchar(100) NOT NULL,
  `telefon` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`idClient`, `idUser`, `numeClient`, `prenumeClient`, `telefon`, `email`) VALUES
(13, 1, 'Andrei', 'Tudor', '0456 884 658', 'andreitudor@dsad.com'),
(14, 3, 'Silviu', 'Popa', '0755 257 777', 'silviu@popa.ro'),
(18, 7, 'Toader', 'Maria Miruna', '0720 898 898', 'maria@email.com'),
(19, 8, 'Dumitrescu', 'Ana Cezara', '0758 888 999', 'ana@email.como');

-- --------------------------------------------------------

--
-- Table structure for table `detaliieveniment`
--

CREATE TABLE `detaliieveniment` (
  `idDetaliiEveniment` int(11) NOT NULL,
  `idEveniment` int(11) NOT NULL,
  `idClient` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `detaliieveniment`
--

INSERT INTO `detaliieveniment` (`idDetaliiEveniment`, `idEveniment`, `idClient`) VALUES
(1, 1, 13),
(2, 1, 14),
(4, 6, 13),
(6, 7, 19),
(8, 8, 18),
(13, 9, 13),
(9, 9, 14),
(10, 9, 18),
(11, 9, 19),
(14, 13, 13),
(15, 14, 13),
(16, 14, 14),
(17, 14, 18),
(18, 14, 19);

-- --------------------------------------------------------

--
-- Table structure for table `eveniment`
--

CREATE TABLE `eveniment` (
  `idEveniment` int(11) NOT NULL,
  `idAngajat` int(11) NOT NULL,
  `nrMaximParticipanti` int(11) NOT NULL,
  `notes` varchar(100) NOT NULL,
  `oraEveniment` time NOT NULL,
  `ziEveniment` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `eveniment`
--

INSERT INTO `eveniment` (`idEveniment`, `idAngajat`, `nrMaximParticipanti`, `notes`, `oraEveniment`, `ziEveniment`) VALUES
(1, 1, 2, 'Antrenament individual', '16:00:00', '2020-06-26'),
(3, 1, 5, 'Cardio session', '09:00:00', '2020-06-23'),
(6, 1, 1, 'Antrenament individual\n', '09:00:00', '2020-06-24'),
(7, 1, 1, 'Antrenament individual', '09:00:00', '2020-06-26'),
(8, 1, 1, 'Antrenament individual', '10:00:00', '2020-07-26'),
(9, 1, 6, 'Cardio training', '10:00:00', '2020-08-30'),
(10, 2, 0, 'NIMIC', '09:00:00', '2020-06-26'),
(11, 1, 3, 'Aerobic ', '10:00:00', '2020-06-27'),
(13, 1, 1, 'Antrenament cu Andrei Tudor', '14:00:00', '2020-07-15'),
(14, 1, 4, 'Cardio', '10:00:00', '2020-07-05');

-- --------------------------------------------------------

--
-- Table structure for table `rezervare`
--

CREATE TABLE `rezervare` (
  `idRezervare` int(11) NOT NULL,
  `idClient` int(11) NOT NULL,
  `oraRezervare` time NOT NULL,
  `ziRezervare` date NOT NULL DEFAULT '2000-01-01'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `rezervare`
--

INSERT INTO `rezervare` (`idRezervare`, `idClient`, `oraRezervare`, `ziRezervare`) VALUES
(22, 13, '10:00:00', '2020-06-26'),
(33, 13, '10:00:00', '2020-06-27'),
(36, 13, '10:00:00', '2020-06-28'),
(29, 13, '11:00:00', '2020-06-26'),
(37, 13, '11:00:00', '2020-06-28'),
(34, 13, '13:00:00', '2020-06-27'),
(30, 13, '14:00:00', '2020-06-25'),
(38, 13, '14:00:00', '2020-06-29'),
(39, 13, '15:00:00', '2020-06-29'),
(17, 13, '16:00:00', '2020-06-25'),
(35, 13, '16:00:00', '2020-06-27'),
(40, 13, '16:00:00', '2020-06-30'),
(41, 14, '09:00:00', '2020-06-26'),
(46, 14, '09:00:00', '2020-06-29'),
(9, 14, '10:00:00', '2020-06-25'),
(47, 14, '10:00:00', '2020-06-29'),
(10, 14, '12:00:00', '2020-06-25'),
(48, 14, '12:00:00', '2020-06-30'),
(42, 14, '13:00:00', '2020-06-26'),
(43, 14, '14:00:00', '2020-06-27'),
(44, 14, '15:00:00', '2020-06-27'),
(45, 14, '16:00:00', '2020-06-28');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `idUser` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `parola` varchar(100) NOT NULL,
  `esteAngajat` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`idUser`, `username`, `parola`, `esteAngajat`) VALUES
(1, 'andreitudor', 'dsada', 0),
(3, 'silviupopa', 'qwerty', 0),
(4, 'mariuscristian', 'parola', 1),
(7, 'mariatoader', 'parola', 0),
(8, 'anacezara', 'parola', 0),
(9, 'cristinastan', 'parola', 0),
(10, 'mihaelabarbu', 'parola', 0),
(11, 'alexandrucoman', 'parola', 0),
(12, 'andreeaionescu', 'parola', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `angajat`
--
ALTER TABLE `angajat`
  ADD PRIMARY KEY (`idAngajat`),
  ADD KEY `idUser` (`idUser`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`idClient`),
  ADD KEY `idUser` (`idUser`);

--
-- Indexes for table `detaliieveniment`
--
ALTER TABLE `detaliieveniment`
  ADD PRIMARY KEY (`idDetaliiEveniment`),
  ADD KEY `idEveniment` (`idEveniment`,`idClient`),
  ADD KEY `detaliieveniment_ibfk_2` (`idClient`);

--
-- Indexes for table `eveniment`
--
ALTER TABLE `eveniment`
  ADD PRIMARY KEY (`idEveniment`),
  ADD KEY `idAngajat` (`idAngajat`);

--
-- Indexes for table `rezervare`
--
ALTER TABLE `rezervare`
  ADD PRIMARY KEY (`idRezervare`),
  ADD UNIQUE KEY `idClient_2` (`idClient`,`oraRezervare`,`ziRezervare`),
  ADD KEY `idClient` (`idClient`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`idUser`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `angajat`
--
ALTER TABLE `angajat`
  MODIFY `idAngajat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `idClient` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `detaliieveniment`
--
ALTER TABLE `detaliieveniment`
  MODIFY `idDetaliiEveniment` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `eveniment`
--
ALTER TABLE `eveniment`
  MODIFY `idEveniment` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `rezervare`
--
ALTER TABLE `rezervare`
  MODIFY `idRezervare` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `idUser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `angajat`
--
ALTER TABLE `angajat`
  ADD CONSTRAINT `angajat_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `client_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`) ON DELETE CASCADE;

--
-- Constraints for table `detaliieveniment`
--
ALTER TABLE `detaliieveniment`
  ADD CONSTRAINT `detaliieveniment_ibfk_1` FOREIGN KEY (`idEveniment`) REFERENCES `eveniment` (`idEveniment`) ON DELETE CASCADE,
  ADD CONSTRAINT `detaliieveniment_ibfk_2` FOREIGN KEY (`idClient`) REFERENCES `client` (`idClient`) ON DELETE CASCADE;

--
-- Constraints for table `eveniment`
--
ALTER TABLE `eveniment`
  ADD CONSTRAINT `eveniment_ibfk_1` FOREIGN KEY (`idAngajat`) REFERENCES `angajat` (`idAngajat`) ON DELETE CASCADE;

--
-- Constraints for table `rezervare`
--
ALTER TABLE `rezervare`
  ADD CONSTRAINT `rezervare_ibfk_1` FOREIGN KEY (`idClient`) REFERENCES `client` (`idClient`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
