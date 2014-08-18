var touch_sensitive = false;
var clickEventType;

var current_menu = 0;

var menu = [{
    name: "#home",
    button: "#home-btn",
    on: [],
    off: []
}, {
    name: "#map",
    button: "#map-btn",
    on: [{
        name: "#site",
        style: "overflow",
        value: "hidden"
    }],
    off: [{
        name: "#site",
        style: "overflow",
        value: "visible"
    }]
}, {
    name: "#settings",
    button: "#settings-btn",
    on: [],
    off: []
}]

var openMenu = function(name) {
    var id = null;
    for (var i = 0; i < menu.length; i++) {
        if (menu[i].name == name) {
            id = i;
            break;
        }
    }

    if (id == null) {
        return;
    }

    $(menu[current_menu].name).css('display', 'none');

    for (var i = 0; i < menu[current_menu].off.length; i++) {
        var off = menu[current_menu].off[i];
        $(off.name).css(off.style, off.value);
    }

    $(menu[id].name).css('display', 'block');

    for (var i = 0; i < menu[id].on.length; i++) {
        var on = menu[id].on[i];
        $(on.name).css(on.style, on.value);
    }

    $(menu[current_menu].button).css('background', 'transparent');
    $(menu[id].button).css('background', 'rgba(0,0,0,0.7)');

    current_menu = id;
}

var setMenuHover = function(i) {
    $(menu[i].button).hover(function() {
        if (i == current_menu) {
            $(menu[i].button).css("background", "rgba(0, 0, 0, 0.9)");
        } else {
            $(menu[i].button).css("background", "rgba(0, 0, 0, 0.2)");
        }
    }, function() {
        if (i == current_menu) {
            $(menu[i].button).css("background", "rgba(0, 0, 0, 0.7)");
        } else {
            $(menu[i].button).css("background", "transparent");
        }
    });
}

var setOpenMenu = function(i) {
    var flag = false;
    $(menu[i].button).bind(clickEventType, function() {
        if (flag) {
            return;
        }
        flag = true;
        setTimeout(function() {
            flag = false;
        }, 200);

        openMenu(menu[i].name);
    });
}

/* checkbox */
var checkboxes = ["#settings_animation_checkbox", "#settings_sound_checkbox", "#settings_authorization_checkbox", "#settings_notification_checkbox"];
var checkboxes_checked = [true, true, false, false];

var checkbox_style = [{
    media: "",
    on: [{
        style: "background-color",
        value: "#099240"
    }],
    off: [{
        style: "background-color",
        value: "#d2d2d2"
    }],
    hover_on: [{
        style: "background-color",
        value: "#09b240"
    }],
    hover_off: [{
        style: "background-color",
        value: "#aaa"
    }]
}]

var checkbox_switch_style = [{
        media: "",
        on: [{
            style: "border-color",
            value: "#099240"
        }, {
            style: "left",
            value: "0"
        }],
        off: [{
            style: "border-color",
            value: "#aaa"
        }, {
            style: "left",
            value: "20px"
        }],
        hover_on: [{
            style: "border-color",
            value: "#09b240"
        }],
        hover_off: [{
            style: "border-color",
            value: "#aaa"
        }]
    }, {
        media: "screen and (orientation: portrait)",
        on: [{
            style: "left",
            value: "0"
        }],
        off: [{
            style: "left",
            value: "4vw"
        }],
        hover_on: [],
        hover_off: []
    }

]

var setCheckboxHover = function(id) {
    $(checkboxes[id]).hover(checkbox_apply_style("hover_", id), checkbox_apply_style("", id));
}

var checkbox_apply_style = function(before, id) {
    return function() {

        var style = "off";
        if (checkboxes_checked[id]) {
            style = "on";
        }

        for (var j = 0; j < checkbox_style.length; j++) {
            if (checkbox_style[j].media != "") {
                var mq = window.matchMedia(checkbox_style[j].media);
                if (!mq.matches) {
                    continue;
                }
            }
            for (var i = 0; i < checkbox_style[j][before + style].length; i++) {
                $(checkboxes[id]).css(checkbox_style[j][before + style][i].style, checkbox_style[j][before + style][i].value);
            }
        }

        for (var j = 0; j < checkbox_switch_style.length; j++) {
            if (checkbox_switch_style[j].media != "") {
                var mq = window.matchMedia(checkbox_switch_style[j].media);
                if (!mq.matches) {
                    continue;
                }
            }
            for (var i = 0; i < checkbox_switch_style[j][before + style].length; i++) {
                $(checkboxes[id]).children().css(checkbox_switch_style[j][before + style][i].style, checkbox_switch_style[j][before + style][i].value);
            }
        }
    }
}

var setCheckboxClicable = function(i) {
    var flag = false;
    $(checkboxes[i]).bind(clickEventType, function() {
        if (flag) {
            return;
        }
        flag = true;
        setTimeout(function() {
            flag = false;
        }, 200);

        checkboxes_checked[i] = !checkboxes_checked[i];
        checkboxApplyStyle(i);
    });
}

var checkboxApplyStyle = function(id) {
    checkbox_apply_style("", id)();
}

