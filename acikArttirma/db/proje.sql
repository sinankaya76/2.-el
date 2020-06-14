-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 28 May 2015, 17:57:25
-- Sunucu sürümü: 5.6.24
-- PHP Sürümü: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Veritabanı: `proje`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kategori`
--

CREATE TABLE IF NOT EXISTS `kategori` (
  `KategoriId` int(18) NOT NULL,
  `Ad` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Tablo döküm verisi `kategori`
--

INSERT INTO `kategori` (`KategoriId`, `Ad`) VALUES
(1, 'Beyaz Esyalar'),
(2, 'Elektronik'),
(3, 'Ev,Bahçe,Ofis'),
(4, 'Otomobil,Motosiklet'),
(5, 'Hobi,E?lence,Sanat');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kullanici`
--

CREATE TABLE IF NOT EXISTS `kullanici` (
  `Kullanici_tipi` int(11) NOT NULL,
  `Kullanici_adi` varchar(11) NOT NULL,
  `İsim` varchar(11) NOT NULL,
  `Soyisim` varchar(11) NOT NULL,
  `Sifre` varchar(50) NOT NULL,
  `Yas` varchar(50) NOT NULL,
  `KullaniciId` int(11) NOT NULL,
  `Onay` bit(1) NOT NULL DEFAULT b'0'
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;

--
-- Tablo döküm verisi `kullanici`
--

INSERT INTO `kullanici` (`Kullanici_tipi`, `Kullanici_adi`, `İsim`, `Soyisim`, `Sifre`, `Yas`, `KullaniciId`, `Onay`) VALUES
(0, 'Yonetici', 'Yonetici', 'Y', '123', '25', 0, b'0'),
(1, 'Musteri', 'Mü?teri', 'a', '123', '43', 1, b'1'),
(2, 'Satici', 'Sat?c?', 's', '123', '45', 2, b'1'),
(2, 'a', 'a', 'a', 'a', 'a', 32, b'1');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `teklif`
--

CREATE TABLE IF NOT EXISTS `teklif` (
  `Teklif_id` int(11) NOT NULL,
  `ÜrünId` int(11) NOT NULL,
  `Teklif_miktari` varchar(50) NOT NULL,
  `Teklif_onay` int(1) NOT NULL DEFAULT '0',
  `Kullanici_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

--
-- Tablo döküm verisi `teklif`
--

INSERT INTO `teklif` (`Teklif_id`, `ÜrünId`, `Teklif_miktari`, `Teklif_onay`, `Kullanici_id`) VALUES
(1, 0, '1', 1, 11),
(7, 0, '10', 0, 2),
(8, 0, '11', 0, 3),
(9, 3, '4', 1, 11),
(10, 2, '4', 1, 19),
(11, 3, '45', 1, 19),
(12, 3, '3543', 1, 19),
(13, 2, '7', 1, 11),
(14, 1, 'req', 1, 19),
(15, 1, 'as', 1, 11),
(16, 0, '12', 1, 11),
(17, 0, 'a', 1, 11),
(18, 0, '122', 1, 19),
(19, 1, '54', 1, 19),
(20, 0, '', 1, 29),
(21, 0, '79', 1, 29),
(22, 0, '12', 1, 11),
(23, 1, '123', 1, 11);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `ürünler`
--

CREATE TABLE IF NOT EXISTS `ürünler` (
  `ÜrünId` int(11) NOT NULL,
  `KategoriId` int(11) NOT NULL,
  `ad` varchar(25) NOT NULL,
  `Aciklama` varchar(25) NOT NULL,
  `Fiyat` varchar(50) NOT NULL,
  `Kullanici_Id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=latin1;

--
-- Tablo döküm verisi `ürünler`
--

INSERT INTO `ürünler` (`ÜrünId`, `KategoriId`, `ad`, `Aciklama`, `Fiyat`, `Kullanici_Id`) VALUES
(22, 1, 'ütü', 'sdfgthyuj?kl', '150', 14),
(64, 1, 'dsf', 'w', 'e', 32),
(65, 1, 'f?r?n', 'az kulan?lm??', '1000', 32),
(66, 3, 'Bilgayar', '.', '1222', 32),
(67, 2, 'Sandalye', '.', '1000', 32),
(68, 4, 'Araba', '.', '10000', 32),
(69, 5, 'Resim', '.', '250', 32);

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`KategoriId`), ADD KEY `KategoriId` (`KategoriId`), ADD KEY `KategoriId_2` (`KategoriId`);

--
-- Tablo için indeksler `kullanici`
--
ALTER TABLE `kullanici`
  ADD PRIMARY KEY (`KullaniciId`), ADD KEY `KullanıcıId` (`KullaniciId`);

--
-- Tablo için indeksler `teklif`
--
ALTER TABLE `teklif`
  ADD PRIMARY KEY (`Teklif_id`);

--
-- Tablo için indeksler `ürünler`
--
ALTER TABLE `ürünler`
  ADD PRIMARY KEY (`ÜrünId`), ADD KEY `KategoriId` (`KategoriId`), ADD KEY `KategoriId_2` (`KategoriId`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `kullanici`
--
ALTER TABLE `kullanici`
  MODIFY `KullaniciId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=33;
--
-- Tablo için AUTO_INCREMENT değeri `teklif`
--
ALTER TABLE `teklif`
  MODIFY `Teklif_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=24;
--
-- Tablo için AUTO_INCREMENT değeri `ürünler`
--
ALTER TABLE `ürünler`
  MODIFY `ÜrünId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=70;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
