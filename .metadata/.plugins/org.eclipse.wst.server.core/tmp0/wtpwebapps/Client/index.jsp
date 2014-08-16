<%
String email = (String) session.getAttribute("email");
String password = (String) session.getAttribute("password");

if (email != null && password != null) {
    response.sendRedirect("main-page.jsp");
}
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Borey</title>
    <link rel="stylesheet" type="text/css" href="./css/index.css">
    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
    <script src="./js/index.js"></script>
    <script src="./js/prefixfree.min.js"></script>
</head>

<body>
    <div class="outer">
        <div class="middle">
            <div id="site">
                <div id="header">
                    <div id="logo">
                        <h1>
                            <span id="itrium">itrium</span>.borey</h1>
                        <span id="slogan">инновации со смыслом</span>
                    </div>
                    <div id="authorization">
                        <div class="title">Авторизация</div>
                        <form autocomplete="off">
                            <input style="display:none">
                            <input type="password" style="display:none">
                            <input id="authorization_email" class="inp" type="email" name="email" placeholder="Email: " autocomplete="off">
                            <input id="authorization_password" class="inp" type="password" name="password" placeholder="Password: " autocomplete="off">
                            <div id="buttons">
                                <div id="enter" class="btn">Войти</div>
                                <div id="to_reg" class="btn">Регистрация</div>
                            </div>
                        </form>
                        <!-- <div id="forget">
                            <a href="">Забыл пароль</a>
                        </div> -->
                    </div>
                    <div id="registration">
                        <div class="title">Регистрация</div>
                        <form>
                            <input style="display:none">
                            <input type="password" style="display:none">
                            <input id="registration_email" class="inp" type="email" placeholder="Email: ">
                            <input id="registration_password" class="inp" type="password" placeholder="Password: ">
                            <input id="registration_confirm" class="inp" type="password" placeholder="Confirm: ">
                            <div id="buttons">
                                <div id="back" class="btn">Назад</div>
                                <div id="reg" class="btn">Зарегистрироваться</div>
                            </div>
                        </form>
                    </div>
                </div>
                <div id="content">
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute ex irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla ea pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet, consectetur qui adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate in velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat nisi amet cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est mollit laborum veniam.
                </div>
                <div id="about_contacts">
                    <div id="about">
                        <div class="title">О нас</div>
                        <div class="simple_text">
                            <span class="important_text">Itrium</span>- компания, создающая программное обеспечение интегрированных систем безопасности. Программные средства автоматизации бизнес-процессов объекта и видеонаблюдения.
                        </div>

                    </div>
                    <div id="contacts">
                        <div class="title">Контакты</div>
                        <table>
                            <tr>
                                <td class="important_text">Телефон и факс:</td>
                                <td class="simple_text">+7 (812) 960-06-13</td>
                            </tr>
                            <tr>
                                <td class="important_text">Email:</td>
                                <td class="simple_text">interop@itrium.ru</td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
            <div id="copyright">Designed by <b>Vladislav Romanov</b>
            </div>
        </div>
    </div>
</body>

</html>
