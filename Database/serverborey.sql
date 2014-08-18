-- phpMyAdmin SQL Dump
-- version 4.0.10.1
-- http://www.phpmyadmin.net
--
-- Хост: 127.10.62.130:3306
-- Время создания: Авг 16 2014 г., 18:05
-- Версия сервера: 5.5.37
-- Версия PHP: 5.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `serverborey`
--

-- --------------------------------------------------------

--
-- Структура таблицы `Controllers`
--

CREATE TABLE IF NOT EXISTS `Controllers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `privateKey` text NOT NULL,
  `user` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=29 ;

--
-- Дамп данных таблицы `Controllers`
--

INSERT INTO `Controllers` (`id`, `privateKey`, `user`) VALUES
(1, 'KHBK233', 11),
(2, '1901DB46', 1),
(3, '4DDDC1DB', -1),
(4, '43280601', -1),
(5, '7853F726', -1),
(27, '4DDDC1DB', 1),
(28, '7A48DC05', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `Events`
--

CREATE TABLE IF NOT EXISTS `Events` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `privateKey` text NOT NULL,
  `msg` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `priority` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Структура таблицы `Statuses`
--

CREATE TABLE IF NOT EXISTS `Statuses` (
  `id` varchar(11) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `Statuses`
--

INSERT INTO `Statuses` (`id`, `time`, `status`) VALUES
('1901DB46', '2014-08-16 14:29:59', 'ALARM'),
('43280601', '2014-07-23 22:12:04', 'ALARM'),
('4DDDC1DB', '2014-08-16 00:02:35', 'ALARM'),
('5BC7B4EC', '2014-07-15 12:07:08', 'ALARM'),
('7853F726', '2014-07-23 22:12:03', 'ALARM'),
('7A48DC05', '2014-08-14 20:19:52', 'ALARM'),
('AAA', '2014-08-07 07:41:10', 'ALARM'),
('DB9BAA3C', '2014-07-21 19:26:37', 'ALARM'),
('F3B805A0', '2014-07-22 21:54:04', 'ALARM'),
('FB5217B6', '2014-07-21 14:31:50', 'ALARM');

-- --------------------------------------------------------

--
-- Структура таблицы `Users`
--

CREATE TABLE IF NOT EXISTS `Users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` text NOT NULL,
  `password` text NOT NULL,
  `notify_all` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Дамп данных таблицы `Users`
--

INSERT INTO `Users` (`id`, `email`, `password`, `notify_all`) VALUES
(1, 'vlad007.1996@yandex.ru', 'vladvlad1996@tut.by', 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
