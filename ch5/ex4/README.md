You've been working with some physicists who need to study, as part of
their experimental design, the interactions among a large number of very
small charged particles. Basically, their setup works as follows. They
have an inert lattice structure, and they use this for placing charged
particles at regular spacing along a straight line. Thus we can model
their structure as consisting of the points $\{1, 2, 3, ..., n\}$ on
the real line; and at each point $j$ they have a particle with charge
$q_j$. 

They want to study the total force on each particle, by measuring it
and comparing then comparing it to a computational prediction. This
computational part is where they need your help. The total net force on
particle $j$, by Coulomb's Law, is equal to

F_j = sum {i < j} (C q_i q_j)/(j - i)^2 - sum {i > j} (C q_i q_j)/(j - i)^2

They've written the following simple program to compute $F_j$ for all $j$:

For j = 1, 2, ..., n
  Initialize F_j to 0
  For i = 1, 2, ..., n
    If i < j then
      Add C q_i q_j / (j - i)^2 to F_j
    Else if i > j then
      Add - C q_i q_j / (j - i)^2 to F_j
    Endif
  Endfor
  Output F_j
Endfor

It's not hard to analyze the running time of this program: each
invocation of the inner loop, over $i$, takes $O(n)$ time, and
this inner loop is invoked $O(n)$ times total, so the overall
running time is $O(n^2)$.

The trouble is, for the large values of $n$ they're working with,
the program takes several minutes to run. On the otherhand, their
experimental setup is optimized so that they can throw down $n$ 
particles, perform measurements, and be ready to handle $n$ more
particles within a few seconds. So they'd really like it if there 
were a way to compute all the forces $F_j$ much more quickly, so
as to keep up with the rate of the experiment.

Help them out by designing an algorithm that computes all the forces 
$F_j$ in $O(n\log n)$ time.
