SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `IPControllerDB`
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Дамп данных таблицы `Controllers`
--

INSERT INTO `Controllers` (`id`, `privateKey`, `user`) VALUES
(1, 'KHBK233', 11),
(2, '1901DB46', 1),
(3, '4DDDC1DB', -1),
(4, '43280601', -1),
(5, '7853F726', -1);

-- --------------------------------------------------------

--
-- Структура таблицы `Events`
--

CREATE TABLE IF NOT EXISTS `Events` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `privateKey` text NOT NULL,
  `msg` text NOT NULL,
  `priority` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Дамп данных таблицы `Events`
--

INSERT INTO `Events` (`id`, `time`, `privateKey`, `msg`, `priority`) VALUES
(1, '2014-07-25 03:37:52', '12345678', 'Some event.', 1),
(2, '2014-07-25 21:44:44', '4DDDC1DB', 'Test', 0),
(3, '2014-07-25 21:45:01', '4DDDC1DB', 'Test2', 0);

-- --------------------------------------------------------

--
-- Структура таблицы `Statuses`
--

CREATE TABLE IF NOT EXISTS `Statuses` (
  `id` varchar(11) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `Statuses`
--

INSERT INTO `Statuses` (`id`, `time`, `status`) VALUES
('1901DB46', '2014-07-21 14:28:11', 'NORMAL'),
('43280601', '2014-07-23 22:12:04', 'PART'),
('4DDDC1DB', '2014-07-25 21:54:22', 'NORMAL'),
('5BC7B4EC', '2014-07-15 12:07:08', 'NORMAL'),
('7853F726', '2014-07-23 22:12:03', 'BREAK'),
('DB9BAA3C', '2014-07-21 19:26:37', 'NORMAL'),
('F3B805A0', '2014-07-22 21:54:04', 'ALARM'),
('FB5217B6', '2014-07-21 14:31:50', 'NORMAL');

-- --------------------------------------------------------

--
-- Структура таблицы `Users`
--

CREATE TABLE IF NOT EXISTS `Users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` text NOT NULL,
  `password` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Дамп данных таблицы `Users`
--

INSERT INTO `Users` (`id`, `email`, `password`) VALUES
(1, 'vlad007.1996@yandex.ru', 'mylittlepass');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
