--
-- База данных: `product-database`
--

-- --------------------------------------------------------

--
-- Структура таблицы `group_variant`
--

CREATE TABLE `group_variant` (
`group_variant_id` bigint(20) NOT NULL,
`name` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `group_variant`
--



-- --------------------------------------------------------

--
-- Структура таблицы `product`
--

CREATE TABLE `product` (
`product_id` bigint(20) NOT NULL,
`name` varchar(256) NOT NULL,
`created` date NOT NULL,
`description` varchar(256) NOT NULL,
`product_group` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `product`
--



-- --------------------------------------------------------

--
-- Структура таблицы `product_group`
--

CREATE TABLE `product_group` (
`product_group_id` bigint(20) NOT NULL,
`name` varchar(50) NOT NULL,
`group_variant` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `product_group`
--



--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `group_variant`
--
ALTER TABLE `group_variant`
ADD PRIMARY KEY (`group_variant_id`);

--
-- Индексы таблицы `product`
--
ALTER TABLE `product`
ADD PRIMARY KEY (`product_id`),
ADD KEY `product_group` (`product_group`);

--
-- Индексы таблицы `product_group`
--
ALTER TABLE `product_group`
ADD PRIMARY KEY (`product_group_id`),
ADD KEY `group_varian` (`group_variant`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `group_variant`
--
ALTER TABLE `group_variant`
MODIFY `group_variant_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT для таблицы `product`
--
ALTER TABLE `product`
MODIFY `product_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT для таблицы `product_group`
--
ALTER TABLE `product_group`
MODIFY `product_group_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `product`
--
ALTER TABLE `product`
ADD CONSTRAINT `FK58i50ngmphh08djmvs3tiw4t4` FOREIGN KEY (`product_group`) REFERENCES `product_group` (`product_group_id`);

--
-- Ограничения внешнего ключа таблицы `product_group`
--
ALTER TABLE `product_group`
ADD CONSTRAINT `FKkncc46vkeu0gi17rb8q6nl2lp` FOREIGN KEY (`group_variant`) REFERENCES `group_variant` (`group_variant_id`);
COMMIT;

--INSERT INTO `product_group` (`product_group_id`, `name`, `group_variant`) VALUES
--(1, 'SmartPhone', 1);
--INSERT INTO `product` (`product_id`, `name`, `created`, `description`, `product_group`) VALUES
--(1, 'Phone', '2020-12-31', 'fff', 1);
--INSERT INTO `group_variant` (`group_variant_id`, `name`) VALUES
--(1, 'Technics');
