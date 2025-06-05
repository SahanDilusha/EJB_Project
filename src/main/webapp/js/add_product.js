
document.addEventListener("DOMContentLoaded",()=>{

    document.getElementById("addProductBTN").onclick = async () => {

        const form = new FormData();
        form.append("name",document.getElementById("productName").value);
        form.append("category",document.getElementById("category").value);
        form.append("price",document.getElementById("price").value);
        form.append("quantity",document.getElementById("quantity").value);
        form.append("description",document.getElementById("productDescription").value);


        try {

            const response = await fetch("/GroceryOrderSystem/addProduct", {
                method: "POST",
                body:form,
            });

            const result = await response.json();

            console.log(result);

            if (result.status) {
                alert("ok");
                document.getElementById("productName").value = "";
                document.getElementById("productDescription").value = "";
                document.getElementById("productPrice").value = "";
                document.getElementById("productDescription").value = "";
            } else {
                alert(result.message);
            }

        } catch (e) {
            console.error("Registration error:", e);
            alert("Something went wrong!");
        }
    };
});



