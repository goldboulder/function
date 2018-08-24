
package Other;

import java.io.Serializable;
import java.util.Arrays;


public class Matrix implements Cloneable, Serializable{
    private double[][] matrix;
    
    public Matrix(){
        matrix = new double[0][0];
    }
    
    //constructs identity matrix of given size
    public Matrix(int size){
        matrix = new double[size][size];
        
    }
    
    public Matrix(int rows, int columns){
        matrix = new double[rows][columns];
    }
    
    public Matrix(double[][] array){
        matrix = array.clone();
    }
    
    public Matrix(Matrix m2){
        matrix = m2.matrix.clone();
    }
    
    public int getRows(){
        return matrix.length;
    }
    
    public int getColumns(){
        return matrix[0].length;
    }
    
    public double getValue(int row, int column){
        return matrix[row][column];
    }
    
    public void setValue(int row, int column, double value){
        matrix[row][column] = value;
    }
    
    //add to end
    public void addRow(){
        
    }
    public void addColumn(){
        
    }
    
    //insert right/down
    public void addRow(int newRow){
        
    }
    public void addColumn(int newColumn){
        
    }
    
    //multiplies all entries by c
    public void multiplyConstant(int c){
        
    }
    
    //multiplies all entries in row by c
    public void multiplyRowConstant(int row, int c){
        
    }
    
    //sets all values to 0
    public void reset(){
        
    }
    
    // sum of all entries
    public double sum(){
        double sum = 0;
        for (int i = 0; i < getRows(); i++){
            for (int j = 0; j < getColumns(); j++){
                sum += matrix[i][j];
            }
        }
        return sum;
    }
    
    public int entries(){
        return getRows() * getColumns();
    }
    
    public double sumRow(int row){
        double sum = 0;
        for (int i = 0; i < getColumns(); i++){
            sum += matrix[row][i];
        }
        return sum;
    }
    
    public double sumColumn(int column){
        double sum = 0;
        for (int i = 0; i < getRows(); i++){
            sum += matrix[i][column];
        }
        return sum;
    }
    
    public double determinant(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public Matrix inverse(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public Matrix transverse(){
        Matrix m = new Matrix(getColumns(),getRows());
        for (int i = 0; i < getColumns(); i++){
            for (int j = 0; j < getRows(); j++){
                m.matrix[i][j] = matrix[j][i];
            }
        }
        return m;
    }
    
    public void gaussian(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void gaussJordan(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public double[] eigenValues(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public double[][] eigenVectors(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public boolean isZero(){
        for (int i = 0; i < getRows(); i++){
            for (int j = 0; j < getColumns(); j++){
                if (matrix[i][j] != 0)
                    return false;
            }
        }
        return true;
    }
    
    public boolean isValid(){
        return !(getRows() == 0 || getColumns() == 0);
    }
    
    public boolean isIdentity(){
        if (getRows() != getColumns())
            return false;
        for (int i = 0; i < getRows(); i++){
            for (int j = 0; j < getColumns(); j++){
                if (i == j && matrix[i][j] != 1)
                    return false;
                if (i != j && matrix[i][j] != 0)
                    return false;
            }
        }
        return true;
    }
    
    public boolean isDiagonal(){
        if (getRows() != getColumns())
            return false;
        for (int i = 0; i < getRows(); i++){
            for (int j = 0; j < getColumns(); j++){
                if (i != j && matrix[i][j] != 0)
                    return false;
            }
        }
        return true;
    }
    
    public boolean isLowerTriangular(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public boolean isUpperTriangular(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public Matrix add(Matrix m2){
        if (getRows() == m2.getRows() && getColumns() == m2.getColumns()){
            Matrix c = new Matrix(getRows(),getColumns());
            for (int i = 0; i < getRows(); i++){
                for (int j = 0; j < getColumns(); j++){
                    c.matrix[i][j] = matrix[i][j] + m2.matrix[i][j];
                }
            }
            return c;
        }
        else
            return new Matrix();
    }
    
    public Matrix multiply(Matrix m2){
        if (getColumns() != m2.getRows()){
            return new Matrix();
        }
        
        int sum = 0;
        Matrix c = new Matrix(getRows(),m2.getColumns());
        for (int i = 0; i < c.getRows(); i++){
            for (int j = 0; j < c.getColumns(); j++){
                sum = 0;
                for (int k = 0; k < getColumns(); k++){
                    sum += matrix[i][k] * m2.matrix[k][j];
                }
                c.matrix[i][j] = sum;
            }
        }
        
        return c;
        
    }
    
    public Matrix pow(int exp){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public Matrix Identity(int size){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
    
    
    
    
    
    
    @Override
    public String toString(){
        if (getRows() == 0 || getColumns() == 0)
            return "[]";
        
        String s = "";
        for (int i = 0; i < matrix.length; i++){
            s += Arrays.toString(matrix[i]);
            if (i < matrix.length - 1)
                s += "\n";
        }
        
        return s;
    }
    
    
}
