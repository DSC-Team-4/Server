import axios from "axios";

function submitSearch() {
    let input = document.getElementById('searchInput').value;
    let country = document.getElementById('country').value;
    let category = document.getElementById('category').value;
    let period = document.getElementById('period').value;
    let endedAt = new Date()
    let startedAt = new Date(endedAt.getMonth() - period)

    const params = { search: input, country: country, category: category, startedAt: startedAt, endedAt: endedAt};
    // alert('검색어 제출: ' + input + '나라: ' + country + '카테고리: ' + category + '기간: ' + period);
    axios.get("/search", {params: params}).then(response => {
            console.log(response);
            response.data.forEach(element => {
                console.log(element);
            })
        }
    );
}
