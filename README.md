# Partitioning Around Medoids (PAM) Algorithm

## Overview

This repository provides an implementation of the Partitioning Around Medoids (PAM) algorithm in [H2O-3](https://github.com/h2oai/h2o-3). PAM is a clustering algorithm that is widely used for data analysis due to its ability to identify representative points (medoids) within a dataset, making it particularly useful for clustering applications where the preservation of data integrity in each cluster's centroid is critical.

## What is PAM?

Partitioning Around Medoids (PAM) is a clustering technique similar to K-means but instead focuses on selecting actual data points (medoids) as the center of clusters, rather than centroids which may not be actual data points. This characteristic makes PAM more robust to noise and outliers, particularly in datasets with non-globular shapes or in high-dimensional spaces.

The PAM algorithm minimizes the sum of dissimilarities between points in a cluster and the medoid of that cluster, leading to more stable and interpretable clusters.

## Features of PAM

- **Deterministic Output**: PAM consistently produces the same clusters for the same data, avoiding random initialization issues seen in K-means.
- **Robustness to Outliers**: By using actual points as medoids, PAM is more resilient to outliers than K-means, which can be influenced significantly by extreme values.
- **Interpretability**: Medoids serve as representative examples, making it easier to understand and interpret each cluster.

## Pros and Cons of PAM

### Pros
- **Deterministic**: PAM’s output is consistent, ensuring reproducibility.
- **Better for Some Applications**: PAM can outperform K-means in scenarios where preserving data integrity in cluster centers is crucial, especially for non-spherical clusters or datasets with outliers.

### Cons
- **Computational Complexity**: The algorithm has a time complexity of `O(n^2)`, making it less scalable for very large datasets compared to K-means.
- **Potentially High Memory Usage**: Depending on the implementation, PAM may also exhibit `O(n^2)` space complexity, posing challenges for high-dimensional or large-scale data.

## Usage in H2O-3

This implementation of PAM is part of the H2O-3 machine learning platform, leveraging H2O’s scalability and efficiency to make PAM more accessible and usable on large datasets. Visit the [H2O-3 GitHub repository](https://github.com/h2oai/h2o-3) for installation and setup instructions.

## References

To learn more about PAM and its theoretical foundation, you can refer to the following resources:

- Kaufman, L., & Rousseeuw, P.J. (1990). *Finding Groups in Data: An Introduction to Cluster Analysis* (pp. 102-104).
- [Wikipedia: K-medoids](https://en.wikipedia.org/wiki/K-medoids)
- [Wikibooks: Data Mining Algorithms in R – Partitioning Around Medoids (PAM)](https://en.wikibooks.org/wiki/Data_Mining_Algorithms_In_R/Clustering/Partitioning_Around_Medoids_(PAM))
