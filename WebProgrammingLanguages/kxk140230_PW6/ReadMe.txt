Net Id - kxk140230
Name - Kanika Kapoor
Submission Date - Oct 26, 2015


Table Structure is as follows:
--
-- Database: `PW6_Books`
--
-- --------------------------------------------------------
--
-- Table structure for table `Books`
--

CREATE TABLE `Books` (
  `book_id` int(11) NOT NULL,
  `category` varchar(100) NOT NULL,
  `title` varchar(255) NOT NULL,
  `author` varchar(100) NOT NULL,
  `year` year(4) NOT NULL,
  `price` float NOT NULL,
  PRIMARY KEY(book_id)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;


Table Data : 
--
-- Dumping data for table `Books`
--

INSERT INTO `Books` (`book_id`, `category`, `title`, `author`, `year`, `price`) VALUES
(1, 'cooking', 'Everyday Italian', 'Giada De Laurentiis', 2005, 30),
(2, 'Children', 'Harry Potter', 'J K Rowling', 2005, 29.99),
(3, 'Web', 'XQuery Kick Start', 'James McGovern, Per Bothner, Kurt Cagle , James Linn, Vaidyanathan Nagarajan', 2003, 49.99),
(4, 'web', 'Learning XML', 'Erik T. Ray', 2003, 39.95);
