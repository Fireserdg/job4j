var path = window.location.pathname.replace("/index.html", "");

$(loadUsers());

function loadUsers() {
    $.ajax({
        type : "GET",
        url : path.concat("json"),
        dataType: "json",
        success : function (data) {
            $.each(data, function (key, value) {
                var akk = "";
                $.each(value, function (key, value) {
                    akk += '<td>' + value + '</td>';
                });
                $('#table tbody').append('<tr>' + akk + '</tr>');
            });
        }

    });
}

function createUser() {
    var firstName = $('#fname');
    var lastName = $('#lname');
    var desc = $('#desc');
    var radio = $('input[name=Sex]:checked');
    var arr = [firstName, lastName, desc];
    var result = validate(arr, radio);
    if (result) {
        var resp = {"firstName" : firstName.val(), "lastName" : lastName.val(),
            "sex" : radio.val(), "desc" : desc.val()};
        $.ajax({
            type : "POST",
            url : path.concat("json"),
            data: JSON.stringify(resp),
            dataType : "json",
            success: function (data) {
                alert("User was successfully added");
                var akk = "";
                $.each(data, function (key, value) {
                    akk += '<td>' + value + '</td>';
                });
                var tableBody = $('#table tbody');
                if (tableBody.html() === "") {
                    tableBody.append('<tr>' + akk + '</tr>');
                } else {
                    $('#table tbody tr:last').after('<tr>' + akk + '</tr>');
                }
            }
        });
        $('#fname, #lname, #desc').val("");
        $('input[name=Sex]').prop('checked', false);
    }
    return result;
}

function validate(arr, radio) {
    var result = true;
    $(arr).each(function () {
        if ($(this).val() === "") {
            alert($(this).attr("placeholder"));
            result = false;
            return result;
        }
    });
    if (result) {
        if (radio.length === 0) {
            alert("Please select sex");
            result = false;
        }
    }
    return result;
}