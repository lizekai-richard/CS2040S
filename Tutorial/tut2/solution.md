# Tutorial2  Solution

## Problem 1.   Sorting Review

### Q1(a)

```java
public void InsertionSort(int[] arr, int n) {
	if(n == 1) {
		return ;
	}
	InsertionSort(arr, n - 1);
	Insert(arr, n - 1, arr[n - 1]);		
}
```

Recurrence relation for time complexity
$$
T(n)\ =\ T(n-1) + O(n) \\
T(n)\ =\ O(n^2)
$$

### Q1(b)

This question is designed for understanding the stability of sorting algorithm.

The solution is: **first sort on $b$ using selection sort, then sort on $a$ using merge sort.**

Let's think about why can't be other way around. First of all, selection sort is unstable, thus if we do it other way around, sorting on $a$ using selection sort might change the order of $b$. We have an example below.
$$
[(2,1), (1,4), (1,3)]
$$
If first sort on $b$ using selection sort, then sort on $a$ using merge sort.
$$
[(2,1), (1,4), (1,3)]\ \rightarrow\ [(2,1),(1,3),(1,4)]\ \rightarrow\ [(1,3), (1,4), (2,1)]
$$
 If first sort on $b$ using merge sort, then sort on $a$ using selection sort.
$$
[(2,1), (1,4), (1,3)]\ \rightarrow\ [(2,1),(1,3),(1,4)]\ \rightarrow\ [(1,4), (1,3), (2,1)]
$$

### Q1(c)

Implementing merge sort iteratively is a bottom-up process. Let's first recall how recursive process goes: Keep dividing into half until we reach the base case, then merge from small to big.

Then, iterative process is just the reverse! We can enumerate the size of array that we want to merge, call it $cur\_size$, from $1$ to $n$ . For each $cur\_size$, we find start index and end index of every pair of sub arrays (left part and right part, since we need to merge two arrays). Then merge every subarray. After each iteration, multiply $cur\_size$ by 2, which is just the reverse of dividing into half in recursive process.

```java
static void mergeSort(int arr[], int n) {
         
    // For current size of subarrays to
    // be merged curr_size varies from 
    // 1 to n/2
    int curr_size; 

    // For picking starting index of 
    // left subarray to be merged
    int left_start;

    for (curr_size = 1; curr_size <= n-1; curr_size = 2*curr_size)
    {

        // Pick start index of different
        // subarrays of current size
        for (left_start = 0; left_start < n-1;
             left_start += 2*curr_size)
        {
            // Find end index of left 
            // subarray. mid+1 is starting 
            // point of right
            int mid = Math.min(left_start + curr_size - 1, n-1);
            // Find the end index of the right subarray
            int right_end = Math.min(left_start + 2*curr_size - 1, n - 1);

            merge(arr, left_start, mid, mid + 1, right_end);
        }
    }
}
```

##  Problem 2.     Stack & Queue

### Q2 (a)

#### Implementation for Stack

```java
public class Stack {
    private int length = 0;
    private final int[] stack;

    public Stack(int maxLength) {
        this.stack = new int[maxLength];
    }
    
    public int getLength() {
        return length;
    }

    public void push(int element) {
        stack[length] = element;
        length++;
    }

    public void pop() {
        if(isEmpty()) {
            System.out.println("StackError! The stack is already empty!");
        } else {
            length--;
        }
    }

    public int peek() {
        return stack[length - 1];
    }

    public boolean isEmpty() {
        return length == 0;
    }
}
```

#### Implementation for Queue

```java
public class Queue {
    private int head = 0;
    private  int tail = 0;
    private final int[] queue;
    private final int maxLength;

    public Queue(int maxLength) {
        this.maxLength = maxLength;
        this.queue = new int[maxLength];
    }
    public int getLength() {
        return tail - head;
    }

    public void enqueue(int element) {
        queue[tail] = element;
        tail = (tail + 1) % maxLength;
    }

    public int dequeue() {
        if(isEmpty()) {
            System.out.println("Error! The queue is already empty!");
            return -1;
        } else {
            head = (head + 1) % maxLength;
            return queue[head - 1];
        }
    }

    public int peek() {
        return queue[head];
    }

    public boolean isEmpty() {
        return head == tail;
    }
}

```

