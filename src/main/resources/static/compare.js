var ticket;
var item;

function get() {
    $.ajax({
        url: "/compare/get", type: "GET", success: function (data) {
            $('#times').text(data.timesString);
            $('#undo').attr("disabled", !data.canUndo);
            if (data.twoResponse) {
                $('#middle').text(data.twoResponse.middle);
                $('#item').text(data.twoResponse.item);
                ticket = data.twoResponse.ticket;
                item = data.twoResponse.item;
            } else {
                window.location.replace("/compare/download")
            }

        }
    });
}

$(document).ready(get());

$('#middle').click(function () {
    $.ajax({
        url: "/compare/set", type: "GET", data: {
            item: item, leftOrRight: 'right', ticket: ticket
        }, success: function (data) {
            get();
        }
    });
});

$('#item').click(function () {
    $.ajax({
        url: "/compare/set", type: "GET", data: {
            item: item, leftOrRight: 'left', ticket: ticket
        }, success: function (data) {
            get();
        }
    });
});

$('#clear').click(function () {
    $.ajax({
        url: "/compare/clear", type: "GET", success: function () {
            window.location.replace("/index.html")
        }
    });
});

$('#undo').click(function () {
    $.ajax({
        url: "/compare/undo", type: "GET", success: function () {
            window.location.replace("/index.html")
        }
    });
});