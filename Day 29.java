/*
01 Matrix
Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.
Example 1:
Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
Output: [[0,0,0],[0,1,0],[0,0,0]]
Example 2:
Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
Output: [[0,0,0],[0,1,0],[1,2,1]]
 
Constraints:
m == mat.length
n == mat[i].length
1 <= m, n <= 104
1 <= m * n <= 104
mat[i][j] is either 0 or 1.
There is at least one 0 in mat.
*/
class Solution {
    public int[][] updateMatrix(int[][] mat) {
        //Using BFS. 
        int row = mat.length, col = mat[0].length;
        Queue <int[]> queue = new LinkedList<>();
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(mat[i][j] == 0) queue.add(new int[]{i, j});
                else mat[i][j] = -1;
            }
        }
        int level = 0;
        int directions[][] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
         while(!queue.isEmpty()){
             int size = queue.size();
             level++;
             for(int i = 0; i < size; i++){
                 int currPosition[] = queue.poll();
                 for(int dir[] : directions){
                     int r = currPosition[0] + dir[0];
                     int c = currPosition[1] + dir[1];
                     if(r < 0 || r == row || c < 0 || c == col || mat[r][c] != -1) continue;
                     mat[r][c] = level;
                     queue.add(new int[]{r, c});
                 }
             }
             
         }
          return mat;      
    }
}

//Alternative Solution
class Solution {
    public int[][] updateMatrix(int[][] mat) {
  int row = mat.length, col = mat[0].length;
  int res[][] = new int[row][col];
        int maxDistance = (row - 1) + (col - 1);
        //check between left and upper cell
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(mat[i][j]  != 0){
                    int upperCell = (i > 0) ? res[i - 1][j] : maxDistance;
                    int leftCell = (j > 0) ? res[i][j - 1] : maxDistance;
                    res[i][j] = Math.min(upperCell, leftCell) + 1; 
                 }
              }
            }
        
        //check between right and lower cell
        for(int i = row - 1; i >= 0; i--){
            for(int j = col - 1; j >= 0; j--){
                if(mat[i][j]  != 0){
                    int lowerCell = (i < row - 1) ? res[i + 1][j] : maxDistance;
                    int rightCell = (j < col - 1) ? res[i][j + 1] : maxDistance;
                    res[i][j] = Math.min(res[i][j], Math.min(lowerCell, rightCell) + 1) ; 
                 }
              }
            }
        return res;
    }
}