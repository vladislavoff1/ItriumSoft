var timerController = setInterval(updateController, 5000);
var timerEvent = setInterval(updateEvent, 10000);

var touch_sensitive = false;
var clickEventType;

function updateController() {
    $.getJSON("api?method=GetControllers", function(data) {
        appendController(data);
    })
}

function updateEvent() {
    $.getJSON("api?method=GetEvents", function(data) {
        appendEvent(data);
    })
}

$(document).ready(function() {
    updateController();
    updateEvent();

    if ('ontouchstart' in document.documentElement) {
        touch_sensitive = true;
    }
    clickEventType = touch_sensitive ? 'touchend' : 'click';

    var flag = false;
    $("#add_window_button").bind(clickEventType, function() {
        if (flag) {
            return;
        }
        flag = true;
        setTimeout(function() {
            flag = false;
        }, 200);

        addController($("#add_window input").val());
    });
    $("#events_buttons_update").bind(clickEventType, function() {
        if (flag) {
            return;
        }
        flag = true;
        setTimeout(function() {
            flag = false;
        }, 200);

        updateEvent();
    });
    $("#events_buttons_update").bind(clickEventType, function() {
        if (flag) {
            return;
        }
        flag = true;
        setTimeout(function() {
            flag = false;
        }, 200);

        updateEvent();
    });
    $("#email_window_ok").bind(clickEventType, function() {
        if (flag) {
            return;
        }
        flag = true;
        setTimeout(function() {
            flag = false;
        }, 200);

        changeEmail($("#email_window_new_email").val(), $("#email_window_password").val());
    });
    $("#password_window_ok").bind(clickEventType, function() {
        if (flag) {
            return;
        }
        flag = true;
        setTimeout(function() {
            flag = false;
        }, 200);

        if ($("#password_window_new_password").val() != $("#password_window_confirm").val()) {
            $("#password_window_confirm").css("background", "#cc0015");
            $("#password_window_confirm").focus(function() {
                $("#password_window_confirm").css("background", "#fff");
            });
            return;
        }
        changePassword($("#password_window_new_password").val(), $("#password_window_password").val());
    });
    $("#remove_window_ok").bind(clickEventType, function() {
        if (flag) {
            return;
        }
        flag = true;
        setTimeout(function() {
            flag = false;
        }, 200);

        removeAccount($("#remove_window_password").val());
    });
    $("#clear_window_ok").bind(clickEventType, function() {
        if (flag) {
            return;
        }
        flag = true;
        setTimeout(function() {
            flag = false;
        }, 200);

        clearHystory();
        rotateUserClient(-1);
    });
    $("#logout_window_ok").bind(clickEventType, function() {
        if (flag) {
            return;
        }
        flag = true;
        setTimeout(function() {
            flag = false;
        }, 200);

        logout();
    });

})

function addController(id) {
    if (id == undefined || id == null || id == "") {
        return;
    }
    $.getJSON("api?method=AddController&key=" + id + "", function(data) {
        // console.log(data);
        updateController();
    });
    // console.log("addController " + id);
    add_window_open();
}

function removeController(id) {
    if (id == undefined || id == null || id == "") {
        return;
    }
    $.getJSON("api?method=RemoveController&key=" + id + "", function(data) {
        // console.log(data);
        updateController();
    });
    // console.log("addController " + id);
}

function setStatus(id, status) {
    if (id == undefined || id == null || id == "") {
        return;
    }
    if (status == undefined || status == null || status == "") {
        return;
    }
    $.getJSON("api?method=SetStatus&key=" + id + "&status=" + status + "", function(data) {
        // console.log(data);
        updateController();
    });
}


var eventBlock = '<div class="events_block"><div class="events_block_color bg_%color"></div><div class="events_block_text">%msg.</div><div class="events_block_time">%time</div></div>';

function appendEvent(data) {

    $("#events .scroll_container_inner").empty();

    for (var i = 0; i < data.length; i++) {
        var color = getStatusColor("NORMAL");
        // var id = data[i].key;
        var date = new Date(data[i].time);
        var time = addZero(date.getHours()) + ":" + addZero(date.getMinutes()) + ":" + addZero(date.getSeconds());
        // var guard = getGuard(data[i].status);
        // var status = getStatus(data[i].status);

        var msg = data[i].msg;

        var block = eventBlock.replace("%color", color).replace("%time", time).replace("%msg", msg);

        $("#events .scroll_container_inner").append(block);
    }
}


var controllerBlock = '<div class="controllers_block %id"><div class="controllers_block_color bg_%color"></div><div class="controllers_block_text">Контроллер: %id.<br>Выходил на связь в %time.<br>%guard.<br>Состояние %status.</div><div class="controllers_block_buttons_wrapper"><div class="controllers_block_off controllers_block_button"></div><div class="controllers_block_update controllers_block_button"></div><div class="controllers_block_bin controllers_block_button"></div></div></div>';

