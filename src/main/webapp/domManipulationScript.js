let boys = ["Peter", "lars", "Ole"]
let girls = ["Janne", "hanne", "Sanne"]
let all = [...boys, ...girls]

const showBoysNode = document.getElementById("show-boys")
const showGirlsNode = document.getElementById("show-girls")
const showAllNode = document.getElementById("show-all")

function displayAllBoys(boys) {
  showBoysNode.innerHTML = boys.map(boy => `<p>${boy}</p>`).join("")
}

function displayAllGirls(girls) {
  showGirlsNode.innerHTML = girls.map(girl => `<p>${girl}</p>`).join("")
}

function displayAll(all) {
  showAllNode.innerHTML = all.map(person => `<p>${person}</p>`).join("")
}

displayAllBoys(boys)
displayAllGirls(girls)
displayAll(all)

function boysReducer(action) {
  switch (action.type) {
    case "ADD_BOY":
      return
    default:
      throw new Error("Unknown action: " + action.type)
  }
}

