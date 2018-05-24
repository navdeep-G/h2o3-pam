# Partitioning Around Medoids (PAM)

## Implementation of Partitioning Around Medoids (PAM) using h2o-3

### Pros:
- Deterministic, easy to verify against R implementation
- Outperforms kmeans for some applications

### Cons:
- Inherently `O(n^2)` time, and possibly space depending on implementation. 

### Reference: 
* pp.102-104 of `Finding Groups in Data by Kaufman and Rousseeuw`.