<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>LABA 6</title>
    </head>
    <body>
        <div>
        <h3>Введите строку и вам её перевернут:</h3>
        <form action="invert" method="GET">
            <p><input title="String" type="text" name="string" size="25"></p>
            <br/>
            <input type="submit" value="Submit">
            <input type="reset" value="Reset">
        </form>
       ${context1}
        </div>
        <div>
        <h3>Введите слово и его разобьют по слогам:</h3>
        <form action="split" method="GET">
            <p><input title="Word" type="text" name="word" size="25"></p>
            <br/>
            <input type="submit" value="Submit">
            <input type="reset" value="Reset">
        </form>
       ${context2}
        </div>
    </body>
</html>