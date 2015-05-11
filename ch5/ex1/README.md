You are interested in analyzing some hard-to-obtain data from two separate
databases. Each database contains $n$ numerical values - so there are $2n$
values total - and you may assume that no two values are the same. You'd
like to determine the median value of this set of $2n$ values, which we will
define here to be the $n^{th}$ smallest value.

However, the only way you can access these values is through queries to the
databases. In a singule query, you can specify a value $k$ to one of the two
databases, and the chosen databse will return the $k^{th}$ smallest value
that it contains. Since queries are expensive, you would like to compute the
median using as few queries as possible.

Give an algorithm that finds the median value using at most $O(\log n)$
queries.
