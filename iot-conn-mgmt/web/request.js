function get(url, onSuccess) {
    $.ajax({
        url: url,
        type: "GET",
        success: onSuccess
    })
}

function post(url, data, onSuccess) {
    $.ajax({
        url: url,
        data: JSON.stringify(data),
        contentType: "application/json;charset=utf-8",
        processData: false,
        type: "POST",
        success: onSuccess
    })
}

function postText(url, text, onSuccess) {
    $.ajax({
        url: url,
        data: text,
        contentType: "text/plain",
        type: "POST",
        success: onSuccess
    })
}