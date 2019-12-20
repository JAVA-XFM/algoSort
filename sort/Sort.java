package sort;

public class Sort {
	
	public static void main(String[] args) {
		int[] array = {2,5,3,19,3,56,49,32,2,5,3,19,3,56,49,32,2,5,3,19,3,56,49,32,2,5,3,19,3,56,49,32,2,5,3,19,3,56,49,32,
				2,5,3,19,3,56,49,32,2,5,3,19,3,56,49,32,2,5,3,19,3,56,49,32,2,5,3,19,3,56,49,32,2,5,3,19,3,56,49,32,2,5,3,19,3,56,49,32,
				2,5,3,19,3,56,49,32,2,5,3,19,3,56,49,32,2,5,3,19,3,56,49,32,2,5,3,19,3,56,49,32,2,5,3,19,3,56,49,32,2,5,3,19,3,56,49,32,
				2,5,3,19,3,56,49,32,2,5,3,19,3,56,49,32,2,5,3,19,3,56,49,32,2,5,3,19,3,56,49,32,2,5,3,19,3,56,49,32,2,5,3,19,3,56,49,32,
				2,5,3,19,3,56,49,32,2,5,3,19,3,56,49,32,2,5,3,19,3,56,49,32,2,5,3,19,3,56,49,32,2,5,3,19,3,56,49,32,2,5,3,19,3,56,49,32,};
//		long currentTimeMillis1 = System.currentTimeMillis();
//		for (int i = 0; i < 1000000; i++) {
//			InsertSort(array);
//		}
//		long currentTimeMillis2 = System.currentTimeMillis();
//		System.out.println(currentTimeMillis2-currentTimeMillis1);
//		long currentTimeMillis3 = System.currentTimeMillis();
//		for (int i = 0; i < 1000000; i++) {
//			halfInsertSort(array);
//		}
//		long currentTimeMillis4 = System.currentTimeMillis();
//		System.out.println(currentTimeMillis4-currentTimeMillis3);
		
//		int[] delets = {8,6,4,2,1};
//		shellSort(array, delets);
		
//		bubbleSort(array);
		
//		FastSort(array, 0, array.length-1);
//		//遍历输出数组元素
//	    for(int value : array){
//	        System.out.print(value + ",");
//	    }
		
		MergeSort(array, 0, array.length-1);
		for(int value : array){
	        System.out.print(value + ",");
	    }
	}

	/**
	 * 	算法稳定性：
	 * 	如果Ai = Aj，排序前Ai在Aj之前，排序后Ai还在Aj之前，则称这种排序算法是稳定的，否则说明该算法不稳定。
	 * 	直接插入排序：（当前实现算法稳定）
	 *	 整个程序的逻辑是从数组的第二个元素开始，每个元素都以其前面所有的元素为基本，找到合适的位置进行插入。
	 *	对于这种按照从小到大的排序原则，程序使用一个临时变量temp保存当前需要插入的元素的值，
	 *	从前面的子序列的最后一个元素开始，循环的与temp进行比较，一旦发现有大于temp的元素，
	 *	让它顺序的往后移动一个位置，直到找到一个元素小于temp，那么就找到合适的插入位置了。
	 *	因为我们使用的判断条件是，key>array[j]。所以来说，插入排序算法也是稳定的算法。
	 *	对于值相同的元素并不会更改他们原来的位置顺序。至于该算法的效率，最好的情况是所有元素都已有序，
	 *	比较次数为n-1，最坏的情况是所有元素都是逆序的，比较次数为（n+2）（n-1）/2，所以该算法的时间复杂度为O（n*n）
	 * @param array
	 */
	public static void InsertSort(int[] array) {
		int j = 0,key;
		//因为第一个元素肯定时有序的
		for (int i = 1; i < array.length; i++) {
			key = array[i];
			j = i-1;
			while(j>=0 && key<array[j]) {
				//需要移动位置，将较大的值array[j]向后移动位置
				array[j+1] = array[j];
				j--;
			}
			array[j+1] = key;
		}
//		for (int value : array) {
//			System.out.print(value + ",");
//		}
//		System.out.println();
	}
	
