$(document).ready(function () {

    $('.btn-confirm').click(function () {

        const TransferParam = {
            fromAccount: $('#fromAccount').val(),
            toAccount: $('#account-number').val(),
            amount: $('#transfer-amount').val(),
        };

        $.ajax({
            url: '/api/transfer',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(TransferParam),

            success: function () {
                alert('송금이 완료되었습니다!');
            },

            error: function (xhr) {
                alert(xhr.responseText);
            }

        });
    });
});
