<!DOCTYPE html>
<html lang="en">
<head>
    <title>BUYSELL</title>
</head>
<body>

<h2>Buy OR Sell</h2>
<p>товары со всей России!</p>

<form action="/" method="get">
    Поиск по названию обьявы: <input type="text" name="title"> <br>
    <input type="submit" value="Поиск">
</form>

<#list products as product>
    <ul>
        <li>${product.getTitle()}; Цена <b>${ product.price} </b> руб <a href="/product/${ product.id }">=></a></li>
    </ul>
<#else>
    <b>Товаров нет!</b>
</#list>


<#--
    private String title;
    private String descriptions;
    private double price;
    private String city;
    private String author;
-->
<h4>Добавить новый товара</h4>
<form action="/product/create" method="post">
    <label>
        <input type="text" name="title" placeholder="Наименование">
    </label>
    <label>
        Описание
        <textarea name="descriptions"></textarea>
    </label>
    <label>
        <input type="number" name="price" placeholder="Цена руб">
    </label>
    <label>
        <input type="text" name="city" placeholder="Город">
    </label>
    <label>
        <input type="text" name="author" placeholder="Автор">
    </label>

    <button type="submit">Создать</button>
</form>

</body>
</html>