function appendController(data) {

    $("#controllers .scroll_container_inner").empty();

    for (var i = 0; i < data.length; i++) {
        var color = getStatusColor(data[i].status);
        var id = data[i].key;
        var date = new Date(data[i].time);
        var time = addZero(date.getHours()) + ":" + addZero(date.getMinutes()) + ":" + addZero(date.getSeconds());
        var guard = getGuard(data[i].status);
        var status = getStatus(data[i].status);
        var not_status = (data[i].status == "DISARM") ? "NORMAL" : "DISARM";

        var block = controllerBlock.replace("%color", color).replace(/%id/g, id).replace("%time", time).replace("%guard", guard).replace("%status", status);

        $("#controllers .scroll_container_inner").append(block);

        var func = function(key) {
            return function() {
                if (flag) {
                    return;
                }
                flag = true;
                setTimeout(function() {
                    flag = false;
                }, 200);

                // console.log("bin");
                removeController(key);
            };
        };

        var on_off = function(key, status) {
            return function() {
                if (flag) {
                    return;
                }
                flag = true;
                setTimeout(function() {
                    flag = false;
                }, 200);

                // console.log(key);
                setStatus(key, status);
            };
        };

        var flag = false;
        $("." + id + " .controllers_block_bin").bind(clickEventType, func(id));

        $("." + id + " .controllers_block_update").bind(clickEventType, function() {
            if (flag) {
                return;
            }
            flag = true;
            setTimeout(function() {
                flag = false;
            }, 200);
            // console.log("update");
            updateEvent();
        });

        $("." + id + " .controllers_block_off").bind(clickEventType, on_off(id, not_status));
    }
}

function addZero(i) {
    if (i < 10) {
        i = "0" + i;
    }
    return i;
}

var status_to_color = {
    NORMAL: "green",
    DISARM: "white",
    BREAK: "red",
    ALARM: "red",
    PART: "yellow"
}

function getStatusColor(status) {
    return status_to_color[status.toUpperCase()];
}

function getGuard(status) {
    switch (status.toUpperCase()) {
        case "NORMAL":
            return "Находится под охраной";
        case "DISARM":
            return "Не под охраной";
        case "BREAK":
            return "Частично под охраной";
        case "ALARM":
            return "Находится под охраной";
        case "PART":
            return "Частично под охраной";
    }
}

var status_to_text = {
    NORMAL: "нормальное",
    DISARM: "нормальное",
    BREAK: "тревожное",
    ALARM: "тревожное",
    PART: "нормальное"
}

function getStatus(status) {
    return status_to_text[status.toUpperCase()];
}

function parseRequest(str) {
    var res = [];
    var preRes = str.split("&");
    for (var i = 0; i < preRes.length; i++) {
        temp = preRes[i].split("=");
        res[temp[0]] = temp[1];
    }
    return res;
}

function changeEmail(newEmail, password) {
    if (newEmail == undefined || newEmail == null || newEmail == "") {
        return;
    }
    $.getJSON("api?method=ChangeEmail&new_email=" + newEmail + "&password=" + password, function(data) {
        // console.log(data);
        if (!data.success) {
            $("#email_window_password").css("background", "#cc0015");
            $("#email_window_password").focus(function() {
                $("#email_window_password").css("background", "#fff");
            });
        } else {
            rotateUserAcc(-1);
        }
    });
    // console.log("addController " + id);
}

function changePassword(newPassword, password) {
    if (newPassword == undefined || newPassword == null || newPassword == "") {
        return;
    }
    $.getJSON("api?method=ChangePassword&new_password=" + newPassword + "&password=" + password, function(data) {
        // console.log(data);
        if (!data.success) {
            $("#password_window_password").css("background", "#cc0015");
            $("#password_window_password").focus(function() {
                $("#password_window_password").css("background", "#fff");
            });
        } else {
            rotateUserAcc(-1);
        }
    });
    // console.log("addController " + id);
}

function removeAccount(password) {
    $.getJSON("api?method=RemoveAccount&password=" + password, function(data) {
        console.log(data);
        if (!data.success) {
            $("#remove_window_password").css("background", "#cc0015");
            $("#remove_window_password").focus(function() {
                $("#remove_window_password").css("background", "#fff");
            });
        } else {
            logout();
        }
    });
    // console.log("addController " + id);
}

function clearHystory() {
    $.getJSON("api?method=ClearHystory", function(data) {
        appendEvent();
        // console.log(data);
    });
    // console.log("addController " + id);
}

function logout() {
    window.location.href = "LogOut";
}
