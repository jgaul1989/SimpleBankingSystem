<h1> Simple Banking System </h1>
<p>
The application is simple bank account manager that stores all data in a sqlite database. CRUD actions are performed on the database using JDBC. Valid bank account numbers are generated and verified with the Luhn algorithim. Users have the ability to create an account, update their account balance and transfer money to other valid accounts.
</p>
<br>

Program Example:

<br>

1. Create an account
2. Log into account
0. Exit
>1

Your card has been created

Your card number:

4000009455296122

Your card PIN:

1961

1. Create an account
2. Log into account
0. Exit
>1

Your card has been created

Your card number:

4000003305160034

Your card PIN:

5639

1. Create an account
2. Log into account
0. Exit
>2

Enter your card number:

>4000009455296122

Enter your PIN:

>1961

You have successfully logged in!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>2

Enter income:

>10000

Income was added!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>1


Balance: 10000


1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>3

Transfer

Enter card number:

>4000003305160035

Probably you made a mistake in the card number. Please try again!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>3

Transfer

Enter card number:

>4000003305061034

Such a card does not exist.

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>3

Transfer

Enter card number:

>4000003305160034

Enter how much money you want to transfer:

>15000

Not enough money!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>3

Transfer

Enter card number:

>4000003305160034

Enter how much money you want to transfer:

>5000

Success!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>1

Balance: 5000

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit

>0
Bye!
