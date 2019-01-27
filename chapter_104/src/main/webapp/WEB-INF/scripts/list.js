/**
 * JavaScript code for delete User
 */
var path = window.location.pathname;
function deleteUser(id){
    var param = {"action" : $("#action").val(), "id" : id.toString()};
    $.ajax({
        type: "POST",
        url : path.replace("/list", "").concat("/main"),
        data : JSON.stringify(param),
        dataType : "text",
        success : function(data) {
            console.log(data);
            window.location.href = path;
        }
    });
}