package com.kwonjh0406.joon_bank.domain.accountNumber;

import com.kwonjh0406.joon_bank.domain.account.entity.AccountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountNumberingService {

    private final AccountNumberRepository accountNumberRepository;

    @Autowired
    AccountNumberingService(AccountNumberRepository accountNumberRepository) {
        this.accountNumberRepository = accountNumberRepository;
    }

    public Long generateAccountNumber(AccountType accountType) {

        AccountNumber accountNumber = accountNumberRepository.findById(1L).get();
        accountNumber.setCountAccounts(accountNumber.getCountAccounts() + 1);

        StringBuilder sb = new StringBuilder();

        if(accountType == AccountType.CHECKING){
            sb.append("1");
            sb.append(String.format("%09d", accountNumber.getCountAccounts()));
            accountNumber.setChecking(accountNumber.getChecking() + 1);
        }
        else if(accountType == AccountType.SAVINGS){
            sb.append("2");
            sb.append(String.format("%09d", accountNumber.getCountAccounts()));
            accountNumber.setSaving(accountNumber.getSaving() + 1);
        }
        else if(accountType == AccountType.INSTALLMENT_SAVINGS){
            sb.append("3");
            sb.append(String.format("%09d", accountNumber.getCountAccounts()));
            accountNumber.setInstallmentSaving(accountNumber.getInstallmentSaving() + 1);
        }

        int checksum = sumOfDigits(sb);

        sb.append(String.format("%02d", checksum));

        accountNumberRepository.saveAndFlush(accountNumber);

        return Long.parseLong(sb.toString());

    }

    public static int sumOfDigits(StringBuilder numberBuilder) {
        int sum = 0;

        // StringBuilder를 문자열로 변환하여 각 문자를 순회
        for (char c : numberBuilder.toString().toCharArray()) {
            sum += Character.getNumericValue(c); // 문자를 숫자로 변환하여 합산
        }

        return sum;
    }

}
