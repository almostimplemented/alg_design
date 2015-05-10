Hidden surface removal is a problem in computer graphics that scarcely
needs an introduction: when Woody is standing in front of Buzz, you
should be able to see Woody but not Buzz; when Buzz is standing in
front of Woody, ... well you get the idea.

The magic of hidden surface removal is that you can often compute
things faster than your intuition suggests. Here's a clean geometric
example to illustrate a basic speed-up that can be achieved. You are
given n nonvertical lines in the plane, labeled $L_1, ..., L_n$, with 
the $i^{th}$ line specified by the equation $y = a_i x + b_i$. We will
make the assumption that no three of the lines all meet at a single
point. We say line $L_i$ is uppermost at a given $x$-coordinate $x_0$ if
its $y$-coordinate at $x_0$ is greater than the $y$-coordinates of all the
other lines at $x_0$: $a_i x_0 + b_i > a_j x_0 b_j$ for all $j \neq i$. We
say line $L_i$ is visible if there is some $x$-coordinate at which it is
uppermost-intuitively, some portion of it can be seen if you look
down from "$y = \infty$".

Give an algorithm that takes $n$ lines as input and in $O(n\log n)$
time returns all of the ones that are visible.
