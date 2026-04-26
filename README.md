# Code Exercise – Risk Clusters

In an n x n grid, each cell contains an integer representing a risk level. In this context, a risk cluster consists of
4 (four) consecutive cells positioned next to each other either horizontally, vertically or diagonally.
A cluster is considered significant if the average risk value of the four cells is greater than or equal to 70.
In the following example, a significant cluster could be identified from a starting position and continuing in
one of the valid directions, producing four connected values that meet the threshold requirement.
Given the file “riskgrid.txt”, containing an n x n grid:

1. Parse the file into a suitable format
2. Identify all significant risk clusters
3. Return the clusters sorted by highest average risk value
4. For each cluster, include the risk values, their indices and the direction of the cluster.
   
If two clusters have the same average risk value, prioritize the cluster containing the highest individual risk
value. If a tie still exists, prioritize the cluster with the earliest starting position.
