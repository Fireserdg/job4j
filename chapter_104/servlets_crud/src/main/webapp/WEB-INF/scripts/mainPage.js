/**
 * JavaScript code for delete User by id
 */

var path = window.location.pathname;
function deleteUser(id){
    var param = {"action" : $("#action").val(), "id" : id.toString()};
    $.ajax({
        type: "POST",
        url : path,
        data : JSON.stringify(param),
        dataType : "text",
        success : function(data) {
            console.log(data);
            if (data.match(/success/)) {
                alert(data);
            }
            window.location.href = path.replace("/main", "").concat("/logout");
        }
    });
}