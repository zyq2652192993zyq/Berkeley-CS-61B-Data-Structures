> # HW1 CodeCamp

第一个作业给人的感觉仿佛在做LeetCode的题目，在每个部分，handout里面给出了函数名，返回类型以及函数的参数，需要根据提示完成函数的内容。另外可以在项目里面引入`Junit`来进行单元测试。全部内容在IDEA内完成。

## Part A

### String Code

仅以此例说明如何在IDEA内完成函数的实现，并进行单元测试，其他将不再重复。

* 在IDEA内新建`porject`，命名为`HW1`。
* 在项目里有一个`src`目录，右键新建一个`package`，命名为`main`。
* 在`main package`下面新建`java class`，命名为`StringCode`
* 依次实现三个函数`blowup, maxRun,  stringIntersect`
* 在`File -> Settings -> Plugins`，安装插件`JUnitGenerator V2.0`
* 在GitHub下载`Junit`相关的`jar`包，并通过`File -> Porject Structure -> + -> Library -> java`，然后选择刚下载的`jar`包。
* 实现完三个函数后，右键选择`generate`，选择`junit4`，会自动在`src`下生成`test package`，并生成文件`StringCodeTest.java`，相应模板也一并生成。
* 完善对应的测试用例后，右键选择`Run StringCodeTest`，三个函数前均为绿色的对勾则表明通过测试。

![CodeCamp.png](https://i.loli.net/2021/02/18/UsfR6WboYKNEvJL.png)

`blowup`

类似于LeetCode里的字符串压缩的变形，不过更简单一些，这个相当于逆过程，需要注意的就是字符串为空，为`null`，以及最后一个字符这些边界条件即可，时间复杂度为$O(n)$。另外注意使用`StringBuilder`而不是`String`，因为`String`会发生频繁的拷贝，当字符串过长的时候会效率很低。

`maxRun`

题意相当于寻找连续的相同字符的最长字串，遍历一次即可，时间复杂度$O(n)$

`stringIntersect`

如果是暴力遍历，时间复杂度是$O(m^2n^2)$，很显然可以利用字符串哈希来做，不过Java里面没有无符号类型整数，所以利用无符号类型来做自然溢出不太可行。如果在`HashSet`里直接存储字串，当`len = n / 2`的时候，空间复杂度为$O(n^2)$，如果按照算法题来讲，可能会超过内存限制，所以可以把这些字串利用`hashCode()`函数转成`int`类型，然后存储在`HashSet`里，这样就节省了存储空间。

例外如果在C++里面在计算`hashCode`的时候是可以利用秦九韶算法来进行优化，这里因为直接使用函数`hashCode`，就无法优化了。

### CharGrid

`charArea`

寻找最小的矩形将字符`ch`包含，实际上只需要遍历一遍矩阵，找出`ch`出现的最小行数，列数，以及最大行数、列数即可，这样就找到了最大矩形，时间复杂度为$O(m, n)$。

`countPlus`

对于一个非空位置，按照上下左右这四个方向，分别计算`arm`的长度，取最小值即可，有点类似DFS。时间复杂度为$O((m + n)mn)$。

### TetrisGrid

相比于之前需要增加一个参数为`boolean [][]`的构造函数，`clearRows()`函数其实就是LeetCode去除重复数字的二维形式，只需要遍历一遍矩阵即可。根据题意，下标位于`0,0`相当于坐标轴的原点，相当于矩阵是围绕点`0, 0`逆时针旋转了90度。于是检查`grid`一行是否全是`true`的时候，实际上应该检查的是矩阵的列。用变量`preCol`记录第一个可以被替换的列，用`col`记录当前正在检查的列，如果检查列发现全是`true`，直接跳过，否则就将`col`列的内容覆盖掉`preCol`列的内容，然后`preCol`位置加一，这样时间复杂度为$O(mn)$。当然最后记得从`preCol`到`colNum - 1`这些列都要填充为`flase`。

在写单元测试的时候，需要检查结果数组和更新后的`grid`内的元素是否一致，就需要用到讲义`intermediate Java`里涉及的`Arrays.deepEquals()`。

### Appearances

添加一个静态方法`sameCount()`，参数为两个泛型的集合，去计算在两个集合里同时出现，并且出现次数相同的元素的个数，用哈希表`HashMap`的键存储元素，值记录次数，分别遍历两个集合，得到两个哈希表，然后遍历其中一个哈希表，去检查当前的键是否在另一个哈希表里存在，如果也存在就接着检查出现的频率即可，可以认为这个问题是为了让我们熟悉`HashMap`的基本操作。命名为`um`是因为等价于C++里的`unordered_map`。

### Taboo

需要实现三个函数，带参数的构造函数，`noFollow`和`reduce`

`constructor`

带参数的构造函数，传入一个`list`，这个`list`包含了设定的规则，维护一个哈希表，键为类型`T`，值为集合`set`，主要是便于在`noFollow`的实现中使用。

`noFollow`

传入类型`T`的元素，根据规则输出在`rule`里面，在当前元素紧随身后的元素，返回一个集合。只需要查询在哈希表里是否存在为当前元素的键，如果存在则直接返回对应的集合，否则返回为`Collections.emptySet()`。

`reduce`

考察的是使用迭代器对`List`进行删除的操作，用`key`记录当前`value`的前一个元素，然后判断是否满足`rule`的要求即可。

## Part B

根据`handout`的内容，设计一个`Shape class`，需要实现的功能有：

* 设计一个构造函数，可以通过输入`String`参数来实现构造，实际上，用一个`ArrayList`来存储`Shape`中的点，`handout`说会提供一个`Point.java`的文件，但是并没有找到，所以就自己手动实现`Point`类型，主要参考了`awt`中的`Point`类，但是由于`awt`中`Point`类的构造函数参数类型为`int`，而我们需要的是`double`类型，所以才不选用`awt`中的`Point`。`Point`提供的功能
  * 三个构造函数，默认构造函数，提供`double`类型的横纵坐标的带参数构造函数（利用`Scanner`来实现），根据一个已知的`Point`类型来构造。最后一个构造函数因为要访问这个已知类型的横纵坐标，所以还需要提供相应的`getX, getY`来访问。同时还需要计算出圆的相关信息，横纵坐标为输入点的序列横纵坐标均值，半径为距离圆心最近的点和圆心之间的距离，所以用辅助函数`calculateRadius`来计算`radius`。
  * `equals`函数虽然在`awt`中有提供，但是在本案例中并不需要，所以暂不实现
  * `distance`函数，用来计算两个点之间的欧几里得距离，主要用来辅助判断点和圆的关系时用到
  * `toString`函数，输出一些信息，可以用来`debug`。
* 实现函数`crossess`来判断`Shape A`中的线段，是否存在线段的一个端点在`Shape B`的内部，另一个端点在圆的外部。所以显然需要存储圆的信息，这里为了简便没有单独实现一个类，而是存储了圆心和半径的信息。点和圆的关系有三种：点在圆内部，圆上，圆外。这里用了一个额外的`private`函数`isInCircle`来辅助实现，参数为一个`Shape`类型，一个`Point`类型，用来判断点是否在`Shape`的圆内。
* 实现函数`encircles`，用来判断两个`Shape`的圆的关系，实际上就计算圆心之间的距离，与半径之间的关系即可。
* 实现`ShapeClient.java`，按照`handout`里的指示，构造四个`Shape`，最后打印相关信息即可。











