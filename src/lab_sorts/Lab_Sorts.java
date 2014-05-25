
package lab_sorts;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Lab_Sorts {
    
    public static void main(String[] args) {
        Lab_Sorts lab=new Lab_Sorts();
        Scanner in =new Scanner(System.in);
        Random r=new Random();
        long timeA=0;
        double total=0.0;
        String resp="";
        do{
            int n=lab.submenu();
            int cant=lab.tamañoArray();
            if (cant==10 || cant==100 || cant==1000 || cant==10000 || cant==100000 || cant==1000000){//valida si el tamaño del array es correcto
                for(int veces=0;veces<10;veces++){
                    int Sort[]=new int[cant];
                    System.out.println("\nArreglo sin ordenar:");
                    for (int i = 0; i < cant; i++) {
                        Sort[i]=r.nextInt(100);
                        System.out.print(" "+Sort[i]+" ");
                    }System.out.println("");
                    switch (n){
                        case 1:
                            timeA=System.nanoTime();
                            lab.bubbleSort(Sort);
                        case 2:
                            timeA=System.nanoTime();
                            lab.insertionSort(Sort);
                        case 3:
                            timeA=System.nanoTime();
                            lab.selectionSort(Sort);
                        case 4:
                            timeA=System.nanoTime();
                            lab.ordenacionMonticulos(Sort);
                        case 5:
                            timeA=System.nanoTime();
                            lab.ordenacionMonticulos(Sort);
                        case 6:
                            timeA=System.nanoTime();
                            lab.mergeSort(Sort);
                        case 7:
                            timeA=System.nanoTime();
                            lab.radixSort(Sort);
                    }//end switch
                    total=total + (Double.valueOf(System.nanoTime()-timeA)*0.000000001);
                    System.out.println("Arreglo Ordenado: ");
                    for (int i = 0; i < cant; i++) {
                        System.out.print(" "+Sort[i]+" ");
                    }System.out.println("\n");
                }//end for
                total=total/10;
                System.out.print("\n\nEl tiempo es: "+total+" segundos");
                System.out.println("\n\nDesea continuar(si/no):\n");
                resp=in.nextLine();
            }else{
                System.out.println("\nTamaño del Arreglo Incorrecto\n");
                resp="si";
            }
        }while (resp.equals("si") || resp.equals("Si"));//end while
    }//end main

    private int valida(int cant) {
        int c=cant;
        if(c==10){
            c=1;
        }else if(c==100){
            c=1;
        }else if(c==1000){
            c=1;
        }else if(c==10000){
            
        }
        return c;
    }
    
    public int submenu(){
        Scanner in =new Scanner(System.in);
        int num=0;
        System.out.println("Laboratorio de Sorts");
        System.out.println("\n 1. Bubble Sort \n 2. Insertion Sort \n 3. Selection Sort "
                + "\n 4. Heap Sort \n 5. Quick Sort \n 6. Merge Sort \n 7. Radix Sort \nElija el Sort:");
        num=in.nextInt();
        return num;
    }//end submenu
    
    public int tamañoArray(){
        Scanner in =new Scanner(System.in);
        int c=0;
        System.out.println("\nTamaño del Arreglo (10, 100, 1000, 10000, 100000, 1000000)\n"
                + "Escriba una cantidad: ");
        c=in.nextInt();
        return c;
    }//end cantidad
    
//Bubble Sort        
    public void bubbleSort(int[] array){
        int n = array.length;
        int temp = 0;
               
        for(int i=0; i < n; i++){
            for(int j=1; j < (n-i); j++){
                     
                if(array[j-1] > array[j]){
                    temp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = temp;
                }               
            }
        }
    }//end bubbleSort
    
//Insertion Sort    
    public void insertionSort(int[] array) {
        int i, j, newValue;
        for (i = 1; i < array.length; i++) {
            newValue = array[i];
            j = i;
            while (j > 0 && array[j - 1] > newValue) {
                  array[j] = array[j - 1];
                  j--;
            }
            array[j] = newValue;
        }
    }//end insertionSort
    
//Selection Sort    
    public void selectionSort(int[] array){
        for (int i=0; i< array.length-1; i++) {
            for (int j=i+1; j< array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }//end selectionSort

//Heap Sort    
    public void ordenacionMonticulos(int[] v) {
        final int N = v.length;
        for(int nodo = N/2; nodo>=0; nodo--) hacerMonticulo(v, nodo, N-1);
        for(int nodo = N-1; nodo>=0; nodo--) {
            int tmp = v[0];
            v[0]    = v[nodo];
            v[nodo] = tmp;
            hacerMonticulo(v, 0, nodo-1);
        }
    }//end ordenacionMonticulos
 
    public void hacerMonticulo(int[] v, int nodo, int fin) {
        int izq = 2*nodo+1;
        int der = izq+1;
        int may;
        if(izq>fin) return;
        if(der>fin) may=izq;
        else may= v[izq]>v[der]?izq:der;
        if(v[nodo] < v[may]) {
            int tmp = v[nodo];
            v[nodo] = v[may];
            v[may]  = tmp;
            hacerMonticulo(v, may, fin);
        }
    }//end hacerMonticulo
    
//Quick Sort    
    public static void ordenacionRapida(int[] v) {
        final int N = v.length;
        quickSort(v,0,N-1);
    }//end ordenacionRapida
 
    public static void quickSort(int[] v, int inicio, int fin) {
        if(inicio>=fin) return ;
        int pivote = v[inicio];
        int izq    = inicio+1;
        int der    = fin;
        while(izq<=der) {
            while(izq<=fin   && v[izq]< pivote) izq++;
            while(der>inicio && v[der]>=pivote) der--;
            if(izq<der) {
                int tmp = v[izq];
                v[izq]  = v[der];
                v[der]  = tmp;
            }
        }
        if(der>inicio) {
            int tmp  = v[inicio];
            v[inicio]= v[der];
            v[der]   = tmp;
        }
        quickSort(v,inicio, der-1);
        quickSort(v, der+1, fin);
    }//end quickSort
    
//Merge Sort    
    public void mergeSort(int[] A) {
        if (A.length > 1) {
            int q = A.length/2;
            int[] leftArray = Arrays.copyOfRange(A, 0, q);
            int[] rightArray = Arrays.copyOfRange(A,q+1,A.length);
            mergeSort(leftArray);
            mergeSort(rightArray);
            A = merge(leftArray,rightArray);
        }
    }//end mergeSort

    public int[] merge(int[] l, int[] r) {
        int totElem = l.length + r.length;
        int[] a = new int[totElem];
        int i,li,ri;
        i = li = ri = 0;
        while ( i < totElem) {
            if ((li < l.length) && (ri<r.length)) {
                if (l[li] < r[ri]) {
                    a[i] = l[li];
                    i++;
                    li++;
                }else {
                    a[i] = r[ri];
                    i++;
                    ri++;
                }
            }else {
                if (li >= l.length) {
                    while (ri < r.length) {
                        a[i] = r[ri];
                        i++;
                        ri++;
                    }
                }
                if (ri >= r.length) {
                    while (li < l.length) {
                        a[i] = l[li];
                        li++;
                        i++;
                    }
                }
            }
        }
        return a;

    }//end marge
    
//Radix Sort
    public void radixSort(int[] arr){
        if(arr.length == 0)
            return;
        int[][] np = new int[arr.length][2];
        int[] q = new int[0x100];
        int i,j,k,l,f = 0;
        for(k=0;k<4;k++){
            for(i=0;i<(np.length-1);i++)
                np[i][1] = i+1;
            np[i][1] = -1;
            for(i=0;i<q.length;i++)
                q[i] = -1;
            for(f=i=0;i<arr.length;i++){
                j = ((0xFF<<(k<<3))&arr[i])>>(k<<3);
                if(q[j] == -1)
                    l = q[j] = f;
                else{
                    l = q[j];
                    while(np[l][1] != -1)
                        l = np[l][1];
                    np[l][1] = f;
                    l = np[l][1];
                }
                f = np[f][1];
                np[l][0] = arr[i];
                np[l][1] = -1;
            }
            for(l=q[i=j=0];i<0x100;i++)
                for(l=q[i];l!=-1;l=np[l][1])
                        arr[j++] = np[l][0];
        }
    }//end radixSort
    
}//end Lab_Sorts
