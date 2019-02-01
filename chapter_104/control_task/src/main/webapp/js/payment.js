var path = window.location.pathname.replace("pages/payment.html", "");

$(function getParamForByTicket() {
    $.ajax({
        type : 'POST',
        url :  path.concat("hall"),
        data : {action : "getHalls"},
        dataType : "json",
        success : function (data) {
            if (data.booked) {
                alert("Билет уже купили");
                window.location.href = path.concat("index.html");
            } else {
                $('.pt-3').append(
                    "<h3 id='"+ data.id +"'>" + "Вы выбрали ряд " + data.row + " место "
                    + data.seat +", Сумма : " + data.price + " рублей." + "</h3>")
            }
        }
    });
});

function validate() {
    var result = false;
    var id = $('h3').attr('id');
    var username = $('#username').val();
    var phone = $('#phone').val();
    if (!checkUserName(username)) {
        result = "Пожалуйста введите имя в соответствии с примером";
    } else if (!checkPhone(phone)) {
        result = "Пожалуйста введите номер телефон в соответствии с примером";
    }
    if (result) {
        alert(result);
    } else {
        username = username.trim().split(/\s+/).join(" ");
        var param = {"id" : id, "username" : username, "phone" : phone};
        $.ajax({
            type : 'POST',
            url :  path.concat("hall"),
            data : JSON.stringify(param),
            contentType : "application/json",
            dataType : "text",
            success : function (data) {
                alert(data);
                window.location.href = path.concat("index.html");
            }
        });
    }
}
function checkUserName(userName) {
    var check = true;
    var regExp = /^[-A-Za-zА-Яа-я]+$/;

    var arr = userName.trim().split(/\s+/);
    for (var index in arr) {
        if (!regExp.test(arr[index])) {
            check = false;
            break;
        }
    }
    return check && arr.length === 3;
}

function checkPhone(phone) {
    var value = phone.trim();
    var regExp = /^\+\d\([0-9]{3,6}\)[0-9]{5,7}$/;
    return regExp.test(value);
}