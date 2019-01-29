/**
 * JavaScript code for check login users
 */
var path = window.location.pathname;
function check() {
    var result = false;
    var page = path.substring(0, path.lastIndexOf("/"));
    var login = $('#login').val();
    var password = $('#password').val();
    var regExp = /^[\s]{1,}/;
    if (login === "" || regExp.test(login)) {
        result = "You didn't input login";
    } else if (password === "") {
        result = "You didn't input password";
    } else if (regExp.test(password)) {
        result = "The password must start with a symbol";
    }
    if (result) {
        alert(result);
    } else {
        $.ajax({
            type : "POST",
            url : page.concat("/signing"),
            data: {login : login, password : password},
            dataType : "text",
            success: function(data){
                if (data.match(/Success/)) {
                    window.location.href = page.concat("/main");
                } else {
                    alert(data);
                }
            }
        });
    }
}