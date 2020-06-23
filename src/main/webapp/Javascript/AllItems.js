function insertTable() {
    $("#myTable tbody tr").remove();
    var itemList;
    $.get("http://localhost:8080/rest/item/all", function (data) {
        itemList = data;
        console.log("insertTable virker i items");
        for (i in itemList) {
            var table = document.getElementById("myTable").getElementsByTagName("tbody")[0];
            var row1 = table.insertRow(0);
            var cell1Id = row1.insertCell(0);
            var cell2Name = row1.insertCell(1);
            var cell3weight = row1.insertCell(2);

            cell1Id.innerHTML = itemList[i].id;
            cell2Name.innerHTML = itemList[i].name;
            cell3weight.innerHTML = itemList[i].weight;
        }
    }).fail(function () {
        console.log("Fejl i insertTable i items");
    });
}

function updateTextField() {
    $.get("http://localhost:8080/rest/item/" + document.getElementById("id").value, function (data) {
        document.getElementById("itemname").value = data.name;
        document.getElementById("weight").value = data.weight;
        document.getElementById("description").value = data.description;
        document.getElementById("itemid").value = document.getElementById("id").value;
    });
}

function selecter(x) {
    document.getElementById("selection").value = x;
}
