function insertTable() {
    $("#myTable tbody tr").remove();
    var characterList;
    $.get("http://localhost:8080/rest/character/all", function (data) {
        characterList = data;
        console.log("insertTable virker i chars");
        for (i in characterList) {
            var table = document.getElementById("myTable").getElementsByTagName("tbody")[0];
            var row1 = table.insertRow(0);
            var cell1Id = row1.insertCell(0);
            var cell2Name = row1.insertCell(1);
            var cell3Location = row1.insertCell(2);

            cell1Id.innerHTML = characterList[i].id;
            cell2Name.innerHTML = characterList[i].name;
            cell3Location.innerHTML = characterList[i].location;
        }
    }).fail(function () {
        console.log("Fejl i insertTable i char");
    });
}

function updateTextField() {
    $.get("http://localhost:8080/rest/character/" + document.getElementById("id").value, function (data) {
        document.getElementById("charactername").value = data.name;
        document.getElementById("location").value = data.location;
        document.getElementById("strength").value = data.strength;
        document.getElementById("bonus").value = data.bonus;
        document.getElementById("characterid").value = document.getElementById("id").value;
    });
}

function selecter(x) {
    document.getElementById("selection").value = x;
}

function approved(approval) {
    $.post("http://localhost:8080/rest/character/approve/" + document.getElementById("id").value + "/" + approval + "/" + role , function (data) {
    });
}

function addItem(itemId,charId) {
    $.post("http://localhost:8080/rest/character/additem/" + itemId + "/" + charId, function (data) {
    })
}

function removeItem(itemId,charId) {
    $.post("http://localhost:8080/rest/character/removeitem/" + itemId + "/" + charId, function (data) {
    })
}

function addGroup(groupId,charId) {
    $.post("http://localhost:8080/rest/character/addgroup/" + groupId + "/" + charId, function (data) {
    })
}

function removeGroup(groupId,charId) {
    $.post("http://localhost:8080/rest/character/removegroup/" + groupId + "/" + charId, function (data) {
    })
}

function insertList(){
    $("#list tbody tr").remove();
    var itemList;
    $.get("http://localhost:8080/rest/relation/characteritems/"+document.getElementById("id").value, function (data) {
        itemList = data;
        console.log("insertList i chars virker");
        console.log(itemList);
        for (i in itemList) {
            var table = document.getElementById("list").getElementsByTagName("tbody")[0];
            var row1 = table.insertRow(0);
            var cell1Name = row1.insertCell(0);

            cell1Name.innerHTML = itemList[i].name;
        }

    }).fail(function () {
        console.log("Fejl i insertList på char");
    });
}

function insertList2() {
    $("#list tbody tr").remove();
    var groupList;
    $.get("http://localhost:8080/rest/relation/charactergroups/"+document.getElementById("id").value, function (data) {
        groupList = data;
        console.log("insertList2 i chars virker");
        console.log(groupList);
        for (i in groupList) {
            var table = document.getElementById("list2").getElementsByTagName("tbody")[0];
            var row1 = table.insertRow(0);
            var cell1Name = row1.insertCell(0);

            cell1Name.innerHTML = groupList[i].name;
        }

    }).fail(function () {
        console.log("Fejl i insertList2 på char");
    });
}
