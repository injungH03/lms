

const formToSerialize = (formId) => {
    return $('#' + formId).serialize(); // jQuery의 serialize() 함수를 사용하여 폼 데이터를 직렬화
};

const myFetch = (args) => {
    // 로딩 컨테이너 표시
    document.getElementById('loadingContainer').style.display = 'flex';

    let body = typeof (args.data) == "string" ? formToSerialize(args.data) : JSON.stringify(args.data);

    $.ajax({
        type: "POST",
        url: args.url,
        data: body,
        contentType: "application/json; charset=utf-8",
        dataType: "json"
    }).done(resp => {
        args.success(resp);
    }).fail(err => {
        if (args.error != null) {
            args.error(typeof (err.responseJSON) == 'object' ? err.responseJSON.message : JSON.stringify(err));
        }
    }).always(() => {
        // 로딩 컨테이너 숨김
        document.getElementById('loadingContainer').style.display = 'none';
    });
};