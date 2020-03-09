package datastructure.sparsearray;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @Description: 需求：用二维数组表示棋盘，棋盘大小为11*11，0表示没有棋子，1表示黑子 2表示白子。
 * 1. 将普通二维数组压缩成稀疏数组
 * 2. 从稀疏数组解压成普通棋盘
 * 3. 完成稀疏数组的存储与读取
 * @Author: BrownQi
 * @date: 2020-03-09 10:41
 */
public class SparseArray {
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

        File file = new File("W:\\workSpace\\IdeaProjects\\Data Structures And Algorithm\\src\\cn\\brownqi\\datastructure\\sparsearray\\map.data");
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

    //将稀疏数组解压成普通数组
    public static int[][] toArray(int[][] sparseArray) {
        int[][] arrs = new int[sparseArray[0][0]][sparseArray[0][1]];

        for (int i = 1; i <= sparseArray[0][2]; i++) {
            arrs[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        return arrs;
    }

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
}
