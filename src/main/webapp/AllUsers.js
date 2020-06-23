function insertTable(){
    $("#myTable tbody tr").remove();
    var userList;
    $.get("http://localhost:8080/rest/user/all", function(data){
        userList = data;
        console.log("insertTable virker i users");
        console.log(userList);
        for ( i in userList) {
            var table = document.getElementById("myTable").getElementsByTagName("tbody")[0];
            var row1 = table.insertRow(0);
            var cell1Id = row1.insertCell(0);
            var cell2Name = row1.insertCell(1);
            var cell3Role = row1.insertCell(2);

            cell1Id.innerHTML = userList[i].id;
            cell2Name.innerHTML = userList[i].name;
            cell3Role.innerHTML= userList[i].role;



        }
    }).fail(function () {
        console.log("Fejl i insertTable i users");
    });

}

function updateTextField() {
    $.get("http://localhost:8080/rest/user/"+document.getElementById("id").value, function(data){
        document.getElementById("username").value = data.name;
        document.getElementById("role").value = data.role;
        document.getElementById("password").value = data.password;
        document.getElementById("userid").value = document.getElementById("id").value;
    });
}

function selecter(x) {
    document.getElementById("selection").value = x;
}

function approved(approval) {
    $.post("http://localhost:8080/rest/user/approve/" + document.getElementById("id").value + "/" + approval + "/" + role , function (data) {
    });
}

function insertList(){
    $("#list tbody tr").remove();
    var characterList;
    $.get("http://localhost:8080/rest/relation/usercharacters/"+document.getElementById("id").value, function (data) {
        characterList = data;
        console.log("insertList i users virker");
        console.log(characterList);
        for (i in characterList) {
            var table = document.getElementById("list").getElementsByTagName("tbody")[0];
            var row1 = table.insertRow(0);
            var cell1Name = row1.insertCell(0);

            cell1Name.innerHTML = characterList[i].name;
        }

    }).fail(function () {
        console.log("Fejl i insertList på user");
    });
}

function addCharacter(charId,userId) {
    $.post("http://localhost:8080/rest/user/addcharacter/" + charId + "/" + userId, function (data) {
    })
}

function removeCharacter(charId) {
    $.post("http://localhost:8080/rest/user/removecharacter/" + charId, function (data) {
    })
}
