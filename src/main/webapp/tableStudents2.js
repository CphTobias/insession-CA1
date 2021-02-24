
const URL = "http://localhost:8080/jpareststarter/api/student/all";

document.getElementById("btn1").onclick = getAll;

function getAll() {
    const DATA = fetch(URL)
            .then((res) => res.json())
            .then(students => {
                const tableRows = students.map(stud => {
                    const row = `<tr>
                        <td> ${stud.id} </td> 
                        <td> ${stud.name} </td>
                        <td> ${stud.studentId} </td>
                        <td> ${stud.fTvShow} </td> 
                        </tr>`;
                    return row;
                });
            let tableAsString = tableRows.join("");
            return document.getElementById("col").innerHTML = tableAsString;
    });
}