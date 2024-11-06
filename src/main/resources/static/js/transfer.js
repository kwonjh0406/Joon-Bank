// $(document).ready(function () {
//
//     $('.btn-confirm').click(function () {
//
//         const TransferParam = {
//             fromAccount: $('#fromAccount').val(),
//             toAccount: $('#account-number').val(),
//             amount: $('#transfer-amount').val(),
//         };
//
//         $.ajax({
//             url: '/api/transfer',
//             type: 'POST',
//             contentType: 'application/json',
//             data: JSON.stringify(TransferParam),
//
//             success: function () {
//                 alert('송금이 완료되었습니다!');
//             },
//
//             error: function (xhr) {
//                 alert(xhr.responseText);
//             }
//
//         });
//     });
// });

$(document).ready(function () {
    const transferBtn = $('.btn-transfer');
    const modal = $('#confirm-modal');
    const btnConfirm = $('.btn-confirm');

    transferBtn.click(function () {
        const TransferParam = {
            fromAccount: $('#fromAccount').val(),
            toAccount: $('#account-number').val(),
            amount: $('#transfer-amount').val(),
        };

        // 입력값 검증 요청
        $.ajax({
            url: '/api/transfer/check',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(TransferParam),

            success: function (response) {
                // 검증이 성공적일 경우 모달에 정보 업데이트
                modal.find('.recipient-name').text(response.recipientName); // 응답에서 수신자 이름을 가져옵니다.
                modal.find('.recipient-account').text(response.recipientAccount); // 응답에서 수신자 계좌를 가져옵니다.
                modal.find('.transfer-summary strong').text(`${TransferParam.amount.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")}원`);

                modal.addClass('show'); // 모달을 띄움
            },

            error: function (xhr) {
                alert(xhr.responseText); // 오류 메시지 표시
            }
        });
    });

    btnConfirm.click(function () {
        const TransferParam = {
            fromAccount: $('#fromAccount').val(),
            toAccount: $('#account-number').val(),
            amount: $('#transfer-amount').val(),
        };

        // 송금 요청
        $.ajax({
            url: '/api/transfer',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(TransferParam),

            success: function () {
                alert('송금이 완료되었습니다!');
                modal.removeClass('show'); // 모달 닫기
            },

            error: function (xhr) {
                alert(xhr.responseText); // 오류 메시지 표시
            }
        });
    });

    // 모달 닫기 버튼
    modal.find('.btn-close, .btn-cancel').click(function () {
        modal.removeClass('show'); // 모달 닫기
    });
});
