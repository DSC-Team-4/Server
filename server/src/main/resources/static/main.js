document.addEventListener('DOMContentLoaded', function () {
    const submitButton = document.getElementById('submitButton');

    submitButton.addEventListener('click', function (event) {
        // 이벤트 기본 동작을 막기 위해 preventDefault() 호출
        event.preventDefault();

        // 폼 데이터 가져오기
        const period = document.getElementById('period').value;
        const country = document.getElementById('country').value;

        $.ajax({
            url: '/search',
            type: 'GET',
            data: {
                period: period,
                country: country
            },
            success: function (response){
                updateWikiList(response);
            },
            error: function (e) {
                console.error('오류 발생', e);
            }
        });
    });

    function updateWikiList(wikis) {
            const container = document.querySelector('.album .container .row');
            container.innerHTML = ''; // 기존 콘텐츠 제거

            wikis.forEach(wiki => {
                const col = document.createElement('div');
                col.className = 'col';

                const card = `
                    <div class="card shadow-sm">
                        <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>
                        <div class="card-body">
                            <p class="card-text">${wiki.title}</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <a type="button" class="btn btn-sm btn-outline-secondary" href="${wiki.uri}">바로가기</a>
                                </div>
                                <small class="text-muted">마지막 편집 날짜: ${wiki.editedAt}</small>
                            </div>
                        </div>
                    </div>
                `;

                col.innerHTML = card;
                container.appendChild(col);
            });
        }
});
