<%--JavaScript code for create User--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<script>
    function createUser() {
        var result = false;
        var id = $('#userId').val();
        var name = $('#name').val();
        var login = $('#login').val();
        var password = $('#password').val();
        var email = $('#email').val();
        var role = $('#role').val();
        var action = $('#action').val();
        var country = $("select[name=country] option:selected").val();
        var city = $("select[name=city] option:selected").val();
        if (!checkValue(name)) {
            result = "The name must start with a letter";
        } else if (!checkValue(login)) {
            result = "The login must start with a letter";
        } else if (!checkPassword(password)) {
            result = "The password must contains 8 or more symbols or numbers";
        } else if (!checkEmail(email)) {
            result = "Please enter correctly email";
        }

        if (result) {
            alert(result);
        } else {
            var user = new User(action, id, name, login, password, email, role, country, city);
            if (!updateUser(user)) {
                result = false;
                return result;
            } else {
                window.location.href = "${pageContext.servletContext.contextPath}".concat("/action/main");
            }
        }
    }

    function updateUser(user) {
        var result = true;
        $.ajax({
            async : false,
            type : "POST",
            url :  "${pageContext.servletContext.contextPath}".concat("/action/main"),
            data: JSON.stringify(user),
            dataType : "text",
            success: function(data){
                if (data.match(/success/)) {
                    alert(data);
                } else {
                    alert(data);
                    result = false;
                }
            }
        });
        return result;
    }

    function checkEmail(email) {
        var regEx = /^[0-9A-Za-zА-Яа-я]{1}[-0-9A-Za-zА-Яа-я\\.]{1,}@[-0-9A-Za-zА-Яа-я\\.]{1,}.{1}[A-Za-zА-Яа-я]{2,}$/;
        return regEx.test(email);
    }

    function checkValue(value) {
        var regEx = /^[A-Za-zА-Яа-я]+.*/;
        return regEx.test(value);
    }

    function checkPassword(pass) {
        var regEx = /^[A-Za-zА-Я0-9]{8,}$/;
        return regEx.test(pass);
    }

    function User(action, id, name, login, password, email, role, country, city) {
        this.action = action;
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.country = country;
        this.city = city;
    }

    $(loadCountry());
    function loadCountry() {
        $.ajax({
            type: "GET",
            url: "${pageContext.servletContext.contextPath}".concat("/action/info"),
            dataType: "json",
            success : function (data) {
                console.log(data);
                for(var id in data) {
                    if (data[id] === "${user.country}") {
                        $("select[name=country]").append('<option id=' + id + ' selected="selected">' + data[id] + '</option>');
                        getCity();
                    } else {
                        $("select[name=country]").append('<option id=' + id + '>' + data[id] + '</option>')
                    }
                }
            }
        });
    }
    function getCity() {
        var arg = $("select[name=country] option:selected").attr("id");
        $("select[name=city]").empty();
        $.ajax({
            type: "POST",
            url: "${pageContext.servletContext.contextPath}".concat("/action/info"),
            data : {country : arg},
            dataType: "json",
            success : function (data) {
                for(var id in data) {
                    if (data[id] === "${user.city}") {
                        $("select[name=city]").append('<option id=' + id + ' selected="selected">' + data[id] + '</option>');
                    } else {
                        $("select[name=city]").append('<option id=' + id + '>' + data[id] + '</option>')
                    }
                }
            }
        });
    }
</script>
