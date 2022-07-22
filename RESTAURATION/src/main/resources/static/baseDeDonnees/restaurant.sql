-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 23 juil. 2022 à 00:17
-- Version du serveur :  10.4.18-MariaDB
-- Version de PHP : 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `restauration`
--

-- --------------------------------------------------------

--
-- Structure de la table `restaurant`
--

CREATE TABLE `restaurant` (
  `recordid` varchar(50) NOT NULL,
  `nom_restaurant` varchar(50) DEFAULT NULL,
  `codepostal` varchar(10) DEFAULT NULL,
  `adresse` varchar(50) DEFAULT NULL,
  `ville` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `restaurant`
--

INSERT INTO `restaurant` (`recordid`, `nom_restaurant`, `codepostal`, `adresse`, `ville`) VALUES
('257af888dc2719b01850e5f6752bbfc0c71d3ede', 'CLIGNANCOURT', '75018', '14-16, SQUARE CLIGNANCOURT', 'PARIS'),
('58ef6604d1ee42e794fc800f13aed39ddf11be37', 'TERNES', '75017', '28, RUE BAYEN', 'PARIS'),
('61359ce3c8b11975f325fd60826784b587f96a62', 'SAINTS-SIMONIENS', '75020', '18, RUE DE LA DUEE', 'PARIS'),
('6d458020e956dbf67570bf3d4c3ad8dd686e005a', 'ANDRE MALRAUX', '75006', '112, RUE DE RENNES', 'PARIS'),
('93a076cb6e797e045a773c5ead9279b177c6a627', 'JOSEPH DE MAISTRE', '75018', '88, RUE JOSEPH DE MAISTRE', 'PARIS'),
('9fa2aae3cb0ab4e1d09dc564333f771b11e2d947', NULL, '75013', NULL, NULL),
('c2db1675a0c3edea29918db60d2aeb9fc7a1f4f3', 'JARDIN DES PLANTES', '75005', '18-22, RUE POLIVEAU', 'PARIS'),
('ce2aff2bbbf5ccd1ed6aeeba5167a40873b585d0', 'MARCHE DE L\'EUROPE', '75008', '11, RUE MALLEVILLE', 'PARIS'),
('dbf93f49d1a164f83eb77174fac6b2f20fc93f4c', 'LA QUINTINIE', '75015', '18, RUE BARGUE', 'PARIS'),
('e4d5befa0e8f878d962f92ab6b0251d5a148887b', 'AU MAIRE', '75003', '2, RUE AU MAIRE', 'PARIS'),
('r1', 'LA TABLE RONDE', '59000', '4 rue Vauban', 'LILLE'),
('r10', 'LA DELICATESSE', '13000', '7 boulevard de la libération', 'MARSEILLE'),
('r11', 'LE KUHN', '67000', '15 rue kuhn', 'STRASBOURG'),
('r12', 'AU VIEUX STRASBOURG', '67000', '5 rue du Maroquin', 'STRASBOURG'),
('r13', 'LES CHAUVINS', '67000', '3 rue du faisan', 'STRASBOURG'),
('r14', 'RESTAURANT GAVROCHE', '67000', '4 rue Klein', 'STRASBOURG'),
('r15', 'AU CRUCHON', '67000', '11 rue des pucelles', 'STRASBOURG'),
('r16', 'MONTE CRISTO', '35000', '40 rue Saint georges', 'RENNES'),
('r17', 'LE GOUT DES AUTRES', '35000', '34 rue Vasselot', 'RENNES'),
('r18', 'LE GLOBE', '35000', '32 boulevard de la liberté', 'RENNES'),
('r19', 'LE GALOPIN', '35000', '21 avenue jean janvier', 'RENNES'),
('r2', 'SEBASTOPOL', '59000', '1 place Sebastopol', 'LILLE'),
('r20', 'LA CHOPE', '35000', '3 rue de la chalotais', 'RENNES'),
('r21', 'LE GABRIEL', '75000', '42 avenue Gabriel', 'PARIS'),
('r22', 'LES RUPINS', '75000', '35 boulevard de Magenta', 'PARIS'),
('r23', 'LE PETIT PRINCE', '75000', '12 rue de Lanneau', 'PARIS'),
('r24', 'CHEZ PAPA', '75000', '6 rue Gassendi', 'PARIS'),
('r25', 'BOUILLON CHARTIER', '75000', '7 rue du Faubourg Montmartre', 'PARIS'),
('r3', 'LA FLEUR DE LILLE', '59000', '19 rue de Gand', 'LILLE'),
('r4', 'RISTORANTE', '59000', '51 rue des tanneurs', 'LILLE'),
('r5', 'LE 28', '59000', '28 rue Thiers', 'LILLE'),
('r6', 'LA GRIVOISE', '13000', '17 rue des trois rois', 'MARSEILLE'),
('r7', 'AUX ANTIPODES', '13000', '26 rue Sainte', 'MARSEILLE'),
('r8', 'MADAME JEANNE', '13000', '86 Rue Grignan', 'MARSEILLE'),
('r9', 'BISTRO O PRADO', '13000', '1 boulevard Périer', 'MARSEILLE');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `restaurant`
--
ALTER TABLE `restaurant`
  ADD PRIMARY KEY (`recordid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
