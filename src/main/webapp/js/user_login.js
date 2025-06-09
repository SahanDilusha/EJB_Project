document.addEventListener("DOMContentLoaded",()=>{
    document.getElementById("loginBTN").onclick = async () => {
        try {
            const response = await fetch("/bidsystem/userLogin", {
                method: "POST",
                body: JSON.stringify({
                    email:document.getElementById("username").value,
                    password:document.getElementById("password").value,
                }),
                headers: {
                    "Content-Type": "application/json",
                },
            });
            const result = await response.json();
            console.log(result);
            if (result.status) {
                window.location.replace("/bidsystem/");
            } else {
                alert(result.message);
            }
        } catch (e) {
            console.error("Registration error:", e);
            alert("Something went wrong!");
        }
    };
});



