
function getQueryParams(qs) {
    qs = qs.split("+").join(" ");
    var params = {},
        tokens,
        re = /[?&]?([^=]+)=([^&]*)/g;

    while (tokens = re.exec(qs)) {
        params[decodeURIComponent(tokens[1])]
            = decodeURIComponent(tokens[2]);
    }
    return params;
}
var $_GET = getQueryParams(document.location.search);

const api_endpoint = "http://localhost:8080/invoice/";

document.addEventListener('DOMContentLoaded', function() {
    var invoiceid = $_GET['invoiceid'];
    if (invoiceid !== undefined) {
        retrieveAndShowInvoice(invoiceid)
    }

    document.querySelector("#invoiceGetter")
        .addEventListener('submit', function(evt) {
            evt.preventDefault();
            const invoiceid = document.querySelector('#invoiceid').value
            retrieveAndShowInvoice(invoiceid)

        })
});

function retrieveAndShowInvoice(invoiceid) {
    const url= api_endpoint + invoiceid
    const description = document.querySelector('.desc-invoice')
    description.innerHTML = 'Showing invoice ID '+ invoiceid

    fetch(url)
        .then(function(response) {
            return response.json()
        })
        .then(function(invoice){
            var details = document.querySelector('.invoice-details');
            if (invoice.status !== undefined) {
                details.innerHTML = '<p><strong>No Invoice Found!</strong></p>'
            } else {
                details.innerHTML = '<p>Invoice ID ' + invoice.id + ' has amount $' + invoice.amount + '</p>'
            }
        });

}