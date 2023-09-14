<!DOCTYPE html>
<html lang="en">
<head>
    <title>BUY & SELL</title>
</head>
<body>

<h2>Buy OR Sell</h2>
<p>Товары со всей России!</p>


<h3>${ product.title }</h3>

<p>Описание: ${product.descriptions}</p>
<p>Автор: ${product.author}</p>
<p>Город: ${product.city}</p>
<p>Цена: ${product.price}</p>

<form method="post" action="/product/${ product.id }/delete" >
    <button type="submit">Удалить</button>
</form>

</body>
</html>