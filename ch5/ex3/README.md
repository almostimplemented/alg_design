Suppose you're consulting for a bank that's concerned about fraud detection, 
and they come to you with the following problem. They have a collection of
$n$ bank canrds that they've confiscated, suspecting them of being used in
fraud. Each bank card is a small plastic object, containing a magnetic stripe
with some encrypted data, and it corresponds to a unique account in the bank.
Each account can have many bank cards corresponding to it, and we'll say that
two bank cards are equivalent if they correspond to the same account.

It's very difficult to read the account number off a bank card directly, but
the bank has a high-tech ``equivalence tester'' that takes two bank cards and,
after performing some computations, determines whether they are equivalent.

Their question is the following: among the collection of $n$ cards, is there
a set of more than $n/2$ of them that are all equivalent to one another? Assume
that the only feasible operations you can do with the cards are to pick two of 
them and plug them in to the equivalence tester. Show how to decide the answer
to their question with only $O(n\log n) invocations of the equivalence tester.