var add_window_open = function() {

    add_window = !add_window;

    var height = add_window ? 90 : 0;
    var margin = add_window ? 5 : 0;

    var mq = window.matchMedia("screen and (orientation: portrait)");
    if (!mq.matches) {
        $("#add_window").css("height", height + 'px');
        $("#add_window").css("margin-bottom", margin + 'px');
        $(".controllers_block").css("margin-top", (5 - margin) + 'px');
        $("#controllers .scroll_container_wrapper").css("height", (440 - height - margin) + 'px');
        $("#controllers .scroll_container").css("height", (440 - height - margin) + 'px');
        $("#controllers .scroll_container_inner").css("min-height", (431 - height - margin) + 'px');
    } else {
        $("#add_window").css("height", height / 5 + 'vw');
    }
}

var user_acc_main = "#user_account";
var user_acc_windows = ["#email_window", "#password_window", "#remove_window"];

var user_client_main = "#user_client";
var user_client_windows = ["#clear_window", "#logout_window"];

function rotateUserAcc(id) {

    if (id == -1) {
        $(user_acc_main).css('-webkit-transform', 'rotateY(0)');
    } else {
        $(user_acc_main).css('-webkit-transform', 'rotateY(180deg)');
    }

    for (var i = 0; i < user_acc_windows.length; i++) {
        $(user_acc_windows[i]).css('visibility', 'visible');
        if (i == id) {
            $(user_acc_windows[i]).css('-webkit-transform', 'rotateY(360deg)');
        } else {
            $(user_acc_windows[i]).css('-webkit-transform', 'rotateY(180deg)');
        }
    }
}

function rotateUserClient(id) {

    if (id == -1) {
        $(user_client_main).css('-webkit-transform', 'rotateY(0)');
    } else {
        $(user_client_main).css('-webkit-transform', 'rotateY(180deg)');
    }

    for (var i = 0; i < user_client_windows.length; i++) {
        $(user_client_windows[i]).css('visibility', 'visible');
        if (i == id) {
            $(user_client_windows[i]).css('-webkit-transform', 'rotateY(360deg)');
        } else {
            $(user_client_windows[i]).css('-webkit-transform', 'rotateY(180deg)');
        }
    }
}


var user_acc_current = 0;
var user_client_current = 0;
var add_window = false;

$(document).ready(function() {

    if ('ontouchstart' in document.documentElement) {
        touch_sensitive = true;
    }
    clickEventType = touch_sensitive ? 'touchend' : 'click';

    /* Main menu */

    for (var i = 0; i < menu.length; i++) {
        setOpenMenu(i);

        if (!touch_sensitive) {
            setMenuHover(i);
        }
    }

    /* checkboxes */

    for (var i = 0; i < checkboxes.length; i++) {
        setCheckboxClicable(i);
        checkboxApplyStyle(i);

        if (!touch_sensitive) {
            setCheckboxHover(i);
        }
    }

    /* add window */
    var flag;
    $("#controllers_buttons_add").bind(clickEventType, function() {
        if (flag) {
            return;
        }
        flag = true;
        setTimeout(function() {
            flag = false;
        }, 200);

        add_window_open();
    });

    $("#cancel_window_button").bind(clickEventType, function() {
        if (flag) {
            return;
        }
        flag = true;
        setTimeout(function() {
            flag = false;
        }, 200);

        add_window_open();
    });

    /* user_acc_buttons */
    $("#email").bind(clickEventType, function() {
        if (flag) {
            return;
        }
        flag = true;
        setTimeout(function() {
            flag = false;
        }, 200);

        rotateUserAcc(0);
    });

    $("#password").bind(clickEventType, function() {
        if (flag) {
            return;
        }
        flag = true;
        setTimeout(function() {
            flag = false;
        }, 200);

        rotateUserAcc(1);
    });

    $("#remove").bind(clickEventType, function() {
        if (flag) {
            return;
        }
        flag = true;
        setTimeout(function() {
            flag = false;
        }, 200);

        rotateUserAcc(2);
    });

    $("#clear").bind(clickEventType, function() {
        if (flag) {
            return;
        }
        flag = true;
        setTimeout(function() {
            flag = false;
        }, 200);

        rotateUserClient(0);
    });

    $("#logout").bind(clickEventType, function() {
        if (flag) {
            return;
        }
        flag = true;
        setTimeout(function() {
            flag = false;
        }, 200);

        rotateUserClient(1);
    });

    $(".user_account .user_windwow_button_cancel").bind(clickEventType, function() {
        if (flag) {
            return;
        }
        flag = true;
        setTimeout(function() {
            flag = false;
        }, 200);

        rotateUserAcc(-1);
    });

    $(".user_client .user_windwow_button_cancel").bind(clickEventType, function() {
        if (flag) {
            return;
        }
        flag = true;
        setTimeout(function() {
            flag = false;
        }, 200);

        rotateUserClient(-1);
    });

});

$(window).resize(function() {
    for (var i = 0; i < checkboxes.length; i++) {
        checkboxApplyStyle(i);
    }
});
