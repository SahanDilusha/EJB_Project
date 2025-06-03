document.getElementById("userRegisterBTN").onclick = async () => {

    try {

        const response = await fetch("/GroceryOrderSystem/register", {
            method: "POST",
            body: JSON.stringify({
                email:document.getElementById("email").value,
                password:document.getElementById("password").value,
                fullName:document.getElementById("fullname").value,
            }),
            headers: {
                "Content-Type": "application/json",
            },
        });

        const result = await response.json();

        console.log(result);

        if (result.status) {
            window.location.replace("/GroceryOrderSystem/login");
        } else {
            alert(result.message);
        }

    } catch (e) {
        console.error("Registration error:", e);
        alert("Something went wrong!");
    }
};
