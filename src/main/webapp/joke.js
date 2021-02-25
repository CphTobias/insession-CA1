const URL = "api/joke/"
document.getElementById("form").addEventListener("submit", function (event) {
    event.preventDefault();
    let id = document.getElementById("id").value;
    fetch(URL + id)
            .then(res => res.json())
            .then(data => document.getElementById("joke").innerHTML = "Joke with ID="+id+":<Br>"+data.theJoke)
});

document.getElementById("rand").addEventListener("click", function (event) {
    fetch(URL +"random")
            .then(res => res.json())
            .then(data => document.getElementById("joke").innerHTML = "Random Joke:<Br>"+data.theJoke)
});
 document.getElementById("all").addEventListener("click", function (event) {
getAll();
 });
    
   
   function getAll() {
    const DATA = fetch(URL + "all")
            .then((res) => res.json())
            .then(jokes => {
                const tableRows = jokes.map(joke => {
                    const row = `<tr>
                                <td> ${joke.id} </td> 
                                <td> ${joke.theJoke} </td>
                                <td> ${joke.reference} </td>
                                <td> ${joke.type}</td>
                                <td> ${joke.rating} </td>
                                </tr>`;
                    return row;
                });
                let tableAsString = "<table class=\"table\"><thead><tr><th>id</th><th>The Joke</th><th>reference</th><th>Type</th><th>Rating</th></tr></thead>".concat(tableRows.join(""),"</table>");
                return document.getElementById("joke").innerHTML = tableAsString;
            });
}

