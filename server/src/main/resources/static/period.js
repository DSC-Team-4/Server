document.addEventListener('DOMContentLoaded', function () {
    const submitButton = document.getElementById('submitButton');

    submitButton.addEventListener('click', function (event) {
        // 이벤트 기본 동작을 막기 위해 preventDefault() 호출
        event.preventDefault();

        const date = document.getElementById('date').value;
        const time = document.getElementById('time').value;

        if(!date || !time){
            alert('날짜와 시간을 제대로 선택해주세요.');
            return;
        }

        const dateTimeString = date + 'T' + time + ':00';
        const start = new Date(dateTimeString).getTime();
        const end = start + (3*60*60*1000);

        $.ajax({
            url: '/get-hot-wikis',
            type: 'GET',
            data: {
                startTime: start,
                endTime: end
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
