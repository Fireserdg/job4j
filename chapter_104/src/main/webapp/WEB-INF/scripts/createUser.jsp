<%--JavaScript code for Update User--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<script>
    function createUser() {
        var result = false;
        var name = $('#name').val();
        var login = $('#login').val();
        var password = $('#password').val();
        var email = $('#email').val();
        var role = $('#role').val();
        var country = $("select[name=country] option:selected").val();
        var city = $("select[name=city] option:selected").val();
        var action = $('#action').val();
        if (!checkValue(name)) {
            result = "The name must start with a letter";
        } else if (!checkValue(login)) {
            result = "The login must start with a letter";
        } else if (!checkPassword(password)) {
            result = "The password must contains 8 symbols or numbers";
        } else if (!checkEmail(email)) {
            result = "Please enter correctly email";
        } else if (country === "0") {
            result = "Please enter country";
        } else if (city === "0") {
            result = "Please enter city";
        }
        if (result) {
            alert(result);
        } else {
            var user = new User(action, name, login, password, email, role, country, city);
            if (!addUser(user)) {
                result = false;
                return result;
            } else {
                window.location.href = "${pageContext.servletContext.contextPath}".concat("/action/main");
            }
        }
    }

    function addUser(user) {
        var result = true;
        $.ajax({
            async : false,
            type : "POST",
            url : "${pageContext.servletContext.contextPath}".concat("/"),
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
        var regEx = /^[A-Za-zА-Я0-9]{8}$/;
        return regEx.test(pass);
    }

    function User(action, name, login, password, email, role, country, city) {
        this.action = action;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.country = country;
        this.city = city;
    }

    $(document).ready(function () {
        $("#labelCity").hide();
        $.ajax({
            type: "GET",
            url: "${pageContext.servletContext.contextPath}".concat("/action/info"),
            dataType: "json",
            success : function (data) {
                for(var id in data) {
                    $("select[name=country]").append('<option id=' + id + '>' + data[id] + '</option>')
                }
            }
        });
    });
    function getCity() {
        var selectCountry = $("select[name=country] option:selected");
        if (selectCountry.val() !== "0") {
            var arg = selectCountry.attr("id");
            $("select[name=city]").empty();
            $.ajax({
                type: "POST",
                url: "${pageContext.servletContext.contextPath}".concat("/action/info"),
                data : {country : arg},
                dataType: "json",
                success : function (data) {
                    for(var id in data) {
                        $("select[name=city]").append('<option id=' + id + '>' + data[id] + '</option>')
                    }
                    $("#labelCity").show();
                }
            });
        } else {
            $("#labelCity").hide();
        }
    }
</script>