Recall the problem of finding the number of inversions. As in the text,
we are given a sequence of $n$ numbers $a_1, ..., a_n$, which we assume
are all distinct, and we define an inversion to be a pair $i < j$ such
that $a_i > a_j$. 

We motivated the problem of counting inversions as a good measure of
how different two orderings are. However, one might feel that this
measure is too sensitive. LEt's call a pair a significant inversion if
$i < j$ and $a_i > 2a_j$. Give an $O(n \log n)$ algorithm to count
the number of significant inversions between two orderings.