### Q2 (b)

```java
public class Deque {
    private int head = 0;
    private int tail = 0;
    private final int[] deque;
    private final int maxLength;
	
    public Deque(int maxLength) {
        this.maxLength = maxLength;
        this.deque = new int[maxLength];
    }
    
    public int getLength() {
        return tail - head;
    }

    public void enqueueFront(int element) {
        head = (head - 1) % maxLength;
        deque[head] = element;
    }

    public void enqueueBack(int element) {
        if(isEmpty()) {
            System.out.println("Error! The queue is already empty!");
        } else {
            deque[tail] = element;
            tail = (tail + 1) % maxLength;
        }
    }

    public void dequeueFront() {
        if(isEmpty()) {
            System.out.println("Error! The queue is already empty!");
        } else {
            head = (head + 1) % maxLength;
        }
    }

    public void dequeueBack(int element) {
        if(isEmpty()) {
            System.out.println("Error! The queue is already empty!");
        } else {
            tail = (tail - 1) % maxLength;
        }
    }

    public int peek() {
        return deque[head];
    }

    public boolean isEmpty() {
        return head == tail;
    }
}

```

### Q2 (c)

Edge case like the stack or queue is already empty but we still carry out pop or deq operation.

### Q2 (d)

This is a very classical problem: Bracket Matching. Using a stack, we can solve this easily.

Go through every bracket in the given string. If it's a left bracket, push it into the stack. Otherwise, pop the top from the stack. In the end, check if the stack is empty. If it's empty, then it's matching. Otherwise, not matching. If the stack becomes empty in the middle, it's also not matching.

## Problem 3.  Trapping Rain Water

This question is an application of **monotonic stack**. A monotonic stack is a stack that stores increasing or decreasing elements. Let's now see how it can be used in this question.

First, let's claim the property that a house $j$ will be flooded: 
$$
\exist{i,\ k}\ \ \ i \lt j \lt k\ \ \ \ \ such\ \  that\  \ h[i]\ \lt\ h[j]\ \lt\ h[k]\ \ \ 
$$
We maintain a monotonic stack $s$ which stores decreasing heights of houses. Then we go through every house $i$ :

1. If $s$ is empty, then push $h[i]$ into $s$.
2. If $s$ is non-empty, then pop all elements that are smaller than $h[i]$. Then push $h[i]$ into $s$. Meanwhile, we need to keep track of the maximum element in $s$.
3. Add the number of elements popped in step 2 to the final answer. However, we also need to count the number of maximum elements popped and let the final answer minus this number.

<img src="../../../../../AppData/Roaming/Typora/typora-user-images/image-20220227175945989.png" alt="image-20220227175945989" style="zoom:67%;" />

The image above illustrates why we need to consider the maximum element. We can see that the house corresponding to the maximum element won't be flooded.

A similar problem on LeetCode: https://leetcode.com/problems/trapping-rain-water/

## Problem 4.  Sorting with Queues

This question has the same idea as the iterative merge sort.

We sort from size $2, 4, 8,\dots,2^{n}$. Let's take size = 2 as an example to illustrate the process. 

First, deq the first half of the size to the extra queue. Then we do the merge operation on the first half and the second half: compare the head of two queues each time, enq the smaller one to the original queue.

Here is an example:

<img src="../../../../../AppData/Roaming/Typora/typora-user-images/image-20220227181048214.png" alt="image-20220227181048214" style="zoom:67%;" />

deq the first half and enq it to the extra queue

<img src="../../../../../AppData/Roaming/Typora/typora-user-images/image-20220227181114332.png" alt="image-20220227181114332"  />

merge, compare the heads each time, enq the smaller one to the original queue

![image-20220227181250992](../../../../../AppData/Roaming/Typora/typora-user-images/image-20220227181250992.png)

![image-20220227181423786](../../../../../AppData/Roaming/Typora/typora-user-images/image-20220227181423786.png)

![image-20220227181325099](../../../../../AppData/Roaming/Typora/typora-user-images/image-20220227181325099.png)