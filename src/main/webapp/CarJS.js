/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


document.getElementById("carTable").innerHTML = fetchAllCars();

let switchbutton = true;
let getAllCarsBtn = document.getElementById("getAllCarsBtn");
getAllCarsBtn.addEventListener('click', (event) => {
    event.preventDefault();
    fetchAllCars();
});

function fetchAllCars() {
    let url = 'api/car/all';
    let allCars = document.getElementById("carTable");
    fetch(url)
            .then(res => res.json())
            .then(data => {
                let newArray = data.map(x => `<tr><td>${x.manufacturer}</td><td>${x.year}</td><td>${x.model}</td><td>${x.price}</td><td>${x.quantity}</td></tr>`);
                allCars.innerHTML =
                    `<table id="carTable">
                        <thead><th onclick="sortTable(0)">Manufacturer</th><th onclick="sortTable(1)">Year</th><th onclick="sortTable(2)">Model</th><th onclick="sortTable(3)">Price</th><th onclick="sortTable(4)">Quantity</th></thead>
                        ${newArray.join("")}
                    </table>`;
            });
}


            function sortTable(n) {
                var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
                table = document.getElementById("carTable");
                switching = true;                
                dir = "asc";
                while (switching) {
                    switching = false;
                    rows = table.rows;
                    for (i = 1; i < (rows.length - 1); i++) {
                        shouldSwitch = false;
                        x = rows[i].getElementsByTagName("TD")[n];
                        y = rows[i + 1].getElementsByTagName("TD")[n];
                        if (dir == "asc") {
                            if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                                shouldSwitch = true;
                                break;
                            }
                        } else if (dir == "desc") {
                            if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                                shouldSwitch = true;
                                break;
                            }
                        }
                    }
                    if (shouldSwitch) {
                        rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                        switching = true;
                        switchcount++;
                    } else {
                        if (switchcount == 0 && dir == "asc") {
                            dir = "desc";
                            switching = true;
                        }
                    }
                }
            }