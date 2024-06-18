document.addEventListener('DOMContentLoaded', function () {
    const submitButton = document.getElementById('submitButton');

    submitButton.addEventListener('click', function (event) {
        // 이벤트 기본 동작을 막기 위해 preventDefault() 호출
        event.preventDefault();

        const country = document.querySelector('input[name="country"]:checked').value;

        $.ajax({
            url: '/search',
            type: 'GET',
            data: {
                country: country,
                maxCount: 10
            },
            success: function (response){
                console.log(response);
            },
            error: function (e) {
                console.error('오류 발생', e);
            }
        })
        .done(function (fragment){
            $('#popularList').replaceWith(fragment);
        })
    });
});
