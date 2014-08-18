<%
String email = (String) session.getAttribute("email");
String password = (String) session.getAttribute("password");

if (email == null || password == null) {
    response.sendRedirect("");
}
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <link rel="stylesheet" type="text/css" href="./css/main-page.css">
    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
    <script src="./js/main-page.js"></script>
    <script src="./js/main-page-json.js"></script>
    <script src="./js/prefixfree.min.js"></script>
    <script src="./js/viewport-units-buggyfill.js"></script>
    <script>
    window.viewportUnitsBuggyfill.init();
    </script>
    <title>Main page</title>
</head>

<body>
    <div class="outer">
        <div class="middle">
            <div id="site">
                <div id="menu_wrapper">
                    <div id="menu">
                        <div id="home-btn" class="menu_btn"></div>
                        <div id="map-btn" class="menu_btn"></div>
                        <div id="settings-btn" class="menu_btn"></div>
                    </div>
                </div>
                <div id="page">
                    <div id="home">
                        <div id="controllers_wrapper" class="wrapper">
                            <div id="controllers_title" class="title">Контроллеры
                                <div class="title_color bg_green"></div>
                            </div>
                            <div id="controllers" class="container">
                                <div id="controllers_buttons_wrapper" class="main_button_wrapper">
                                    <div id="controllers_buttons_add" class="controllers_button">добавить</div>
                                    <!-- <div id="controllers_buttons_more" class="controllers_button">подробнее</div> -->
                                </div>
                                <div id="add_window">
                                    <input type="text" name="controller_key" placeholder="ID контроллера:">
                                    <div id="cancel_window_button" class="window_button">Отмена</div>
                                    <div id="add_window_button" class="window_button">Добавить</div>
                                </div>
                                <div class="scroll_container_wrapper">
                                    <div class="scroll_container">
                                        <div class="scroll_container_inner">
                                            <!-- <div class="controllers_block">
                                                <div class="controllers_block_color bg_green"></div>
                                                <div class="controllers_block_text">Контроллер: DC453AF0.
                                                    <br>Выходил на связь 35 секунд назад.
                                                    <br>Находится под охраной.
                                                    <br>Состояние нормальное.
                                                </div>
                                                <div class="controllers_block_buttons_wrapper">
                                                    <div class="controllers_block_off controllers_block_button"></div>
                                                    <div class="controllers_block_update controllers_block_button"></div>
                                                    <div class="controllers_block_bin controllers_block_button"></div>
                                                </div>
                                            </div>
                                            <div class="controllers_block">
                                                <div class="controllers_block_color bg_red"></div>
                                                <div class="controllers_block_text">Контроллер: BAF451AA.
                                                    <br>Выходил на связь 9 секунд назад.
                                                    <br>Находится под охраной.
                                                    <br>Состояние тревожное.
                                                </div>
                                                <div class="controllers_block_buttons_wrapper">
                                                    <div class="controllers_block_off controllers_block_button"></div>
                                                    <div class="controllers_block_update controllers_block_button"></div>
                                                    <div class="controllers_block_bin controllers_block_button"></div>
                                                </div>
                                            </div>
                                            <div class="controllers_block">
                                                <div class="controllers_block_color bg_yellow"></div>
                                                <div class="controllers_block_text">Контроллер: F5C1DE92.
                                                    <br>Выходил на связь 13 секунд назад.
                                                    <br>Частично под охраной.
                                                    <br>Состояние нормальное.
                                                </div>
                                                <div class="controllers_block_buttons_wrapper">
                                                    <div class="controllers_block_off controllers_block_button"></div>
                                                    <div class="controllers_block_update controllers_block_button"></div>
                                                    <div class="controllers_block_bin controllers_block_button"></div>
                                                </div>
                                            </div>
                                            <div class="controllers_block">
                                                <div class="controllers_block_color bg_white"></div>
                                                <div class="controllers_block_text">Контроллер: 7D195A2C.
                                                    <br>Выходил на связь 49 секунд назад.
                                                    <br>Не под охраной.
                                                    <br>Состояние нормальное.
                                                </div>
                                                <div class="controllers_block_buttons_wrapper">
                                                    <div class="controllers_block_off controllers_block_button"></div>
                                                    <div class="controllers_block_update controllers_block_button"></div>
                                                    <div class="controllers_block_bin controllers_block_button"></div>
                                                </div>
                                            </div> -->
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="events_wrapper" class="wrapper">
                            <div id="events_title" class="title">История
                                <div class="title_color bg_white"></div>
                            </div>
                            <div id="events" class="container">
                                <div id="events_buttons_wrapper" class="main_button_wrapper">
                                    <div id="events_buttons_update" class="events_button">обновить</div>
                                </div>
                                <div class="scroll_container_wrapper">
                                    <div class="scroll_container">
                                        <div class="scroll_container_inner">
                                            <!-- <div class="events_block">
                                                <div class="events_block_color bg_red"></div>
                                                <div class="events_block_text">Сработал датчик движения контроллера
                                                    <br>BAF451AA.</div>
                                                <div class="events_block_time">только что</div>
                                            </div>
                                            <div class="events_block">
                                                <div class="events_block_color bg_green"></div>
                                                <div class="events_block_text">Контроллер DC453AF0 перешел в режим
                                                    <br>охраны.</div>
                                                <div class="events_block_time">минуту назад</div>
                                            </div>
                                            <div class="events_block">
                                                <div class="events_block_color bg_green"></div>
                                                <div class="events_block_text">Контроллер BAF451AA перешел в режим
                                                    <br>охраны.</div>
                                                <div class="events_block_time">две минуты назад</div>
                                            </div>
                                            <div class="events_block">
                                                <div class="events_block_color bg_yellow"></div>
                                                <div class="events_block_text">Отключена камера контроллера
                                                    <br>F5C1DE92.</div>
                                                <div class="events_block_time">12:24</div>
                                            </div>
                                            <div class="events_block">
                                                <div class="events_block_color bg_white"></div>
                                                <div class="events_block_text">Контроллер 7D195A2C снят с режима
                                                    <br>охраны.</div>
                                                <div class="events_block_time">12:18</div>
                                            </div>
                                            <div class="events_block">
                                                <div class="events_block_color bg_green"></div>
                                                <div class="events_block_text">Контроллер F5C1DE92 перешел в режим
                                                    <br>охраны.</div>
                                                <div class="events_block_time">21:23 вчера</div>
                                            </div>
                                            <div class="events_block">
                                                <div class="events_block_color bg_white"></div>
                                                <div class="events_block_text">Контроллер BAF451AA снят с режима
                                                    <br>охраны.</div>
                                                <div class="events_block_time">21:19 вчера</div>
                                            </div> -->
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="map">
                        <div id="map_text">Данный раздел еще в разработке.</div>
                    </div>
                    <div id="settings">
                        <div id="settings_wrapper" class="wrapper">
                            <div id="settings_title" class="title">Веб-приложение
                                <div class="title_color bg_yellow"></div>
                            </div>
                            <div id="site_settings" class="container">
                                <div class="scroll_container_wrapper">
                                    <div class="scroll_container">
                                        <div class="scroll_container_inner">
                                            <div class="site_settings_block">
                                                <div class="site_settings_title">
                                                    <div id="settings_animation_checkbox" class="checkbox">
                                                        <div class="switch"></div>
                                                    </div>
                                                    <div class="site_settings_title_text">Анимация</div>
                                                </div>
                                                <div class="description">Ты можешь отключить всю анимацию на сайте, если
                                                    <br>она влияет на производительность</div>
                                            </div>

                                            <div class="site_settings_block">
                                                <div class="site_settings_title">
                                                    <div id="settings_sound_checkbox" class="checkbox">
                                                        <div class="switch"></div>
                                                    </div>
                                                    <div class="site_settings_title_text">Звуковые уведомления</div>
                                                </div>
                                                <div class="description">Звуковые уведомления возникают в тот момент, когда
                                                    <br>в ленте истории появляются новые события.</div>
                                            </div>

                                            <div class="site_settings_block">
                                                <div class="site_settings_title">
                                                    <div id="settings_authorization_checkbox" class="checkbox">
                                                        <div class="switch"></div>
                                                    </div>
                                                    <div class="site_settings_title_text">Автоматическая авторизация</div>
                                                </div>
                                                <div class="description">Мы можем сохранить твои данные для авторизации в
                                                    <br>браузере. Это позволит тебе заходить на сайт не вводя
                                                    <br>свои email и пароль.</div>
                                            </div>

                                            <div class="site_settings_block">
                                                <div class="site_settings_title">
                                                    <div id="settings_notification_checkbox" class="checkbox">
                                                        <div class="switch"></div>
                                                    </div>
                                                    <div class="site_settings_title_text">Присылать все события на email</div>
                                                </div>
                                                <div class="description">Обычно на электронную почту присылаются только
                                                    <br>тревожные события.</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="user_wrapper" class="wrapper">
                            <div id="user_title" class="title">Учетная запись
                                <div class="title_color bg_red"></div>
                            </div>

                            <div id="user_account" class="user_account container">
                                <div id="email" class="user_button">Изменить email</div>
                                <div id="password" class="user_button">Изменить пароль</div>
                                <div id="remove" class="user_button">Удалить учетную запись</div>
                            </div>
                            <div id="email_window" class="user_account container">
                                <div class="user_account_window_title">Изменить email</div>
                                <input id="email_window_new_email" type="text" name="new_email" placeholder="Новый email:">
                                <input id="email_window_password" type="password" name="password" placeholder="Текущий пароль:">
                                <div id="email_window_cancel" class="user_windwow_button_cancel">Назад</div>
                                <div id="email_window_ok" class="user_windwow_button_ok">Готово</div>
                            </div>
                            <div id="password_window" class="user_account container">
                                <div class="user_account_window_title">Изменить пароль</div>
                                <input id="password_window_new_password" type="password" name="new_password" placeholder="Новый пароль:">
                                <input id="password_window_confirm" type="password" name="new_passford_confirm" placeholder="Еще раз:">
                                <input id="password_window_password" type="password" name="password" placeholder="Текущий пароль:">
                                <div id="password_window_cancel" class="user_windwow_button_cancel">Назад</div>
                                <div id="password_window_ok" class="user_windwow_button_ok">Готово</div>
                            </div>
                            <div id="remove_window" class="user_account container">
                                <div class="user_account_window_title">Удалить учетную запись</div>
                                <div class="user_account_window_description">Ты сможешь создать новую учетную запись, но получить доступ к этой у тебя не получится.</div>
                                <input id="remove_window_password" type="password" name="passwond" placeholder="Пароль:">
                                <div id="remove_window_cancel" class="user_windwow_button_cancel">Назад</div>
                                <div id="remove_window_ok" class="user_windwow_button_ok">Удалить</div>
                            </div>

                            <div id="user_client" class="user_client container">
                                <div id="clear" class="user_button">Очистить историю</div>
                                <div id="logout" class="user_button">Выйти</div>
                            </div>
                            <div id="clear_window" class="user_client container">
                                <div class="user_account_window_title">Очистить историю</div>
                                <div class="user_account_window_description">Восстановить историю после удаления нельзя.</div>
                                <div id="clear_window_cancel" class="user_windwow_button_cancel">Назад</div>
                                <div id="clear_window_ok" class="user_windwow_button_ok">Очистить</div>
                            </div>
                            <div id="logout_window" class="user_client container">
                                <div class="user_account_window_title">Выйти</div>
                                <div class="user_account_window_description">Для входа на сайт тебе придется авторизовываться.</div>
                                <div id="logout_window_cancel" class="user_windwow_button_cancel">Назад</div>
                                <div id="logout_window_ok" class="user_windwow_button_ok">Выйти</div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <div id="copyright">Designed by <b>Vladislav Romanov</b>
            </div>
        </div>
    </div>
</body>

</html>
