
    const URL = "http://localhost:8080/jpareststarter/api/student/all"; 
    
    var data = [
  {
    "id": 1,
    "name": "Jens Gelbeck",
    "studentId": 101,
    "fTvShow": "Den store bagedyst"
  },
  {
    "id": 2,
    "name": "Rasmus Klump",
    "studentId": 102,
    "fTvShow": "Badehotellet"
  },
  {
    "id": 3,
    "name": "Tobias Zimmermann",
    "studentId": 103,
    "fTvShow": "Muppet Show"
  },
  {
    "id": 4,
    "name": "Peter Andersen",
    "studentId": 104,
    "fTvShow": "Simpsons"
  }
]
    
    
    
    
    let dateTable = function table(data){               
        let students = data.map(function(student){ 
            return "<tr>" +
                  
                "<td>" + student.id + "</td>" + 
                "<td>" + student.name + "</td>" + 
                "<td>" + student.studentId + "</td>" + 
                "<td>" + student.fTvShow + "</td>" 
            });
        let tableStudents = students.join("");
        return document.getElementById("col").innerHTML = tableStudents;
     }(data );