	/**
	 * 	二分折半插入排序：
	 * 	当前实现算法不稳定(经过我改造之后稳定了)
	 * @param array
	 */
	public static void halfInsertSort(int[] array){
	    for(int k=1;k<array.length;k++){
	        int key = array[k];
	        //找到合适的位置
	        int low,high,mid;
	        low = 0;high = k-1;
	        while(low <= high){
	            mid = (low+high)/2;
	            if(key == array[mid]){
	            	//这段代码是完善该算法稳定性的
	            	mid = mid + 1;
            		while(mid <= high && key == array[mid]) {
	            		mid = mid + 1;
	            	}
            		
	            	low = mid;
	            	break;
	            }else if(key > array[mid]){
	                low = mid+1;
	            }else{
	                high = mid-1;
	            }
	        }
	        //low的索引位置就是即将插入的位置
	        //移动low索引位置后面的所有元素
	        for(int x=k-1;x>=low;x--){
	            array[x+1] = array[x];
	        }
	        array[low] = key;
	    }
	    //遍历输出有序队列内容
//	    for(int key:array){
//	        System.out.print(key + ",");
//	    }
//	    System.out.println();
	}
	
	public static void ShellSort(){
	    int[] array = {46,55,13,42,94,17,5,70};
	    int[] delets = {4,2,1};
	    for (int i=0;i<delets.length;i++){
	        oneShellSort(array,delets[i]);
	    }
	    //遍历输出数组内容
	    for(int value : array){
	        System.out.print(value + ",");
	    }
	}
	/**
	 * 	希尔排序：
	 *	 直接插入排序在整个待排序序列基本有序的情况下，效率最佳，但我们往往不能保证每次待排序的序列都是基本有序的。
	 *	希尔排序就是基于这样的情形，它将待排序序列拆分成多个子序列，保证每个子序列的组成元素相对较少，
	 *	然后通过对子序列使用直接排序。对于本就容量不大的子序列来说，直接排序的效率是相当优秀的。
	 *
	 *	很显然，当距离增量变小的时候，序列的个数也会变少，但是这些子序列的内部都基本有序，
	 *	当对他们进行直接插入排序的时候会使得效率变高。一旦距离增量减少为1，那么子序列的个数也将减少为1，
	 *	也就是我们的原序列，而此时的序列内部基本有序，最后执行一次直接插入排序完成整个排序操作。
	 */
	public static void shellSort(int[] array, int[] delets){
	    for (int i=0;i<delets.length;i++){
	        oneShellSort(array,delets[i]);
	    }
	    //遍历输出数组内容
	    for(int value : array){
	        System.out.print(value + ",");
	    }
	}
	
	/**
	 * 	根据距离增量的值划分子序列并对子序列内部进行直接插入排序
	 */
	public static void oneShellSort(int[] array,int delet){
	    int temp;
	    for(int i=delet;i<array.length;i++){
	        //从第二个子序列开始交替进行直接的插入排序
	        //将当前元素插入到前面的有序队列中
	        if(array[i-delet] > array[i]){
	            temp = array[i];
	            int j=i-delet;
	            while(j>=0 && array[j] > temp){
	                array[j+delet] = array[j];
	                j -= delet;
	            }
	            array[j + delet] = temp;
	        }
	    }
	}
	
	/**
	 * 	冒泡排序：
	 * 	冒泡排序通过两两比较，每次将最大或者最小的元素移动到整个序列的一端。
	 * @param array
	 */
	public static void bubbleSort(int[] array){
	    int temp = 0;
	    for(int i=0;i<array.length-1;i++){
	        for(int j =0;j<array.length-1-i;j++){
	            if(array[j]>array[j+1]){
	                //交换两个数组元素的值
	                temp = array[j];
	                array[j] = array[j+1];
	                array[j+1] = temp;
	            }
	        }
	    }
	    //遍历输出数组元素
	    for(int value : array){
	        System.out.print(value + ",");
	    }
	}
	
