const URL = "api/joke/"
document.getElementById("form").addEventListener("submit", function (event) {
    event.preventDefault();
    let id = document.getElementById("id").value;
    fetch(URL + id)
            .then(res => res.json())
            .then(data => document.getElementById("joke").innerHTML = data.theJoke)
});


 
    
   
   function getAll() {
    const DATA = fetch(URL+"all")
            .then((res) => res.json())
            .then(jokes => {
                const tableRows = jokes.map(joke => {
                    const row = `<tr>
                        <td> ${joke.id} </td> 
                        <td> ${joke.theJoke} </td>
               <td> ${joke.reference} </td>
               <td> ${joke.type} </td>
               <td> ${joke.rating} </td>
                        </tr>`;
                    return row;
                });
            let tableAsString = "<tr><th>id</th><th>The Joke</th><th>reference</th><th>Type</th><th>Rating</th></tr>".concat(tableRows.join("  "));
            return document.getElementById("jokes").innerHTML = tableAsString;
    });
}

getAll();