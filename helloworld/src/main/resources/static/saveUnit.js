async function save() {
    try {
        let data = {
            name: document.getElementById("name").value,
            grade: parseFloat(document.getElementById("grade").value),
            semester: parseInt(document.getElementById("semester").value),
            ects: parseInt(document.getElementById("ects").value)
        };
        let res = await $.ajax({
            url: "/api/java/tester/units",
            data: JSON.stringify(data),
            method: "post",
            contentType:"application/json",
            dataType: "json"
        });
        document.getElementById("message").innerHTML = JSON.stringify(res);
    } catch (error) {
        console.log(error);
    }
}