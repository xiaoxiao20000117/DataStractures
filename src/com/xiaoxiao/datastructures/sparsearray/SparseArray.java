package com.xiaoxiao.datastructures.sparsearray;

//稀疏数组数据结构的实现
//主要的作用是压缩数据，优化存储
public class SparseArray {
    public static void main(String[] args) {
        int chessArray1[][] = new int[11][11];
        chessArray1[1][2] = 1;
        chessArray1[2][3] = 2;
        chessArray1[4][8] = 2;
        int sum = 0;

        System.out.println("原始数组");
        for (int[] row: chessArray1 ){
            for (int data: row){
                if (data != 0){
                    sum++;
                }
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //对应的稀疏数组
        //注意推到到一般情况，行数=数组名.length,列数=数组名[0].length
        int sparseArray[][] = new int[sum+1][3];
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;

        //用于累计稀疏数组的行数
        int count = 0;

        for (int i = 0; i < 11; i++){
            for (int j = 0; j < 11; j++){
                if (chessArray1[i][j] != 0){
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArray1[i][j];
                }
            }
        }

        System.out.println("生成的稀疏数组");
        for (int[] row: sparseArray) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        int chessArray2[][] = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i < sparseArray.length; i++) {
            chessArray2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }

        System.out.println("由稀疏数组还原的原始数组");
        for (int[] row: chessArray2 ){
            for (int data: row){
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
