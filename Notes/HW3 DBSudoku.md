> # HW3 DB/Sudoku

## Part A - Database

在`CommandLineInterface.java`里面已经给出了程序的大体框架，根据`handout`里面的样例去理解每一个操作对应的输出。然后需要理清楚`DBTable, DBRecord, DBBinding`三者之间的关系，并据此选择合适的数据结构。根据提示，`DBBinding`存储`key, value`，比如`name:Alien`这样的类型，键值之间通过冒号进行划分，还需要考虑键值前后包含的空格（`trim`操作），比如`year   : 1979 `这样的形式。`DBRecord`是键值的集合，比如下面这种形式：

```
name:Sense and Sensibility, stars:Emma Thompson, stars:Hugh Grant
```

共包含三个键值对，之间通过逗号进行分隔，相同的键可以对应不同的值，比如键为`stars`，分别对应`Emma Thompson`和`Hugh Grant`。`DBTable`是`DBRecord`的集合，也是在`CommandLineInterface.java`主要操作的对象。然后根据不同的命令用不同的函数进行处理。

* `r`命令：通过`createTableFromFile`来打开`movies.txt`文件，读取数据。在构造的过程中，如果`DBRecord`前面包含一个星号，则读取数据时候忽略掉，并修改相应的`selected`状态（虽然在`movies.txt`里面并不涉及）。
* `p`命令：覆盖三个类的`toString`方法，根据`handout`里的输出形式完成，对于`DBRecord`需要注意其包含一个`selected`变量，产生的影响是在每个`DBRecord`前面多出一个星号。
* `sa`命令：选择同时包含两个`DBBinding`的`DBRecord`，需要在`DBBinding`里面增加`equals`方法，根据提示，需要键相同，值只需要是原有存储内容的子串即可。一般时候`sa`操作会定义返回类型为`void`类型，但是注意到在`CommandLineInterface.java`里有变量`selectedLength`来记录有多少个被选择的记录，所以该操作对应的方法`selectAnd`的返回值应该是`int`类型。
* `so`命令：和`sa`命令形式几乎一致，需要改动的是判断选择的条件。
* `da`命令：直接清除`DBTable`里面的数据即可，注意`CommandLineInterface.java`里的`length, selectedLength`都要清零。
* `c`和`ds`和`du`命令：改变的是`DBRecord`里的`selected`变量，另外还影响`selectedLength`。

完成后的最终效果：


![database.png](https://i.loli.net/2021/03/24/pqOj89JlnIGwmDW.png)

## Part 2 -- ChunkList





























