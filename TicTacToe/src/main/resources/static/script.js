let boxes = document.querySelectorAll(".box");

let turn = "X";
let isGameOver = false;

let play1, play2;
const gameData = getGameDataFromCookie();

// Now you can access dataObject.pl1 and dataObject.pl2
function getGameDataFromCookie() {
    const gameDataString = document.cookie.split(';')[0]

     // Replace with your actual string
    const gameDataObject = JSON.parse(gameDataString.substring(9));

    const player1 = gameDataObject.pl1.toLowerCase(); // Accesses the value of "pl1" property (Walter)
    const player2 = gameDataObject.pl2.toLowerCase(); // Accesses the value of "pl2" property (mr robot)

    return [player1, player2]


}


// Now you can access gameData.pl1 and gameData.pl2 (if data exists)

function addGame(winner) {

    play1 = gameData[0].toLowerCase();
    play2 = gameData[1].toLowerCase();


    if (winner == 0) {
        gameWinner = play1

    } else if (winner == 1) {
        gameWinner = play2

    } else {
        gameWinner = "Draw"
    }
    // Define the API endpoint URL
    // Define the API endpoint URL

    const gameId = Date().toString().substring(4, 23)
    console.log(gameId + "is the gameID")
// Data to send in the request body (replace with your actual data)
    const data = {
        "gameWinner": gameWinner,
        "pl1": play1,
        "pl2": play2,
        "gameId": gameId
    };

    const xhr = new XMLHttpRequest();
    const url = "http://localhost:8080/api/v1/games/addGame";


    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                console.log("Success:", JSON.parse(xhr.responseText));
            } else {
                console.error("Error:", xhr.statusText);
            }
        }
    };

    xhr.send(JSON.stringify(data));


}

boxes.forEach(e => {
    e.innerHTML = ""
    e.addEventListener("click", () => {
        if (!isGameOver && e.innerHTML === "") {
            e.innerHTML = turn;
            checkWin();
            checkDraw();
            changeTurn();
        }
    })
})

function changeTurn() {
    if (turn === "X") {
        turn = "O";
        document.querySelector(".bg").style.left = "85px";
    } else {
        turn = "X";
        document.querySelector(".bg").style.left = "0";
    }
}


function checkWin() {
    let winConditions = [
        [0, 1, 2], [3, 4, 5], [6, 7, 8],
        [0, 3, 6], [1, 4, 7], [2, 5, 8],
        [0, 4, 8], [2, 4, 6]
    ]
    let winner;
    let dispWinner;
    for (let i = 0; i < winConditions.length; i++) {
        let v0 = boxes[winConditions[i][0]].innerHTML;
        let v1 = boxes[winConditions[i][1]].innerHTML;
        let v2 = boxes[winConditions[i][2]].innerHTML;

        if (v0 != "" && v0 === v1 && v0 === v2) {
            isGameOver = true;
            if (turn == "X") {
                winner = 0;
            } else {
                winner = 1;

            }
            addGame(winner)
            if (turn == "X") {
                dispWinner = play1
            } else {
                dispWinner = play2

            }

            document.querySelector("#results").innerHTML = dispWinner + " wins";
            document.querySelector("#play-again").style.display = "inline"

            for (j = 0; j < 3; j++) {
                boxes[winConditions[i][j]].style.backgroundColor = "#08D9D6"
                boxes[winConditions[i][j]].style.color = "#000"
            }
        }
    }
}

function checkDraw() {
    if (!isGameOver) {
        let isDraw = true;
        boxes.forEach(e => {
            if (e.innerHTML === "") isDraw = false;
        })

        if (isDraw) {
            isGameOver = true;
            document.querySelector("#results").innerHTML = "Draw";
            document.querySelector("#play-again").style.display = "inline"
            addGame(2)
        }
    }
}

(function () {
    'use strict';
    $('.hamburger-menu').click(function (e) {
        e.preventDefault();
        if ($(this).hasClass('active')) {
            $(this).removeClass('active');
            $('.menu-overlay').fadeToggle('fast', 'linear');
            $('.menu .menu-list').slideToggle('slow', 'swing');
            $('.hamburger-menu-wrapper').toggleClass('bounce-effect');
        } else {
            $(this).addClass('active');
            $('.menu-overlay').fadeToggle('fast', 'linear');
            $('.menu .menu-list').slideToggle('slow', 'swing');
            $('.hamburger-menu-wrapper').toggleClass('bounce-effect');
        }
    })
})();

// document.querySelector("#play-again").addEventListener("click", () => {
//     isGameOver = false;
//     turn = "X";
//     document.querySelector(".bg").style.left = "0";
//     document.querySelector("#results").innerHTML = "";
//     document.querySelector("#play-again").style.display = "none";
//
//     boxes.forEach(e => {
//         e.innerHTML = "";
//         e.style.removeProperty("background-color");
//         e.style.color = "#fff"
//     })
// })

