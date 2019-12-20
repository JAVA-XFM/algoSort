package visualSorting;

import java.awt.EventQueue;


public class AlgoVisualizer {

    private static int DELAY = 100;

    private SelectionSortData data;
    private AlgoFrame frame;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N){
        //准备数据
        data = new SelectionSortData(N, sceneHeight);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Selection Sort Visualization", sceneWidth, sceneHeight);
            new Thread(() -> {
            	//runSelect();
            	//runBubble();
            	//runFastSort(data, 0, data.N()-1);
            	//runInsertSort();
            	//shellSort();
            }).start();
        });
    }

    /**
     * 	选择排序
     */
    private void runSelect(){

        setData(0, -1, -1);

        for( int i = 0 ; i < data.N() ; i ++ ){
            // 寻找[i, n)区间里的最小值的索引
            int minIndex = i;
            setData(i, -1, minIndex);

            for( int j = i + 1 ; j < data.N() ; j ++ ){
                setData(i, j, minIndex);

                if( data.get(j) < data.get(minIndex) ){
                	//注释的是另外一种方式
//                    minIndex = j;
//                    data.swap(i , minIndex);
                	data.swap(j , minIndex);
                    setData(i, j, minIndex);
                }
            }
            //根据数组索引交换数据
//            data.swap(i , minIndex);
            setData(i+1, -1, -1);
        }

        setData(data.N(),-1,-1);
    }
    
    /**
     * 	冒泡排序
     */
    private void runBubble() {
    	setData(0,-1,-1);
    	for (int i = 0; i < data.N(); i++) {
    		
			for (int j = data.N()-1-i; j > 0; j--) {
				setData(i, j, j);
				
				if(data.get(j) < data.get(j-1)) {
					data.swap(j-1, j);
					setData(i, j, j);
				}
			}
			setData(i+1, -1, -1);
		}
    	
    	setData(data.N(),-1,-1);
    	
    }
    
    /**
     * 	快速排序
     * @param array
     * @param low
     * @param high
     */
    private void runFastSort(SelectionSortData array, int low, int high) {
    	if(low<high){
    		
  	        int pos = oneFastSort(array,low,high);
  	        runFastSort(array,low,pos-1);
  	        runFastSort(array,pos+1,high);
  	        
  	    }
    }
    
    private int oneFastSort(SelectionSortData array, int low, int high) {
    	//实现一次快速排序
	    int key = array.getNumber()[low];
	    int flag = 0;
	    while (low != high) {
	        if (flag == 0) {
	            //flag为0表示指针从high的一端开始移动
	            if (array.getNumber()[high] < key) {
	                array.getNumber()[low] = array.getNumber()[high];
	                low++;
	                flag = 1;
	            } else {
	                high--;
	            }
	        } else {
	            //指针从low的一端开始移动
	            if (array.getNumber()[low] > key) {
	                array.getNumber()[high] = array.getNumber()[low];
	                high--;
	                flag = 0;
	            } else {
	                low++;
	            }
	        }
	        setData(1, 1, 1);
	    }
	    array.getNumber()[low] = key;
	    return low;
    }
    
    /**
     * 	插入排序
     */
    private void runInsertSort() {
    	//key用来存放最小值
    	int j = 0,key;
    	setData(0,-1,-1);
		for (int i = 0; i < data.N(); i++) {
			//开始默认循环开始元素最小
			key = data.get(i);
			//setData(i,i,i);
			//指向循坏元素前一个元素
			j = i-1;
			//当前循环元素比较前面所有的元素
			
			while(j>=0 && key<data.get(j)) {
				//交换位置
				data.swap(j+1, j);
				setData(i,j,j);
				j--;
			}
			data.setNumberByIndex(j+1, key);
		}
		setData(data.N(),-1,-1);
    }
    
    /**
     * 	希尔排序
     * @param array
     * @param delets
     */
    public void shellSort(){
    	int[] delets = {6,4,2,1};
	    for (int i=0;i<delets.length;i++){
	        oneShellSort(data.getNumber(),delets[i]);
	    }
	    setData(data.N(),-1,-1);
	}
	
	/**
	 * 	根据距离增量的值划分子序列并对子序列内部进行直接插入排序
	 */
	public void oneShellSort(int[] array,int delet){
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
	                setData(i,j,j);
	            }
	            setData(0,-1,-1);
	            array[j + delet] = temp;
	        }
	    }
	}
	
    //根据以排序、当前正在比较的元素索引和当前找到的最小元素的索引绘制图形
    private void setData(int orderedIndex, int currentCompareIndex, int currentMinIndex){
        data.orderedIndex = orderedIndex;
        data.currentCompareIndex = currentCompareIndex;
        data.currentMinIndex = currentMinIndex;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 600;
        int N = 100;

        AlgoVisualizer vis = new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }
}