
# BST and Hash Table Performance Comparison

## 1. Introduction

The purpose of this project is to implement a Binary Search Tree (BST) and a Hash Table and compare their performance for insertion, searching, and deletion.

Both data structures store unique integer values, but they organize and access the data differently.

## 2. Implementation

### Binary Search Tree (BST)

The BST stores smaller values in the left subtree and larger values in the right subtree. It uses recursive methods for insertion, searching, and deletion.

### Hash Table

The Hash Table uses a hash function to calculate a bucket index. Separate chaining with linked lists handles collisions. The table has 101 buckets and does not resize.

## 3. Time Complexity Analysis

Let n represent the number of elements.

| Operation | BST Best | BST Average* | BST Worst | Hash Table Best | Hash Table Average* | Hash Table Worst |
|---|---|---|---|---|---|---|
| Insertion | O(1) | O(log n) | O(n) | O(1) | O(1) | O(n) |
| Searching | O(1) | O(log n) | O(n) | O(1) | O(1) | O(n) |
| Deletion | O(1) | O(log n) | O(n) | O(1) | O(1) | O(n) |

*The BST average assumes a reasonably balanced tree. The Hash Table average assumes a good hash function and a controlled load factor. A basic BST can become unbalanced. The Hash Table's fixed capacity means its average performance can deteriorate as the number of elements grows.

## 4. Experimental Setup

Both data structures were tested with 100, 1,000, and 10,000 unique integer values.

Insertion, searching, and deletion were measured separately. Each measurement was repeated five times, and the average execution time was recorded in milliseconds.

The experiments were run using Java. Actual execution times can vary depending on hardware, the Java runtime, and system activity.

## 5. Experimental Results

| Elements | Structure | Insertion (ms) | Search (ms) | Deletion (ms) |
|---:|---|---:|---:|---:|
| 100 | BST | 0.136100 | 0.031960 | 0.031680 |
| 100 | Hash Table | 0.406700 | 0.063560 | 0.102240 |
| 1,000 | BST | 0.151780 | 0.126560 | 0.144920 |
| 1,000 | Hash Table | 0.378040 | 0.397560 | 0.189100 |
| 10,000 | BST | 2.435100 | 1.420560 | 1.178280 |
| 10,000 | Hash Table | 3.482600 | 3.348240 | 0.198420 |

## 6. Results Analysis

For 100 elements, the BST was faster for insertion, searching, and deletion. The Hash Table has additional overhead from calculating bucket indexes and managing linked lists.

For 1,000 elements, the BST was still faster in all three operations in this experiment.

For 10,000 elements, the BST was faster for insertion and searching. However, the Hash Table had a lower measured deletion time.

The Hash Table uses only 101 buckets and does not resize. When many values share the same bucket, insertion and searching may require checking multiple entries. This helps explain its slower insertion and searching times with larger datasets.

The deletion measurements depend on the particular data, collision distribution, and implementation. More repetitions and additional experiments would help determine whether the observed differences are consistent.

## 7. Why Performance Differs

Performance depends on several factors:

- Tree balance affects the BST's operation times.
- Hash collisions increase the number of entries that must be checked.
- Linked lists and tree nodes require memory and additional operations.
- Hardware, memory allocation, JVM optimizations, and system activity affect execution time.
- Small datasets may not show the theoretical advantages of either structure clearly.

## 8. Conclusion

In this experiment, the BST performed better for most operations, especially searching at larger input sizes. The Hash Table performed better for deletion with 10,000 elements in the measured run.

These results are specific to the implementations used. A Hash Table with resizing and a well-distributed hash function would likely perform differently. A balanced BST would also provide more predictable performance than a basic BST.

The experiment demonstrates that theoretical complexity is important, but implementation details and test conditions also affect actual execution time.
