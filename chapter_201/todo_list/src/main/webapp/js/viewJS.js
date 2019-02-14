const path = window.location.pathname.replace("index.html", "");
$(loadItem());
function loadItem() {
    $.ajax({
        type : "GET",
        url : path.concat("items"),
        dataType : "json",
        success : function (data) {
            const check = $('#check').is(':checked');
            const table = $("table tbody");
            table.empty();
            data.forEach(function (item) {
                if (check) {
                    var raw = '<tr><td>' + item.id + '</td>';
                    raw += '<td>' + item.desc + '</td>';
                    raw += '<td>' + item.created + '</td>';
                    if (item.done) {
                        raw += '<td><input type="checkbox" disabled checked/></td></tr>';
                    } else {
                        raw += '<td><input type="checkbox" disabled/></td></tr>';
                    }
                    table.append(raw);
                } else if(!check && !item.done) {
                    printRaw(item);
                }
            });
        },
        error : function () {
            alert("Internal Server error\nInput OK and reload page");
        }
    });
}

function addItem() {
    const desc = $('#area').val();
    const regExp = /^[\s]+$/;
    if (desc ==="" || regExp.test(desc)) {
        alert("Please input description");
        return false;
    } else {
        $.ajax({
            type: "POST",
            url : path.concat("items"),
            data : {desc : desc},
            dataType: "json",
            success : function (data) {
                printRaw(data);
                return true;
            },
            error : function () {
                alert("Internal Server error\nInput OK and reload page");
            }
        });
    }
}

function printRaw(data) {
    const table = $("table tbody");
    var akk ='<tr><td>' + data.id + '</td>';
    akk+='<td>' + data.desc + '</td>';
    akk+='<td>' + data.created + '</td>';
    akk+='<td><input type="checkbox" disabled/></td></tr>';
    table.append(akk);
}
