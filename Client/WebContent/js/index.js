var touch_sensitive = false;
var clickEventType;

function rotateReg(angle) {
    document.getElementById('registration').style['visibility'] = 'visible';

    document.getElementById('authorization').style['-webkit-transform'] = 'rotateY(' + angle + 'deg)';
    document.getElementById('registration').style['-webkit-transform'] = 'rotateY(' + (angle + 180) + 'deg)';

    document.getElementById('authorization').style['-moz-transform'] = 'rotateY(' + angle + 'deg)';
    document.getElementById('registration').style['-moz-transform'] = 'rotateY(' + (angle + 180) + 'deg)';

    document.getElementById('authorization').style['-o-transform'] = 'rotateY(' + angle + 'deg)';
    document.getElementById('registration').style['-o-transform'] = 'rotateY(' + (angle + 180) + 'deg)';

    document.getElementById('authorization').style['-ms-transform'] = 'rotateY(' + angle + 'deg)';
    document.getElementById('registration').style['-ms-transform'] = 'rotateY(' + (angle + 180) + 'deg)';

    document.getElementById('authorization').style['transform'] = 'rotateY(' + angle + 'deg)';
    document.getElementById('registration').style['transform'] = 'rotateY(' + (angle + 180) + 'deg)';
}

$(document).ready(function() {

    if ('ontouchstart' in document.documentElement) {
        touch_sensitive = true;
    }
    clickEventType = touch_sensitive ? 'touchstart' : 'click';

    var flag = false;

    $('#to_reg').bind(clickEventType, function() {
        if (flag) {
            return;
        }
        flag = true;
        setTimeout(function() {
            flag = false;
        }, 200);

        rotateReg(180);
    });

    $('#back').bind(clickEventType, function() {
        if (flag) {
            return;
        }
        flag = true;
        setTimeout(function() {
            flag = false;
        }, 200);

        rotateReg(0);
    });

    $('#enter').bind(clickEventType, function() {
        if (flag) {
            return;
        }
        flag = true;
        setTimeout(function() {
            flag = false;
        }, 200);

        enter($("#authorization_email").val(), $("#authorization_password").val());
    });

    $("#reg").bind(clickEventType, function() {
        if (flag) {
            return;
        }
        flag = true;
        setTimeout(function() {
            flag = false;
        }, 200);

        if ($("#registration_password").val() != $("#registration_confirm").val()) {
            $("#registration_confirm").css("background", "#cc0015");
            $("#registration_confirm").focus(function() {
                $("#registration_confirm").css("background", "#fff");
            });
            return;
        }
        registration($("#registration_email").val(), $("#registration_confirm").val());
    });

});

function enter(email, password) {
    if (email == undefined || email == null || email == "") {
        return;
    }
    if (password == undefined || password == null || password == "") {
        return;
    }
    $.getJSON("signin?email=" + email + "&password=" + password, function(data) {
        if (!data.success) {
            $("#authorization_email").css("background", "#cc0015");
            $("#authorization_password").css("background", "#cc0015");
            $("#authorization_password").focus(function() {
                $("#authorization_password").css("background", "#fff");
                $("#authorization_email").css("background", "#fff");
            });
            $("#authorization_email").focus(function() {
                $("#authorization_password").css("background", "#fff");
                $("#authorization_email").css("background", "#fff");
            });
        } else {
            window.location.href = "main-page.jsp"
        }
    });
    // console.log("addController " + id);
}

function registration(email, password) {
    if (email == undefined || email == null || email == "") {
        return;
    }
    if (password == undefined || password == null || password == "") {
        return;
    }
    $.getJSON("signup?email=" + email + "&password=" + password, function(data) {
        if (!data.success) {
            // TODO smth
        } else {
            window.location.href = "main-page.jsp"
        }
    });
    // console.log("addController " + id);
}