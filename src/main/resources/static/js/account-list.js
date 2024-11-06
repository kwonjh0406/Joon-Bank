// 금액 표시/숨김 토글
function toggleVisibility() {
    const amounts = document.querySelectorAll('.amount');
    const icon = document.getElementById('visibility-icon');
    const text = document.getElementById('visibility-text');

    amounts.forEach(amount => {
        amount.classList.toggle('hidden-amount');
    });

    if (amounts[0].classList.contains('hidden-amount')) {
        icon.classList.remove('fa-eye');
        icon.classList.add('fa-eye-slash');
        text.textContent = '금액 보기';
    } else {
        icon.classList.remove('fa-eye-slash');
        icon.classList.add('fa-eye');
        text.textContent = '금액 숨기기';
    }
}

// 계좌 검색
function searchAccounts() {
    const input = document.getElementById('searchInput');
    const filter = input.value.toLowerCase();
    const tbody = document.querySelector('.account-table tbody');
    const rows = tbody.getElementsByTagName('tr');

    for (let row of rows) {
        const cells = row.getElementsByTagName('td');
        let found = false;

        for (let cell of cells) {
            const text = cell.textContent || cell.innerText;
            if (text.toLowerCase().indexOf(filter) > -1) {
                found = true;
                break;
            }
        }

        row.style.display = found ? '' : 'none';
    }
}

// 테이블 정렬
let sortDirection = {};

function sortTable(column) {
    const tbody = document.querySelector('.account-table tbody');
    const rows = Array.from(tbody.getElementsByTagName('tr'));

    // 정렬 방향 토글
    sortDirection[column] = sortDirection[column] === 'asc' ? 'desc' : 'asc';

    rows.sort((a, b) => {
        let aValue, bValue;

        if (column === 'balance') {
            // 금액 정렬을 위해 숫자만 추출
            aValue = parseInt(a.cells[3].getAttribute('data-amount').replace(/[^0-9]/g, ''));
            bValue = parseInt(b.cells[3].getAttribute('data-amount').replace(/[^0-9]/g, ''));
        } else {
            const cellIndex = column === 'bankName' ? 0 : 2;
            aValue = a.cells[cellIndex].textContent;
            bValue = b.cells[cellIndex].textContent;
        }

        if (sortDirection[column] === 'asc') {
            return aValue > bValue ? 1 : -1;
        } else {
            return aValue < bValue ? 1 : -1;
        }
    });

    // 정렬된 행을 다시 테이블에 추가
    rows.forEach(row => tbody.appendChild(row));

    // 정렬 아이콘 업데이트
    updateSortIcons(column);
}

function updateSortIcons(activeColumn) {
    const sortButtons = document.querySelectorAll('.sort-button');

    sortButtons.forEach(button => {
        const icon = button.querySelector('i');
        const column = button.onclick.toString().match(/sortTable\('(.+?)'\)/)[1];

        if (column === activeColumn) {
            icon.className = `fas fa-sort-${sortDirection[column] === 'asc' ? 'up' : 'down'}`;
        } else {
            icon.className = 'fas fa-sort';
        }
    });
}