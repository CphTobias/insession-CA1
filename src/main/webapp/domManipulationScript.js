let boys = ["Peter", "lars", "Ole"]
let girls = ["Janne", "hanne", "Sanne"]
let all = [...boys, ...girls]

const showBoysNode = document.getElementById("show-boys")
const showGirlsNode = document.getElementById("show-girls")
const showAllNode = document.getElementById("show-all")

//Called to update the current display of boys
function displayAllBoys(boys) {
  showBoysNode.innerHTML = boys.map(boy => `<p>${boy}</p>`).join("")
}

//Called to update the current display of girls
function displayAllGirls(girls) {
  showGirlsNode.innerHTML = girls.map(girl => `<p>${girl}</p>`).join("")
}

//Called to update the current display of all the people
function displayAll(all) {
  showAllNode.innerHTML = all.map(person => `<p>${person}</p>`).join("")
}

displayAllBoys(boys)
displayAllGirls(girls)
displayAll(all)

const boyActions = {
  ADD_BOY: "ADD_BOY",
  REMOVE_LAST_BOY: "REMOVE_LAST_BOY",
  REMOVE_FIRST_BOY: "REMOVE_FIRST_BOY",
}
//Responsible for all of the actions related to boys
function boysReducer(action) {
  switch (action.type) {
    case boyActions.ADD_BOY:
      boys = [...boys, action.payload]
      all = [...boys, ...girls]
      displayAllBoys(boys)
      displayAll(all)
      break
    case boyActions.REMOVE_FIRST_BOY:
      boys.shift()
      all = [...boys, ...girls]
      displayAllBoys(boys)
      displayAll(all)
      break
    case boyActions.REMOVE_LAST_BOY:
      boys.pop()
      all = [...boys, ...girls]
      displayAllBoys(boys)
      displayAll(all)
      break
    default:
      throw new Error("Unknown action: " + action.type)
  }
}

const girlActions = {
  ADD_GIRL: "ADD_GIRL",
  REMOVE_LAST_GIRL: "REMOVE_LAST_GIRL",
  REMOVE_FIRST_GIRL: "REMOVE_FIRST_GIRL",
}
//Responsible for all of the actions related to girls
function girlsReducer(action) {
  switch(action.type) {
    case girlActions.ADD_GIRL:
      girls = [...girls, action.payload]
      all = [...boys, ...girls]
      displayAllGirls(girls)
      displayAll(all)
      break
    case girlActions.REMOVE_FIRST_BOY:
      girls.shift()
      all = [...boys, ...girls]
      displayAllGirls(girls)
      displayAll(all)
      break
    case girlActions.REMOVE_LAST_BOY:
      girls.pop()
      all = [...boys, ...girls]
      displayAllGirls(girls)
      displayAll(all)
      break
    default:
      throw new Error("Unknown action: " + action.type)
  }
}

const boyInputNode = document.getElementById("boy-input")
const addBoyFormNode = document.getElementById("add-boy-form")

//Add boy form submit
addBoyFormNode.addEventListener("submit", (e) => {
  e.preventDefault()
  const boyInputValue = boyInputNode.value
  boysReducer({type: boyActions.ADD_BOY, payload: boyInputValue})
  boyInputNode.value = ""
})

const girlInputNode = document.getElementById("girl-input")
const addGirlFormNode = document.getElementById("add-girl-form")

//Add girl form submit
addGirlFormNode.addEventListener("submit", (e) => {
  e.preventDefault()
  const girlInputValue = girlInputNode.value
  girlsReducer({type: girlActions.ADD_GIRL, payload: girlInputValue})
  girlInputNode.value = ""
})

const removeBoyButtonNode = document.getElementById("remove-boy")

//Remove boy click
removeBoyButtonNode.addEventListener("click", () => {
  const checkedValue = document.querySelector("input[name='remove-selection']:checked").value;
  if (checkedValue === "first") {
    console.log(checkedValue)
    boysReducer({type: boyActions.REMOVE_FIRST_BOY})
  } else if (checkedValue === "last") {
    console.log(checkedValue)
    boysReducer({type: boyActions.REMOVE_LAST_BOY})
  }
})

const removeGirlButtonNode = document.getElementById("remove-girl")

//Remove girl click
removeGirlButtonNode.addEventListener("click", () => {
  const checkedValue = document.querySelector("input[name='remove-selection']:checked").value;
  if (checkedValue === "first") {
    console.log(checkedValue)
    girlsReducer({type: girlActions.REMOVE_FIRST_GIRL})
  } else if (checkedValue === "last") {
    console.log(checkedValue)
    girlsReducer({type: girlActions.REMOVE_LAST_GIRL})
  }
})

