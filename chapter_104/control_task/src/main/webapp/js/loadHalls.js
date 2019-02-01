var path = window.location.pathname.replace("index.html", "");
$(loadHalls());
setInterval(loadHalls, 10000);
function loadHalls() {
    $.ajax({
        type : "GET",
        url : path.concat("hall"),
        dataType : "json",
        success : function (data) {
            var table = $("table tbody");
            table.empty();
            $.each(data, function (index, value) {
                var akk = "<tr><th>" + index +"</th>";
                $.each(value, function (index, value) {
                    if (!value.booked){
                        akk+="<td><input type='radio' name='place' value='";
                        akk+= value.id;
                        akk+="'>";
                        akk+=" Ряд " + value.row + ", Место " + value.seat;
                        akk+="</input></td>";
                    } else {
                        akk+="<td>Место забронировано</td>";
                    }

                });
                akk+="</tr>";
                table.append(akk);

            });
        }
    });
}

function validateInput(input) {
    var result = true;
    if (input.length === 0) {
        result = false;
        return result;
    }
    return result;
}

function getPlace() {
    var value = $('input[name=place]:checked');
    if (validateInput(value)) {
        var place = value.val();
        $.ajax({
            type : 'POST',
            url : path.concat("hall"),
            data : {id : place},
            dataType : "text",
            success : function (data) {
                alert("Вы выбрали место. Нажмите на кнопку ОК");
                window.location.href = path.concat("pages/payment.html")
            }
        });
    } else {
        alert("Вы не выбрали место");
    }
}