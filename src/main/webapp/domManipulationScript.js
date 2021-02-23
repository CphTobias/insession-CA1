// let boys = [ "Peter", "lars", "Ole" ]
// let girls = [ "Janne", "hanne", "Sanne" ]
let boys = [
  { id: "" + Math.random(), name: "Peter" },
  { id: "" + Math.random(), name: "lars" },
  { id: "" + Math.random(), name: "Ole" }
]
let girls = [
  { id: Math.random(), name: "Janne" },
  { id: Math.random(), name: "hanne" },
  { id: Math.random(), name: "Sanne" }
]
let all = [ ...boys, ...girls ]

const showBoysNode = document.getElementById("show-boys")
const showGirlsNode = document.getElementById("show-girls")
const showAllNode = document.getElementById("show-all")

//Called to update the current display of boys
function displayAllBoys(boys) {
  showBoysNode.innerHTML = boys.map(
      boy => `<div style="display: flex; justify-content: center">
                <p>${boy.name}</p>
                <button id="${boy.id}">x</button>
              </div>`).join("")
}

//Remove by boy by id
function createBoyOnClicks() {
  boys.forEach(boy => {
    const boyButton = document.getElementById(boy.id);
    boyButton.addEventListener("click", () => {
      boysReducer({ type: boyActions.REMOVE_BY_ID, payload: boy.id })
    })
  })
}

//Called to update the current display of girls
function displayAllGirls(girls) {
  showGirlsNode.innerHTML = girls.map(
      girl => `<div style="display: flex; justify-content: center">
                <p>${girl.name}</p>
                <button id="${girl.id}">x</button>
              </div>`).join("")
}

//Remove girl by id
function createGirlOnClicks() {
  girls.forEach(girl => {
    const girlButton = document.getElementById(girl.id);
    girlButton.addEventListener("click", () => {
      girlsReducer({ type: girlActions.REMOVE_BY_ID, payload: girl.id })
    })
  })
}

//Called to update the current display of all the people
function displayAll(all) {
  showAllNode.innerHTML = all.map(person => `<p>${person.name}</p>`).join("")
}

function rebuild(array, all, gender) {
  if (gender === "boy") {
    displayAllBoys(array)
    createBoyOnClicks()
  } else if (gender === "girl"){
    displayAllGirls(array)
    createGirlOnClicks()
  }
  displayAll(all)
}

rebuild(boys, all, "boy")
rebuild(girls, all, "girl")


const boyActions = {
  ADD_BOY: "ADD_BOY",
  REMOVE_LAST_BOY: "REMOVE_LAST_BOY",
  REMOVE_FIRST_BOY: "REMOVE_FIRST_BOY",
  REMOVE_BY_ID: "REMOVE_BY_ID"
}

//Responsible for all of the actions related to boys
function boysReducer(action) {
  switch (action.type) {
    case boyActions.ADD_BOY:
      boys = [ ...boys, { id: Math.random(), name: action.payload } ]
      all = [ ...boys, ...girls ]
      rebuild(boys, all, "boy")
      break
    case boyActions.REMOVE_FIRST_BOY:
      boys.shift()
      all = [ ...boys, ...girls ]
      rebuild(boys, all, "boy")
      break
    case boyActions.REMOVE_LAST_BOY:
      boys.pop()
      all = [ ...boys, ...girls ]
      rebuild(boys, all, "boy")
      break
    case boyActions.REMOVE_BY_ID:
      boys = boys.filter(boy => boy.id !== action.payload)
      all = [ ...boys, ...girls ]
      rebuild(boys, all, "boy")
      break
    default:
      throw new Error("Unknown action: " + action.type)
  }
}

const girlActions = {
  ADD_GIRL: "ADD_GIRL",
  REMOVE_LAST_GIRL: "REMOVE_LAST_GIRL",
  REMOVE_FIRST_GIRL: "REMOVE_FIRST_GIRL",
  REMOVE_BY_ID: "REMOVE_BY_ID"
}

//Responsible for all of the actions related to girls
function girlsReducer(action) {
  switch (action.type) {
    case girlActions.ADD_GIRL:
      girls = [ ...girls, { id: Math.random(), name: action.payload } ]
      all = [ ...boys, ...girls ]
      rebuild(girls, all, "girl")
      break
    case girlActions.REMOVE_FIRST_GIRL:
      girls.shift()
      all = [ ...boys, ...girls ]
      rebuild(girls, all, "girl")
      break
    case girlActions.REMOVE_LAST_GIRL:
      girls.pop()
      all = [ ...boys, ...girls ]
      rebuild(girls, all, "girl")
      break
    case girlActions.REMOVE_BY_ID:
      girls = girls.filter(girl => girl.id !== action.payload)
      all = [ ...boys, ...girls ]
      rebuild(girls, all, "girl")
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
  boysReducer({ type: boyActions.ADD_BOY, payload: boyInputValue })
  boyInputNode.value = ""
})

const girlInputNode = document.getElementById("girl-input")
const addGirlFormNode = document.getElementById("add-girl-form")

//Add girl form submit
addGirlFormNode.addEventListener("submit", (e) => {
  e.preventDefault()
  const girlInputValue = girlInputNode.value
  girlsReducer({ type: girlActions.ADD_GIRL, payload: girlInputValue })
  girlInputNode.value = ""
})

const removeBoyButtonNode = document.getElementById("remove-boy")

//Remove boy click
removeBoyButtonNode.addEventListener("click", () => {
  const checkedValue = document.querySelector(
      "input[name='remove-selection']:checked").value;
  if (checkedValue === "first") {
    boysReducer({ type: boyActions.REMOVE_FIRST_BOY })
  } else if (checkedValue === "last") {
    boysReducer({ type: boyActions.REMOVE_LAST_BOY })
  }
})

const removeGirlButtonNode = document.getElementById("remove-girl")

//Remove girl click
removeGirlButtonNode.addEventListener("click", () => {
  const checkedValue = document.querySelector(
      "input[name='remove-selection']:checked").value;
  if (checkedValue === "first") {
    girlsReducer({ type: girlActions.REMOVE_FIRST_GIRL })
  } else if (checkedValue === "last") {
    girlsReducer({ type: girlActions.REMOVE_LAST_GIRL })
  }
})

const reverseAllButtonNode = document.getElementById("reverse-all-button")

//Reverse all click
reverseAllButtonNode.addEventListener("click", () => {
  const allReversed = all.reverse()
  displayAll(allReversed)
})

const sortAllButtonNode = document.getElementById("sort-all-button")

//Sort all click
sortAllButtonNode.addEventListener("click", () => {
  displayAll([ ...boys, ...girls ])
})


