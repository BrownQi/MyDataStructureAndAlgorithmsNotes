## 稀疏数组
### 1.定义
像如下这种无效数据远大于有效数据的数组，可以压缩成稀疏数组
~~~
0	0	0	0	0	0	0	0	0	0	0	
0	0	1	0	0	0	0	0	0	0	0	
0	0	0	2	0	0	0	0	0	0	0	
0	0	0	0	0	0	0	0	0	0	0	
0	0	0	0	0	0	0	0	0	0	0	
0	0	0	0	0	0	0	0	0	0	0	
0	0	0	0	0	0	0	0	0	0	0	
0	0	0	0	0	0	0	0	0	0	0	
0	0	0	0	0	0	0	0	0	0	0	
0	0	0	0	0	0	0	0	0	0	0	
0	0	0	0	0	0	0	0	0	0	0	
~~~
压缩之后得到的稀疏数组：
~~~
11	11	2	
1	2	1	
2	3	2	
~~~
- 其中第一行数组为原数组的基本信息，依次为 行数、列数、有效数据数。
- 稀疏数组的第二行开始，记录了有有效数据的存放位置及其值。例如`1  2   1`代表这个有效数据存放在第一行第二列，其值为1；

### 2. 稀疏数组的转换（思路）
例题：完成上述稀疏数组的转换，使用二维数组存储，并实现稀疏数组的读写操作。
1. 普通数组->稀疏数组
    - 稀疏数组为二维数组，其中列数为3，行数为`有效数据总数+1(表头信息)`。
    - 遍历普通数组，记录有效数据个数（此时有效数据个数还未得到，稀疏数组不能创建，可以先将有效数据的信息缓存到ArrayList里，避免之后再一次循环浪费效率（个人理解））
    - 根据得到的有效数据个数，创建稀疏数组，并将缓存的有效数据信息传入稀疏数组里。
2. 稀疏数组->普通数组
    - 处理稀疏数组表头信息，创建普通数组
    - 将稀疏数组保存的有效数据，依次修改普通数组相应的值
    
### 3. 具体实现
1. 普通数组->稀疏数组
    ~~~java
    //将数组压缩为稀疏数组
        public static int[][] toSparesArray(int[][] arrs) {
            if (arrs == null) {
                return null;
            }
    
            int sum = 0;
            ArrayList<int[]> temp = new ArrayList<>();
            for (int i = 0; i < arrs.length; i++) {
                for (int j = 0; j < arrs[i].length; j++) {
                    if (arrs[i][j] != 0) {
                        sum++;
                        temp.add(new int[]{i, j, arrs[i][j]});
                    }
                }
            }
            if ((sum + 1) * 3 >= arrs.length * arrs[0].length) {
                System.err.println("压缩为稀疏数组后所需的存储空间更大，返回原数组");
                return arrs;
            }
    
            int[][] sparseArray = new int[sum + 1][3];
    
            sparseArray[0] = new int[]{arrs.length, arrs[0].length, sum};
    
            Iterator<int[]> its = temp.iterator();
            for (int i = 1; i < sparseArray.length; i++) {
                sparseArray[i] = its.next();
            }
            return sparseArray;
        }
    ~~~
2. 稀疏数组->普通数组
    ~~~java
    //将稀疏数组解压成普通数组
        public static int[][] toArray(int[][] sparseArray) {
            int[][] arrs = new int[sparseArray[0][0]][sparseArray[0][1]];
    
            for (int i = 1; i <= sparseArray[0][2]; i++) {
                arrs[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
            }
            return arrs;
        }
    ~~~
3. 将稀疏数组写入到本地(map.data)
    ~~~java
    //将稀疏数组写入到本地
        public static void writeSparseArray(int[][] sparseArray, File file) throws IOException {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < sparseArray.length; i++) {
                String line = String.format("%d\t%d\t%d", sparseArray[i][0], sparseArray[i][1], sparseArray[i][2]);
                bw.write(line);
                bw.newLine();
    
            }
            bw.close();
            fw.close();
        }
    ~~~
4. 从本地读取稀疏数组(map.data)
    ~~~java
    //从本地读取稀疏数组
        public static int[][] readSparseArray(File file) throws IOException {
    
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            //rows 用于临时存放读取到的 每一行 sum用于计算总行数
            ArrayList<int[]> rows = new ArrayList<>();
            int sum = 0;
            while ((line = br.readLine()) != null) {
                String[] split = line.split("\t");
                rows.add(new int[3]);
                for (int i = 0; i < split.length; i++) {
                    rows.get(sum)[i] = Integer.valueOf(split[i]);
                }
                sum++;
            }
            int[][] sparseArray = new int[sum][3];
            Iterator<int[]> its = rows.iterator();
            for (int i = 0; i < sparseArray.length; i++) {
                sparseArray[i] = its.next();
            }
            br.close();
            fr.close();
            return sparseArray;
        }
    ~~~
5. 测试
    ~~~java
        public static void main(String[] args) throws IOException {
    
            int[][] chessArr = new int[11][11];
            chessArr[1][2] = 1;
            chessArr[2][3] = 2;
    
            //输出原始的二维数组
            System.out.println("原始的二维数组：");
            for (int[] row : chessArr) {
                for (int data : row) {
                    System.out.printf("%d\t", data);
                }
                System.out.println();
            }
            System.out.println("******************************");
            System.out.println("稀疏数组：");
            int[][] ints = toSparesArray(chessArr);
            for (int[] row : ints) {
                for (int data : row) {
                    System.out.print(data + "\t");
                }
                System.out.println();
            }
    
            System.out.println("******************************");
            System.out.println("稀疏数组解压：");
            int[][] ints1 = toArray(ints);
            for (int[] row : ints1) {
                for (int data : row) {
                    System.out.print(data + "\t");
                }
                System.out.println();
            }
            System.out.println("******************************");
    
            File file = new File("W:\workSpace\IdeaProjects\Data Structures And Algorithm\src\cn\brownqi\datastructure\sparsearray\map.data");
            writeSparseArray(ints, file);
    
            System.out.println("从磁盘读取的稀疏数组：");
            int[][] ints2 = readSparseArray(file);
            for (int[] row :
                    ints2) {
                for (int data :
                        row) {
                    System.out.print(data + "\t");
                }
                System.out.println();
            }
        }
    ~~~
