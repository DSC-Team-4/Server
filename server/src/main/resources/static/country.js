document.addEventListener('DOMContentLoaded', function () {
    const submitButton = document.getElementById('submitButton');

    submitButton.addEventListener('click', function (event) {
        // 이벤트 기본 동작을 막기 위해 preventDefault() 호출
        event.preventDefault();

        // 폼 데이터 가져오기
        const country = document.getElementById('country').value;

        $.ajax({
            url: '/search',
            type: 'GET',
            data: {
                country: country,
                maxCount: 10
            }
        })
        .done(function (fragment){
            $('#popularList').replaceWith(fragment);
        })
    });
});