	/**
	 *	 快速排序的递归定义
	 * 
	 */
	public static void FastSort(int[] array,int low,int high){
	    if(low<high){
	        int pos = OneFastSort(array,low,high);
	        FastSort(array,low,pos-1);
	        FastSort(array,pos+1,high);
	    }
	}
	/**
	 * 	一次快排
	 * @param array
	 * @param low
	 * @param high
	 * @return
	 */
	public static int OneFastSort(int[] array,int low,int high){
	    //实现一次快速排序
	    int key = array[low];
	    int flag = 0;
	    while (low != high) {
	        if (flag == 0) {
	            //flag为0表示指针从high的一端开始移动
	            if (array[high] < key) {
	                array[low] = array[high];
	                low++;
	                flag = 1;
	            } else {
	                high--;
	            }
	        } else {
	            //指针从low的一端开始移动
	            if (array[low] > key) {
	                array[high] = array[low];
	                high--;
	                flag = 0;
	            } else {
	                low++;
	            }
	        }
	    }
	    array[low] = key;
	    return low;
	}
	/**
	 * 	选择排序：
	 * 	选择类排序的基本思想是，每一趟会在n个元素中比较n-1次，选择出最大或者最小的一个元素放在整个序列的端点处
	 * @param array
	 */
	public static void ChooseSort(int[] array){
	    for (int i=0;i<array.length;i++){
	        for (int j=i+1;j<array.length;j++){
	            if(array[i]>array[j]){
	                //发现比自己小的元素，则交换位置
	                int temp = array[j];
	                array[j]=array[i];
	                array[i] = temp;
	            }
	        }
	    }
	    //输出排序后的数组内容
	    for (int key  : array){
	        System.out.print(key+",");
	    }
	}
	
	/**
	 * 	归并排序的递归调用
	 */
	public static void MergeSort(int[] array,int low,int high){
	    if(low == high){
	        //说明子数组长度为1，无需分解，直接返回即可
	    }else{
	        int p = (low+high)/2;
	        MergeSort(array,low,p);
	        MergeSort(array,p+1,high);
	        //完成相邻两个子集合的归并
	        MergeTwoData(array,low,high);
	    }
	}
	
	/**
	 * 	用于排序两个子序列的归并排序算法实现
	 * 
	 * 	 这里的归并类排序算法指的就是归并排序。归并排序的核心思想是，
	 * 	对于一个初始的序列不断递归，直到子序列中的元素足够少时，对他们进行直接排序。
	 * 	然后递归返回继续对两个分别有序的序列进行直接排序，最终递归结束的时候，整个序列必然是有序的。
	 */
	public static void MergeTwoData(int[] array,int low,int high){ 
	    int[] arrCopy = new int[high-low+1];
	    int i,j;
	    i = low;j= (low+high)/2+1;
	    for (int key=0;key<=high-low;key++){
	        //如果左边子数组长度小于右边数组长度，当左数组全部入库之后，右侧数组不用做比较直接入库
	        if(i==(low+high)/2+1){
	            arrCopy[key] = array[j];
	            j++;
	        }
	        //如果右侧数组长度小于左侧数组长度，当右侧数组全部入库之后，左侧数组不用做比较直接入库
	        else if(j==high+1){
	            arrCopy[key]=array[i];
	            i++;
	        }else if(array[i]<array[j]){
	            arrCopy[key]=array[i];
	            i++;
	        }else{
	            arrCopy[key] = array[j];
	            j++;
	        }
	    }
	    j = 0;
	    //按顺序写回原数组
	    for(int x=low;x<=high;x++) {
	        array[x] = arrCopy[j];
	        j++;
	    }
	}
}
