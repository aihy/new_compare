var ticket;
var item;

function get() {
    $.ajax({
        url: "/get", type: "GET", success: function (data) {
            $('#times').text(data.timesString);
            $('#undo').attr("disabled", !data.canUndo);
            if (data.twoResponse) {
                $('#middle').text(data.twoResponse.middle);
                $('#item').text(data.twoResponse.item);
                ticket = data.twoResponse.ticket;
                item = data.twoResponse.item;
            } else {
                window.location.replace("/show.html")
            }

        }
    });
}


function show() {
    $.ajax({
        url: "/show", type: "GET", success: function (data) {
            for (let i = 0; i < data.length; i++) {
                $('#submit').before("<div class=\"form-check\">\n" +
                    "  <input class=\"form-check-input\" type=\"checkbox\" name=\"" + i + "\" id=\"" + i + "\">\n" +
                    "  <label class=\"form-check-label\" for=\"" + i + "\">" + data[i] + "</label>\n" +
                    "</div>");
            }
        }
    });
}

$(document).ready(show());

// $('#submit').click(function () {
//     $.ajax({
//         url: "/set", type: "POST", data: {
//             data:"data"
//         }, success: function (data) {
//             alert("a")
//         }
//     });
// });

$('#middle').click(function () {
    $.ajax({
        url: "/set", type: "GET", data: {
            item: item, leftOrRight: 'right', ticket: ticket
        }, success: function (data) {
            get();
        }
    });
});

$('#item').click(function () {
    $.ajax({
        url: "/set", type: "GET", data: {
            item: item, leftOrRight: 'left', ticket: ticket
        }, success: function (data) {
            get();
        }
    });
});

$('#clear').click(function () {
    $.ajax({
        url: "/clear", type: "GET", success: function () {
            window.location.replace("/index.html")
        }
    });
});

$('#undo').click(function () {
    $.ajax({
        url: "/undo", type: "GET", success: function () {
            window.location.replace("/index.html")
        }
    });
});