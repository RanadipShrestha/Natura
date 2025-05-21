<%@ page import="java.util.List" %>
<%@ page import="models.ReportItemModel" %>

<!DOCTYPE html>
<html>
<head>
    <title>Order Report</title>
    <style>
        table {
            border-collapse: collapse;
            width: 90%;
            margin: 20px auto;
        }
        th, td {
            border: 1px solid #333;
            padding: 8px 12px;
            text-align: center;
        }
        th {
            background-color: #eee;
        }
    </style>
</head>
<body>

<h2 style="text-align:center;">Sale Report</h2>

<table>
    <thead>
        <tr>
            <th>Name</th>
            <th>Product Name</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>Total Price</th>
            <th>Order Date</th>
        </tr>
    </thead>
    <tbody>
        <%
            List<ReportItemModel> orderReports = (List<ReportItemModel>) request.getAttribute("orderReports");
            if (orderReports != null && !orderReports.isEmpty()) {
                for (ReportItemModel item : orderReports) {
        %>
                    <tr>
                        <td><%= item.getUserName() %></td>
                        <td><%= item.getProductName() %></td>
                        <td><%= item.getQuantity() %></td>
                        <td>RS : <%= item.getPrice() %></td>
                        <td>RS : <%= item.getTotalPrice() %></td>
                        <td><%= item.getOrderDate() %></td>
                    </tr>
        <%
                }
            } else {
        %>
                <tr>
                    <td colspan="6">No data available</td>
                </tr>
        <%
            }
        %>
    </tbody>
</table>

</body>
</html>
