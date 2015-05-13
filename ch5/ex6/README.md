Consider an $n$-node complete binary tree $T$, where $n = 2d − 1$ for some $d$. 
Each node $v$ of $T$ is labeled with a real number $x_v$. You may assume that 
the real numbers labeling the nodes are all distinct. A node $v$ of $T$ is a 
local minimum if the label $x_v$ is less than the label $x_w$ for all nodes $w$
that are joined to $v$ by an edge.

You are given such a complete binary tree $T$, but the labeling is only specified
in the following implicit way: for each node $v$, you can determine the value 
$x_v$ by probing the node $v$. Show how to find a local minimum of $T$ using only
$O(\log n)$ probes to the nodes of $T$.
