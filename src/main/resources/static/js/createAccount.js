$(document).ready(function () {

    $('.create-account-button').click(function () {

        const CreateAccountParam = {
            accountName: $('#nickname').val(),
            accountType: $('input[name="bank"]:checked').val()



        };

        $.ajax({
            url: '/api/account/createAccount',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(CreateAccountParam),

            success: function () {
                alert('계좌가 개설되었습니다!');
                location.href = '/home';
            },

            error: function (xhr) {
                alert(xhr.responseText);
            }

        });
    });
});
