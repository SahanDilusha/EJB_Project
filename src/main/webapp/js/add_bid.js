async function addBid(id) {

    try {

        const response = await fetch("/GroceryOrderSystem/addBid", {
            method: "POST",
            body: JSON.stringify({
                prodctId: id,
                price: document.getElementById("bidAmount").value.trim().length > 0 ? document.getElementById("bidAmount").value : 0,
            }),
            headers: {
                "Content-Type": "application/json",
            },
        });

        const result = await response.json();

        console.log(result);

        if (result.status) {
            alert("ok");
            document.getElementById("bidAmount").value = "";
        } else {
            alert(result.message);
        }

    } catch (e) {
        console.error("Registration error:", e);
        alert("Something went wrong!");
    }

}











