## Project Definition
### General
Projects aims to benchmark and illustrate differences between different kind of search heaps. Also to present the advantages of different kind of structures for diffenret kind of data.

### Data Structures
The intention is to implement **Binomial heaps**, **Fibonacci heaps** and some classics like basic **Binary heaps**.
The time complexity will be ranging from constant time or O(log n) to O(n) depending on heap imlementation.

| *Opreation:*        | *Binary*   | *Fibonacci* | *Binomial* |
| --------------------|:----------:|:-----------:|:----------:|
| peek (return root)  |  O(1)      |  O(1)       | O(log n)   |
| pop (delete root)   |  O(log n)  |  O(log n)   | O(log n)   |
| insert              |  O(log n)  |  O(1)       | O(1)       |
| decrease key        |  O(log n)  |  O(1)       | O(log n)   |
| merge               |  O(n)      |  O(1)       | O(log n)   |

### Input and Output
Program will be a GUI-application and the user can choose what kind of heaps will be used for the benchmark and the order and the lenght of the test input.
The user can also select to use randomly generated data for the benchmark and the number of operation iterations. The appliation will show averages, worst and best case scenarios for different kinds of operations.
