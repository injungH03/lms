

const myFetch = (args) => {
    let body;
    let contentType = "application/json; charset=utf-8";
    let processData = true;

    if (args.isFormData) {
        body = new FormData(document.getElementById(args.data));
        contentType = false; // jQuery가 contentType을 설정하지 않도록 함
        processData = false; // jQuery가 데이터를 처리하지 않도록 함
    } else if (typeof(args.data) === "string") {
        body = {};
        $('#' + args.data).serializeArray().forEach(function(item) {
            body[item.name] = item.value;
        });
        body = JSON.stringify(body);
    } else {
        body = JSON.stringify(args.data);
    }

    $.ajax({
        type: "POST",
        url: args.url,
        data: body,
        contentType: contentType,
        processData: processData,
        dataType: "json"
    }).done(resp => {
        args.success(resp);
    }).fail(err => {
        if (args.error != null) {
            args.error(typeof (err.responseJSON) === 'object' ? err.responseJSON.message : JSON.stringify(err));
        }
    });
};