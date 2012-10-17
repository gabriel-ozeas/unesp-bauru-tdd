Scenario: account transfer is authorized

Given a account with balance of 5000
And owner Gabriel

When owner request transfer of 4000 to ITAU
And owner authorize the transfer
And a transfer happens

Then the account has balance of 1000