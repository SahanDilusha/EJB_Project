<%@ page import="com.popcorntech.bidsystem.entities.Product" %>

<%
    Product product = (Product) request.getAttribute("product");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${product.name} - Product Bidding</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container my-5">
    <div class="card shadow-lg">
        <div class="row g-0">
            <div class="col-md-5">
                <img src="images/sample-product.jpg" class="img-fluid rounded-start" alt="Product Image">
            </div>
            <div class="col-md-7">
                <div class="card-body">
                    <h3 class="card-title">${product.name}</h3>
                    <p class="card-text text-muted">Category: ${product.productCategory.name}</p>
                    <p class="card-text">Description: ${product.description}</p>
                    <p class="card-text"><strong>Base Price:</strong> $${product.basePrice}</p>
                    <p class="card-text"><strong>Quantity:</strong> ${product.quantity}</p>
                    <p class="card-text"><strong>Bid Status:</strong> ${product.bidStatus.name}</p>

                    <form class="mt-4">
                        <div class="mb-3">
                            <label for="bidAmount" class="form-label">Your Bid Amount (₹)</label>
                            <input type="number" class="form-control" id="bidAmount" name="bidAmount" required>
                        </div>
                        <button type="button" class="btn btn-success" onclick="addBid(${product.id})">Place Bid</button>
                    </form>

                    <div class="mt-3 text-muted">
                        Bidding ends on: <strong>June 10, 2025</strong>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="js/add_bid.js"></script>
</body>
</html>
