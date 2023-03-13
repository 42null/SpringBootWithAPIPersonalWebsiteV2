/**

 DO NOT MODIFY ANYTHING IN THIS FILE!!

 CREATE DTO OBJECTS TO SATISFY THE JAVASCRIPT IN populateOrders()

 DO NOT MODIFY ANYTHING IN THIS FILE!!

 */

$(function() {
    $.ajax({
        url: "http://localhost:8080/api/orders/",
        success: function (data) {
            populateOrders(data);
        },
        error: function (jqHXR, textStatus, errorThrown) {
            alert("Check console for error!");
            console.log(jqHXR.responseText);
        },
        dataType: "json"
    });

    var populateOrders = function(data) {
        var tbody = $("#order-table-body");

        for(var i = 0; i < data.length; i++) {
            var order = data[i];

            var tr = $("<tr>");
            /**
             * Ensure that your DTO has the following fields.
             * Do not modify this code!
             */
            tr.append("<td>" + order.customerName + "</td>");
            tr.append("<td>" + order.purchaseDate + "</td>");
            tr.append("<td>" + order.purchaseOrderNumber + "</td>");
            tr.append("<td>" + order.productName + "</td>");
            tr.append("<td>" + order.terms + "</td>");
            tr.append("<td>" + order.shippedDate + "</td>");
            tr.append("<td>" + order.productCost + "</td>");

            tbody.append(tr);
        }
    };
});
