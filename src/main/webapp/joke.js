     const URL = "api/joke/"
document.getElementById("form").addEventListener("submit", function (event) {
    event.preventDefault();
   let id=document.getElementById("id").value;
 fetch(URL+id)
                        .then(res=>res.json())
                        .then(data => document.getElementById("joke").innerHTML=data.theJoke)
});


