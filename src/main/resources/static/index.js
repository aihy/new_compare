function get() {
    $.ajax({
        url: "/main/get", type: "GET", success: function (data) {
            if (data) {
                $('#hidden_index').val(data.index);
                for (let i = 0; i < data.thisList.length; i++) {
                    $('#submit').before("<div class=\"form-check\">\n" + "  <input class=\"form-check-input\" type=\"checkbox\" name=\"" + i + "\" id=\"" + i + "\" >\n" + "  <label class=\"form-check-label\" for=\"" + i + "\">" + data.thisList[i] + "</label>\n" + "</div>");
                }
            } else {
                window.location.replace("/compare.html")
            }

        }
    });
}


$(document).ready(get());


$('#download').click(function () {
    window.location.replace("/main/download")
});
