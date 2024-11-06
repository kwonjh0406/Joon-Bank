$(document).ready(function () {

    $('#signup-button').click(function () {

        const SignUpParam = {
            name: $('#name').val(),
            phoneNumber: $('#phoneNumber').val(),
            username: $('#username').val(),
            password: $('#password').val()
        };

        $.ajax({
            url: '/api/user/signup',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(SignUpParam),

            success: function () {
                alert('회원가입이 완료되었습니다!');
                location.href = '/login';
            },

            error: function (xhr) {
                alert(xhr.responseText);
            }

        });
    });
});
