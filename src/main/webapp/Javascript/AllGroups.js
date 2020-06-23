function insertTable(){
    $("#myTable tbody tr").remove();
    var groupList;
    $.get("http://localhost:8080/rest/group/all", function(data){
        groupList = data;
        console.log("insertTable virker i groups");
        console.log(groupList);
        for ( i in groupList) {
            var table = document.getElementById("myTable").getElementsByTagName("tbody")[0];
            var row1 = table.insertRow(0);
            var cell1Id = row1.insertCell(0);
            var cell2Name = row1.insertCell(1);


            cell1Id.innerHTML = groupList[i].id;
            cell2Name.innerHTML = groupList[i].name;

        }
    }).fail(function () {
        console.log("Fejl i insertTable i group");
    });
}

function updateTextField() {
    $.get("http://localhost:8080/rest/group/"+document.getElementById("id").value, function(data){
        document.getElementById("groupname").value = data.name;
        document.getElementById("description").value = data.description;
        document.getElementById("groupid").value = document.getElementById("id").value;});
}

function selecter(x) {
    document.getElementById("selection").value = x;
}

function insertList(){
    $("#list tbody tr").remove();
    var characterList;
    $.get("http://localhost:8080/rest/relation/groupcharacters/"+document.getElementById("id").value, function (data) {
        characterList = data;
        console.log("insertList i groups virker");
        console.log(characterList);
        for (i in characterList) {
            var table = document.getElementById("list").getElementsByTagName("tbody")[0];
            var row1 = table.insertRow(0);
            var cell1Name = row1.insertCell(0);

            cell1Name.innerHTML = characterList[i].name;
        }

    }).fail(function () {
        console.log("Fejl i insertList på groups");
    });
}

