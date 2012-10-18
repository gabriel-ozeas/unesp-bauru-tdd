Scenario: account transfer is authorized by owner

Given a account with balance of 5000
And owner Gabriel
When owner request transfer of 4000 to ITAU
And owner authorize the transfer
And a transfer happens

Then the account has balance of 1000

Scenario: account transfer above 10000 is authorized by owner but not by partners

Given a account with balance of 80000
And partners Joao,Maria
And owner Gabriel
When owner request transfer of 20000 to ITAU
And owner authorize the transfer
And a transfer happens

Then the account has balance of 80000
