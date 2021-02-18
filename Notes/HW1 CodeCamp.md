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

对于一个非空位置，按照上下左右这四个方向，分别计算`arm`的长度，取最小值即可。时间复杂度为$O((m + n)mn)$。

### TetrisGrid







